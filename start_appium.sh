#!/bin/bash

# Set Android SDK environment
export ANDROID_HOME=/Users/GajulRa/Library/Android/sdk
export ANDROID_SDK_ROOT=/Users/GajulRa/Library/Android/sdk
export PATH=$PATH:$ANDROID_HOME/platform-tools:$ANDROID_HOME/tools

# Start Appium server on port 4723
echo "Starting Appium server on port 4723..."
appium --port 4723 --use-drivers uiautomator2 > appium_4723.log 2>&1 &
echo $! > appium_4723.pid
echo "Appium server started on port 4723 (PID: $(cat appium_4723.pid))"

# Start Appium server on port 4724
echo "Starting Appium server on port 4724..."
appium --port 4724 --use-drivers uiautomator2 > appium_4724.log 2>&1 &
echo $! > appium_4724.pid
echo "Appium server started on port 4724 (PID: $(cat appium_4724.pid))"

echo "Both Appium servers are running."
echo "Logs: appium_4723.log and appium_4724.log"
echo "To stop servers, run: ./stop_appium.sh"
