package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for finding Android app package names and activities
 * Provides various methods to discover package information using ADB commands
 */
public class AppPackageUtility {

    /**
     * Get the currently focused app package and activity
     * 
     * @return String containing package/activity info, or null if not found
     */
    public static String getCurrentFocusedApp() {
        try {
            String[] command = { "adb", "shell", "dumpsys", "window", "displays" };
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("mCurrentFocus") || line.contains("mFocusedApp")) {
                    return extractPackageFromFocusLine(line);
                }
            }

            process.waitFor();
            reader.close();
        } catch (IOException | InterruptedException e) {
            System.err.println("Error getting current focused app: " + e.getMessage());
        }
        return null;
    }

    /**
     * Get the package name from focus line
     * 
     * @param focusLine The line containing focus information
     * @return Package name or null if not found
     */
    private static String extractPackageFromFocusLine(String focusLine) {
        // Pattern to match package name from focus line
        // Example: mCurrentFocus=Window{abc123 u0
        // com.example.app/com.example.app.MainActivity}
        Pattern pattern = Pattern.compile("([a-zA-Z0-9._]+)/([a-zA-Z0-9._]+)");
        Matcher matcher = pattern.matcher(focusLine);

        if (matcher.find()) {
            String packageName = matcher.group(1);
            String activityName = matcher.group(2);
            return "Package: " + packageName + ", Activity: " + activityName;
        }
        return focusLine.trim();
    }

    /**
     * Get package name only from currently focused app
     * 
     * @return Package name string or null
     */
    public static String getCurrentPackageName() {
        String focusedApp = getCurrentFocusedApp();
        if (focusedApp != null && focusedApp.contains("Package: ")) {
            return focusedApp.split(",")[0].replace("Package: ", "").trim();
        }
        return null;
    }

    /**
     * Get activity name only from currently focused app
     * 
     * @return Activity name string or null
     */
    public static String getCurrentActivityName() {
        String focusedApp = getCurrentFocusedApp();
        if (focusedApp != null && focusedApp.contains("Activity: ")) {
            return focusedApp.split("Activity: ")[1].trim();
        }
        return null;
    }

    /**
     * List all installed packages on the device
     * 
     * @return List of package names
     */
    public static List<String> getAllInstalledPackages() {
        List<String> packages = new ArrayList<>();
        try {
            String[] command = { "adb", "shell", "pm", "list", "packages" };
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("package:")) {
                    packages.add(line.replace("package:", "").trim());
                }
            }

            process.waitFor();
            reader.close();
        } catch (IOException | InterruptedException e) {
            System.err.println("Error getting installed packages: " + e.getMessage());
        }
        return packages;
    }

    /**
     * Search for packages containing specific text
     * 
     * @param searchText Text to search for in package names
     * @return List of matching package names
     */
    public static List<String> searchPackages(String searchText) {
        List<String> allPackages = getAllInstalledPackages();
        List<String> matchingPackages = new ArrayList<>();

        for (String packageName : allPackages) {
            if (packageName.toLowerCase().contains(searchText.toLowerCase())) {
                matchingPackages.add(packageName);
            }
        }
        return matchingPackages;
    }

    /**
     * Get package information from APK file using aapt
     * 
     * @param apkPath Path to the APK file
     * @return Package information string
     */
    public static String getPackageFromAPK(String apkPath) {
        try {
            // Try aapt2 first, then fall back to aapt
            String[] commands = {
                    "aapt2 dump badging " + apkPath,
                    "aapt dump badging " + apkPath
            };

            for (String cmd : commands) {
                try {
                    String[] command = cmd.split("\\s+");
                    Process process = Runtime.getRuntime().exec(command);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("package:")) {
                            return extractPackageFromAaptOutput(line);
                        }
                    }

                    process.waitFor();
                    reader.close();
                    break; // If command succeeded, don't try the next one
                } catch (IOException e) {
                    // Continue to next command
                    continue;
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Error getting package from APK: " + e.getMessage());
        }
        return null;
    }

    /**
     * Extract package name from aapt output
     * 
     * @param aaptLine Line from aapt output
     * @return Formatted package information
     */
    private static String extractPackageFromAaptOutput(String aaptLine) {
        // Example: package: name='com.example.app' versionCode='1' versionName='1.0'
        Pattern pattern = Pattern.compile("name='([^']+)'.*versionCode='([^']+)'.*versionName='([^']+)'");
        Matcher matcher = pattern.matcher(aaptLine);

        if (matcher.find()) {
            return String.format("Package: %s, VersionCode: %s, VersionName: %s",
                    matcher.group(1), matcher.group(2), matcher.group(3));
        }
        return aaptLine;
    }

    /**
     * Get all running activities
     * 
     * @return List of running activities
     */
    public static List<String> getRunningActivities() {
        List<String> activities = new ArrayList<>();
        try {
            String[] command = { "adb", "shell", "dumpsys", "activity", "activities" };
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Run #") || line.contains("mResumedActivity")) {
                    activities.add(line.trim());
                }
            }

            process.waitFor();
            reader.close();
        } catch (IOException | InterruptedException e) {
            System.err.println("Error getting running activities: " + e.getMessage());
        }
        return activities;
    }

    /**
     * Check if ADB is available and device is connected
     * 
     * @return true if ADB is working and device is connected
     */
    public static boolean isAdbAvailable() {
        try {
            String[] command = { "adb", "devices" };
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            boolean deviceFound = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("device") && !line.contains("List of devices")) {
                    deviceFound = true;
                    break;
                }
            }

            process.waitFor();
            reader.close();
            return deviceFound;
        } catch (IOException | InterruptedException e) {
            return false;
        }
    }

    /**
     * Get connected devices
     * 
     * @return List of connected device UDIDs
     */
    public static List<String> getConnectedDevices() {
        List<String> devices = new ArrayList<>();
        try {
            String[] command = { "adb", "devices" };
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("device") && !line.contains("List of devices")) {
                    String[] parts = line.split("\\s+");
                    if (parts.length >= 2) {
                        devices.add(parts[0]); // Device UDID
                    }
                }
            }

            process.waitFor();
            reader.close();
        } catch (IOException | InterruptedException e) {
            System.err.println("Error getting connected devices: " + e.getMessage());
        }
        return devices;
    }

    /**
     * Print comprehensive app information
     */
    public static void printAppInfo() {
        System.out.println("🔍 App Package Discovery Utility");
        System.out.println("==================================");

        // Check ADB availability
        if (!isAdbAvailable()) {
            System.out.println("❌ ADB not available or no devices connected");
            return;
        }

        // Connected devices
        System.out.println("📱 Connected Devices:");
        List<String> devices = getConnectedDevices();
        for (String device : devices) {
            System.out.println("   - " + device);
        }

        // Current focused app
        System.out.println("\n🎯 Current Focused App:");
        String currentApp = getCurrentFocusedApp();
        if (currentApp != null) {
            System.out.println("   " + currentApp);
        } else {
            System.out.println("   No app currently focused");
        }

        // Package name only
        String packageName = getCurrentPackageName();
        if (packageName != null) {
            System.out.println("   📦 Package Only: " + packageName);
        }

        // Activity name only
        String activityName = getCurrentActivityName();
        if (activityName != null) {
            System.out.println("   🎬 Activity Only: " + activityName);
        }

        System.out.println("\n📋 Total Installed Packages: " + getAllInstalledPackages().size());
    }

    /**
     * Main method for testing the utility
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            String command = args[0];

            switch (command.toLowerCase()) {
                case "current":
                    System.out.println("Current App: " + getCurrentFocusedApp());
                    break;
                case "package":
                    System.out.println("Package: " + getCurrentPackageName());
                    break;
                case "activity":
                    System.out.println("Activity: " + getCurrentActivityName());
                    break;
                case "search":
                    if (args.length > 1) {
                        List<String> results = searchPackages(args[1]);
                        System.out.println("Search results for '" + args[1] + "':");
                        for (String pkg : results) {
                            System.out.println("  - " + pkg);
                        }
                    } else {
                        System.out.println("Please provide search term");
                    }
                    break;
                case "devices":
                    System.out.println("Connected devices:");
                    for (String device : getConnectedDevices()) {
                        System.out.println("  - " + device);
                    }
                    break;
                case "apk":
                    if (args.length > 1) {
                        System.out.println("APK Info: " + getPackageFromAPK(args[1]));
                    } else {
                        System.out.println("Please provide APK path");
                    }
                    break;
                default:
                    printAppInfo();
            }
        } else {
            printAppInfo();
        }
    }
}
