# AppiumForMe
Appium is a mobile automation testing tool that allows developers to write automated tests for mobile applications across different platforms (iOS, Android, Windows) using a single API. The architecture of Appium consists of several components that work together to execute test scripts on mobile devices.
## Appium Architecture

Here's a brief overview of the Appium architecture:

**Test Script**: The test script is written in a programming language such as Java, Python, Ruby, etc., and it contains the commands and logic for the test.

**Client Libraries**: The client libraries are the language-specific bindings that allow the test script to communicate with the Appium server. These libraries provide an API for the test script to interact with the Appium server.

**Appium Server**: The Appium server is the main component of the Appium architecture. It receives commands from the test script and executes them on the mobile device using the appropriate mobile automation framework (such as UI Automator for Android or XCUITest for iOS).

**Mobile Automation Framework**: The mobile automation framework is a set of tools and libraries that enable Appium to automate the mobile device. These frameworks differ depending on the platform (iOS or Android) and are responsible for interacting with the mobile device to perform actions such as tapping, swiping, typing, and more.

**Mobile Device**: The mobile device is the physical device or emulator on which the app is installed and tested.

**Appium Client**: The Appium client is a tool that provides a user interface for interacting with the Appium server. It allows users to start and stop the Appium server, manage mobile devices, and view logs and reports.

Overall, the Appium architecture is designed to provide a flexible, cross-platform solution for mobile app automation testing that can work with different programming languages and mobile platforms.


## Native
Native mobile apps are developed specifically for a particular mobile platform using the platform's native programming languages and development tools. For example, iOS apps are typically developed using Swift or Objective-C, while Android apps are developed using Java or Kotlin. Native apps are designed to take advantage of the specific features and functionality of the platform they are developed for, resulting in a highly optimized user experience.

## Hybrid
Hybrid mobile apps, on the other hand, are developed using web technologies like HTML, CSS, and JavaScript and are wrapped in a native container that allows them to be installed and run on a mobile device. Hybrid apps can be developed once and deployed across multiple platforms, making them more cost-effective than developing multiple native apps. However, they may not be as optimized for a particular platform as a native app, and may not have access to all of the device's features and functionality.

## Install, Run Appium and uninstall
```node
npm install -g appium
appium
npm uninstall -g appium
```

## Install UIAutomator2 driver using appium cli
```bash
appium driver --help or (-h)
appium driver list
appium driver install uiautomator2
```

## Download appium inspector
https://github.com/appium/appium-inspector

## Install Adroid studio and set env variables
https://developer.android.com/ 


## Check Android Emulator is checked
Tools->SDK Manager -> SDKTools(Tab) -...Ensure Android Emulator is selected and installed also ADroid SDK Paltform tools is selected. 
Select and install Adroid SDK commandline tools

## Appium Driver Managerment

### List Drivers

```bash
appium driver list
appium driver list --installed
appium driver list --updates
```

### Update Drivers

```bash
appium driver update uiautomator2
appium driver update --unsafe 
appium driver update installed
```

### Install Driver

```bash
appium driver install <driver_name>
appium driver install <driver_name@version>
appium driver install --source <source>  --package <name>
source: npm (Default) , github, git, local
```

## List the devices
This displays the udid of the devices runnning. udid can be used in ui inspector to uniquely identify the device.
```bash
adb devices
```

## Desired Capabilities
Desired capabilities in Appium are key-value pairs that define the configuration and settings for an Appium test session. They allow you to specify details about the test environment, device, and application you want to automate. Here are the key points about desired capabilities in Appium:

1. Purpose: Desired capabilities instruct the Appium server about the desired mobile platform, device, and other settings to be used as the test environment.

2. Format: They are represented as key-value pairs encoded in a JSON object.

3. Functionality: Capabilities help configure the Appium server and provide criteria for running automation scripts, such as specifying the environment (emulator or real device), operating system version, and more.

4. Platform-specific: Appium supports both Android and iOS, with some capabilities being common and others specific to each platform.

5. Examples of common capabilities:
   - platformName: Specifies the mobile OS (e.g., "Android" or "iOS")
   - deviceName: Name of the device to be used
   - platformVersion: Version of the mobile OS
   - app: Path to the mobile app to be tested
   - automationName: Name of the driver to be used (e.g., "UiAutomator2" for Android, "XCUITest" for iOS)

6. Usage: Desired capabilities are sent to the Appium server by the Appium client when a new automation session is requested.

7. Flexibility: They allow testers to define precisely what attributes their test session should possess, enabling customization of the test environment.

8. Standardization: Appium follows the W3C WebDriver spec for capabilities, adding its own custom capabilities with the "appium:"

9. Security: It's recommended to use environment variables for storing sensitive information like API keys or credentials when setting up capabilities.

By properly configuring desired capabilities, testers can ensure their Appium tests run in the intended environment with the correct settings, leading to more reliable and efficient mobile app testing.

## AppPackage and AppActivity 
These are two important concepts in Android app development and testing, particularly when working with tools like Appium. Here's a concise explanation of each:

AppPackage:

1. AppPackage is the unique identifier for an Android application.
2. It's essentially the technical name of the app provided by its developers.
3. It's the top-level package under which all the code for the app resides.
4. Examples:
   - YouTube: com.google.android.youtube
   - Facebook: com.facebook.katana
   - WhatsApp: com.whatsapp

AppActivity:

