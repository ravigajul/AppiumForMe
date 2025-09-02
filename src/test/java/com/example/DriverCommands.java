package com.example;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class DriverCommands extends BaseTest {

    @BeforeMethod
    public void setUpApp() {
        // Set the app for the first emulator
        String appPath = System.getProperty("user.dir") + "/src/main/resources/ApiDemos-debug.apk";
        options1.setApp(appPath);
    }

    @Test
    public void installApp() {
        String appPath = System.getProperty("user.dir") + "/src/main/resources/ApiDemos-debug.apk";
        driver1.installApp(appPath);
        Assert.assertTrue(driver1.isAppInstalled("io.appium.android.apis"));
    }

    @Test
    public void removeApp() {
        String appPath = System.getProperty("user.dir") + "/src/main/resources/ApiDemos-debug.apk";
        driver1.installApp(appPath);
        Assert.assertTrue(driver1.isAppInstalled("io.appium.android.apis"));
        driver1.removeApp("io.appium.android.apis");
        Assert.assertFalse(driver1.isAppInstalled("io.appium.android.apis"));
    }

    @Test
    public void isAppInstalled() {
        driver1.isAppInstalled("io.appium.android.apis");
    }

    @Test
    public void runAppInBackGroundForCertainDuration() {
        driver1.findElement(AppiumBy.accessibilityId("Views")).click();
        driver1.runAppInBackground(Duration.ofSeconds(3)); // running in background for 3 seconds
        driver1.findElement(AppiumBy.accessibilityId("Animation")).click();
    }

    @Test
    public void activateSettings() {
        driver1.activateApp("com.android.settings"); // you can get this using apk info app
    }

    @Test
    public void queryAppState() {
        driver1.activateApp("com.android.settings"); // you can get this using apk info app
        System.out.println(driver1.queryAppState("com.android.settings")); // RUNNING_FOREGROUND
        driver1.terminateApp("com.android.settings");
        System.out.println(driver1.queryAppState("com.android.settings")); // NOT_RUNNING
    }

    @Test
    public void lockUnlockAndroidDevice() {
        String appPath = System.getProperty("user.dir") + "/src/main/resources/ApiDemos-debug.apk";
        driver1.installApp(appPath);
        Assert.assertTrue(driver1.isAppInstalled("io.appium.android.apis"));
        driver1.lockDevice(); // locks the device
        Assert.assertTrue(driver1.isDeviceLocked());
        driver1.unlockDevice();
        Assert.assertFalse(driver1.isDeviceLocked());
    }
}
