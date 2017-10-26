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
  
  /* String title: Name of the question
   * String question: The question asked to the student
   * String[] options: Each possible answer to the question
   * int correct: which answer in options correct. 
   * Returns true if successfully inserted, false if an exception is thrown
   */
  public static boolean insert(String title, String question, String[] options, int correct) {
	    
	    Connection conn;
	    Statement st;
	    String q;
	    try {
	      conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
	      st = conn.createStatement();
	      
	      q = "INSERT INTO QUESTION " + 
	          "VALUES(1, '" + title + "', '" + question + "', " + correct + ")";
	      st.executeUpdate(q);
	      
	      for(int i = 1; i <= options.length; i++) {
	    	  
	    	  q = "INSERT INTO ANSWER " +
	    	          "VALUES(" + i + ", 1, '" + options[i] + "')";
	    	      st.executeUpdate(q);
	    	  
	      } 
	      
	      st.close();
	      conn.close();

	    } catch (Exception e) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      return false;
	    }
	  
	  return true;
  }
  
}