1. AppActivity refers to the different functionalities or screens provided by the app.
2. Each distinct screen or function within an app is represented by an Activity.
3. Every app has a main activity, which is the main screen you see when you launch the app.
4. For example, in WhatsApp, the main activity is the Chats window, while for Facebook, it would be the News Feed.

When using Appium or other testing tools:

1. You need to provide both the AppPackage and AppActivity to launch a specific app and screen.
2. The main activity is typically used when you want to start the app from its primary interface.
3. These details are crucial for setting up automated tests, as they tell Appium which app to open and which screen to start with[1].

To find the AppPackage and AppActivity for an app, you can use ADB (Android Debug Bridge) commands like:

```bash
adb shell dumpsys window displays | grep -E 'mCurrentFocus'
```

or

```bash
adb shell dumpsys window displays | grep -E 'mFocusedApp'
```

These commands will provide you with the necessary information to set up your Appium tests correctly.


## Locator Strategies

1. **ID**:
   ```java
   driver.findElement(AppiumBy.id("element_id"));
   ```

2. **Accessibility ID**:
   ```java
   driver.findElement(AppiumBy.accessibilityId("accessibility_id"));
   ```

3. **Class Name**:
   ```java
   driver.findElements(AppiumBy.className("android.widget.Button"));
   ```

4. **XPath**:
   ```java
   driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='example']"));
   ```

5. **Android UIAutomator**:
   ```java
   driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"example\")"));
   ```

6. **iOS Predicate String**:
   ```java
   driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' AND name CONTAINS 'example'"));
   ```

For the UIAutomator example with scrolling, you would use:

```java
driver.findElement(AppiumBy.androidUIAutomator(
    "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"example\"))"
));
```

7. **Locating by Name**:

```java
// Locating an element by name
WebElement elementByName = driver.findElement(AppiumBy.name("element_name"));
```

8. **Locating by Image**:

Appium supports locating elements by image, which is useful for elements that do not have unique identifiers or are dynamically generated. This requires the image to be stored locally or accessible via a URL.

```java
// Locating an element by image
WebElement elementByImage = driver.findElement(AppiumBy.image("/path/to/image.png"));
```

### Steps to Use Image Locator
1. **Capture the Image**: Take a screenshot of the element you want to locate.
2. **Store the Image**: Save the image in a local directory or make it accessible via a URL.
3. **Use the Image in Appium**: Use the `AppiumBy.image` method to locate the element.

### Example Code for Image Locator
```java
// Example of locating an element using an image
WebElement elementByImage = driver.findElement(AppiumBy.image("/path/to/image.png"));
elementByImage.click();

//Example of locating an element using a base64 image
// import java.util.Base64;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Paths;
try {
   // Read the image file and encode it to Base64
   byte[] fileContent = Files.readAllBytes(Paths.get("/path/to/your/image.png"));
   String base64Image = Base64.getEncoder().encodeToString(fileContent);

   // Use the Base64 encoded image to locate the element
   WebElement element = driver.findElement(AppiumBy.image(base64Image));

   // Perform action on the element
   element.click();

   } catch (IOException e) {
      e.printStackTrace();
   }
```

## Practical Tips for Using Image Locators
- **Ensure Image Quality**: The image used for locating the element should be clear and of high quality to improve accuracy.
- **Image Path**: Use an absolute path or ensure the relative path is correctly set in your project.
- **Performance Considerations**: Locating elements by image can be slower compared to other strategies. Use it when other locators are not feasible.


## Mobile Gestures
https://github.com/appium/appium-uiautomator2-driver
https://github.com/appium/appium-uiautomator2-driver/blob/master/docs/android-mobile-gestures.md

UiAutomator2 provides several extensions that allow to automate popular mobile gesture shortcuts:

1. mobile: dragGesture
2. mobile: flingGesture
3. mobile: doubleClickGesture
4. mobile: clickGesture
5. mobile: longClickGesture
6. mobile: pinchCloseGesture
7. mobile: pinchOpenGesture
8. mobile: swipeGesture
9. mobile: scrollGesture

## Understanding clickGesture 
```java
WebElement element = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
((JavascriptExecutor) driver).executeScript("mobile: clickGesture", ImmutableMap.of(
    "elementId", ((RemoteWebElement) element).getId()));
```

## Components

1. `(JavascriptExecutor) driver`
   - This is a cast of the `driver` object to `JavascriptExecutor` interface.
   - It allows execution of JavaScript code within the context of the current session.

2. `executeScript()`
   - A method of JavascriptExecutor that executes JavaScript in the current context.

3. `"mobile: clickGesture"`
   - This is a special command recognized by Appium for mobile testing.
   - It instructs Appium to perform a click gesture on a mobile element.

4. `ImmutableMap.of()`
   - Creates an immutable map with key-value pairs.
   - In this case, it's creating a map with a single key-value pair.

5. `"elementId"` (key)
   - The key in the map, specifying that we're providing an element ID.

6. `((RemoteWebElement) element).getId()` (value)
   - Casts `element` to `RemoteWebElement` and calls `getId()` to get its unique identifier.

## Purpose

This code is executing a mobile click gesture on a specific element. Here's what it does:

1. It uses JavaScript execution capabilities to run a mobile-specific command.
2. The command `"mobile: clickGesture"` tells Appium to perform a click action.
3. It provides the ID of the element to be clicked, obtained from the `element` object.

## Key-Value Pair

The key-value pair in this context is:
- Key: `"elementId"`
- Value: The ID of the element (obtained from `element.getId()`)

This pair is used to tell Appium which specific element should be clicked.



