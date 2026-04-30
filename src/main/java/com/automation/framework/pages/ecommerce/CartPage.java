package com.automation.framework.pages.ecommerce;

import com.automation.framework.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

import static com.automation.framework.core.DriverManager.logger;

public class CartPage extends BasePage {

    @FindBy(xpath = "//table[@id='cart_info_table']//tbody//tr")
    private List<WebElement> cartItems;

    @FindBy(xpath = "//b[contains(text(), 'Cart is empty')]")
    private WebElement emptyCartMessage;

    @FindBy(xpath = "//td[contains(@class,'cart_quantity')]//button")
    private WebElement quantity;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public boolean isCartEmpty() {
            return emptyCartMessage.isDisplayed();
    }

    public  void validateCartQuantity(String expQuantity){
       String actQuantity = quantity.getText();
        if(actQuantity.equalsIgnoreCase(expQuantity)){
            logger.info("Cart quantity is correct: " + actQuantity);
        } else {
            logger.error("Cart quantity mismatch. Expected: " + expQuantity + ", Actual: " + actQuantity);
        }
    }
}
