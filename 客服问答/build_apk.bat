@echo off
set JAVA_HOME=C:\jbr_link
set ANDROID_HOME=C:\Users\Administrator\AppData\Local\Android\Sdk
set PATH=C:\jbr_link\bin;C:\Users\Administrator\AppData\Local\Android\Sdk\platform-tools;%PATH%
cd /d C:\Users\Administrator\Desktop\DifyApp
echo Building APK...
call gradlew.bat assembleDebug
echo DONE=%ERRORLEVEL%
