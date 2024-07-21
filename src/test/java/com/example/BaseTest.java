package com.example;

import java.net.MalformedURLException;

import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BaseTest {
    
    protected AndroidDriver driver;
    protected UiAutomator2Options options;
    @BeforeTest
    public void initialize() throws MalformedURLException{
        options = new UiAutomator2Options();
        options.setUdid("emulator-5554");
        options.setPlatformName("Android");
        options.setAutomationName("uiautomator2");
        

    }
}
