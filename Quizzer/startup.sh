#!/bin/bash

cd src

javac application/Quizzer.java

java -classpath ".:../lib/sqlite-jdbc-3.20.0.jar" application.Quizzer
