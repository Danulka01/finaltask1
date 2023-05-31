package main.login.helper.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSuccessPage extends BasePage {

    @FindBy(xpath = "//*[@id=\"header_container\"]/div[1]/div[2]/div")
    WebElement divSwagLabs;

    public void assertThatUserDivPresent() {

        assertThat(divSwagLabs.isDisplayed()).isTrue();
    }
}
