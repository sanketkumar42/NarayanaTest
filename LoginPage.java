package com.project.pages;

import com.project.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WaitUtils wait;

    private By email = By.id("Email");
    private By password = By.id("Password");
    private By loginButton = By.cssSelector("button.login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://demo.nopcommerce.com/login");
    }

    public void login(String userEmail, String passwords) {
        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(userEmail);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(passwords);
        driver.findElement(loginButton).click();
    }
}
