package backend;

import java.sql.*;

public class DataSetupTool {
	/*
  public static void main( String args[] ) {
    Connection conn;
    Statement st;
    String q;
    try {
      conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
      st = conn.createStatement();
      
      q = "DROP TABLE IF EXISTS QUESTION";
      st.executeUpdate(q);
      
      q = "CREATE TABLE QUESTION " +
          "(Q_ID INTEGER PRIMARY KEY, " +
          " LABEL CHAR(50) UNIQUE, " + 
          " QUESTION_TEXT CHAR(250) NOT NULL," + 
          " CORRECT_ANSWER_ID INT NOT NULL)";
      // correct_answer is the answer_id of the right answer
      // label is UNIQUE.
      st.executeUpdate(q);
      
      q = "DROP TABLE IF EXISTS ANSWER";
      st.executeUpdate(q);
      
      q = "CREATE TABLE ANSWER " +
          "(A_ID INTEGER PRIMARY KEY, " +
          " Q_ID INT NOT NULL, " +
          " ANSWER_TEXT CHAR(250) NOT NULL, " +
          " FOREIGN KEY (Q_ID) REFERENCES QUESTION (Q_ID))";
      st.executeUpdate(q);
      // Void rows are used for intermediate steps when creating new questions
      //q = "INSERT INTO QUESTION " + 
	  //        "VALUES(NULL, '" + "void" + "', '" + "VOID FIRST ROW" + "', " + "1" + ")";
	  //    st.executeUpdate(q);
      
      q = "INSERT INTO ANSWER " +
	      "VALUES(" + "NULL, 1, '" + "VOID FIRST ROW" + "')";
	  st.executeUpdate(q);
	  
	 // Create assigned_questions table
	  q = "DROP TABLE IF EXISTS ASSIGNED_QUESTIONS";
	  st.executeUpdate(q);
	          
	  q = "CREATE TABLE ASSIGNED_QUESTIONS" +
	          "(ASS_ID INT NOT NULL, " +
	          " Q_ID INT NOT NULL, " +
			  " FOREIGN KEY (Ass_ID) REFERENCES ASSIGNMENTS (ASS_ID)," + 
			  " FOREIGN KEY (Q_ID) REFERENCES QUESTION(Q_ID))";
	  st.executeUpdate(q);
	  
	  q = "DROPTABLE IF EXISTS ASSIGNMENTS";
	  st.executeUpdate(q);
	  q = "CREATE TABLE ASSIGNMENTS" +
	      "(ASS_ID INTEGER PRIMARY KEY, " +
		  " NAME CHAR(30) UNIQUE)";
	  st.executeUpdate(q);
	  
      st.close();
      conn.close();
          
      
      //System.out.println("Success!");
    } catch (Exception e) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
  }
*/

  
  public static boolean initialize() {
	  Connection conn;
	    Statement st;
	    String q;
	    try {
	      conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
	      st = conn.createStatement();
	      
	      q = "DROP TABLE IF EXISTS QUESTION";
	      st.executeUpdate(q);
	      
	      q = "CREATE TABLE QUESTION " +
	          "(Q_ID INTEGER PRIMARY KEY, " +
	          " LABEL CHAR(50) UNIQUE, " + 
	          " QUESTION_TEXT CHAR(250) NOT NULL," + 
	          " CORRECT_ANSWER_ID INT NOT NULL)";
	      // correct_answer is the answer_id of the right answer
	      // label is UNIQUE.
	      st.executeUpdate(q);
	      
	      q = "DROP TABLE IF EXISTS ANSWER";
	      st.executeUpdate(q);
	      
	      q = "CREATE TABLE ANSWER " +
	          "(A_ID INTEGER PRIMARY KEY, " +
	          " Q_ID INT NOT NULL, " +
	          " ANSWER_TEXT CHAR(250) NOT NULL, " +
	          " FOREIGN KEY (Q_ID) REFERENCES QUESTION (Q_ID))";
	      st.executeUpdate(q);
	      // Void rows are used for intermediate steps when creating new questions
	      //q = "INSERT INTO QUESTION " + 
		  //        "VALUES(NULL, '" + "void" + "', '" + "VOID FIRST ROW" + "', " + "1" + ")";
		  //    st.executeUpdate(q);
	      
	      q = "INSERT INTO ANSWER " +
		      "VALUES(" + "NULL, 0, '" + "VOID FIRST ROW" + "')";
		  st.executeUpdate(q);
		  
		 // Create assigned_questions table
		  q = "DROP TABLE IF EXISTS ASSIGNED_QUESTIONS";
		  st.executeUpdate(q);
		          
		  q = "CREATE TABLE ASSIGNED_QUESTIONS" +
	          "(ASS_ID INT NOT NULL, " +
	          " Q_ID INT NOT NULL, " +
			  " FOREIGN KEY (ASS_ID) REFERENCES ASSIGNMENTS (ASS_ID)," + 
			  " FOREIGN KEY (Q_ID) REFERENCES QUESTION(Q_ID))";
		  st.executeUpdate(q);
		  
		  q = "DROP TABLE IF EXISTS ASSIGNMENTS";
		  st.executeUpdate(q);
		  q = "CREATE TABLE ASSIGNMENTS" +
	      "(ASS_ID INTEGER PRIMARY KEY, " +
		  " NAME CHAR(30) UNIQUE)";
		  st.executeUpdate(q);
		  
		  // Create login_credentials table
		  q = "DROP TABLE IF EXISTS LOGIN_CREDENTIALS";
	      st.executeUpdate(q);
	      
	      // Username. password, and role(admin(a) or student user(u))
	      q = "CREATE TABLE LOGIN_CREDENTIALS" +
	          "(USERNAME CHAR(50) PRIMARY KEY, " +
	          " PASSWORD CHAR(50) NOT NULL, " + 
	          " ROLE CHAR(1) NOT NULL)";
	      st.executeUpdate(q);
	      
	      q = "INSERT INTO LOGIN_CREDENTIALS " +
			      "VALUES('STUDENT', 'student', 'u')";
		  st.executeUpdate(q);
		  
		  q = "INSERT INTO LOGIN_CREDENTIALS " +
			      "VALUES('ADMIN', 'admin', 'a')";
		  st.executeUpdate(q);
	      
	      st.close();
	      conn.close();
	      //System.out.println("Success!");
	      return true;
	    } catch (Exception e) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      return false;
	    }
  }
  
}