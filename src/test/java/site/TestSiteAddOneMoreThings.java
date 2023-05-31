package site;





import lombok.Getter;
        import lombok.SneakyThrows;
        import main.login.helper.steps.LoginSteps;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.edge.EdgeDriver;
        import org.testng.annotations.AfterClass;
        import org.testng.annotations.BeforeClass;
        import org.testng.annotations.Test;

        import java.io.BufferedReader;
        import java.io.File;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.time.Duration;
        import java.util.Properties;

        import static org.assertj.core.api.Assertions.assertThat;

public class TestSiteAddOneMoreThings {


    @Getter
    static WebDriver driver = null;

    Properties properties;

    LoginSteps steps;

    @BeforeClass
    @SneakyThrows
    public void beforeClass() {
        properties = new Properties();
        try (BufferedReader reader = Files.newBufferedReader(Path.of("config.properties"))) {
            properties.load(reader);
            String driverName = properties.getProperty("driver");
            File file = new File(properties.getProperty("path"));
            System.setProperty(driverName, file.getAbsolutePath());
            if (driverName.contains("chrome")) {
                driver = new ChromeDriver();
            } else if (driverName.contains("edge")) {
                driver = new EdgeDriver();
            }
            assertThat(driver).isNotNull();

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));

            steps = new LoginSteps();
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test(priority = 1)
    @SneakyThrows
    public void doLogin() {


        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/inventory.html");

        WebElement login = driver.findElement(By.id("user-name"));
        login.sendKeys("standard_user");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.id("login-button")).click();

    }

    @Test(priority = 2)
    @SneakyThrows
    public void addOneMoreThings(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

        WebElement element = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
        element.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();

        driver.findElement(By.id("continue-shopping")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        driver.findElement(By.id("checkout")).click();
    }

    @Test(priority = 3)
    @SneakyThrows
    public void testCheckout(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

        WebElement FirstName = driver.findElement(By.id("first-name"));

        FirstName.sendKeys("Daniil");

        WebElement LastName = driver.findElement(By.id("last-name"));

        LastName.sendKeys("Kachurka");

        WebElement Code = driver.findElement(By.id("postal-code"));

        Code.sendKeys("55200");

        driver.findElement(By.id("continue")).click();

        WebElement finish = driver.findElement(By.id("finish"));
        finish.click();
        WebElement galochka = driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/img"));
        assertThat(galochka.isDisplayed()).isTrue();

    }

}