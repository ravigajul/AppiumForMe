package com.example;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BaseTest {

    protected AndroidDriver driver1;
    protected AndroidDriver driver2;
    protected UiAutomator2Options options1;
    protected UiAutomator2Options options2;

    // Appium server URLs - assuming different ports for each server
    protected String appiumServer1 = "http://127.0.0.1:4723";
    protected String appiumServer2 = "http://127.0.0.1:4724";

    @BeforeTest
    public void initialize() throws MalformedURLException, URISyntaxException {
        // Initialize first emulator configuration
        options1 = new UiAutomator2Options();
        options1.setUdid("emulator-5554");
        options1.setPlatformName("Android");
        options1.setAutomationName("uiautomator2");
        options1.setDeviceName("Emulator1");
        // Set app path for auto-installation
        options1.setApp(System.getProperty("user.dir") + "/src/main/resources/ApiDemos-debug.apk");

        // Initialize second emulator configuration
        options2 = new UiAutomator2Options();
        options2.setUdid("emulator-5556");
        options2.setPlatformName("Android");
        options2.setAutomationName("uiautomator2");
        options2.setDeviceName("Emulator2");
        // Set app path for auto-installation
        options2.setApp(System.getProperty("user.dir") + "/src/main/resources/ApiDemos-debug.apk");

        // Create driver instances for both emulators in parallel
        System.out.println("🚀 Initializing dual emulator setup...");

        // Create both drivers
        driver1 = new AndroidDriver(new URI(appiumServer1).toURL(), options1);
        driver2 = new AndroidDriver(new URI(appiumServer2).toURL(), options2);

        // Verify apps are installed on both devices
        ensureAppInstalled(driver1, "io.appium.android.apis");
        ensureAppInstalled(driver2, "io.appium.android.apis");

        System.out.println("✅ Dual emulator setup complete!");
        System.out.println("📱 Emulator 1 (emulator-5554) ready on port 4723");
        System.out.println("📱 Emulator 2 (emulator-5556) ready on port 4724");
    }

    private void ensureAppInstalled(AndroidDriver driver, String appPackage) {
        if (!driver.isAppInstalled(appPackage)) {
            String appPath = System.getProperty("user.dir") + "/src/main/resources/ApiDemos-debug.apk";
            System.out.println("App not installed. Installing: " + appPackage);
            driver.installApp(appPath);
        } else {
            System.out.println("App already installed: " + appPackage);
        }
    }

    @AfterTest
    public void destroy() {
        if (driver1 != null) {
            try {
                driver1.terminateApp("io.appium.android.apis");
            } catch (Exception e) {
                System.out.println("App not running or already terminated on Emulator 1");
            }
            driver1.quit();
            System.out.println("✅ Emulator 1 session closed");
        }

        if (driver2 != null) {
            try {
                driver2.terminateApp("io.appium.android.apis");
            } catch (Exception e) {
                System.out.println("App not running or already terminated on Emulator 2");
            }
            driver2.quit();
            System.out.println("✅ Emulator 2 session closed");
        }

        System.out.println("🏁 Dual emulator cleanup complete!");
    }
}
