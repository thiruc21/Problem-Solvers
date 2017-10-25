

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
          "(Q_ID INT PRIMARY KEY, " +
          " LABEL CHAR(50), " + 
          " QUESTION_TEXT CHAR(250) NOT NULL," + 
          " CORRECT_ANSWER INT NOT NULL)";
      st.executeUpdate(q);
      
      q = "DROP TABLE IF EXISTS ANSWER";
      st.executeUpdate(q);
      
      q = "CREATE TABLE ANSWER " +
          "(A_ID INT PRIMARY KEY, " +
          " Q_ID INT NOT NULL, " +
          " ANSWER_TEXT CHAR(250) NOT NULL, " +
          " FOREIGN KEY (Q_ID) REFERENCES QUESTION (Q_ID))";
      st.executeUpdate(q);
      
      st.close();
      conn.close();
          
      
      System.out.println("Success!");
    } catch (Exception e) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
  }
}