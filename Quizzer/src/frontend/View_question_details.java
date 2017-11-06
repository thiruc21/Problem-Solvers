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

import application.Quizzer;

import java.util.ArrayList;
import java.util.List;


public class View_question_details {

    public JFrame frame;



    /**
     * Create the application.
     */
    public View_question_details(int q_id) {
        initialize(q_id);
    }
    
    public boolean mc_to_db(String title, String question, String[] answers, String answer) {

        //Here is where some Database stuff will be done... IDK 
        try {
        backend.DataFillTool.insert(title, question, answers, answer);
        } catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(new JLabel(), "Unable to insert question. Please input a unique question label.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(int q_id) {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(119, 136, 153));
        frame.setBounds(100, 100, 510, 421);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JRadioButton[] rdbtn = new JRadioButton[6];
        
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
        
        final JLabel lblQuestion = new JLabel("Question");
        lblQuestion.setVerticalAlignment(SwingConstants.TOP);
        lblQuestion.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblQuestion.setForeground(new Color(173, 255, 47));
        lblQuestion.setBounds(109, 11, 350, 84);
        frame.getContentPane().add(lblQuestion);
        
        final JLabel lblAnswer = new JLabel("Answer: ");
        lblAnswer.setForeground(new Color(124, 252, 0));
        lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAnswer.setBounds(119, 319, 322, 50);
        frame.getContentPane().add(lblAnswer);
        
        final JButton btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                View_questions_gui app = new View_questions_gui();
                app.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton.setBackground(new Color(0, 0, 0));
        btnNewButton.setForeground(new Color(124, 252, 0));
        btnNewButton.setBounds(10, 321, 78, 50);
        frame.getContentPane().add(btnNewButton);
        
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