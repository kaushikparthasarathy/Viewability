package com.flipkart.Pages; /**
 * Created on 25/08/15 by kaushik.p.
 */



import org.openqa.selenium.By;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.System;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Locator {

    static Map<String, String> paths = new HashMap<String, String>();
    static Map<String, Map<String, String>> locatorsMap = new HashMap<String, Map<String, String>>();
    public String key;
    public String value;

    public Locator(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getlocatorsDirectory() {
        String srcDir = System.getProperty("user.dir") + File.separator;
//        return srcDir + "automobile-tests/" + "mobile-test/" + "src/" + "main/" + "resources/" + "locators/" + System.getProperty("device") + "/";
        return srcDir + "src/" + "main/" + "resources/" + "Selendroid" + "/";

    }

    public static Map<String, String> getLocators(String pageName) {
        Map<String, String> locators = locatorsMap.get(pageName);
        paths.put(pageName, getlocatorsDirectory() + pageName.toString() + ".properties");

        if (locators != null)
            return locators;

        locators = new HashMap<String, String>();
        Properties props = new Properties();
        try {
            FileInputStream fis = new FileInputStream(paths.get(pageName));
            props.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Enumeration em = props.keys();
        while (em.hasMoreElements()) {
            String key = em.nextElement().toString();
            locators.put(key, props.getProperty(key));
        }

        locatorsMap.put(pageName, locators);
        return locators;
    }

    public static By getBy(String locator) {
        By by;
        if (locator.startsWith("//"))
            by = By.xpath(locator);
        else if (locator.startsWith("xpath="))
            by = By.xpath(locator.replace("xpath=", ""));
        else if (locator.startsWith("class="))
            by = By.className(locator.replace("class=", ""));
        else if (locator.startsWith("css="))
            by = By.cssSelector(locator.replace("css=", "").trim());
        else if (locator.startsWith("link="))
            by = By.linkText(locator.replace("link=", ""));
        else if (locator.startsWith("name="))
            by = By.name(locator.replace("name=", "").trim());
        else if (locator.startsWith("tag="))
            by = By.tagName(locator.replace("tag=", "").trim());
        else if (locator.startsWith("partialText="))
            by = By.partialLinkText(locator.replace("partialText=", ""));
        else if (locator.startsWith("id="))
            by = By.id(locator.replace("id=", ""));
        else if(locator.contains("(//"))
            by=By.xpath(locator);
        else
            by = By.id(locator);
        return by;
    }

    public static By getBy(String locator, String replace) {
        return getBy(locator.replace("~", replace));
    }
}
