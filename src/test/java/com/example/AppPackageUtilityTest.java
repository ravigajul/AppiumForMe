package com.example;

import org.testng.annotations.Test;
import java.util.List;

/**
 * Test class demonstrating the usage of AppPackageUtility
 * This class shows various ways to discover package information
 */
public class AppPackageUtilityTest {

    @Test
    public void testGetCurrentApp() {
        System.out.println("=== Testing Current App Detection ===");

        // Check if ADB is available
        if (!AppPackageUtility.isAdbAvailable()) {
            System.out.println("❌ ADB not available or no devices connected");
            return;
        }

        // Get current focused app
        String currentApp = AppPackageUtility.getCurrentFocusedApp();
        System.out.println("Current App: " + currentApp);

        // Get package name only
        String packageName = AppPackageUtility.getCurrentPackageName();
        System.out.println("Package Name: " + packageName);

        // Get activity name only
        String activityName = AppPackageUtility.getCurrentActivityName();
        System.out.println("Activity Name: " + activityName);
    }

    @Test
    public void testSearchPackages() {
        System.out.println("\n=== Testing Package Search ===");

        if (!AppPackageUtility.isAdbAvailable()) {
            System.out.println("❌ ADB not available or no devices connected");
            return;
        }

        // Search for common packages
        String[] searchTerms = { "google", "android", "appium" };

        for (String term : searchTerms) {
            List<String> results = AppPackageUtility.searchPackages(term);
            System.out.println("\nPackages containing '" + term + "':");
            for (int i = 0; i < Math.min(results.size(), 5); i++) { // Show only first 5
                System.out.println("  - " + results.get(i));
            }
            if (results.size() > 5) {
                System.out.println("  ... and " + (results.size() - 5) + " more");
            }
        }
    }

    @Test
    public void testDeviceInfo() {
        System.out.println("\n=== Testing Device Information ===");

        // Get connected devices
        List<String> devices = AppPackageUtility.getConnectedDevices();
        System.out.println("Connected Devices:");
        for (String device : devices) {
            System.out.println("  - " + device);
        }

        // Get total package count
        if (!devices.isEmpty()) {
            List<String> allPackages = AppPackageUtility.getAllInstalledPackages();
            System.out.println("Total Installed Packages: " + allPackages.size());
        }
    }

    @Test
    public void testAPKInfo() {
        System.out.println("\n=== Testing APK Information ===");

        // Test with the ApiDemos APK
        String apkPath = System.getProperty("user.dir") + "/src/main/resources/ApiDemos-debug.apk";
        String apkInfo = AppPackageUtility.getPackageFromAPK(apkPath);

        if (apkInfo != null) {
            System.out.println("APK Information: " + apkInfo);
        } else {
            System.out.println("Could not get APK information (aapt/aapt2 may not be available)");
        }
    }

    @Test
    public void testFullReport() {
        System.out.println("\n=== Full App Package Report ===");
        AppPackageUtility.printAppInfo();
    }

    /**
     * Demonstration method to show how to use utility in BaseTest
     */
    public void demonstrateBaseTestUsage() {
        System.out.println("\n=== BaseTest Integration Example ===");

        // This is how you could use it in your BaseTest class
        if (AppPackageUtility.isAdbAvailable()) {
            // Get current package for verification
            String currentPackage = AppPackageUtility.getCurrentPackageName();

            if (currentPackage != null && currentPackage.equals("io.appium.android.apis")) {
                System.out.println("✅ ApiDemos app is currently focused");
            } else {
                System.out.println("ℹ️ Current app: " + currentPackage);
            }

            // Verify devices are connected
            List<String> devices = AppPackageUtility.getConnectedDevices();
            System.out.println("Available devices for testing: " + devices);

            // Check if your target emulators are connected
            if (devices.contains("emulator-5554") && devices.contains("emulator-5556")) {
                System.out.println("✅ Both target emulators are connected");
            } else {
                System.out.println("⚠️ Target emulators not found. Connected: " + devices);
            }
        }
    }
}
