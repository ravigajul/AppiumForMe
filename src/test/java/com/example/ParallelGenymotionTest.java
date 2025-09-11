package com.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.URI;

public class ParallelGenymotionTest {
    protected AndroidDriver driver;

    public AndroidDriver createAndroidDriver(String udid, int systemPort) throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setUdid(udid); // Use the passed UDID parameter
        options.setPlatformName("Android");
        options.setAutomationName("uiautomator2");
        options.setDeviceName("Emulator1");
        // Set app path for auto-installation
        options.setApp(System.getProperty("user.dir") + "/src/main/resources/ApiDemos-debug.apk");
        options.setCapability("systemPort", systemPort);
        //options.setAppPackage("com.android.settings");
        //options.setAppActivity(".Settings");
        return new AndroidDriver(new URI("http://localhost:4723").toURL(), options);
    }

    @DataProvider(name = "devices", parallel = true)
    public Object[][] deviceDataProvider() {
        return new Object[][] {
                { "127.0.0.1:6555", 8201 },
        };
    }

    @Test(dataProvider = "devices")
    public void testBattery(String udid, int systemPort) throws Exception {
        driver = createAndroidDriver(udid, systemPort);
        try {
            driver.findElement(AppiumBy.accessibilityId("Animation")).click();
            driver.findElement(AppiumBy.accessibilityId("Loading")).click();
            driver.findElement(
                    AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(0)"))
                    .click();
        } finally {
            driver.quit();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}