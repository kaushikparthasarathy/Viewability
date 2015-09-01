package com.flipkart.Pages;

import org.openqa.selenium.By;
import org.testng.log4testng.Logger;

import java.util.Map;
import java.util.Random;

/**
 * Created on 25/08/15 by kaushik.p.
 */
public class BasePage {
    protected static final Logger logger = Logger.getLogger(BasePage.class);

    public Map<String, String> searchPageLocators, loginPageLocators, productPageLocators,
            homePageLocators, browsePageLocators, checkOutPageLocators, landingPageLocators, cartPageLocators,
            orderConfirmationPageLocators,loginpageLocatorsIOS,homePageLocatorsIOS,productPageLocatorsIOS,landingPageLocatorsIOS;


    public BasePage() {
        initPageLocators();
    }

    private void initPageLocators() {
        homePageLocators = Locator.getLocators("HomePage");
        homePageLocatorsIOS= Locator.getLocators("HomePageIOS");
        searchPageLocators = Locator.getLocators("SearchPage");
        browsePageLocators = Locator.getLocators("BrowsePage");
        loginPageLocators = Locator.getLocators("LoginPage");
        loginpageLocatorsIOS= Locator.getLocators("LoginPageIOS");
        productPageLocators = Locator.getLocators("ProductPage");
        productPageLocatorsIOS = Locator.getLocators("ProductPageIOS");
        checkOutPageLocators = Locator.getLocators("CheckoutPage");
        landingPageLocators = Locator.getLocators("LandingPage");
        landingPageLocatorsIOS = Locator.getLocators("LandingPageIOS");
        cartPageLocators = Locator.getLocators("CartPage");
        orderConfirmationPageLocators = Locator.getLocators("OrderConfirmationPage");
    }

    protected int randomNumber(int limit) {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(limit) + 1;
    }

    public By getBy(String locator) {
        return Locator.getBy(locator);
    }
}
