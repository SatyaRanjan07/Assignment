package com.automation.framework.pages.ecommerce;

import com.automation.framework.base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    private static final String LOGIN_URL = "https://automationexercise.com/login";
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @FindBy(xpath = "//input[@data-qa='login-email']")
    private WebElement loginEmail;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    private WebElement loginPassword;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//h2[contains(text(),'Login to your account')]")
    private WebElement loginHeading;

    @FindBy(xpath = "//p[contains(text(),'Your email or password is incorrect!')]")
    private WebElement loginErrorMessage;

    @FindBy(xpath = "//h2[contains(text(),'New User Signup!')]")
    private WebElement signupHeading;

    @FindBy(xpath = "//input[@data-qa='signup-name']")
    private WebElement signupName;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement signupEmail;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    private WebElement signupButton;

    @FindBy(xpath = "//p[contains(text(),'Email Address already exist!')]")
    private WebElement emailExistsErrorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToLoginPage() {
       navigateTo(LOGIN_URL);
    }

    public void loginWithValidCredentials(String email, String password) {
        sendKeys(loginEmail, email);
        sendKeys(loginPassword, password);
        js.executeScript("arguments[0].scrollIntoView(true);", loginButton);
        js.executeScript("arguments[0].click();", loginButton);
    }

    public boolean isLoginHeadingDisplayed() {
            return loginHeading.isDisplayed();
    }

    public boolean isLoginErrorDisplayed() {
            return loginErrorMessage.isDisplayed();
    }
    public boolean isSignupHeadingDisplayed() {
            return signupHeading.isDisplayed();

    }

    public void signupNewUser(String name, String email) {
        sendKeys(signupName, name);
       sendKeys(signupEmail, email);
       js.executeScript("arguments[0].scrollIntoView(true);", signupButton);
       js.executeScript("arguments[0].click();", signupButton);
    }

    public boolean isEmailExistsErrorDisplayed() {
            return emailExistsErrorMessage.isDisplayed();

    }

    public void enterLoginEmail(String email) {
        sendKeys(loginEmail, email);
    }

    public void enterLoginPassword(String password) {
        sendKeys(loginPassword, password);
    }

    public void clickLoginButton() {
        js.executeScript("arguments[0].scrollIntoView(true);", loginButton);
        js.executeScript("arguments[0].click();", loginButton);
    }

    public void enterSignupName(String name) {
       sendKeys(signupName, name);
    }

    public void enterSignupEmail(String email) {
        sendKeys(signupEmail, email);
    }

    public void clickSignupButton() {
       js.executeScript("arguments[0].scrollIntoView(true);", signupButton);
        js.executeScript("arguments[0].click();", signupButton);
    }
}
