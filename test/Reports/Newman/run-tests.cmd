@echo off
call mvn clean test
call mvn allure:serve
pause
