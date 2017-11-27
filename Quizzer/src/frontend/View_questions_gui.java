package frontend;


import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import application.Quizzer;
import backend.DataQueryTool;
import frontend.Do_assignment;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class View_questions_gui {

  public JFrame frame;
  public boolean student;

  public JRadioButton[] rdbtn;
  
  public JLabel lblQuestion;
  
  public JLabel lblAnswer;
  
  public JList<String> list;
  
  private int assignment_id;
  /**
   * Launch the application.
   */
   /*
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          View_questions_gui window = new View_questions_gui();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }*/

  /**
   * Create the application.
   */
  public View_questions_gui(int ass_id) {
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
    frame.setBounds(100, 100, 850, 421);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel lblSelectTheQuestions = new JLabel("Select the question to view:");
    lblSelectTheQuestions.setForeground(Quizzer.FOREGROUND);
    lblSelectTheQuestions.setFont(new Font("Tahoma", Font.BOLD, 12));
    lblSelectTheQuestions.setBounds(30, 20, 268, 14);
    frame.getContentPane().add(lblSelectTheQuestions);
    // Refresh the list
    refreshList();
    
    ButtonGroup group = new ButtonGroup();
    // Create question display
	rdbtn = new JRadioButton[6];
   
    rdbtn[0] = new JRadioButton("Answer 1");
    rdbtn[0].setBackground(Quizzer.BUTTON);
    rdbtn[0].setForeground(Quizzer.FOREGROUND);
    rdbtn[0].setFont(Quizzer.BOLDQUIZZERFONT);
    rdbtn[0].setBounds(459, 102, 312, 23);
    group.add(rdbtn[0]);
    frame.getContentPane().add(rdbtn[0]);
   
    rdbtn[1] = new JRadioButton("Answer 2");
    rdbtn[1].setForeground(Quizzer.FOREGROUND);
    rdbtn[1].setBackground(Quizzer.BUTTON);
    rdbtn[1].setFont(Quizzer.BOLDQUIZZERFONT);
    rdbtn[1].setBounds(459, 138, 312, 23);
    group.add(rdbtn[1]);
    frame.getContentPane().add(rdbtn[1]);
    
    
    rdbtn[2] = new JRadioButton("Answer 3");
    rdbtn[2].setForeground(Quizzer.FOREGROUND);
    rdbtn[2].setBackground(Quizzer.BUTTON);
    rdbtn[2].setFont(Quizzer.BOLDQUIZZERFONT);
    rdbtn[2].setBounds(459, 170, 312, 23);
    group.add(rdbtn[2]);
    frame.getContentPane().add(rdbtn[2]);
    
    rdbtn[3] = new JRadioButton("Answer 4");
    rdbtn[3].setForeground(Quizzer.FOREGROUND);
    rdbtn[3].setBackground(Quizzer.BUTTON);
    rdbtn[3].setFont(Quizzer.BOLDQUIZZERFONT);
    rdbtn[3].setBounds(459, 206, 312, 23);
    group.add(rdbtn[3]);
    frame.getContentPane().add(rdbtn[3]);
    
    rdbtn[4] = new JRadioButton("Answer 5");
    rdbtn[4].setForeground(Quizzer.FOREGROUND);
    rdbtn[4].setBackground(Quizzer.BUTTON);
    rdbtn[4].setFont(Quizzer.BOLDQUIZZERFONT);
    rdbtn[4].setBounds(459, 242, 312, 23);
    group.add(rdbtn[4]);
    frame.getContentPane().add(rdbtn[4]);
    
    rdbtn[5] = new JRadioButton("Answer 6");
    rdbtn[5].setForeground(Quizzer.FOREGROUND);
    rdbtn[5].setBackground(Quizzer.BUTTON);
    rdbtn[5].setFont(Quizzer.BOLDQUIZZERFONT);
    rdbtn[5].setBounds(459, 277, 312, 23);
    group.add(rdbtn[5]);
    frame.getContentPane().add(rdbtn[5]);
    
    lblQuestion = new JLabel("Question");
    lblQuestion.setVerticalAlignment(SwingConstants.TOP);
    lblQuestion.setFont(Quizzer.BOLDQUIZZERFONT);
    lblQuestion.setForeground(Quizzer.FOREGROUND);
    lblQuestion.setBounds(459, 11, 350, 84);
    lblQuestion.setName("lblQuestion");
    frame.getContentPane().add(lblQuestion);
    
    lblAnswer = new JLabel("");
    lblAnswer.setForeground(Quizzer.FOREGROUND);
    lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 14));
    lblAnswer.setBounds(459, 319, 322, 50);
    lblAnswer.setName("lblAnswer");
    frame.getContentPane().add(lblAnswer);
  }
  
  // Helper function to refresh the listbox with available questions.
  private void refreshList(){
	    // Find all question IDs of questions already assigned to assignment id 1 and store in a list of strings
	    List<String> all_assigned = DataQueryTool.get_assigned(assignment_id);
	    // Append the question's text to each question in the list
	    String[] questions = all_assigned.toArray(new String[all_assigned.size()]);
	    // List of all q_ids
	    List<Integer> q_ids  = new ArrayList<Integer>();
	    for(int i = 0; i < questions.length; i++) {
	    	q_ids.add(Integer.valueOf(questions[i]));
	    	questions[i] += ": " + DataQueryTool.question_text_query(Integer.valueOf((questions[i])));	
	    }
	    listBox(questions, q_ids);
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
private void listBox(String questions[], List<Integer> q_ids) {  
    final DefaultListModel listModel = new DefaultListModel();
    list = new JList(listModel);
    final JButton btnStart = new JButton("Start assignment");
    btnStart.setEnabled(false);
    for(int i = 0; i < questions.length; i++) {
    	btnStart.setEnabled(true);
    	listModel.addElement(questions[i]);
    }
    list.setForeground(Quizzer.FOREGROUND);
    list.setBackground(Quizzer.BUTTON);
    list.setVisibleRowCount(5);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    final JScrollPane scrollPane = new JScrollPane(list);
    scrollPane.setBounds(30, 50, 400, 200);
    frame.getContentPane().add(scrollPane);
     
   
    btnStart.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    	  Do_assignment app = new Do_assignment(assignment_id, q_ids);
          app.frame.setVisible(true);
          app.student = student;
          frame.dispose();
    	}
    });
    btnStart.setFont(new Font("Tahoma", Font.BOLD, 11));
    btnStart.setBackground(Quizzer.BUTTON);
    btnStart.setForeground(Quizzer.FOREGROUND);
    btnStart.setBounds(30, 297, 132, 23);
    frame.getContentPane().add(btnStart);
    
    list.addListSelectionListener(new ListSelectionListener() {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			// TODO Auto-generated method stub
			if (list.isSelectionEmpty()) {
    			JOptionPane.showMessageDialog(new JLabel(), "Please select a question.", "Error", JOptionPane.INFORMATION_MESSAGE);
    		} else {
    			String selected_value = (String) list.getSelectedValue();
    			String selected_qid = selected_value.substring(0, selected_value.indexOf(":"));
    	        int q_id = Integer.parseInt(selected_qid);
    	        String question_text = backend.DataQueryTool.question_text_query(q_id);
    	        List<Integer> answer_ids = backend.DataQueryTool.get_question_answer_ids(q_id);
    	        
    	        List<String> answer_texts = new ArrayList<String>();
    	        String correct_answer_text = "";
    	        int correct_answer_id = backend.DataQueryTool.get_correct_answer_id(q_id);
    	        int i = 0;
    	        for (Integer an_answer_id : answer_ids) {
    	          
    	          answer_texts.add(backend.DataQueryTool.get_answer_text(an_answer_id));
    	          if (an_answer_id == correct_answer_id) { correct_answer_text = backend.DataQueryTool.get_answer_text(an_answer_id);}
    	          rdbtn[i].setText( answer_texts.get(i) );
    	          ++i;
    	        }
    	        
    	        
    	        lblQuestion.setText("<html><body style='width: 237px'>" + question_text);
    	        // Only admins see the answer.
    	        if (student) 
    	        	lblAnswer.setText("");
    	        else
    	        	lblAnswer.setText("<html><body style='width: 237px'>Answer: " + correct_answer_text);
    	        
    	        
		}
		}
    });
    final JButton btnBack = new JButton("Back");
    btnBack.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		// Go to admin or student quizzer depending on role of user
    		Quizzer.Start(frame, !student, true);
    		
    	}
    });
    btnBack.setBackground(Quizzer.BUTTON);
    btnBack.setForeground(Quizzer.FOREGROUND);
    btnBack.setBounds(172, 297, 137, 23);
    frame.getContentPane().add(btnBack);
       
  }
}