package backend;

import java.sql.*;

public class DataSetupTool {
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
	          "(Ass_ID INT, " +
	          " Q_ID REFERENCES QUESTION(Q_ID))";
	  st.executeUpdate(q);
	  
      st.close();
      conn.close();
          
      
      //System.out.println("Success!");
    } catch (Exception e) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
  }
  
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
		          "(Ass_ID INT, " +
		          " Q_ID REFERENCES QUESTION(Q_ID))";
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