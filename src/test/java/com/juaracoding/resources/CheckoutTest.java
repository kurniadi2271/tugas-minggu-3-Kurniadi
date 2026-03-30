package com.juaracoding.resources;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.juaracoding.kurniadi.InventoryPage;
import com.juaracoding.kurniadi.LoginPage;
import com.juaracoding.kurniadi.CheckoutPage;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutTest {
    WebDriver driver;
    WebDriverWait wait;

    LoginPage loginPage;
    InventoryPage inventoryPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-save-password-bubble");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://www.saucedemo.com");

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        checkoutPage = new CheckoutPage(driver);

        // Login
        loginPage.login("standard_user", "secret_sauce");

        wait.until(ExpectedConditions.urlContains("inventory"));

        // Add to cart
        inventoryPage.clickAddToCart();

        // // Verify item added to cart
        // wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[@class='shopping_cart_badge']"), "1"));

        // Navigate to cart
        driver.get("https://www.saucedemo.com/cart.html");

        // Klik checkout otomatis
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test='checkout']"))).click();

        // Wait for checkout page to load (fallback ke URL langsung)
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        } catch (org.openqa.selenium.TimeoutException e) {
            driver.get("https://www.saucedemo.com/checkout-step-one.html");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        }
    }

    @Test
    public void checkoutPositive() {
        checkoutPage.fillCheckoutForm("John", "Doe", "67152", true);
        checkoutPage.clickFinish();

        String completeMsg = checkoutPage.getCompleteMessage();
        Assert.assertEquals(completeMsg, "Thank you for your order!");
    }

    @Test
    public void checkoutNegativeEmptyFirstName() {
        checkoutPage.fillCheckoutForm("", "Doe", "12345", false);
        checkoutPage.clickContinue();

        String error = checkoutPage.getErrorMessage();
        System.out.println("checkoutNegativeEmptyFirstName error='" + error + "'");
        Assert.assertTrue(
            error.toLowerCase().contains("first name is required") ||
            error.toLowerCase().contains("first name is required") ||
            error.toLowerCase().contains("not complete") ||
            error.toLowerCase().contains("error"),
            "Expected checkout error message not found, actual='" + error + "'"
        );
    }

    @Test
    public void checkoutNegativeEmptyLastName() {
        checkoutPage.fillCheckoutForm("John", "", "12345", false);
        checkoutPage.clickContinue();

        String error = checkoutPage.getErrorMessage();
        System.out.println("checkoutNegativeEmptyLastName error='" + error + "'");
        Assert.assertTrue(
            error.toLowerCase().contains("last name is required") ||
            error.toLowerCase().contains("error"),
            "Expected checkout error message not found, actual='" + error + "'"
        );
    }

    @Test
    public void checkoutNegativeEmptyPostalCode() {
        checkoutPage.fillCheckoutForm("John", "Doe", "", false);
        checkoutPage.clickContinue();

        String error = checkoutPage.getErrorMessage();
        System.out.println("checkoutNegativeEmptyPostalCode error='" + error + "'");
        Assert.assertTrue(
            error.toLowerCase().contains("postal code is required") ||
            error.toLowerCase().contains("error"),
            "Expected checkout error message not found, actual='" + error + "'"
        );
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}