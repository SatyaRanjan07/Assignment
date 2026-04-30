package com.automation.framework.pages.ecommerce;

import com.automation.framework.base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage {
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @FindBy(id = "quantity")
    private WebElement quantityInput;

    @FindBy(xpath = "//button[contains(@class,'btn btn-default cart')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//u[normalize-space()='View Cart']")
    private WebElement viewCartButton;

    @FindBy(xpath = "//p[normalize-space()='Your product has been added to cart.']")
    private WebElement addedToCartMessage;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void setQuantity(String quantity) {
        quantityInput.clear();
        sendKeys(quantityInput, quantity);
    }

    public void addToCart() {

        js.executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        js.executeScript("arguments[0].click();", addToCartButton);
    }

    public void clickViewCart() {
        js.executeScript("arguments[0].scrollIntoView(true);", viewCartButton);
        js.executeScript("arguments[0].click();", viewCartButton);
    }


    public boolean isAddedToCartMessageDisplayed() {
        return addedToCartMessage.isDisplayed();

    }
}
