package com.juaracoding.kurniadi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    WebDriver driver;

    By firstAddToCartBtn = By.cssSelector("button[id^='add-to-cart']");
    By cartBadge = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAddToCart() {
        driver.findElement(firstAddToCartBtn).click();
    }

    public String getCartCount() {
        return driver.findElement(cartBadge).getText();
    }

    public String getButtonText() {
        return driver.findElement(firstAddToCartBtn).getText();
    }
}
