package frontend;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import application.Quizzer;
import backend.DataQueryTool;
import frontend.View_question_details;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class View_questions_gui {

  public JFrame frame;

  /**
   * Launch the application.
   */
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
  }

  /**
   * Create the application.
   */
  public View_questions_gui() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.getContentPane().setBackground(new Color(119, 136, 153));
    frame.getContentPane().setLayout(null);
    frame.setBounds(100, 100, 850, 421);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel lblSelectTheQuestions = new JLabel("Select the question to view:");
    lblSelectTheQuestions.setForeground(new Color(124, 252, 0));
    lblSelectTheQuestions.setFont(new Font("Tahoma", Font.BOLD, 12));
    lblSelectTheQuestions.setBounds(30, 20, 268, 14);
    frame.getContentPane().add(lblSelectTheQuestions);
    // Refresh the list
    refreshList();
  }
  
  // Helper function to refresh the listbox with available questions.
  private void refreshList(){
	    // Find all question IDs of questions already assigned to assignment id 1 and store in a list of strings
	    List<String> all_assigned = DataQueryTool.get_assigned(1);
	    // Append the question's text to each question in the list
	    String[] questions = all_assigned.toArray(new String[all_assigned.size()]);
	    for(int i = 0; i < questions.length; i++) {
	    	questions[i] += ": " + DataQueryTool.question_text_query(Integer.valueOf((questions[i])));
	    }
	    listBox(questions);
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
private void listBox(String questions[]) {  
    final DefaultListModel listModel = new DefaultListModel();
    final JList list = new JList(listModel);
    for(int i = 0; i < questions.length; i++) {
    	listModel.addElement(questions[i]);
    }
    list.setForeground(new Color(124, 252, 0));
    list.setBackground(new Color(0, 0, 0));
    list.setVisibleRowCount(5);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    final JScrollPane scrollPane = new JScrollPane(list);
    scrollPane.setBounds(30, 50, 400, 200);
    frame.getContentPane().add(scrollPane);
     
    final JButton btnNewButton = new JButton("Start assignment");
    list.addListSelectionListener(new ListSelectionListener() {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			// TODO Auto-generated method stub
			if (list.isSelectionEmpty()) {
    			JOptionPane.showMessageDialog(new JLabel(), "Please select a question.", "Error", JOptionPane.INFORMATION_MESSAGE);
    		} else {
    			String selected_value = (String) list.getSelectedValue();
    			String selected_qid = selected_value.substring(0, selected_value.indexOf(":"));
    			// launch View_question_details with selected_qid
    			JRadioButton[] rdbtn = new JRadioButton[6];
    	        
    	        rdbtn[1] = new JRadioButton("Answer 2");
    	        rdbtn[1].setForeground(new Color(124, 252, 0));
    	        rdbtn[1].setBackground(new Color(0, 0, 0));
    	        rdbtn[1].setFont(new Font("Tahoma", Font.BOLD, 11));
    	        rdbtn[1].setBounds(459, 138, 312, 23);
    	        frame.getContentPane().add(rdbtn[1]);
    	        
    	        rdbtn[0] = new JRadioButton("Answer 1");
    	        rdbtn[0].setBackground(new Color(0, 0, 0));
    	        rdbtn[0].setForeground(new Color(124, 252, 0));
    	        rdbtn[0].setFont(new Font("Tahoma", Font.BOLD, 11));
    	        rdbtn[0].setBounds(459, 102, 312, 23);
    	        frame.getContentPane().add(rdbtn[0]);
    	        
    	        rdbtn[2] = new JRadioButton("Answer 3");
    	        rdbtn[2].setForeground(new Color(124, 252, 0));
    	        rdbtn[2].setBackground(new Color(0, 0, 0));
    	        rdbtn[2].setFont(new Font("Tahoma", Font.BOLD, 11));
    	        rdbtn[2].setBounds(459, 170, 312, 23);
    	        frame.getContentPane().add(rdbtn[2]);
    	        
    	        rdbtn[3] = new JRadioButton("Answer 4");
    	        rdbtn[3].setForeground(new Color(124, 252, 0));
    	        rdbtn[3].setBackground(new Color(0, 0, 0));
    	        rdbtn[3].setFont(new Font("Tahoma", Font.BOLD, 11));
    	        rdbtn[3].setBounds(459, 206, 312, 23);
    	        frame.getContentPane().add(rdbtn[3]);
    	        
    	        rdbtn[4] = new JRadioButton("Answer 5");
    	        rdbtn[4].setForeground(new Color(124, 252, 0));
    	        rdbtn[4].setBackground(new Color(0, 0, 0));
    	        rdbtn[4].setFont(new Font("Tahoma", Font.BOLD, 11));
    	        rdbtn[4].setBounds(459, 242, 312, 23);
    	        frame.getContentPane().add(rdbtn[4]);
    	        
    	        rdbtn[5] = new JRadioButton("Answer 6");
    	        rdbtn[5].setForeground(new Color(124, 252, 0));
    	        rdbtn[5].setBackground(new Color(0, 0, 0));
    	        rdbtn[5].setFont(new Font("Tahoma", Font.BOLD, 11));
    	        rdbtn[5].setBounds(459, 277, 312, 23);
    	        frame.getContentPane().add(rdbtn[5]);
    	        
    	        final JLabel lblQuestion = new JLabel("Question");
    	        lblQuestion.setVerticalAlignment(SwingConstants.TOP);
    	        lblQuestion.setFont(new Font("Tahoma", Font.BOLD, 16));
    	        lblQuestion.setForeground(new Color(173, 255, 47));
    	        lblQuestion.setBounds(459, 11, 350, 84);
    	        frame.getContentPane().add(lblQuestion);
    	        
    	        final JLabel lblAnswer = new JLabel("Answer: ");
    	        lblAnswer.setForeground(new Color(124, 252, 0));
    	        lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 14));
    	        lblAnswer.setBounds(459, 319, 322, 50);
    	        frame.getContentPane().add(lblAnswer);
    	        
    	        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
    	        btnNewButton.setBackground(new Color(0, 0, 0));
    	        btnNewButton.setForeground(new Color(124, 252, 0));
    	        btnNewButton.setBounds(10, 321, 78, 50);
    	        frame.getContentPane().add(btnNewButton);
    	        
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
    	        lblAnswer.setText("<html><body style='width: 237px'>Answer: " + correct_answer_text);
    	        
		}
		}
    });
    btnNewButton.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		if (list.isSelectionEmpty()) {
    			JOptionPane.showMessageDialog(new JLabel(), "Please select a question.", "Error", JOptionPane.INFORMATION_MESSAGE);
    		} else {
    			String selected_value = (String) list.getSelectedValue();
    			String selected_qid = selected_value.substring(0, selected_value.indexOf(":"));
    			// launch View_question_details with selected_qid
    			View_question_details app = new View_question_details(Integer.valueOf(selected_qid));
          app.frame.setVisible(true);
          frame.dispose();
          
    		}
    		
    	}
    });
    btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
    btnNewButton.setBackground(new Color(0, 0, 0));
    btnNewButton.setForeground(new Color(124, 252, 0));
    btnNewButton.setBounds(30, 297, 132, 23);
    frame.getContentPane().add(btnNewButton);
    
    final JButton btnBack = new JButton("Back");
    btnBack.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		Quizzer app = new Quizzer(true);
            app.frame.setVisible(true);
            frame.dispose();
    	}
    });
    btnBack.setBackground(new Color(0, 0, 0));
    btnBack.setForeground(new Color(124, 252, 0));
    btnBack.setBounds(172, 297, 137, 23);
    frame.getContentPane().add(btnBack);
       
  }
}

