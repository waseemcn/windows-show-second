@echo off
set value=0
if not "%~1"=="" set value=%1
reg add "HKCU\Software\Microsoft\Windows\CurrentVersion\Explorer\Advanced" /v ShowSecondsInSystemClock /t REG_DWORD /d %value% /f
taskkill /f /im explorer.exe
timeout /t 5 /nobreak >nul
start explorer.exe
