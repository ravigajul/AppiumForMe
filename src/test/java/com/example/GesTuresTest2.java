package com.example;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class GesTuresTest2 extends BaseTest {

    @BeforeMethod
    public void setUp() {
        // Set up Google Maps for the second emulator
        options2.setCapability("appPackage", "com.google.android.apps.maps");
        options2.setCapability("appActivity", "com.google.android.maps.MapsActivity");
    }

    @Test
    public void pinchOpenGestureTest() throws InterruptedException {
        Thread.sleep(3000);
        driver2.findElement(AppiumBy.xpath("//android.widget.Button[@text='SKIP']")).click();
        Thread.sleep(5000);
        ((JavascriptExecutor) driver2).executeScript("mobile: pinchOpenGesture", ImmutableMap.of(
                "left", 200,
                "top", 470,
                "width", 600,
                "height", 600,
                "percent", 0.75));
    }

}
