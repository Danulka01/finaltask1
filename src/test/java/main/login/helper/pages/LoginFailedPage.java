package main.login.helper.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.fail;

public class LoginFailedPage extends BasePage {

    @FindBy(xpath = "//*[@id=\"login_button_container\"]/div/form/div[3]")
    WebElement Error;


    public void assertThatUserNotFound() {

        assertThat(Error.isDisplayed()).isTrue();
    }
}

