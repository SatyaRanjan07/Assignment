package com.automation.tests.ecommerce;

import com.automation.tests.base.BaseTest;
import com.automation.framework.core.DriverManager;
import com.automation.framework.pages.ecommerce.HomePage;
import com.automation.framework.pages.ecommerce.ProductPage;
import com.automation.framework.pages.ecommerce.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductAddToCartTest extends BaseTest {

//    Scenario 1: “I want to buy something”
//
//    A user wants to browse categories and search for a product that matches their needs.
//    Once they find the product, they wish to:
//    View its details
//    Add it to the cart
//    Review their cart
//    The cart should reflect accurate quantities and totals.

    @Test
    public void testProductFlow() throws InterruptedException {
        HomePage homePage = new HomePage(DriverManager.getDriver());
        homePage.navigateToHomePage();

        //Verify catagories are displayed or not
        Assert.assertTrue(homePage.isCategoriesDisplayed(), "Categories should be displayed on home page");
        logger.info("Categories displayed on home page");

        //Click on men catagory
        homePage.clickOnMenCategory();
        logger.info("Clicked on men category");

        //Click on shirt sub catagory
        homePage.clickOnShirtSubCategory();
        logger.info("Clicked on shirt sub category");

        //Verify that we are on Tshirt category page
        homePage.TshirtCategoryHeaderTextValidate();
        logger.info("Validated T-shirt category page");

        // Verify products are displayed or not
        Assert.assertTrue(homePage.isProductsDisplayed(), "Products should be displayed on home page");
        logger.info("Products displayed on home page");

        //View first product details
        homePage.viewFirstProductDetails();
        logger.info("Viewing first product details");

        // Add product to cart
        ProductPage productPage = new ProductPage(DriverManager.getDriver());
        productPage.setQuantity("2");
        productPage.addToCart();

        Thread.sleep(2000);

        // Verify product added to cart
        Assert.assertTrue(productPage.isAddedToCartMessageDisplayed(),
                "Product should be added to cart successfully");
        logger.info("Product added to cart successfully");

        // Navigate to cart
        productPage.clickViewCart();
//        Thread.sleep(2000);

        // Verify cart contents
        CartPage cartPage = new CartPage(DriverManager.getDriver());
        Assert.assertFalse(cartPage.isCartEmpty(), "Cart should not be empty");
        Assert.assertEquals(cartPage.getCartItemCount(), 1, "Cart should contain 1 product");
        cartPage.validateCartQuantity("2");
        logger.info("Cart contains correct items with accurate quantities and totals");
    }
}
