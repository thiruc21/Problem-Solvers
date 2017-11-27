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
      
	  q = "SELECT * FROM ASSIGNMENTS ";
	  rs = st.executeQuery(q);
      while (rs.next() ) {
        System.out.println("ASS_ID:" + rs.getInt("ASS_ID") + " NAME:" + rs.getString("NAME"));
      }
      q = "SELECT * FROM ASSIGNED_QUESTIONS ";
	  rs = st.executeQuery(q);
      while (rs.next() ) {
        System.out.println("ASS_ID:" + rs.getInt("ASS_ID") + " Q_ID:" + rs.getInt("Q_ID"));
      }
      
      
      
      st.close();
      conn.close();
          
      
      System.out.println("Success!");
    } catch (Exception e) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
  }
  
  // Returns the answer's id having inputted the answer's text and qid
  public static int answer_query(String answer_text, int q_id) {
	  Connection conn;
	  PreparedStatement st;
	  String q;
	  ResultSet rs;
	  int a_id = 1;
	  try {
	  conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
	  q = "SELECT * FROM ANSWER WHERE ANSWER_TEXT = ? AND Q_ID = ?";
	  st = conn.prepareStatement(q);
	  st.setString(1, answer_text);
	  st.setInt(2, q_id);
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
			  " (SELECT Q_ID FROM ASSIGNED_QUESTIONS WHERE ASS_ID = ?)";
			  
	  st = conn.prepareStatement(q);
	  st.setInt(1, ass_id);
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
// Return list of IDs of all unassigned questions to the assignment id entered
public static List<String> get_assigned(int ass_id) {
	Connection conn;
	PreparedStatement st;
	String q;
	ResultSet rs;
	List<String> result = new ArrayList<String>();
	try {
	  conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
	  q = "SELECT * FROM QUESTION WHERE Q_ID IN"+
			  " (SELECT Q_ID FROM ASSIGNED_QUESTIONS WHERE ASS_ID = ?)";
		
	  st = conn.prepareStatement(q);	
	  st.setInt(1, ass_id);
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
public static List<Integer> get_question_answer_ids(int q_id) {
  	Connection conn;
	  PreparedStatement st;
	  String q;
	  ResultSet rs;
	  List<Integer> result = new ArrayList<Integer>();
	  try {
	  conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
	  q = "SELECT A_ID FROM ANSWER WHERE Q_ID = '" + q_id + "'";
	  st = conn.prepareStatement(q);
    
    
	  rs = st.executeQuery();
      while(rs.next()) {
        result.add(rs.getInt("A_ID"));
        
      }
      st.close();
      conn.close();

	  } catch (Exception e) {
	    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  }
	return result;
}
public static String get_answer_text(int a_id) {
  Connection conn;
	  PreparedStatement st;
	  String q;
	  ResultSet rs;
	  String result = "";
	  try {
	  conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
	  q = "SELECT ANSWER_TEXT FROM ANSWER WHERE A_ID = '" + a_id + "'";
	  st = conn.prepareStatement(q);
	  rs = st.executeQuery();
      if(rs.next()) {
        
        result = rs.getString("ANSWER_TEXT");
        
      }
      st.close();
      conn.close();

	  } catch (Exception e) {
	    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  }
	return result;
}
public static int get_correct_answer_id(int q_id) {
  Connection conn;
	  PreparedStatement st;
	  String q;
	  ResultSet rs;
	  int result = -1;
	  try {
	  conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
	  q = "SELECT CORRECT_ANSWER_ID FROM QUESTION WHERE Q_ID = '" + q_id + "'";
	  st = conn.prepareStatement(q);
	  rs = st.executeQuery();
      if(rs.next()) {
        result = rs.getInt("CORRECT_ANSWER_ID");
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
 
 // Returns the role (admin as 'a' or student user as 'u') of the user given username and password. 'f' if user doesn't exist.
  public static String user_query(String user, String pass) {
	  Connection conn;
	  PreparedStatement st;
	  String q;
	  ResultSet rs;
	  String role = "f";
	  try {
	  conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
	  q = "SELECT * FROM LOGIN_CREDENTIALS WHERE USERNAME = ? AND PASSWORD = ?";
	  st = conn.prepareStatement(q);	
	  st.setString(1, user);
	  st.setString(2, pass);
     rs = st.executeQuery();
     if(rs.next()) {
       role = rs.getString("ROLE");
     }
     st.close();
     conn.close();

	  } catch (Exception e) {
	    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  }
	return role;
  }
  
  // Check if user exists.
  public static boolean user_exists(String user) {
	  Connection conn;
	  PreparedStatement st;
	  String q;
	  ResultSet rs;
	  boolean exists = false;
	  try {
		  conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
		  q = "SELECT * FROM LOGIN_CREDENTIALS WHERE USERNAME = ?";
		  st = conn.prepareStatement(q);	
		  st.setString(1, user);
		 rs = st.executeQuery();
		 exists = rs.next(); 
		 st.close();
		 conn.close();

	  } catch (Exception e) {
	    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  }
	return exists;
  }
  public static int get_assignment_id(String name) {
	  int ass_id = -1;
	  
	  Connection conn;
	  PreparedStatement st;
	  String q;
	  ResultSet rs;
	  boolean exists = false;
	  try {
		  conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
		
		  q = "SELECT ASS_ID FROM ASSIGNMENTS WHERE NAME = ?";
		  st = conn.prepareStatement(q);	
		  st.setString(1, name);
		  
		  rs = st.executeQuery();
		  if(rs.next()) {
		
			ass_id = rs.getInt("ASS_ID");
		
		  }
		  st.close();
		  conn.close();

	  } catch (Exception e) {
	    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  }
	  
	  return ass_id;
  }
  public static List<String> get_assignment_names() {
	  List<String> result = new ArrayList<String>();
	  
	  Connection conn;
	  PreparedStatement st;
	  String q;
	  ResultSet rs;
	  boolean exists = false;
	  try {
		  conn = DriverManager.getConnection("jdbc:sqlite:quizzer.db");
		
		  q = "SELECT NAME FROM ASSIGNMENTS";
		  
		  st = conn.prepareStatement(q);
		  rs = st.executeQuery();
		  while (rs.next()) {
		
			result.add(rs.getString("NAME"));
		
		  }
		
		  st.close();
		  conn.close();

	  } catch (Exception e) {
	    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  }
	  
	  
	  return result;
  }
}