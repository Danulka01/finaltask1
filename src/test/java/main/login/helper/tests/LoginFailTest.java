package main.login.helper.tests;

import main.login.helper.steps.LoginFailedSteps;
import org.testng.annotations.Test;

public class LoginFailTest extends BaseTest {

    @Test(dataProvider = "loginDataFailed")
    public void testLoginFailed(String login, String password) {
        visit("");
    }

    @Test(dependsOnMethods = {"testLoginFailed"}, dataProvider = "loginDataFailed")
    public void testLoginFailedAndBackLinkPresent(String login, String password) {
        visit("");
        LoginFailedSteps steps1 = steps.doIncorrectLogin(login, password);

    }
}
