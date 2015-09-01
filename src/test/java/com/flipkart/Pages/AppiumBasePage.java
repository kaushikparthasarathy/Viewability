package com.flipkart.Pages;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by user on 1/8/2015.
 */
public class AppiumBasePage extends BasePage {

    protected AppiumDriver driver;
    public static TouchActions touchActions;
    public AppiumBasePage(WebDriver driver) {
        this.driver = ((AppiumDriver) driver);
        touchActions = new TouchActions(this.driver);
    }

    public boolean isElementPresent(By by) {
        try {
            List allElements = driver.findElements(by);
            if ((allElements == null) || (allElements.size() == 0))
                return false;
            else
                return true;
        } catch (java.util.NoSuchElementException e) {
            return false;
        }
    }
    public boolean isElementPresent(By by, int pageIndex) {
        try {
            if ((driver.findElements(by).size()==0) ||(driver.findElements(by).get(pageIndex).isDisplayed() == false))
                return false;
            else
                return true;
        }
        catch(java.util.NoSuchElementException e)
        {
            return false;
        }
    }
    public void waitForElementPresent(final String locator, int timeToWaitInSeconds) throws Exception {
        try {
            new WebDriverWait(driver, timeToWaitInSeconds)
                    .until(new ExpectedCondition<WebElement>() {
                        public WebElement apply(WebDriver d) {
                            return driver.findElement(getBy(locator));
                        }
                    });

        } catch (Exception e) {
            System.err.println("There was an issue in finding the WebElement " + locator + "." + e.getMessage().split("waiting")[0]);
        }
    }

    public String textAt(String locator) throws Exception {
        String text = null;
        try {
            waitForElementPresent(locator, 3);
            text = driver.findElement(getBy(locator)).getText().trim();
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return text;
    }

    public boolean textAtContains(String locator, String verificationText) throws Exception {
        boolean isTextMatched = false;
        try {
            String temp = textAt(locator).toLowerCase();
            if (temp.length() < 1)
                return isTextMatched;
            isTextMatched = temp.contains(verificationText.toLowerCase()) || verificationText.toLowerCase().contains(temp);
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return isTextMatched;
    }
    public boolean textAtEquals(String locator, String verificationText) throws Exception {
        boolean isTextMatched = false;
        try {
            String temp = textAt(locator);
            if(temp.length() < 1)
                return isTextMatched;
            isTextMatched = temp.equals(verificationText);
        } catch(NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return isTextMatched;
    }
}