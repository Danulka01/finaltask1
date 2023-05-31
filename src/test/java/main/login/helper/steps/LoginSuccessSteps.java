package main.login.helper.steps;

import main.login.helper.pages.LoginSuccessPage;

public class LoginSuccessSteps {
    LoginSuccessPage page = new LoginSuccessPage();

    public void verifyThatOk() {
        page.assertThatUserDivPresent();
    }
}
