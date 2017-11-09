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
          "VALUES(NULL, 'Easy Mean', 'What is the mean of 4, 5, and 6?', 1)";
      st.executeUpdate(q);
      
      q = "INSERT INTO ANSWER " +
          "VALUES(NULL, 1, '5')";
      st.executeUpdate(q);
      
      q = "INSERT INTO ANSWER " +
          "VALUES(NULL, 1, '4')";
      st.executeUpdate(q);
      
      q = "INSERT INTO ANSWER " +
          "VALUES(NULL, 1, '6')";
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
   * String correct: the correct answer. 
   * Returns true if successfully inserted, false if an exception is thrown
   */
  public static boolean insert(String title, String question, String[] options, String correct) throws IllegalArgumentException {
	    
	    Connection conn;
	    PreparedStatement st;
	    String q;
	    try {
	      conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
	      // Use a default answer ID of 1 when first inserting the question
	      q = "INSERT INTO QUESTION " + 
	          "VALUES(NULL, '" + title + "', '" + question + "', " + 1 + ")";
	      st = conn.prepareStatement(q);
	      st.execute();
	   // Find the latest inserted question 
	      int q_id = backend.DataQueryTool.question_query(title);
	      // Insert the answer, an answer ID will be generated
	      for(int i = 0; i < options.length; i++) {
	    	  
	    	  q = "INSERT INTO ANSWER " +
	    	          "VALUES(" + "NULL, " + q_id + ", '" + options[i] + "')";
	    	      st = conn.prepareStatement(q);
	    	      st.execute();
	      } 
	      // Find the answer ID with right answer
	      int a_id = backend.DataQueryTool.answer_query(correct);

	      // Update answer ID  for the question
	      q = "UPDATE QUESTION " + 
		          "SET CORRECT_ANSWER_ID  = " + a_id + " WHERE " +
	    		  "Q_ID = '" + q_id + "'";
	      st = conn.prepareStatement(q);
	      st.execute();
	      st.close();
	      conn.close();

	    } catch (SQLException e) {
	      if (e.getMessage().contains("[SQLITE_CONSTRAINT_UNIQUE]"))
	    	  throw new IllegalArgumentException();
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      return false;
	    }
	  
	  return true;
  }
  
public static void addAssignedQuestion(int ass_id, String q_id) {
	  Connection conn;
	  Statement st;
	  String q;
	  try {
	      conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
	      st = conn.createStatement();

	      q = "INSERT INTO ASSIGNED_QUESTIONS " +
	          "VALUES(" + Integer.toString(ass_id) + ", " + q_id + ");";
	      st.executeUpdate(q);
	      st.close();
		  conn.close();
	    } catch (Exception e) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	 
  }

public static void RemoveAssignedQuestion(int ass_id, String q_id) {
	
	Connection conn;
	Statement st;
	String q;
	try {
		conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
		st = conn.createStatement();

		q = "DELETE FROM ASSIGNED_QUESTIONS " +
	          "VALUES(" + Integer.toString(ass_id) + ", " + q_id + ");";
		st.executeUpdate(q);
		st.close();
		conn.close();
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }

}
  
}