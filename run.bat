@echo off
setlocal enabledelayedexpansion

:: Run style formatter
cmd /c "mvn spotless:apply"

echo Compiling sources...

REM Create the bin directory if it doesn't exist
if not exist bin mkdir bin

REM --- EDITED: This section now finds all .java files first ---
REM Gather all .java file paths into a single variable
set SOURCES=
for /r %%f in (*.java) do (
    set SOURCES=!SOURCES! "%%f"
)
REM ---------------------------------------------------------

REM Compile all sources at once using the generated list of files.
REM This now correctly includes the library path for the database driver.
javac -cp ".;lib/*" -d bin !SOURCES!

REM Check if compilation failed
if %errorlevel% neq 0 (
    echo Compilation failed. Exiting...
    pause
    exit /b %errorlevel%
)

echo Running Main...
REM Run the main class, including both the library and bin folders in the classpath.
java -cp ".;lib/*;bin" Main

