@cd %~dp0
@java -cp "h2-1.3.173.jar;%H2DRIVERS%;%CLASSPATH%" org.h2.tools.Console %*
@if errorlevel 1 pause