package com.project.pages;

import com.project.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class NotebooksPage {
    private WebDriver driver;
    private WaitUtils wait;

    private By pageTitle = By.cssSelector("div.page-title h1");
    private By addToCartButtonByProduct = By.xpath("//h2[@class='product-title']/a[normalize-space()='%s']/../..//button[contains(@class,'product-box-add-to-cart-button')]");
    private By successBar = By.cssSelector("div.bar-notification.success");
    private By shoppingCartLink = By.cssSelector("a.ico-cart");

    public NotebooksPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, Duration.ofSeconds(10));
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public void addToCartByProductName(String productName) {
        By btn = By.xpath(String.format("//h2[@class='product-title']/a[normalize-space()='%s']/../..//button[contains(@class,'product-box-add-to-cart-button')]", productName));
        driver.findElement(btn).click();
    }

    public boolean waitForAddToCartSuccess() {
        try {
            wait.waitForVisibility(successBar);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void goToShoppingCart() {
        driver.findElement(shoppingCartLink).click();
    }
}
