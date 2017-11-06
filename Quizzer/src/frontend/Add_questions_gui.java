package frontend;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.*;

import application.Quizzer;
import backend.DataFillTool;
import backend.DataQueryTool;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Add_questions_gui {

  public JFrame frame;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Add_questions_gui window = new Add_questions_gui();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public Add_questions_gui() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.getContentPane().setBackground(new Color(119, 136, 153));
    frame.getContentPane().setLayout(null);
    frame.setBounds(100, 100, 510, 421);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel lblSelectTheQuestions = new JLabel("Select the questions to add:");
    lblSelectTheQuestions.setForeground(new Color(124, 252, 0));
    lblSelectTheQuestions.setFont(new Font("Tahoma", Font.BOLD, 12));
    lblSelectTheQuestions.setBounds(30, 20, 268, 14);
    frame.getContentPane().add(lblSelectTheQuestions);
    // Refresh the list
    refreshList();
  }
  
  // Helper function to refresh the listbox with available questions.
  private void refreshList(){
	    // Find all question IDs of questions not already assigned to assignment id 1 and store in a list of strings
	    List<String> all_unassigned = DataQueryTool.get_unassigned(1);
	    // Append the question's text to each question in the list
	    String[] questions = all_unassigned.toArray(new String[all_unassigned.size()]);
	    for(int i = 0; i < questions.length; i++) {
	    	questions[i] += ": " + DataQueryTool.question_text_query(Integer.valueOf((questions[i])));
	    }
	    listBox(questions);
  }
  
  private void listBox(String questions[]) {  
    @SuppressWarnings({"rawtypes", "unchecked"})
    JList list = new JList(questions);
    list.setForeground(new Color(124, 252, 0));
    list.setBackground(new Color(0, 0, 0));
    list.setVisibleRowCount(5);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane scrollPane = new JScrollPane(list);
    scrollPane.setBounds(30, 50, 400, 200);
    frame.getContentPane().add(scrollPane);
     
    JButton btnNewButton = new JButton("Add questions");
    btnNewButton.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		if (list.isSelectionEmpty()) {
    			JOptionPane.showMessageDialog(new JLabel(), "Please select a question.", "Error", JOptionPane.INFORMATION_MESSAGE);
    		} else {
    			String selected_value = (String) list.getSelectedValue();
    			String selected_qid = selected_value.substring(0, selected_value.indexOf(":"));
    			// Insert Q_ID into the table assigned_questions here
    			// Make and invoke the function by DataFillTool.insertfunctionname(selected_qid)
    			DataFillTool.addAssignedQuestion(1, selected_qid);
    		}
    		// Refresh the list after insertion
    		refreshList();
    	}
    });
    btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
    btnNewButton.setBackground(new Color(0, 0, 0));
    btnNewButton.setForeground(new Color(124, 252, 0));
    btnNewButton.setBounds(30, 297, 120, 23);
    frame.getContentPane().add(btnNewButton);
    
    JButton btnBack = new JButton("Back");
    btnBack.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		Quizzer app = new Quizzer(true);
            app.frame.setVisible(true);
            frame.dispose();
    	}
    });
    btnBack.setBackground(new Color(0, 0, 0));
    btnBack.setForeground(new Color(124, 252, 0));
    btnBack.setBounds(172, 297, 126, 23);
    frame.getContentPane().add(btnBack);
       
  }
  
}

