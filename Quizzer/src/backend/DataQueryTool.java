package backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataQueryTool {
  public static void main( String args[] ) {
    Connection conn;
    Statement st;
    String q;
    ResultSet rs;
    try {
      conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
      st = conn.createStatement();
      
      q = "SELECT * FROM QUESTION ";
      rs = st.executeQuery(q);
      while (rs.next() ) {
        int id = rs.getInt("Q_ID");
        String label = rs.getString("LABEL");
        String q_text = rs.getString("QUESTION_TEXT");
        int correct_id = rs.getInt("CORRECT_ANSWER_ID");
        System.out.println("ID:" + id + " LABEL:" + label + " QUESTION:" + q_text + " CORRECT_ID:" + correct_id);
      }
      
      q = "SELECT * FROM ANSWER ";
      rs = st.executeQuery(q);
      while (rs.next() ) {
        int a_id = rs.getInt("A_ID");
        int q_id = rs.getInt("Q_ID");
        String answer_text = rs.getString("ANSWER_TEXT");
        System.out.println("A_ID:" + a_id + " Q_ID:" + q_id + " ANSWER:" + answer_text);
      }
      
      
      
      
      st.close();
      conn.close();
          
      
      System.out.println("Success!");
    } catch (Exception e) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
  }
  
  // Returns the answer's id having inputted the answer's text
  public static int answer_query(String answer_text) {
	  Connection conn;
	  PreparedStatement st;
	  String q;
	  ResultSet rs;
	  int a_id = 1;
	  try {
	  conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
	  q = "SELECT * FROM ANSWER WHERE ANSWER_TEXT = '" + answer_text + "'";
	  st = conn.prepareStatement(q);	
	  rs = st.executeQuery();
      if(rs.next()) {
        a_id = rs.getInt("A_ID");
      }
      st.close();
      conn.close();

	  } catch (Exception e) {
	    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  }
	return a_id;
  }
// Return list of Q_ID of all unassigned questions to the assignment id entered
public static List<String> get_unassigned(int ass_id) {
	Connection conn;
	PreparedStatement st;
	String q;
	ResultSet rs;
	List<String> result = new ArrayList<String>();
	try {
	  conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
	  q = "SELECT * FROM QUESTION WHERE Q_ID NOT IN"+
			  " (SELECT Q_ID FROM ASSIGNED_QUESTIONS)";
			  
	  st = conn.prepareStatement(q);	
      rs = st.executeQuery();
      while(rs.next()) {
        result.add(Integer.toString(rs.getInt("Q_ID")));
      }
      st.close();
      conn.close();

	  } catch (Exception e) {
	    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  }
	return result;
}

// Return a question's text having inputted the question's id 
public static String question_text_query(int q_id) {
	Connection conn;
	  PreparedStatement st;
	  String q;
	  ResultSet rs;
	  String result = "Question not found";
	  try {
	  conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
	  q = "SELECT QUESTION_TEXT FROM QUESTION WHERE Q_ID = '" + q_id + "'";
	  st = conn.prepareStatement(q);
	  rs = st.executeQuery();
      if(rs.next()) {
        result = rs.getString("QUESTION_TEXT");
      }
      st.close();
      conn.close();

	  } catch (Exception e) {
	    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  }
	return result;
}
  
//Returns the question's id having inputted the question's label
 public static int question_query(String question_label) {
	  Connection conn;
	  PreparedStatement st;
	  String q;
	  ResultSet rs;
	  int q_id = 1;
	  try {
	  conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
	  q = "SELECT * FROM QUESTION WHERE LABEL = '" + question_label + "'" +
			  "ORDER BY Q_ID DESC";
	  st = conn.prepareStatement(q);	
     rs = st.executeQuery();
     if(rs.next()) {
       q_id = rs.getInt("Q_ID");
     }
     st.close();
     conn.close();

	  } catch (Exception e) {
	    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  }
	return q_id;
 }
  
}