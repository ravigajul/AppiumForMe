package com.example;

import java.net.MalformedURLException;
import java.net.URI;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class GesTuresTest2 extends BaseTest {

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        options.setCapability("appPackage", "com.google.android.apps.maps");
        options.setCapability("appActivity", "com.google.android.maps.MapsActivity");
        driver = new AndroidDriver(URI.create("http://127.0.0.1:4723").toURL(), options);
    }

    @Test
    public void pinchOpenGestureTest() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='SKIP']")).click();
        Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript("mobile: pinchOpenGesture", ImmutableMap.of(
                "left", 200,
                "top",470,
                "width",600,
                "height",600,
                "percent", 0.75));
    }

}
