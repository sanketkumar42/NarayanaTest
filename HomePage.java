package com.project.pages;

import com.project.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WaitUtils wait;

    private By registerLink = By.cssSelector("a.ico-register");
    private By loginLink = By.cssSelector("a.ico-login");
    private By logoutLink = By.cssSelector("a.ico-logout");
    private By searchBox = By.id("small-searchterms");
    private By searchButton = By.cssSelector("button[type='submit'][class*='search-box-button']");
    private By computersMenu = By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']");
    private By notebooksSubMenu = By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Notebooks']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://demo.nopcommerce.com/");
    }

    public void goToRegister() {
        driver.findElement(registerLink).click();
    }

    public void goToLogin() {
        driver.findElement(loginLink).click();
    }

    public boolean isLogoutVisible() {
        try {
            return driver.findElement(logoutLink).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void search(String query) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(query);
        driver.findElement(searchButton).click();
    }

    public void hoverOverMenu(String menuName) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(computersMenu)).perform();
    }

    public void clickSubMenu(String subMenu) {
        driver.findElement(notebooksSubMenu).click();
    }
}

