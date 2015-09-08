package com.flipkart.Drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.net.URL;

/**
 * Created on 25/08/15 by kaushik.p.
 */
public class BaseConfig {

    public static WebDriver driver;
    public static TouchActions touchActions;
    @BeforeSuite
    public void setup(){

        try {

            //ServerSocket s = create(new int[] { 3843, 4584, 4843 });
            //System.out.println("listening on port: " + s.getLocalPort());
            //String port = "http://127.0.0.1:"+s.getLocalPort()+"/wd/hub";
            driver = new SwipeableWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), getCapabilities1());
            //driver = new com.flipkart.Drivers.SwipeableWebDriver(new URL("http://172.20.216.113:4723/wd/hub"), getCapabilities2());
            touchActions = new TouchActions(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public DesiredCapabilities getCapabilities1(){

        File appDir = new File("/Users/kaushik.p/Documents/Apks/");
        File app = new File(appDir, "flipkart_ecom_app_4_1_610000.apk");


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "Android");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("deviceName", "a");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformVersion", "5.0");

        capabilities.setCapability("app-package", "com.flipkart.android.feature.AdsSdkInitializeChanges");
        capabilities.setCapability("app-activity", "com.flipkart.android.SplashActivity");

        System.out.println(capabilities);


        return capabilities;

    }
}
