package com.project.pages;

import com.project.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    private WaitUtils wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, Duration.ofSeconds(10));
    }

    private By genderMale = By.id("gender-male");
    private By firstName = By.id("FirstName");
    private By lastName = By.id("LastName");
    private By email = By.id("Email");
    private By password = By.id("Password");
    private By confirmPassword = By.id("ConfirmPassword");
    private By dayDropdown = By.name("DateOfBirthDay");
    private By monthDropdown = By.name("DateOfBirthMonth");
    private By yearDropdown = By.name("DateOfBirthYear");
    private By registerButton = By.id("register-button");
    private By resultMessage = By.cssSelector("div.result");

    public void open() {
        driver.get("https://demo.nopcommerce.com/register");
    }

    public void selectGenderMale() { driver.findElement(genderMale).click(); }
    public void enterFirstName(String sanket) { driver.findElement(firstName).sendKeys(sanket); }
    public void enterLastName(String kumar) { driver.findElement(lastName).sendKeys(kumar); }
    public void enterEmail(String x) { driver.findElement(email).sendKeys(x); }
    public void enterPassword(String p) { driver.findElement(password).sendKeys(p); }
    public void enterConfirmPassword(String p) { driver.findElement(confirmPassword).sendKeys(p); }

    public void selectDOB(String d, String m, String y) {
    	
    	wait.waitForVisibility(dayDropdown);
        wait.waitForVisibility(monthDropdown);
        wait.waitForVisibility(yearDropdown);
    	
        new Select(driver.findElement(dayDropdown)).selectByVisibleText(d);
        new Select(driver.findElement(monthDropdown)).selectByVisibleText(m);
        new Select(driver.findElement(yearDropdown)).selectByVisibleText(y);
    }

    public void clickRegister() { driver.findElement(registerButton).click(); }

    public String getResultMessage() {
        return wait.waitForVisibility(resultMessage).getText();
    }
}

