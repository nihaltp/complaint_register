@echo off
setlocal enabledelayedexpansion

:: Run style formatter
cmd /c "mvn spotless:apply"

:: Create bin directory if missing
if not exist bin mkdir bin

:: Gather all .java files recursively
set "SOURCES="
for /r %%f in (*.java) do (
    set "SOURCES=!SOURCES! "%%f""
)

REM Compile all the found source files, including the library path.
javac -cp ".;lib/*" -d bin %SOURCES%

REM Check if compilation failed
if %errorlevel% neq 0 (
    echo Compilation failed. Exiting...
    REM Pause to allow user to see the error before the window closes
    pause
    exit /b %errorlevel%
)

echo Running Main...
REM Run the main class, making sure to include both the lib and bin folders in the classpath.
java -cp ".;lib/*;bin" Main

