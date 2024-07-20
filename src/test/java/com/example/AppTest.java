package com.example;

import java.net.MalformedURLException;
import java.net.URI;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AppTest 
{
   public static void main(String[] args) throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setUdid("emulator-5554");
        options.setApp("/Users/ravigajul/Downloads/ApiDemos-debug.apk");
        options.setPlatformName("Android");
        options.setAutomationName("uiautomator2");
        options.setApp(System.getProperty("user.dir")+"/src/main/resources/ApiDemos-debug.apk");
        AndroidDriver driver = new AndroidDriver(URI.create("http://127.0.0.1:4723").toURL(), options);
            driver.findElement(AppiumBy.accessibilityId("Animation")).click();
            driver.findElement(AppiumBy.accessibilityId("Loading")).click();
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(0)")).click();
            System.out.println(driver.getSessionId());

   }
}
