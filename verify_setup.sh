#!/bin/bash

echo "Verifying Appium Parallel Testing Setup..."
echo "=============================================="

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

ISSUES_FOUND=0

# Function to print status
print_status() {
    if [ $2 -eq 0 ]; then
        echo -e "${GREEN}[PASS] $1${NC}"
    else
        echo -e "${RED}[FAIL] $1${NC}"
        ISSUES_FOUND=$((ISSUES_FOUND + 1))
    fi
}

print_warning() {
    echo -e "${YELLOW}[WARN] $1${NC}"
}

echo ""
echo "Checking Android SDK Environment..."

# Check ANDROID_HOME
if [ -z "$ANDROID_HOME" ]; then
    print_status "ANDROID_HOME environment variable" 1
    echo "   Set with: export ANDROID_HOME=/Users/\$USER/Library/Android/sdk"
else
    print_status "ANDROID_HOME is set: $ANDROID_HOME" 0
    
    # Check if directory exists
    if [ -d "$ANDROID_HOME" ]; then
        print_status "ANDROID_HOME directory exists" 0
    else
        print_status "ANDROID_HOME directory exists" 1
        echo "   Directory not found: $ANDROID_HOME"
    fi
fi

# Check ANDROID_SDK_ROOT
if [ -z "$ANDROID_SDK_ROOT" ]; then
    print_status "ANDROID_SDK_ROOT environment variable" 1
    echo "   Set with: export ANDROID_SDK_ROOT=/Users/\$USER/Library/Android/sdk"
else
    print_status "ANDROID_SDK_ROOT is set: $ANDROID_SDK_ROOT" 0
fi

echo ""
echo "Checking Required Tools..."

# Check Java
if command -v java &> /dev/null; then
    JAVA_VERSION=$(java -version 2>&1 | head -1 | cut -d'"' -f2)
    print_status "Java is installed: $JAVA_VERSION" 0
else
    print_status "Java is installed" 1
    echo "   Install Java 21 or later"
fi

# Check Maven
if command -v mvn &> /dev/null; then
    MVN_VERSION=$(mvn -version 2>&1 | head -1 | cut -d' ' -f3)
    print_status "Maven is installed: $MVN_VERSION" 0
else
    print_status "Maven is installed" 1
    echo "   Install Maven: brew install maven"
fi

# Check Node.js
if command -v node &> /dev/null; then
    NODE_VERSION=$(node -version)
    print_status "Node.js is installed: $NODE_VERSION" 0
else
    print_status "Node.js is installed" 1
    echo "   Install Node.js: brew install node"
fi

# Check Appium
if command -v appium &> /dev/null; then
    APPIUM_VERSION=$(appium -v)
    print_status "Appium is installed: $APPIUM_VERSION" 0
else
    print_status "Appium is installed" 1
    echo "   Install Appium: npm install -g appium"
fi

# Check ADB
if command -v adb &> /dev/null; then
    ADB_VERSION=$(adb version | head -1)
    print_status "ADB is available: $ADB_VERSION" 0
    
    # Check connected devices
    DEVICES=$(adb devices | grep -c "device$")
    if [ $DEVICES -ge 2 ]; then
        print_status "At least 2 Android devices/emulators connected" 0
        echo "   Connected devices:"
        adb devices | grep "device$" | while read line; do
            echo "     - $line"
        done
    elif [ $DEVICES -eq 1 ]; then
        print_warning "Only 1 device connected (need 2 for parallel testing)"
        adb devices | grep "device$" | while read line; do
            echo "     - $line"
        done
    else
        print_status "Android devices/emulators connected" 1
        echo "   Start at least 2 emulators for parallel testing"
    fi
else
    print_status "ADB is available" 1
    echo "   ADB should be available after setting ANDROID_HOME"
fi

echo ""
echo "Checking Appium Servers..."

# Check if ports are free or have Appium running
PORT_4723=$(lsof -ti:4723 2>/dev/null)
PORT_4724=$(lsof -ti:4724 2>/dev/null)

if [ -n "$PORT_4723" ]; then
    PROCESS_4723=$(ps -p $PORT_4723 -o comm= 2>/dev/null)
    if [[ "$PROCESS_4723" == *"appium"* ]]; then
        print_status "Appium server running on port 4723" 0
    else
        print_warning "Port 4723 is occupied by: $PROCESS_4723"
    fi
else
    print_warning "No process running on port 4723 (Appium server needed)"
fi

if [ -n "$PORT_4724" ]; then
    PROCESS_4724=$(ps -p $PORT_4724 -o comm= 2>/dev/null)
    if [[ "$PROCESS_4724" == *"appium"* ]]; then
        print_status "Appium server running on port 4724" 0
    else
        print_warning "Port 4724 is occupied by: $PROCESS_4724"
    fi
else
    print_warning "No process running on port 4724 (Appium server needed)"
fi

echo ""
echo "Checking Project Files..."

# Check if required files exist
if [ -f "pom.xml" ]; then
    print_status "Maven pom.xml exists" 0
else
    print_status "Maven pom.xml exists" 1
fi

if [ -f "src/main/resources/ApiDemos-debug.apk" ]; then
    print_status "Test APK exists" 0
else
    print_status "Test APK exists" 1
    echo "   File: src/main/resources/ApiDemos-debug.apk"
fi

if [ -f "start_appium.sh" ]; then
    print_status "start_appium.sh script exists" 0
    if [ -x "start_appium.sh" ]; then
        print_status "start_appium.sh is executable" 0
    else
        print_status "start_appium.sh is executable" 1
        echo "   Fix with: chmod +x start_appium.sh"
    fi
else
    print_status "start_appium.sh script exists" 1
fi

echo ""
echo "=============================================="

if [ $ISSUES_FOUND -eq 0 ]; then
    echo -e "${GREEN}Setup verification completed successfully!${NC}"
    echo -e "${GREEN}You should be able to run: ./start_appium.sh && mvn test${NC}"
else
    echo -e "${RED}Found $ISSUES_FOUND issue(s) that need to be fixed.${NC}"
    echo -e "${YELLOW}Please address the issues above before running tests.${NC}"
fi

echo ""
echo "Quick setup commands:"
echo "   1. Set environment variables in ~/.zshrc:"
echo "      export ANDROID_HOME=/Users/\$USER/Library/Android/sdk"
echo "      export ANDROID_SDK_ROOT=/Users/\$USER/Library/Android/sdk"
echo "      export PATH=\$PATH:\$ANDROID_HOME/platform-tools:\$ANDROID_HOME/tools"
echo ""
echo "   2. Reload shell: source ~/.zshrc"
echo "   3. Start emulators (if not running)"
echo "   4. Run: ./start_appium.sh"
echo "   5. Run tests: mvn test"

exit $ISSUES_FOUND
