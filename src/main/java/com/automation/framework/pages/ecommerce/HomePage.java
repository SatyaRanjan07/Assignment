package com.automation.framework.pages.ecommerce;

import com.automation.framework.base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage extends BasePage {
    private static final String BASE_URL = "https://automationexercise.com";
        JavascriptExecutor js = (JavascriptExecutor) driver;

    @FindBy(id = "accordian")
    private WebElement categoriesMenu;

    @FindBy(xpath = "//a[@href='#Men']")
    private WebElement menCategory;

    @FindBy(xpath = "//a[text()='Tshirts ']")
    private WebElement shirtSubCategory;

    @FindBy(xpath = "//h2[normalize-space()='Men - Tshirts Products']")
    private WebElement tshirtCategoryHeader;

    @FindBy(className = "features_items")
    private WebElement productsContainer;

    @FindBy(xpath = "(//a[contains(text(),'View Product')])[1]")
    private WebElement firstProductViewButton;

    @FindBy(xpath = "//a[contains(text(),'Signup / Login')]")
    private WebElement loginLink;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        navigateTo(BASE_URL);
    }
    public boolean isCategoriesDisplayed() {
        return categoriesMenu.isDisplayed();
    }

    public  void clickOnMenCategory() {
         click(menCategory);
    }

    public void clickOnShirtSubCategory() {
        js.executeScript("arguments[0].scrollIntoView(true);", shirtSubCategory);
       js.executeScript("arguments[0].click();", shirtSubCategory);
    }

    public void  TshirtCategoryHeaderTextValidate() {
         String act = tshirtCategoryHeader.getText().toLowerCase().trim();
         String exp = "Men - Tshirts Products".toLowerCase().trim();
        Assert.assertEquals(act,exp,"Should navigate to T-shirt category page");
    }
    public boolean isProductsDisplayed() {
        return productsContainer.isDisplayed();
    }

    public void viewFirstProductDetails() {
        js.executeScript("arguments[0].scrollIntoView(true);", firstProductViewButton);
        js.executeScript("arguments[0].click();", firstProductViewButton);
    }

    public void clickLoginLink() {
        js.executeScript("arguments[0].scrollIntoView(true);", loginLink);
        js.executeScript("arguments[0].click();", loginLink);
    }


}
