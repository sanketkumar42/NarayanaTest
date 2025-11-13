package com.project.tests;

import com.project.base.BaseTest;
import com.project.pages.*;
import com.project.utils.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UiTests extends BaseTest {

    @DataProvider(name = "users")
    public Object[][] users() {
        return new Object[][]{
                {"Test", "UserA", "userA_" + System.currentTimeMillis() + "@mail.com", TestData.defaultPassword, "1", "January", "1990"},
                {"Test", "UserB", "userB_" + System.currentTimeMillis() + "@mail.com", TestData.defaultPassword, "2", "February", "1991"}
        };
    }

    @Test(dataProvider = "users", groups = {"UI"})
    public void registrationFlow(String sanket, String kumar, String email, String passwords, String day, String month, String year) {
        RegisterPage reg = new RegisterPage(driver);
        reg.open();
        reg.selectGenderMale();
        reg.enterFirstName(sanket);
        reg.enterLastName(kumar);
        reg.selectDOB(day, month, year);
        reg.enterEmail(email);
        reg.enterPassword(passwords);
        reg.enterConfirmPassword(passwords);
        reg.clickRegister();

        String msg = reg.getResultMessage();
        Assert.assertTrue(msg.contains("Your registration completed"), "Registration confirmation must be shown");

        // store first user for later test
        if (TestData.firstUserEmail == null) {
            TestData.firstUserEmail = email;
        }
    }

    @Test(dependsOnMethods = {"registrationFlow"}, groups = {"UI"})
    public void loginSearchAddToCartAndCheckout() {
        // Use first user created above
        String firstUserEmail = TestData.firstUserEmail;
        String firstUserPassword = TestData.defaultPassword;

        HomePage home = new HomePage(driver);
        home.open();
        home.goToLogin();

        LoginPage login = new LoginPage(driver);
        login.login(firstUserEmail, firstUserPassword);

        Assert.assertTrue(home.isLogoutVisible(), "Logout link should be visible after login");

        home.search("Apple MacBook Pro 13-inch");
        ProductPage product = new ProductPage(driver);
        Assert.assertTrue(product.getProductTitle().contains("Apple MacBook Pro 13-inch"), "Product page should show MacBook title");

        // hover over Computers -> Notebooks
        Actions actions = new Actions(driver);
        home.hoverOverMenu("Computers");
        home.clickSubMenu("Notebooks");

        NotebooksPage notebooks = new NotebooksPage(driver);
        Assert.assertEquals(notebooks.getPageTitle(), "Notebooks", "Page title should be Notebooks");

        notebooks.addToCartByProductName("Apple MacBook Pro 13-inch");
        Assert.assertTrue(notebooks.waitForAddToCartSuccess(), "Add to cart success must be visible");

        notebooks.goToShoppingCart();
        CartPage cart = new CartPage(driver);
        cart.agreeTerms();
        cart.clickCheckout();

        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.fillBillingAddressIfEmpty("Test", "User", "India", "Some Street", "City", "560001", "9999999999");
        checkout.continueThroughCheckoutSteps();
        Assert.assertTrue(checkout.confirmOrder(), "Order must be successfully processed");
    }
}

