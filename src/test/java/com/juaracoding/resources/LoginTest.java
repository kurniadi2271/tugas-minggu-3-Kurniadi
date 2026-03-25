package com.juaracoding.resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.juaracoding.kurniadi.InventoryPage;
import com.juaracoding.kurniadi.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginPositive() {
        loginPage.login("standard_user", "secret_sauce");
        // Verifikasi login berhasil (cek URL atau elemen halaman inventory)
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Test
    public void loginNegativeWrongPassword() {
        loginPage.login("standard_user", "wrong_password");
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Username and password do not match"));
    }

    @Test
    public void loginNegativeWrongUsername() {
        loginPage.login("wrong_user", "secret_sauce");
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Username and password do not match"));
    }

    @AfterMethod
    public void tearDown() {
        try {
            Thread.sleep(5000); // delay 5 detik
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
