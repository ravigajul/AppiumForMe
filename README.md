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


## Appium Driver Commands

## APKInfo app
It s a utility application for Android devices that provides detailed information about installed apps and APK files. Here are the key points about APKInfo:

1. It's a free tool specifically developed for Android devices.

2. The app allows users to view detailed information about installed applications on their Android device, including:

   - Package name
   - Version number
   - Installation date
   - Last update date
   - App permissions
   - App size
   - Target SDK version
   - Minimum SDK version required

3. APKInfo can also analyze APK files that are not yet installed on the device, providing information about their contents and structure.

4. It's useful for developers, power users, and those interested in understanding more about the apps on their devices.

5. The app can help users identify potentially suspicious or unnecessary permissions requested by apps, which can be helpful for security and privacy considerations.

6. APKInfo does not modify or install/uninstall apps; it's purely an information tool.

7. It can be particularly useful for developers testing their own apps or for users who want to verify the authenticity and details of apps before installation.

While APKInfo is a useful tool, it's important to note that users should always exercise caution when dealing with APK files and only download apps from trusted sources like the Google Play Store to avoid potential security risks.

---

## 🚀 Parallel Testing with Multiple Emulators

This project supports running Appium tests in parallel across multiple Android emulators, enabling faster test execution and improved CI/CD pipeline efficiency.

### 🏗️ Architecture Overview

```
BaseTest.java
├── driver1 (emulator-5554) → Appium Server Port 4723
├── driver2 (emulator-5556) → Appium Server Port 4724
├── initialize() → Sets up both drivers
└── destroy() → Cleans up both sessions

FirstTest.java
├── firstTestOnEmulator1() → Uses driver1
├── firstTestOnEmulator2() → Uses driver2
└── parallelTest() → Uses both drivers in parallel threads
```

### 📋 Prerequisites

1. **Multiple Android Emulators**: Ensure you have at least 2 emulators created and running
2. **Appium Installation**: Latest version of Appium installed globally
3. **UIAutomator2 Driver**: Installed via Appium CLI
4. **Sufficient System Resources**: Each emulator requires dedicated CPU and memory

### 🔧 Setup Instructions

#### Step 1: Create and Start Multiple Emulators

```bash
# List available AVDs
avd list

# Start first emulator (will get port 5554)
emulator -avd <Your_AVD_Name_1> &

# Start second emulator (will get port 5556)
emulator -avd <Your_AVD_Name_2> &

# Verify both emulators are connected
adb devices
```

Expected output:
```
List of devices attached
emulator-5554   device
emulator-5556   device
```

#### Step 2: Start Multiple Appium Servers

**Terminal 1 - First Appium Server:**
```bash
appium server --port 4723 --session-override
```

**Terminal 2 - Second Appium Server:**
```bash
appium server --port 4724 --session-override
```

#### Step 3: Verify Server Status

```bash
# Check if both servers are running
curl http://localhost:4723/status
curl http://localhost:4724/status

# Alternative check
lsof -i :4723
lsof -i :4724
```

### 🧪 Test Execution

#### Run Individual Emulator Tests

```bash
# Test on Emulator 1 only
mvn test -Dtest=FirstTest#firstTestOnEmulator1

# Test on Emulator 2 only
mvn test -Dtest=FirstTest#firstTestOnEmulator2

# Test parallel operations
mvn test -Dtest=FirstTest#parallelTest
```

#### Run All Tests (Sequential + Parallel)

```bash
# Run complete test suite
mvn test -Dtest=FirstTest

# Results will show:
# Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
```

### 📁 Project Structure

```
src/test/java/com/example/
├── BaseTest.java          # Dual driver management
├── FirstTest.java         # Parallel test examples
├── DriverCommands.java    # Driver utility methods
├── GesturesTest1.java     # Gesture testing
└── GesTuresTest2.java     # Additional gestures

src/main/resources/
└── ApiDemos-debug.apk     # Test application
```

### 🔧 Configuration Details

#### BaseTest.java - Dual Driver Setup

```java
public class BaseTest {
    protected AndroidDriver driver1; // emulator-5554
    protected AndroidDriver driver2; // emulator-5556
    
    @BeforeMethod
    public void initialize() {
        // Initialize both drivers with different configurations
        // Port 4723 for driver1, Port 4724 for driver2
    }
    
    @AfterMethod
    public void destroy() {
        // Clean up both sessions properly
    }
}
```

#### Key Configuration Parameters

| Parameter | Emulator 1 | Emulator 2 |
|-----------|------------|------------|
| **Appium Server Port** | 4723 | 4724 |
| **Device UDID** | emulator-5554 | emulator-5556 |
| **Device Name** | Emulator1 | Emulator2 |
| **App Path** | Same APK for both | Same APK for both |

### 🏃‍♂️ Parallel Test Examples

