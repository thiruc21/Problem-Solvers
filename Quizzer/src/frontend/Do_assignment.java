package frontend;

import application.Quizzer;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.SwingConstants;


import java.util.ArrayList;
import java.util.List;


public class Do_assignment {

    public JFrame frame;
    public final List<Integer> q_ids;
    public Integer answer_id;
    public boolean user_correct;
    public int corrects;
    public int curr_question;
    public JRadioButton[] rdbtn;
    public JLabel lblQuestion;
    public JLabel lblAnswer;
    public boolean student;
	private int ass_id;
    
    /**
     * Create the application.
     */
    public Do_assignment(int ass_id, List<Integer> q_ids) {
    	this.q_ids = q_ids;
		this.ass_id = ass_id;
    	answer_id = 0;
    	corrects = 0;
    	curr_question = 0;
        initialize(q_ids);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(List<Integer> q_ids) {
    	ButtonGroup group = new ButtonGroup();
        frame = new JFrame();
        frame.getContentPane().setBackground(Quizzer.BACKGROUND);
        frame.setBounds(219, 100, 550, 421);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        rdbtn = new JRadioButton[6];
        
        rdbtn[1] = new JRadioButton("Answer 2");
        rdbtn[1].setForeground(Quizzer.FOREGROUND);
        rdbtn[1].setBackground(Quizzer.BUTTON);
        rdbtn[1].setFont(Quizzer.BOLDQUIZZERFONT);
        rdbtn[1].setBounds(109, 138, Quizzer.DETAIL_BTN_X, Quizzer.DETAIL_BTN_Y);
        group.add(rdbtn[1]);
        frame.getContentPane().add(rdbtn[1]);
        
        rdbtn[0] = new JRadioButton("Answer 1");
        rdbtn[0].setBackground(Quizzer.BUTTON);
        rdbtn[0].setForeground(Quizzer.FOREGROUND);
        rdbtn[0].setFont(Quizzer.BOLDQUIZZERFONT);
        rdbtn[0].setBounds(109, 102, Quizzer.DETAIL_BTN_X, Quizzer.DETAIL_BTN_Y);
        group.add(rdbtn[0]);
        frame.getContentPane().add(rdbtn[0]);
        
        rdbtn[2] = new JRadioButton("Answer 3");
        rdbtn[2].setForeground(Quizzer.FOREGROUND);
        rdbtn[2].setBackground(Quizzer.BUTTON);
        rdbtn[2].setFont(Quizzer.BOLDQUIZZERFONT);
        rdbtn[2].setBounds(109, 170, Quizzer.DETAIL_BTN_X, Quizzer.DETAIL_BTN_Y);
        group.add(rdbtn[2]);
        frame.getContentPane().add(rdbtn[2]);
        
        rdbtn[3] = new JRadioButton("Answer 4");
        rdbtn[3].setForeground(Quizzer.FOREGROUND);
        rdbtn[3].setBackground(Quizzer.BUTTON);
        rdbtn[3].setFont(Quizzer.BOLDQUIZZERFONT);
        rdbtn[3].setBounds(109, 206, Quizzer.DETAIL_BTN_X, Quizzer.DETAIL_BTN_Y);
        group.add(rdbtn[3]);
        frame.getContentPane().add(rdbtn[3]);
        
        rdbtn[4] = new JRadioButton("Answer 5");
        rdbtn[4].setForeground(Quizzer.FOREGROUND);
        rdbtn[4].setBackground(Quizzer.BUTTON);
        rdbtn[4].setFont(Quizzer.BOLDQUIZZERFONT);
        rdbtn[4].setBounds(109, 242, Quizzer.DETAIL_BTN_X, Quizzer.DETAIL_BTN_Y);
        group.add(rdbtn[4]);
        frame.getContentPane().add(rdbtn[4]);
        
        rdbtn[5] = new JRadioButton("Answer 6");
        rdbtn[5].setForeground(Quizzer.FOREGROUND);
        rdbtn[5].setBackground(Quizzer.BUTTON);
        rdbtn[5].setFont(Quizzer.BOLDQUIZZERFONT);
        rdbtn[5].setBounds(109, 277, Quizzer.DETAIL_BTN_X, Quizzer.DETAIL_BTN_Y);
        group.add(rdbtn[5]);
        frame.getContentPane().add(rdbtn[5]);
        
        lblQuestion = new JLabel("Question");
        lblQuestion.setVerticalAlignment(SwingConstants.TOP);
        lblQuestion.setFont(Quizzer.BOLDQUIZZERFONT);
        lblQuestion.setForeground(Quizzer.FOREGROUND);
        lblQuestion.setBounds(109, 11, 350, 84);
        frame.getContentPane().add(lblQuestion);
        

       
        lblAnswer = new JLabel("Answer: ");
        lblAnswer.setForeground(Quizzer.FOREGROUND);

        lblAnswer.setFont(Quizzer.BOLDQUIZZERFONT);
        lblAnswer.setBounds(109, 319, 322, 50);
        frame.getContentPane().add(lblAnswer);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		btnBack.setFocusPainted(false);
        		int user_prompt = JOptionPane.showConfirmDialog(null, "Are you sure you wish to return to the assignment viewing menu? All progress will be lost.", "Warning", JOptionPane.YES_NO_OPTION);
                if (user_prompt == JOptionPane.YES_OPTION) {
                	View_questions_gui app = new View_questions_gui(ass_id);
                    app.frame.setVisible(true);
                    frame.dispose();
    	    		app.student = student;
                }
        	}
        });
        btnBack.setFont(Quizzer.BOLDQUIZZERFONT);
        btnBack.setBackground(Quizzer.BUTTON);
        btnBack.setForeground(Quizzer.FOREGROUND);
        btnBack.setBounds(10, 321, 78, 50);
        frame.getContentPane().add(btnBack);
        displayQuestion();
        
        JButton btnNext = new JButton("Next");
        // If last question, change the next buttons text to 'Submit'
        if (curr_question + 1  == q_ids.size()) {
        	btnNext.setText("Submit");
        }
        btnNext.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnNext.setFocusPainted(false);
        		// No answer selected
        		if (group.getSelection() == null) {
        			JOptionPane.showMessageDialog(new JLabel(), "Please select an answer.", "Error", JOptionPane.INFORMATION_MESSAGE);
        		} else if (btnNext.getText() == "Next") {
        			// Compare answers and increment correct count
        			compareAnswer(q_ids.get(curr_question));
        			curr_question++;
        			group.clearSelection();
        			displayQuestion();	
        			if (curr_question + 1 == q_ids.size()) {
        				btnNext.setText("Submit");
        			}
        		} else if (btnNext.getText() == "Submit") {
        			// Compare answers and increment correct count
        			compareAnswer(q_ids.get(curr_question));
        			curr_question++;
        			lblAnswer.setText(String.format("You scored: %d/%d  (%.2f%%)", corrects, curr_question, ((float)corrects/(float)curr_question)*100));
        			btnNext.setEnabled(false);
        		}
        	}
        });
        btnNext.setForeground(new Color(124, 252, 0));
        btnNext.setFont(Quizzer.BOLDQUIZZERFONT);
        btnNext.setBackground(Color.BLACK);
        btnNext.setBounds(435, 321, 78, 50);
        frame.getContentPane().add(btnNext);
        
    }
    
    // Compare selected answer to actual answer, increment correct count if so
	public void compareAnswer(Integer q_id) {
		List<Integer> answer_ids = backend.DataQueryTool.get_question_answer_ids(q_id);
		
		for (int i=0; i < answer_ids.size(); i++) {
			// If the user answer is equal to correct answer
			// then increment the number of corrects
			if ((answer_ids.get(i) == answer_id) && (rdbtn[i].isSelected())) {
				corrects++;
			}
		}
	}
    // Display the question/answers in the radio buttons
    public void displayQuestion() {
        int q_id = q_ids.get(curr_question);
        String question_text = backend.DataQueryTool.question_text_query(q_id);
        List<Integer> answer_ids = backend.DataQueryTool.get_question_answer_ids(q_id);
        
        List<String> answer_texts = new ArrayList<String>();
        int correct_answer_id = backend.DataQueryTool.get_correct_answer_id(q_id);
        answer_id = correct_answer_id;
        int i = 0;
        for (Integer an_answer_id : answer_ids) {
          
          answer_texts.add(backend.DataQueryTool.get_answer_text(an_answer_id));
          rdbtn[i].setText( answer_texts.get(i) );
          ++i;
        }
        lblQuestion.setText("<html><body style='width: 237px'>Q" + (curr_question+1) + ": " + question_text);
        lblQuestion.setName("lblQuestion");
        lblAnswer.setText("");
        // Used for buttonmatcher in testing
        lblAnswer.setName("lblAnswer");
    }
}