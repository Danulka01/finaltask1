package main.login.helper.steps;

import lombok.SneakyThrows;
import main.login.helper.pages.LoginPage;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();

    public LoginSuccessSteps doLogin(String login, String password) {
        inputLoginData(login, password);
        return new LoginSuccessSteps();
    }

    @SneakyThrows
    public void inputLoginData(String login, String password) {
        loginPage.fillLoginField(login);
        loginPage.fillPasswordField(password);
        Thread.sleep(2000);
        loginPage.clickButton();
    }

    public LoginFailedSteps doIncorrectLogin(String login, String password) {
        inputLoginData(login, password);
        return new LoginFailedSteps();
    }
}