#### Sequential Testing
```java
@Test
public void firstTestOnEmulator1() {
    // Runs only on emulator-5554
    driver1.findElement(AppiumBy.accessibilityId("Animation")).click();
}

@Test  
public void firstTestOnEmulator2() {
    // Runs only on emulator-5556
    driver2.findElement(AppiumBy.accessibilityId("Animation")).click();
}
```

#### True Parallel Testing
```java
@Test
public void parallelTest() {
    Thread thread1 = new Thread(() -> {
        // Emulator 1 operations
        driver1.findElement(AppiumBy.accessibilityId("Views")).click();
    });
    
    Thread thread2 = new Thread(() -> {
        // Emulator 2 operations  
        driver2.findElement(AppiumBy.accessibilityId("Graphics")).click();
    });
    
    // Start both threads simultaneously
    thread1.start();
    thread2.start();
    
    // Wait for completion
    thread1.join();
    thread2.join();
}
```

### 🐛 Troubleshooting

#### Common Issues and Solutions

**1. Port Already in Use**
```bash
# Kill existing Appium processes
pkill -f appium

# Wait and restart
sleep 2
appium server --port 4723 --session-override &
appium server --port 4724 --session-override &
```

**2. Emulator Not Responding**
```bash
# Restart ADB
adb kill-server
adb start-server

# Check device status
adb devices
```

**3. Session Creation Failures**
- Ensure both Appium servers are running
- Verify emulator UDIDs match configuration
- Check available system memory for multiple emulators

**4. App Installation Issues**
```bash
# Manually install APK on both emulators
adb -s emulator-5554 install src/main/resources/ApiDemos-debug.apk
adb -s emulator-5556 install src/main/resources/ApiDemos-debug.apk
```

### 📊 Performance Considerations

#### Resource Requirements
- **CPU**: Multi-core processor recommended (4+ cores)
- **RAM**: Minimum 8GB, 16GB+ recommended
- **Storage**: SSD recommended for faster emulator performance

#### Optimization Tips
1. **Emulator Settings**: Allocate appropriate RAM (2-4GB per emulator)
2. **Parallel Execution**: Limit concurrent emulators based on system capacity
3. **Test Design**: Keep parallel tests independent to avoid conflicts
4. **Cleanup**: Properly terminate sessions to free resources

### 🔄 CI/CD Integration

#### Maven Surefire Configuration
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.22.1</version>
    <configuration>
        <parallel>methods</parallel>
        <threadCount>2</threadCount>
        <includes>
            <include>**/*Test.java</include>
        </includes>
    </configuration>
</plugin>
```

#### Jenkins Pipeline Example
```groovy
pipeline {
    agent any
    stages {
        stage('Setup Emulators') {
            steps {
                sh 'emulator -avd API_35_1 &'
                sh 'emulator -avd API_35_2 &'
                sh 'sleep 30' // Wait for emulators to boot
            }
        }
        stage('Start Appium Servers') {
            parallel {
                stage('Server 1') {
                    steps {
                        sh 'appium server --port 4723 --session-override &'
                    }
                }
                stage('Server 2') {
                    steps {
                        sh 'appium server --port 4724 --session-override &'
                    }
                }
            }
        }
        stage('Run Parallel Tests') {
            steps {
                sh 'mvn test -Dtest=FirstTest'
            }
        }
    }
    post {
        always {
            sh 'pkill -f appium'
            sh 'pkill -f emulator'
        }
    }
}
```

### 📈 Scaling to More Devices

To add additional emulators and parallel execution:

1. **Start More Emulators**:
   ```bash
   emulator -avd API_35_3 &  # Will get emulator-5558
   emulator -avd API_35_4 &  # Will get emulator-5560
   ```

2. **Launch Additional Appium Servers**:
   ```bash
   appium server --port 4725 --session-override &
   appium server --port 4726 --session-override &
   ```

3. **Extend BaseTest Class**:
   ```java
   protected AndroidDriver driver3; // emulator-5558
   protected AndroidDriver driver4; // emulator-5560
   ```

4. **Update Test Methods**: Add corresponding test methods for new drivers

### ✅ Best Practices

1. **Independent Tests**: Ensure tests don't interfere with each other
2. **Resource Management**: Monitor system resources during parallel execution
3. **Error Handling**: Implement proper exception handling for device failures
4. **Logging**: Use distinct logging for each emulator/driver
5. **Test Data**: Use separate test data sets for parallel tests
6. **Cleanup**: Always clean up sessions in @AfterMethod
7. **Timeouts**: Set appropriate timeouts for parallel operations

### 🎯 Benefits

- **Faster Execution**: 2x speed improvement with 2 emulators
- **Better Coverage**: Test different scenarios simultaneously  
- **CI/CD Efficiency**: Reduced pipeline execution time
- **Resource Utilization**: Better use of available system resources
- **Scalability**: Easy to add more devices as needed

---

**Happy Parallel Testing! 🚀📱📱**

