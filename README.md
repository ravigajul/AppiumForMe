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
File->Settings->AndroiSDK --> Next
ANDROID_HOME = C:\Users\<username>\AppData\Local\Android\Sdk   
PATH: %ANDROID_HOME%\platform-tools;%ANDROID_HOME%\tools%ANDROID_HOME%\tools\bin  
In the latest version if the above folders are unavailable set the below path.  
PATH : %ANDROID_HOME%\platform-tools;%ANDROID_HOME%\cmdline-tools  

## Check Android Emulator is checked
Tools->SDK Manager -> SDKTools(Tab) -...Ensure Android Emulator is selected and installed also ADroid SDK Paltform tools is selected.
