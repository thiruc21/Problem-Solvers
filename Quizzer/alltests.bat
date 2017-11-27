cd test

javac -cp .;../src;../lib/* application_validation/AssignQuestionTest.java
javac -cp .;../src;../lib/* application_validation/CompleteAssignmentTest.java
javac -cp .;../src;../lib/* application_validation/CreateMCTest.java
javac -cp .;../src;../lib/* application_validation/LoginTest.java
javac -cp .;../src;../lib/* application_validation/MultipleAssignmentTest.java
javac -cp .;../src;../lib/* application_validation/QuizzerTest.java
javac -cp .;../src;../lib/* application_validation/SubmitAssignmentTest.java
javac -cp .;../src;../lib/* application_validation/ViewAssignmentTest.java
javac -cp .;../src;../lib/* backend/DataFillToolTest.java
javac -cp .;../src;../lib/* backend/DataQueryToolTest.java
javac -cp .;../src;../lib/* backend/DataSetupToolTest.java

java -cp .;../src;../lib/* org.junit.runner.JUnitCore application_validation.AssignQuestionTest
java -cp .;../src;../lib/* org.junit.runner.JUnitCore application_validation.CompleteAssignmentTest
java -cp .;../src;../lib/* org.junit.runner.JUnitCore application_validation.CreateMCTest
java -cp .;../src;../lib/* org.junit.runner.JUnitCore application_validation.LoginTest
java -cp .;../src;../lib/* org.junit.runner.JUnitCore application_validation.MultipleAssignmentTest
java -cp .;../src;../lib/* org.junit.runner.JUnitCore application_validation.QuizzerTest
java -cp .;../src;../lib/* org.junit.runner.JUnitCore application_validation.SubmitAssignmentTest
java -cp .;../src;../lib/* org.junit.runner.JUnitCore application_validation.ViewAssignmentTest
java -cp .;../src;../lib/* org.junit.runner.JUnitCore backend.DataFillToolTest
java -cp .;../src;../lib/* org.junit.runner.JUnitCore backend.DataQueryToolTest
java -cp .;../src;../lib/* org.junit.runner.JUnitCore backend.DataSetupToolTest

cd ..