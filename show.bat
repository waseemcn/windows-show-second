@echo off

reg add "HKCU\Software\Microsoft\Windows\CurrentVersion\Explorer\Advanced" /v ShowSecondsInSystemClock /t REG_DWORD /d 1 /f
taskkill /f /im explorer.exe
timeout /t 5 /nobreak >nul
start explorer.exe
