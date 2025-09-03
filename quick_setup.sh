#!/bin/bash

echo "Quick Setup for Appium Parallel Testing"
echo "=========================================="

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}This script will help you set up Android SDK environment variables.${NC}"
echo ""

# Detect shell
SHELL_CONFIG=""
if [ -f "$HOME/.zshrc" ]; then
    SHELL_CONFIG="$HOME/.zshrc"
    SHELL_NAME="zsh"
elif [ -f "$HOME/.bash_profile" ]; then
    SHELL_CONFIG="$HOME/.bash_profile"
    SHELL_NAME="bash"
elif [ -f "$HOME/.bashrc" ]; then
    SHELL_CONFIG="$HOME/.bashrc"
    SHELL_NAME="bash"
else
    echo -e "${RED}Could not find shell configuration file${NC}"
    exit 1
fi

echo -e "${GREEN}Detected shell: $SHELL_NAME${NC}"
echo -e "${GREEN}Configuration file: $SHELL_CONFIG${NC}"
echo ""

# Check if Android SDK exists
ANDROID_SDK_PATH="/Users/$USER/Library/Android/sdk"
if [ ! -d "$ANDROID_SDK_PATH" ]; then
    echo -e "${YELLOW}Android SDK not found at: $ANDROID_SDK_PATH${NC}"
    echo "Please install Android Studio first, or update the path below."
    echo ""
    read -p "Enter Android SDK path (or press Enter for default): " USER_SDK_PATH
    if [ -n "$USER_SDK_PATH" ]; then
        ANDROID_SDK_PATH="$USER_SDK_PATH"
    fi
fi

if [ ! -d "$ANDROID_SDK_PATH" ]; then
    echo -e "${RED}Android SDK path does not exist: $ANDROID_SDK_PATH${NC}"
    echo "Please install Android Studio and Android SDK first."
    exit 1
fi

echo -e "${GREEN}Android SDK found at: $ANDROID_SDK_PATH${NC}"
echo ""

# Check if environment variables are already set
if grep -q "ANDROID_HOME" "$SHELL_CONFIG" && grep -q "ANDROID_SDK_ROOT" "$SHELL_CONFIG"; then
    echo -e "${YELLOW}Android environment variables are already configured in $SHELL_CONFIG${NC}"
    echo ""
    read -p "Do you want to update them? (y/N): " UPDATE_VARS
    if [[ ! "$UPDATE_VARS" =~ ^[Yy]$ ]]; then
        echo "Skipping environment variable setup."
        echo ""
        source "$SHELL_CONFIG"
        exec ./verify_setup.sh
        exit 0
    fi
fi

# Backup existing config
cp "$SHELL_CONFIG" "$SHELL_CONFIG.backup.$(date +%Y%m%d_%H%M%S)"
echo -e "${GREEN}Backed up existing configuration${NC}"

# Add environment variables
echo "" >> "$SHELL_CONFIG"
echo "# Android SDK Environment Variables (Added by setup script)" >> "$SHELL_CONFIG"
echo "export ANDROID_HOME=\"$ANDROID_SDK_PATH\"" >> "$SHELL_CONFIG"
echo "export ANDROID_SDK_ROOT=\"$ANDROID_SDK_PATH\"" >> "$SHELL_CONFIG"
echo "export PATH=\$PATH:\$ANDROID_HOME/platform-tools:\$ANDROID_HOME/tools" >> "$SHELL_CONFIG"

echo -e "${GREEN}Added Android SDK environment variables to $SHELL_CONFIG${NC}"
echo ""

# Source the configuration
source "$SHELL_CONFIG"

echo -e "${GREEN}Environment variables loaded${NC}"
echo "   ANDROID_HOME: $ANDROID_HOME"
echo "   ANDROID_SDK_ROOT: $ANDROID_SDK_ROOT"
echo ""

# Make scripts executable
chmod +x start_appium.sh stop_appium.sh verify_setup.sh 2>/dev/null
echo -e "${GREEN}Made scripts executable${NC}"
echo ""

echo -e "${BLUE}Running setup verification...${NC}"
echo ""
./verify_setup.sh

echo ""
echo -e "${YELLOW}Next Steps:${NC}"
echo "1. Start your Android emulators if they're not running"
echo "2. Run: ./start_appium.sh"
echo "3. Run tests: mvn test"
echo "4. Stop servers when done: ./stop_appium.sh"
echo ""
echo -e "${GREEN}Setup complete!${NC}"
