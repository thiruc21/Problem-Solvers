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
	      q = "INSERT INTO QUESTION(Q_ID, LABEL, QUESTION_TEXT, CORRECT_ANSWER_ID) VALUES(NULL, ?, ?, 1)";
	      st = conn.prepareStatement(q);
	      st.setString(1, title);
	      st.setString(2, question);
	      st.execute();
	   // Find the latest inserted question 
	      int q_id = backend.DataQueryTool.question_query(title);
	      // Insert the answer, an answer ID will be generated
	      for(int i = 0; i < options.length; i++) {
	    	  
	    	  q = "INSERT INTO ANSWER(A_ID, Q_ID, ANSWER_TEXT) VALUES(NULL, ?, ?)";
	    	      st = conn.prepareStatement(q);
	    	      st.setInt(1, q_id);
	    	      st.setString(2, options[i]);
	    	      st.execute();
	      } 
	      // Find the answer ID with right answer
	      int a_id = backend.DataQueryTool.answer_query(correct, q_id);

	      // Update answer ID  for the question
	      q = "UPDATE QUESTION " + 
		          "SET CORRECT_ANSWER_ID  = " + "?" + " WHERE " +
	    		  "Q_ID = " + "?" + "";
	      st = conn.prepareStatement(q);
	      st.setInt(1, a_id);
	      st.setInt(2, q_id);
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
  
public static void addAssignedQuestion(int ass_id, int q_id) {
	  Connection conn;
	  PreparedStatement st;
	  String q;
	  try {
	      conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");

	      q = "INSERT INTO ASSIGNED_QUESTIONS " +
	          "VALUES(?, ?)";
	      st = conn.prepareStatement(q);
		  st.setInt(1, ass_id);
		  st.setInt(2, q_id);
	      st.execute();
	      st.close();
		  conn.close();
	    } catch (Exception e) {
		  System.err.println( ass_id);
		  System.err.println( q_id);
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
  }
// Add user to database.
public static void addStudentUser(String user, String pass) {
	  Connection conn;
	  PreparedStatement st;
	  String q;
	  try {
	      conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
	      q = "INSERT INTO LOGIN_CREDENTIALS (USERNAME, PASSWORD, ROLE)" +
			      "VALUES(?, ?, 'u')";
	      st = conn.prepareStatement(q);
	      st.setString(1, user);
	      st.setString(2, pass);
	      st.execute();
	      st.close();
		  conn.close();
	    } catch (Exception e) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
}
	public static int createAssignment(String assignmentName) {
		int ass_id = -1;
		Connection conn;
	    PreparedStatement st;
	    String q;
		ResultSet rs;
		try {
	      conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");

	      q = "INSERT INTO ASSIGNMENTS VALUES(NULL, ?)";
		  //q = "INSERT INTO ASSIGNMENTS VALUES(0, 'abc')";
	      st = conn.prepareStatement(q);
		  st.setString(1, assignmentName);
		  System.out.println(assignmentName + "!");
		  st.execute();
		  System.out.println("got here");
		  
		  st.close();
		  conn.close();
		  ass_id = DataQueryTool.get_assignment_id(assignmentName);
		  System.out.println(ass_id);
		  return ass_id;
	    } catch (Exception e) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  System.out.println("why");
		  return ass_id;
	    }
	}
  
}