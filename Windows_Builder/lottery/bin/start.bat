@echo off

cd /d %~dp0
cd ..
set GOM_HOME=.
set LIB_PATH=%GOM_HOME%\lib

set APP_PATH=%LIB_PATH%\lottery.jar;
set DOM_PATH=%LIB_PATH%\dom4j-1.6.1.jar
set APACHE_PATH=%LIB_PATH%\commons-lang3-3.3.1.jar
set POI_PATH=%LIB_PATH%\poi-3.11-20141221.jar;%LIB_PATH%\poi-ooxml-3.11-20141221.jar;%LIB_PATH%\poi-ooxml-schemas-3.11-20141221.jar;%LIB_PATH%\poi-scratchpad-3.11-20141221.jar
set JFREECHART_PATH=%LIB_PATH%\jcommon-1.0.17.jar;%LIB_PATH%\jfreechart-1.0.14.jar
set CLASS_PATH=%APP_PATH%;%DOM_PATH%;%APACHE_PATH%;%POI_PATH%;%JFREECHART_PATH%

start java -cp %CLASS_PATH% -agentlib:jdwp=transport=dt_socket,address=33234,server=y,suspend=n lottery.start.Bootstrap

cd bin