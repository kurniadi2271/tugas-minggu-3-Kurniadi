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

public class AddToCartTest {
    WebDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        // Precondition: login berhasil
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void addToCartPositive() {
        inventoryPage.clickAddToCart();
        Assert.assertEquals(inventoryPage.getCartCount(), "1");
        Assert.assertEquals(inventoryPage.getButtonText(), "Remove");
    }

    @Test
    public void addToCartNegativeWithoutLogin() {
        driver.quit(); // Hapus session login
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/inventory.html"); // langsung ke inventory
        inventoryPage = new InventoryPage(driver);
        // Cek apakah user diarahkan ke login
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
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
