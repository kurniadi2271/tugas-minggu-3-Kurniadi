package com.juaracoding.kurniadi;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;

    By firstNameField = By.id("first-name");
    By lastNameField = By.id("last-name");
    By postalCodeField = By.id("postal-code");
    By continueBtn = By.id("continue");
    By finishBtn = By.id("finish");
    By errorMsg = By.cssSelector("h3");
    By completeMsg = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void fillCheckoutForm(String firstName, String lastName, String postalCode, boolean clickContinue) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).clear();
        driver.findElement(firstNameField).sendKeys(firstName);

        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).clear();
        driver.findElement(lastNameField).sendKeys(lastName);

        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeField)).clear();
        driver.findElement(postalCodeField).sendKeys(postalCode);

        if (clickContinue) {
            wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
        }
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
    }

    public String getErrorMessage() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(errorMsg))
                .getText();
        } catch (org.openqa.selenium.TimeoutException e) {
            return "";
        }
    }

    public String getCompleteMessage() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(completeMsg))
                .getText();
        } catch (org.openqa.selenium.TimeoutException e) {
            return "";
        }
    }
}