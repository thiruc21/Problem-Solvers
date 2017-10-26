package backend;

import java.sql.*;

public class DataFillTool {
  public static void main( String args[] ) {
    Connection conn;
    Statement st;
    String q;
    try {
      conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
      st = conn.createStatement();
      
      q = "INSERT INTO QUESTION " + 
          "VALUES(1, 'Easy Mean', 'What is the mean of 4, 5, and 6?', 1)";
      st.executeUpdate(q);
      
      q = "INSERT INTO ANSWER " +
          "VALUES(1, 1, '5')";
      st.executeUpdate(q);
      
      q = "INSERT INTO ANSWER " +
          "VALUES(2, 1, '4')";
      st.executeUpdate(q);
      
      q = "INSERT INTO ANSWER " +
          "VALUES(3, 1, '6')";
      st.executeUpdate(q);
      
      
      
      st.close();
      conn.close();
          
      
      System.out.println("Success!");
    } catch (Exception e) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
  }
}