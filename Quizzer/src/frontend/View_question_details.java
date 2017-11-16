package frontend;


import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;


import java.util.ArrayList;
import java.util.List;


public class View_question_details {

    public JFrame frame;

    public final List<Integer> q_ids;
    public int curr_question;
    public JRadioButton[] rdbtn;
    public JLabel lblQuestion;
    public JLabel lblAnswer;
    
    /**
     * Create the application.
     */
    public View_question_details(List<Integer> q_ids) {
    	this.q_ids = q_ids;
    	curr_question = 0;
        initialize(q_ids);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(List<Integer> q_ids) {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(119, 136, 153));
        frame.setBounds(219, 100, 550, 421);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        rdbtn = new JRadioButton[6];
        
        rdbtn[1] = new JRadioButton("Answer 2");
        rdbtn[1].setForeground(new Color(124, 252, 0));
        rdbtn[1].setBackground(new Color(0, 0, 0));
        rdbtn[1].setFont(new Font("Tahoma", Font.BOLD, 11));
        rdbtn[1].setBounds(109, 138, 312, 23);
        frame.getContentPane().add(rdbtn[1]);
        
        rdbtn[0] = new JRadioButton("Answer 1");
        rdbtn[0].setBackground(new Color(0, 0, 0));
        rdbtn[0].setForeground(new Color(124, 252, 0));
        rdbtn[0].setFont(new Font("Tahoma", Font.BOLD, 11));
        rdbtn[0].setBounds(109, 102, 312, 23);
        frame.getContentPane().add(rdbtn[0]);
        
        rdbtn[2] = new JRadioButton("Answer 3");
        rdbtn[2].setForeground(new Color(124, 252, 0));
        rdbtn[2].setBackground(new Color(0, 0, 0));
        rdbtn[2].setFont(new Font("Tahoma", Font.BOLD, 11));
        rdbtn[2].setBounds(109, 170, 312, 23);
        frame.getContentPane().add(rdbtn[2]);
        
        rdbtn[3] = new JRadioButton("Answer 4");
        rdbtn[3].setForeground(new Color(124, 252, 0));
        rdbtn[3].setBackground(new Color(0, 0, 0));
        rdbtn[3].setFont(new Font("Tahoma", Font.BOLD, 11));
        rdbtn[3].setBounds(109, 206, 312, 23);
        frame.getContentPane().add(rdbtn[3]);
        
        rdbtn[4] = new JRadioButton("Answer 5");
        rdbtn[4].setForeground(new Color(124, 252, 0));
        rdbtn[4].setBackground(new Color(0, 0, 0));
        rdbtn[4].setFont(new Font("Tahoma", Font.BOLD, 11));
        rdbtn[4].setBounds(109, 242, 312, 23);
        frame.getContentPane().add(rdbtn[4]);
        
        rdbtn[5] = new JRadioButton("Answer 6");
        rdbtn[5].setForeground(new Color(124, 252, 0));
        rdbtn[5].setBackground(new Color(0, 0, 0));
        rdbtn[5].setFont(new Font("Tahoma", Font.BOLD, 11));
        rdbtn[5].setBounds(109, 277, 312, 23);
        frame.getContentPane().add(rdbtn[5]);
        
        lblQuestion = new JLabel("Question");
        lblQuestion.setVerticalAlignment(SwingConstants.TOP);
        lblQuestion.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblQuestion.setForeground(new Color(173, 255, 47));
        lblQuestion.setBounds(109, 11, 350, 84);
        frame.getContentPane().add(lblQuestion);
        
        lblAnswer = new JLabel("Answer: ");
        lblAnswer.setForeground(new Color(124, 252, 0));
        lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAnswer.setBounds(109, 319, 322, 50);
        frame.getContentPane().add(lblAnswer);
        
        JButton btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int user_prompt = JOptionPane.showConfirmDialog(null, "Are you sure you wish to return to the assignment viewing menu? All progress will be lost.", "Warning", JOptionPane.YES_NO_OPTION);
                if (user_prompt == JOptionPane.YES_OPTION) {
            	View_questions_gui app = new View_questions_gui();
                app.frame.setVisible(true);
                frame.dispose();
                }
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton.setBackground(new Color(0, 0, 0));
        btnNewButton.setForeground(new Color(124, 252, 0));
        btnNewButton.setBounds(10, 321, 78, 50);
        frame.getContentPane().add(btnNewButton);
        displayQuestion();
        
        JButton btnNext = new JButton("Next");
        if (curr_question + 1  == q_ids.size()) {
        	btnNext.setText("Submit");
        }
        btnNext.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (btnNext.getText() == "Next") {
        		curr_question++;
        		displayQuestion();
        		if (curr_question + 1 == q_ids.size()) {
        			btnNext.setText("Submit");
        		}
        		} else if (btnNext.getText() == "Submit") {
        			
        		}
        	}
        });
        btnNext.setForeground(new Color(124, 252, 0));
        btnNext.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNext.setBackground(Color.BLACK);
        btnNext.setBounds(435, 321, 78, 50);
        frame.getContentPane().add(btnNext);
        
    }
    
    public void displayQuestion() {
        int q_id = q_ids.get(curr_question);
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