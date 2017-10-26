package backend;

import java.sql.*;

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
        int correct_id = rs.getInt("CORRECT_ANSWER");
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
}