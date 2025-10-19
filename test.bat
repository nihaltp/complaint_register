@echo off
setlocal enabledelayedexpansion

:: Create logs directory
if not exist logs mkdir logs
del logs\*.log >nul 2>&1

echo ============================================================
echo Starting Java Quality Checks...
echo ============================================================

:: -------------------- COMPILE --------------------
echo [1/6] Compiling Java files...
mkdir bin 2>nul
(for /r %%f in (*.java) do @echo %%f) > logs\java_files.txt

cmd /c "javac -d bin @logs\java_files.txt >logs\compile.log 2>&1"
if %errorlevel% neq 0 (
    echo [FAIL] Compilation failed. Check logs\compile.log
) else (
    echo [PASS] Compilation successful.
)
echo.

:: -------------------- RUN UNIT TESTS --------------------
echo [2/6] Running Unit Tests (mvn test)...
cmd /c "mvn -B test >logs\tests.log 2>&1"
if %errorlevel% neq 0 (
    echo [FAIL] Tests failed. Check logs\tests.log
) else (
    echo [PASS] All tests passed.
)
echo.

:: -------------------- CHECKSTYLE --------------------
echo [3/6] Running Checkstyle...
cmd /c "mvn checkstyle:check >logs\checkstyle.log 2>&1"
if %errorlevel% neq 0 (
    echo [FAIL] Checkstyle violations found. Check logs\checkstyle.log
) else (
    echo [PASS] No Checkstyle violations.
)
echo.

:: -------------------- SPOTBUGS --------------------
echo [4/6] Running SpotBugs...
cmd /c "mvn spotbugs:check -Dspotbugs.skip=true >logs\spotbugs.log 2>&1" 
:: remove -Dspotbugs.skip=true to enable SpotBugs check
if %errorlevel% neq 0 (
    echo [FAIL] SpotBugs found issues. Check logs\spotbugs.log
) else (
    echo [PASS] No SpotBugs issues found.
)
echo.

:: -------------------- SPOTLESS --------------------
echo [5/6] Verifying code formatting (Spotless)...
cmd /c "mvn spotless:check >logs\spotless.log 2>&1"
if %errorlevel% neq 0 (
    echo [FAIL] Formatting issues detected. Check logs\spotless.log
) else (
    echo [PASS] Code formatting is correct.
)
echo.

:: -------------------- RUN MAIN --------------------
echo [6/6] Running main class...
cmd /c "if not exist out mkdir out"

:: Generate file list without quotes
cmd /c "(for /r %%f in (*.java) do @echo %%f) > logs\java_files.txt"

:: Compile all Java files
cmd /c "javac -d out @logs\java_files.txt >logs\runmain_compile.log 2>&1"
if %errorlevel% neq 0 (
    echo [FAIL] Main compilation failed. Check logs\runmain_compile.log
) else (
    echo [PASS] Main compilation successful.
)

:: Run the main class
set MAIN_CLASS=Main
cmd /c "java -cp out %MAIN_CLASS% >logs\runmain.log 2>&1"
if %errorlevel% neq 0 (
    echo [WARN] Main class exited with errors. Check logs\runmain.log
) else (
    echo [PASS] Main class executed successfully.
)
echo.

echo ============================================================
echo All steps completed. Check logs folder for details.
echo ============================================================
