@echo off
setlocal enabledelayedexpansion

:: Create bin directory if missing
if not exist bin mkdir bin

:: Gather all .java files recursively
set SOURCES=
for /r %%f in (*.java) do (
    set SOURCES=!SOURCES! "%%f"
)

:: Compile all sources at once
echo Compiling sources...
javac -d bin -cp bin !SOURCES!
if errorlevel 1 (
    echo Compilation failed. Exiting...
    exit /b 1
)

:: Run Main class
echo.
echo Running Main
echo.
java -cp bin Main
