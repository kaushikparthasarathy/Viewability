package com.flipkart.Drivers;
/**
 * Created on 25/08/15 by kaushik.p.
 */

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.remote.RemoteTouchScreen;

import java.net.URL;

public class SwipeableWebDriver extends AppiumDriver implements HasTouchScreen {

    public RemoteTouchScreen touch;
    public SwipeableWebDriver(URL url, Capabilities caps) {
        super(url,  caps);
        touch = new RemoteTouchScreen(getExecuteMethod());
    }

    public TouchScreen getTouch() {
        return touch;
    }

    @Override
    public MobileElement scrollTo(String s) {
        return null;
    }

    @Override
    public MobileElement scrollToExact(String s) {
        return null;
    }
}