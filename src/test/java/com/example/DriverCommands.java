package com.example;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class DriverCommands extends BaseTest {
    @Test
    public void installApp() throws MalformedURLException {
        String appPath = System.getProperty("user.dir") + "/src/main/resources/ApiDemos-debug.apk";
        options.setApp(appPath);
        driver = new AndroidDriver(URI.create("http://127.0.0.1:4723").toURL(), options);
        driver.installApp(appPath);
        Assert.assertTrue(driver.isAppInstalled("io.appium.android.apis"));

    }

    @Test
    public void removeApp() throws MalformedURLException {
        String appPath = System.getProperty("user.dir") + "/src/main/resources/ApiDemos-debug.apk";
        options.setApp(appPath);
        driver = new AndroidDriver(URI.create("http://127.0.0.1:4723").toURL(), options);
        driver.installApp(appPath);
        Assert.assertTrue(driver.isAppInstalled("io.appium.android.apis"));
        driver.removeApp("io.appium.android.apis");
        Assert.assertFalse(driver.isAppInstalled("io.appium.android.apis"));

    }

    @Test
    public void isAppInstalled() throws MalformedURLException {
        driver.isAppInstalled("io.appium.android.apis");
    }

    @Test
    public void runAppInBackGroundForCertainDuration() throws MalformedURLException {
        options.setApp(System.getProperty("user.dir") + "/src/main/resources/ApiDemos-debug.apk");
        driver = new AndroidDriver(URI.create("http://127.0.0.1:4723").toURL(), options);
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.runAppInBackground(Duration.ofSeconds(3)); // running in background for 3 seconds
        driver.findElement(AppiumBy.accessibilityId("Animation")).click();

    }

    @Test
    public void activateSettings() throws MalformedURLException {

        driver = new AndroidDriver(URI.create("http://127.0.0.1:4723").toURL(), options);
        driver.activateApp("com.android.settings"); // you can get this using apk info app

    }

    @Test
    public void queryAppState() throws MalformedURLException {
        driver = new AndroidDriver(URI.create("http://127.0.0.1:4723").toURL(), options);
        driver.activateApp("com.android.settings"); // you can get this using apk info app
        System.out.println(driver.queryAppState("com.android.settings")); // RUNNING_FOREGROUND
        driver.terminateApp("com.android.settings");
        System.out.println(driver.queryAppState("com.android.settings")); // NOT_RUNNING
    }

    @Test
    public void lockUnlockAndroidDevice() throws MalformedURLException{
        String appPath = System.getProperty("user.dir") + "/src/main/resources/ApiDemos-debug.apk";
        options.setApp(appPath);
        driver = new AndroidDriver(URI.create("http://127.0.0.1:4723").toURL(), options);
        driver.installApp(appPath);
        Assert.assertTrue(driver.isAppInstalled("io.appium.android.apis"));
        driver.lockDevice(); //locks the device
        Assert.assertTrue(driver.isDeviceLocked());
        driver.unlockDevice();
        Assert.assertFalse(driver.isDeviceLocked());
    }
}
