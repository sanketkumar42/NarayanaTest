package com.project.pages;

import com.project.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WaitUtils wait;

    private By billingFirstName = By.id("BillingNewAddress_FirstName");
    private By billingLastName = By.id("BillingNewAddress_LastName");
    private By billingEmail = By.id("BillingNewAddress_Email");
    private By billingCountry = By.id("BillingNewAddress_CountryId");
    private By billingCity = By.id("BillingNewAddress_City");
    private By billingAddress1 = By.id("BillingNewAddress_Address1");
    private By billingZip = By.id("BillingNewAddress_ZipPostalCode");
    private By billingPhone = By.id("BillingNewAddress_PhoneNumber");
    private By billingContinue = By.xpath("//button[@name='save' and contains(@onclick,'BillingSave') or @id='billing-buttons-container']/..|//div[@id='billing-buttons-container']/button");
    private By continueShipping = By.cssSelector("button[name='save' ]");
    private By confirmOrderButton = By.xpath("//button[contains(.,'Confirm')]" );
    private By orderSuccess = By.cssSelector("div.section.order-completed div.title");
    private By billingContinueBtn = By.cssSelector("#billing-buttons-container button[type='button']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, Duration.ofSeconds(15));
    }

    public void fillBillingAddressIfEmpty(String first, String last, String country, String address, String city, String zip, String phone) {
        try {
            if (driver.findElement(billingFirstName).getAttribute("value").isEmpty()) {
                driver.findElement(billingFirstName).clear();
                driver.findElement(billingFirstName).sendKeys(first);
                driver.findElement(billingLastName).clear();
                driver.findElement(billingLastName).sendKeys(last);
                driver.findElement(billingEmail).clear();
                driver.findElement(billingEmail).sendKeys("test@example.com");
                new Select(driver.findElement(billingCountry)).selectByVisibleText(country);
                driver.findElement(billingCity).sendKeys(city);
                driver.findElement(billingAddress1).sendKeys(address);
                driver.findElement(billingZip).sendKeys(zip);
                driver.findElement(billingPhone).sendKeys(phone);
            }
        } catch (Exception e) {
        }
        try {
            driver.findElement(billingContinueBtn).click();
        } catch (Exception e) {
            try { driver.findElement(billingContinue).click(); } catch (Exception ex) {}
        }
    }

    public void continueThroughCheckoutSteps() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        try { driver.findElement(By.cssSelector("#shipping-method-buttons-container button[type='button']")).click(); } catch (Exception e) {}
        try { driver.findElement(By.cssSelector("#payment-method-buttons-container button[type='button']")).click(); } catch (Exception e) {}
        try { driver.findElement(By.cssSelector("#payment-info-buttons-container button[type='button']")).click(); } catch (Exception e) {}
    }

    public boolean confirmOrder() {
        try {
            driver.findElement(confirmOrderButton).click();
            Thread.sleep(5500);
            return driver.findElement(orderSuccess).getText().contains("Your order has been successfully processed!") || driver.findElement(orderSuccess).getText().length() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
