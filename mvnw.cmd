@echo off
setlocal

set "MVNW_DIR=%~dp0"
for %%I in ("%MVNW_DIR%.") do set "BASE_DIR=%%~fI"
set "WRAPPER_PROPERTIES=%BASE_DIR%\.mvn\wrapper\maven-wrapper.properties"

if not exist "%WRAPPER_PROPERTIES%" (
  echo Maven wrapper properties not found: "%WRAPPER_PROPERTIES%"
  exit /b 1
)

for /f "usebackq delims=" %%I in (`powershell -NoProfile -ExecutionPolicy Bypass -Command ^
  "$ErrorActionPreference = 'Stop';" ^
  "$baseDir = [System.IO.Path]::GetFullPath('%BASE_DIR%');" ^
  "$propertiesPath = Join-Path $baseDir '.mvn\wrapper\maven-wrapper.properties';" ^
  "$distributionUrl = (Get-Content $propertiesPath | Where-Object { $_ -match '^distributionUrl=' } | Select-Object -First 1).Split('=', 2)[1];" ^
  "if (-not $distributionUrl) { throw 'distributionUrl not found in maven-wrapper.properties.' }" ^
  "$uri = [Uri]$distributionUrl;" ^
  "$zipName = [System.IO.Path]::GetFileName($uri.AbsolutePath);" ^
  "$zipPath = Join-Path $baseDir ('.mvn\wrapper\' + $zipName);" ^
  "$mavenDirName = [System.IO.Path]::GetFileNameWithoutExtension($zipName) -replace '-bin$', '';" ^
  "$mavenHome = Join-Path $baseDir ('.mvn\wrapper\' + $mavenDirName);" ^
  "if (-not (Test-Path $mavenHome)) {" ^
  "  if (-not (Test-Path $zipPath)) {" ^
  "    Write-Host 'Downloading Maven distribution...' $distributionUrl;" ^
  "    Invoke-WebRequest -Uri $distributionUrl -OutFile $zipPath;" ^
  "  }" ^
  "  Write-Host 'Extracting Maven distribution...' $zipPath;" ^
  "  Expand-Archive -Path $zipPath -DestinationPath (Join-Path $baseDir '.mvn\wrapper') -Force;" ^
  "}" ^
  "$mvnCmd = Join-Path $mavenHome 'bin\mvn.cmd';" ^
  "if (-not (Test-Path $mvnCmd)) { throw ('mvn.cmd not found at ' + $mvnCmd) }" ^
  "Write-Output $mvnCmd"`) do set "MVN_CMD=%%I"

if not exist "%MVN_CMD%" (
  echo Maven executable not found: "%MVN_CMD%"
  exit /b 1
)

call "%MVN_CMD%" %*
set "EXIT_CODE=%ERRORLEVEL%"
endlocal & exit /b %EXIT_CODE%
