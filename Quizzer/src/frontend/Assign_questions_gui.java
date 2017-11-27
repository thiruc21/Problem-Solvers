package frontend;

import application.Quizzer;


import java.util.List;

import javax.swing.*;

import backend.DataFillTool;
import backend.DataQueryTool;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Assign_questions_gui {

  public JFrame frame;
  public JList<String> list;
  private int assignment_id;

  /**
   * Create the application.
   */
  public Assign_questions_gui(int ass_id) {
	assignment_id = ass_id;
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.getContentPane().setBackground(Quizzer.BACKGROUND);
    frame.getContentPane().setLayout(null);
    frame.setBounds(100, 100, 510, 421);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel lblSelectTheQuestions = new JLabel("Select the questions to assign:");
    lblSelectTheQuestions.setForeground(Quizzer.FOREGROUND);
    lblSelectTheQuestions.setFont(new Font("Tahoma", Font.BOLD, 12));
    lblSelectTheQuestions.setBounds(30, 20, 268, 14);
    frame.getContentPane().add(lblSelectTheQuestions);
    // Refresh the list
    refreshList();
  }
  
  // Helper function to refresh the listbox with available questions.
  private void refreshList(){
	    // Find all question IDs of questions not already assigned to assignment id 1 and store in a list of strings
	    List<String> all_unassigned = DataQueryTool.get_unassigned(assignment_id);
	    // Append the question's text to each question in the list
	    String[] questions = all_unassigned.toArray(new String[all_unassigned.size()]);
	    for(int i = 0; i < questions.length; i++) {
	    	questions[i] += ": " + DataQueryTool.question_text_query(Integer.valueOf((questions[i])));
	    }
	    listBox(questions);
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
private void listBox(String questions[]) {  
    final DefaultListModel listModel = new DefaultListModel();
    list = new JList(listModel);
    for(int i = 0; i < questions.length; i++) {
    	listModel.addElement(questions[i]);
    }
    list.setForeground(Quizzer.FOREGROUND);
    list.setBackground(Quizzer.BUTTON);
    list.setVisibleRowCount(5);
    list.setName("masterList");
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    final JScrollPane scrollPane = new JScrollPane(list);
    scrollPane.setBounds(30, 50, 400, 200);
    frame.getContentPane().add(scrollPane);
     
    final JButton btnAssign = new JButton("Assign questions");
    btnAssign.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		btnAssign.setFocusPainted(false);
    		if (list.isSelectionEmpty()) {
    			JOptionPane.showMessageDialog(new JLabel(), "Please select a question.", "Error", JOptionPane.INFORMATION_MESSAGE);
    		} else {
    			String selected_value = (String) list.getSelectedValue();
    			int selected_qid = Integer.parseInt(selected_value.substring(0, selected_value.indexOf(":")));
    			// Insert Q_ID into the table assigned_questions here
    			// Make and invoke the function by DataFillTool.insertfunctionname(selected_qid)
    			DataFillTool.addAssignedQuestion(assignment_id, selected_qid);
    			listModel.removeElementAt(list.getSelectedIndex());
    			//list.remove(list.getSelectedIndex());
    		}
    		
    	}
    });
    btnAssign.setFont(new Font("Tahoma", Font.BOLD, 11));
    btnAssign.setBackground(Quizzer.BUTTON);
    btnAssign.setForeground(Quizzer.FOREGROUND);
    btnAssign.setBounds(30, 297, 132, 23);
    frame.getContentPane().add(btnAssign);
    
    final JButton btnBack = new JButton("Back");
    btnBack.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		Quizzer.Start(frame, true, true);
    	}
    });
    btnBack.setBackground(Quizzer.BUTTON);
    btnBack.setForeground(Quizzer.FOREGROUND);
    btnBack.setBounds(172, 297, 137, 23);
    frame.getContentPane().add(btnBack);
       
  }
}

