package com.project.pages;

import com.project.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WaitUtils wait;

    private By productTitle = By.cssSelector("div.product-name h1");

    public ProductPage(org.openqa.selenium.WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, Duration.ofSeconds(10));
    }

    public String getProductTitle() {
        try {
            return driver.findElement(productTitle).getText();
        } catch (Exception e) {
            return "";
        }
    }
}

