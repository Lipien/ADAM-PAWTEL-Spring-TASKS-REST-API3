call runcrud.bat

:servercheck
@echo off
timeout /T 12 /NOBREAK
netstat -na | find "LISTENING" | find /C /I ":8080" > NUL
if "%errorlevel%"=="0" goto :startwebbrowser
echo Tomcat is not running
goto :eof

:eof

:startwebbrowser
start /d "C:\Program Files\Mozilla Firefox" firefox.exe  http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo Cannot start browser
goto fail

:fail
echo.
echo showtasks file error

:end
echo.
echo WebApp has started.