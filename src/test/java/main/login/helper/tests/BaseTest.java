package main.login.helper.tests;

import lombok.Getter;
import lombok.SneakyThrows;
import main.login.helper.steps.LoginSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BaseTest {
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

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

            steps = new LoginSteps();
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void visit(String path) {
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @DataProvider(name = "loginDataCorrect")
    public Object[][] loginSuccessData() {
        return new Object[][] {
                {"standard_user", "secret_sauce"},

        };
    }

    @DataProvider(name = "loginDataFailed")
    public Object[][] loginFailedData() {
        return new Object[][] {
                {"",""},
                {"Danulka",""},
                {"123","Danulka"},
                {"maria","111"}
        };
    }



}
