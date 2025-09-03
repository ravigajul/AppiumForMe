#!/bin/bash

# Stop Appium servers
echo "Stopping Appium servers..."

if [ -f appium_4723.pid ]; then
    PID=$(cat appium_4723.pid)
    if kill -0 $PID 2>/dev/null; then
        kill $PID
        echo "Stopped Appium server on port 4723 (PID: $PID)"
    else
        echo "Appium server on port 4723 was not running"
    fi
    rm -f appium_4723.pid
fi

if [ -f appium_4724.pid ]; then
    PID=$(cat appium_4724.pid)
    if kill -0 $PID 2>/dev/null; then
        kill $PID
        echo "Stopped Appium server on port 4724 (PID: $PID)"
    else
        echo "Appium server on port 4724 was not running"
    fi
    rm -f appium_4724.pid
fi

# Clean up any remaining Appium processes
pkill -f "appium.*472[34]" 2>/dev/null

echo "All Appium servers stopped."
