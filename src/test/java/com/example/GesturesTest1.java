package com.example;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class GesturesTest1 extends BaseTest {

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        options.setApp(System.getProperty("user.dir") + "/src/main/resources/ApiDemos-debug.apk");
        driver = new AndroidDriver(URI.create("http://127.0.0.1:4723").toURL(), options);

    }

    @Test
    public void longClickAtCenterOfTheElement() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        WebElement element = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
        // Java
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(), // key value pair
                "duration", 1000)); // key value pair
    }

    @Test
    public void longClickAtCoordinatesOfTheElement() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "x", 226,
                "y", 637,
                "duration", 1000)); // key value pair
    }

    @Test
    public void clickGestureTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        WebElement element = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
        // Java
        ((JavascriptExecutor) driver).executeScript("mobile: clickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId())); // key value pair

    }

    @Test
    public void dragGestureTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        WebElement element = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
        // Java
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "endX", 715,
                "endY", 668));

    }

    @Test
    public void pinchOpenGestureTest() throws InterruptedException {

        // Navigate to the Graphics demo
        driver.findElement(By.xpath("//android.widget.TextView[@text='Graphics']")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Vertices\"))"))
                .click();

        // Wait for the graphics to load
        Thread.sleep(2000);

        // Find the graphics element
        WebElement graphicsElement = driver.findElement(By.id("android:id/content"));

        // Perform pinch open gesture
        Map<String, Object> params = new HashMap<>();
        params.put("elementId", ((RemoteWebElement) graphicsElement).getId());
        params.put("percent", 0.75);

        driver.executeScript("mobile: pinchOpenGesture", params);

        // Wait to see the result
        Thread.sleep(5000);
    }

    @Test
    public void pinchCloseGestureTest() throws InterruptedException {
        // Navigate to the Graphics demo
        driver.findElement(By.xpath("//android.widget.TextView[@text='Graphics']")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"FingerPaint\"))"))
                .click();

        // Wait for the graphics to load
        Thread.sleep(2000);

        // Find the graphics element
        WebElement graphicsElement = driver.findElement(By.id("android:id/content"));

        // Perform pinch close gesture
        Map<String, Object> params = new HashMap<>();
        params.put("elementId", ((RemoteWebElement) graphicsElement).getId());
        params.put("percent", 0.50); // Adjust the percent as needed

        driver.executeScript("mobile: pinchCloseGesture", params);

        // Wait to see the result
        Thread.sleep(5000);
    }

    @Test
    public void swipeLeftGestureTest() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
        Thread.sleep(2000);
        WebElement firstPhoto = driver
                .findElement(AppiumBy.xpath("(//android.widget.ImageView[@class='android.widget.ImageView'])[1]"));
        // Java
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) firstPhoto).getId(),
                "direction", "left",
                "percent", 0.75));
    }

    @Test
    public void swipeRightGestureTest() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        Thread.sleep(3000);
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
        Thread.sleep(2000);

        // swipe left
        WebElement firstPhoto = driver
                .findElement(AppiumBy.xpath("(//android.widget.ImageView[@class='android.widget.ImageView'])[1]"));
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) firstPhoto).getId(),
                "direction", "left",
                "percent", 0.75));

        // swipe right
        WebElement secondPhoto = driver
                .findElement(AppiumBy.xpath("(//android.widget.ImageView[@class='android.widget.ImageView'])[2]"));
        // Java
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) secondPhoto).getId(),
                "direction", "right",
                "percent", 0.75));
    }

    @Test
    public void swipeUpGestureTest() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        Thread.sleep(3000);
        WebElement element = driver.findElement(AppiumBy.id("android:id/list"));
        // Java
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                // "left", 139, "top", 2771, "width", 200, "height", 200,
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", "up",
                "percent", 0.75));
    }

    @Test
    public void swipeDownGestureTest() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        Thread.sleep(3000);

        // swipe up
        WebElement element = driver.findElement(AppiumBy.id("android:id/list"));
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                // "left", 139, "top", 2771, "width", 200, "height", 200,
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", "up",
                "percent", 0.75));

        // swipe down
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                // "left", 139, "top", 2771, "width", 200, "height", 200,
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", "down",
                "percent", 0.75));
    }

    @Test
    public void scrollGestureByBoundingAreaTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        // Scrolling by the bounding area
        @SuppressWarnings("unused")
        boolean canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
                ImmutableMap.of(
                        "left", 100, "top", 100, "width", 200, "height", 200, // scroll by this bound area defined by
                                                                              // ,200,200
                        "direction", "down",
                        "percent", 1.0));
    }

    @Test
    public void scrollGestureByElementAreaTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        WebElement element = driver.findElement(AppiumBy.accessibilityId("Animation"));
        // Scrolling by the element Animantion in this case
        @SuppressWarnings("unused")
        boolean canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) element).getId(),
                        "direction", "down",
                        "percent", 1.0));
    }

    @Test
    public void scrollGestureAllTheWayDownTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        // Scrolling all the way to the bottom of the page
        boolean canScrollMore = true;
        while (canScrollMore) {
             canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
                    ImmutableMap.of(
                        "left", 100, "top", 100, "width", 200, "height", 200, 
                            "direction", "down",
                            "percent", 1.0));
        }
    }

}
