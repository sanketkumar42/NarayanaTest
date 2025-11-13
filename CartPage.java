package com.project.pages;

import com.project.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WaitUtils wait;

    private By termsCheckbox = By.id("termsofservice");
    private By checkoutButton = By.id("checkout");
    private By cartHeader = By.cssSelector("div.page-title h1");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, Duration.ofSeconds(10));
    }

    public boolean isOnCartPage() {
        try {
            return driver.findElement(cartHeader).getText().contains("Shopping cart");
        } catch (Exception e) {
            return false;
        }
    }

    public void agreeTerms() {
        if (!driver.findElement(termsCheckbox).isSelected()) {
            driver.findElement(termsCheckbox).click();
        }
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
