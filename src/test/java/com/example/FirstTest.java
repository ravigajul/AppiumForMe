package com.example;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class FirstTest extends BaseTest {

   @Test
   public void firstTestOnEmulator1() {
      System.out.println("🔵 Starting test on Emulator 1...");
      driver1.findElement(AppiumBy.accessibilityId("Animation")).click();
      driver1.findElement(AppiumBy.accessibilityId("Loading")).click();
      driver1.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(0)"))
            .click();
      System.out.println("🔵 Emulator 1 Session ID: " + driver1.getSessionId());
      System.out.println("✅ Test completed on Emulator 1");
   }

   @Test
   public void firstTestOnEmulator2() {
      System.out.println("🔴 Starting test on Emulator 2...");
      driver2.findElement(AppiumBy.accessibilityId("Animation")).click();
      driver2.findElement(AppiumBy.accessibilityId("Loading")).click();
      driver2.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(0)"))
            .click();
      System.out.println("🔴 Emulator 2 Session ID: " + driver2.getSessionId());
      System.out.println("✅ Test completed on Emulator 2");
   }

   @Test
   public void parallelTest() {
      System.out.println("🚀 Running parallel operations...");

      // Start parallel threads for both emulators
      Thread thread1 = new Thread(() -> {
         System.out.println("🔵 Thread 1 - Navigating to Views on Emulator 1");
         driver1.findElement(AppiumBy.accessibilityId("Views")).click();
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
         }
         driver1.findElement(AppiumBy.accessibilityId("Animation")).click();
         System.out.println("🔵 Thread 1 - Completed Views navigation");
      });

      Thread thread2 = new Thread(() -> {
         System.out.println("🔴 Thread 2 - Navigating to Graphics on Emulator 2");
         driver2.findElement(AppiumBy.accessibilityId("Graphics")).click();
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
         }
         driver2.findElement(AppiumBy.accessibilityId("Arcs")).click();
         System.out.println("🔴 Thread 2 - Completed Graphics navigation");
      });

      // Start both threads
      thread1.start();
      thread2.start();

      // Wait for both to complete
      try {
         thread1.join();
         thread2.join();
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

      System.out.println("✅ Parallel test completed on both emulators!");
   }
}
