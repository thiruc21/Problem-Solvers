@echo off
cd src
javac.exe application/Quizzer.java
java.exe -classpath ".;../lib/sqlite-jdbc-3.20.0.jar" application/Quizzer

alert hello