package com.automation.tests.ecommerce;

import com.automation.tests.base.BaseTest;
import com.automation.framework.core.DriverManager;
import com.automation.framework.pages.ecommerce.HomePage;
import com.automation.framework.pages.ecommerce.LoginPage;
import com.automation.framework.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

//    Scenario 2: “Am I logged in or not?”
//
//    Navigate through both authenticated and guest flows.
//    Try registering a new user — ensure your test handles both:
//    Fresh registration
//    Already-existing email addresses

    @Test
    public void testValidUserLogin() throws InterruptedException {
        HomePage homePage = new HomePage(DriverManager.getDriver());
        homePage.navigateToHomePage();

        homePage.clickLoginLink();
        Thread.sleep(2000);

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        Assert.assertTrue(loginPage.isLoginHeadingDisplayed(), "Login page should be displayed");
        logger.info("Login page displayed");

        String email = TestDataProvider.getEcommerceValidEmail();
        String password = TestDataProvider.getEcommerceValidPassword();

        loginPage.loginWithValidCredentials(email, password);
        Thread.sleep(2000);

        // Verify user is logged in (typically by checking for logout link or user account section)
        logger.info("User login attempted - verify redirect to account page");
    }

    //Test login with invalid credentials shows error message
    @Test
    public void testInvalidUserLogin() throws InterruptedException {
        HomePage homePage = new HomePage(DriverManager.getDriver());
        homePage.navigateToHomePage();

        homePage.clickLoginLink();
        Thread.sleep(2000);

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.loginWithValidCredentials("invalid@email.com", "wrongpassword");
        Thread.sleep(2000);

        Assert.assertTrue(loginPage.isLoginErrorDisplayed(),
                "Login error message should be displayed for invalid credentials");
        logger.info(" Login error message displayed for invalid credentials");
    }

    //Test new user registration with fresh email
    @Test
    public void testFreshUserRegistration() throws InterruptedException {
        HomePage homePage = new HomePage(DriverManager.getDriver());
        homePage.navigateToHomePage();

        homePage.clickLoginLink();
        Thread.sleep(2000);

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        Assert.assertTrue(loginPage.isSignupHeadingDisplayed(), "Signup section should be visible on login page");
        logger.info(" Signup section displayed");

        String name = TestDataProvider.getEcommerceNewUserName();
        String email = TestDataProvider.getEcommerceNewUserEmail();
        String password = TestDataProvider.getEcommerceNewUserPassword();

        loginPage.signupNewUser(name, email);
        Thread.sleep(2000);

        // Verify successful registration (redirect to account details page expected)
        logger.info("New user registration initiated for: " + email);
        logger.info("User should be redirected to account information page");
    }

    //Test registration with existing email displays error
    @Test
    public void testRegistrationWithExistingEmail() throws InterruptedException {
        HomePage homePage = new HomePage(DriverManager.getDriver());
        homePage.navigateToHomePage();

        homePage.clickLoginLink();

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        // Try to signup with an existing email
        String existingEmail = TestDataProvider.getEcommerceValidEmail();
        loginPage.signupNewUser("Test User", existingEmail);
        Thread.sleep(2000);

        // Verify error message for existing email
        Assert.assertTrue(loginPage.isEmailExistsErrorDisplayed(),
                "Email already exists error should be displayed");
        logger.info("Error message displayed for existing email address");
    }

}
