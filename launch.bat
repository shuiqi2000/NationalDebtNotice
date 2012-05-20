@echo off
set CLASS_PATH=.;lib\httptool.jar;lib\mail.jar;lib\nationaldebtnotice.jar
start javaw -classpath %CLASS_PATH% com.nationaldebtnotice.Main %*

exit
