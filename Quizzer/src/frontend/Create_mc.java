package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import application.Quizzer;


public class Create_mc {

    public JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Create_mc window = new Create_mc();
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
    public Create_mc() {
        initialize();
    }
    
    public boolean mc_to_db(String title, String question, String[] answers, String answer) {

        // Insert question into database if the question's label is unique.
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
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Quizzer.BACKGROUND);
        frame.setBounds(100, 100, 510, 421);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        final JButton btnCreate = new JButton("Create New");
        btnCreate.setFont(Quizzer.BOLDQUIZZERFONT);
    
        btnCreate.setBackground(Quizzer.BUTTON);
		btnCreate.setForeground(Quizzer.FOREGROUND);
        btnCreate.setBounds(109, 321, 133, 50);
        
        frame.getContentPane().add(btnCreate);
    	ButtonGroup group = new ButtonGroup();
        JRadioButton[] rdbtn = new JRadioButton[6];        
        rdbtn[0] = new JRadioButton("Answer 1");
        rdbtn[0].setBackground(Quizzer.BUTTON);
        rdbtn[0].setForeground(Quizzer.FOREGROUND);
        rdbtn[0].setFont(Quizzer.BOLDQUIZZERFONT);
        rdbtn[0].setBounds(109, 102, Quizzer.DETAIL_BTN_X, Quizzer.DETAIL_BTN_Y);
        group.add(rdbtn[0]);
        frame.getContentPane().add(rdbtn[0]);
        
        rdbtn[1] = new JRadioButton("Answer 2");
        rdbtn[1].setForeground(Quizzer.FOREGROUND);
        rdbtn[1].setBackground(Quizzer.BUTTON);
        rdbtn[1].setFont(Quizzer.BOLDQUIZZERFONT);
        rdbtn[1].setBounds(109, 138, Quizzer.DETAIL_BTN_X, Quizzer.DETAIL_BTN_Y);
        group.add(rdbtn[1]);
        frame.getContentPane().add(rdbtn[1]);
        
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
        
        final JLabel lblQuestion = new JLabel("Question");
        lblQuestion.setVerticalAlignment(SwingConstants.TOP);
        lblQuestion.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblQuestion.setForeground(new Color(173, 255, 47));
        lblQuestion.setBounds(109, 11, 350, 84);
        frame.getContentPane().add(lblQuestion);
        
        final JLabel lblAnswer = new JLabel("Answer: ");
        lblAnswer.setForeground(Quizzer.FOREGROUND);
        lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAnswer.setBounds(252, 321, 237, 50);
        frame.getContentPane().add(lblAnswer);
        
        final JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Quizzer.Start(frame, true);
            }
        });
        btnBack.setFont(Quizzer.BOLDQUIZZERFONT);
        btnBack.setBackground(Quizzer.BUTTON);
        btnBack.setForeground(Quizzer.FOREGROUND);
        btnBack.setBounds(10, 321, 78, 50);
        frame.getContentPane().add(btnBack);
        
        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	btnCreate.setFocusPainted(false);
                String[] options = new String[6];
                boolean validAnswer = false;
                String label = "";
                // Prompt for question label, this is the title of the question as stored in the database. Must be unique.
                while (!validAnswer) {
                    label = JOptionPane.showInputDialog(null, "What would you like to use as the question's label?", "Tag used to refer to this specific question", JOptionPane.INFORMATION_MESSAGE);
                    if (label == null) {
                        return;
                    }
                    if (label.isEmpty()) {
                        JOptionPane.showMessageDialog(new JLabel(), "Label is mandatory.", "Label needed", JOptionPane.INFORMATION_MESSAGE);
                        validAnswer = false;
                        
                    } else {
                        validAnswer = true;
                    }
                }
                validAnswer = false;
                String question = "";
                // Prompt for question text, this is the question text shown to all users
                while (!validAnswer) {
                    question = JOptionPane.showInputDialog(null, "What is the question?", "Multiple Choice Question Creation (2-6 options)", JOptionPane.INFORMATION_MESSAGE);
                    if (question == null) {
                        return;
                    }
                    if (question.isEmpty()) {
                        JOptionPane.showMessageDialog(new JLabel(), "Question is mandatory.", "Question needed", JOptionPane.INFORMATION_MESSAGE);
                        validAnswer = false;
                        
                    } else {
                        validAnswer = true;
                    }
                }
                // Prompt 2-6 option inputs, first two are mandatory. Optional is indicated by empty string.
                for (int i = 0; i < 6; i++) {
                	validAnswer = false;
                	while (!validAnswer) {
                    options[i] = JOptionPane.showInputDialog(null, "What is option " + (i+1) + "? (optional)", "Option " + (i+1), JOptionPane.INFORMATION_MESSAGE);
                    if (options[i] == null) {
                        return;
                    } else if ((i < 2) && (options[i].isEmpty())){
                        JOptionPane.showMessageDialog(new JLabel(), "Option " + (i+1) + " is mandatory.", "Option " + (i+1) + " needed", JOptionPane.INFORMATION_MESSAGE);
                    } else if ((Arrays.asList(Arrays.copyOfRange(options, 0, i)).contains(options[i])&& (!options[i].isEmpty()))){
                    	JOptionPane.showMessageDialog(new JLabel(), "Option " + (i+1) + " must be different from other answers.", "Option "+ (i+1) + " not unique", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                    	validAnswer = true;
                    }
                	}
                }
                // Store correct answer
                validAnswer = false;
                int answer_index = 0;
                
                while (!validAnswer) {
                    String answer = JOptionPane.showInputDialog(null, "What is the answer? Pick a number 1-6 as below.\n 1:" + options[0] + "\n 2:" + options[1] + "\n 3:" + options[2] + "\n 4:" + options[3] + "\n 5:" + options[4] + "\n 6:" + options[5], "Pick an answer", JOptionPane.INFORMATION_MESSAGE);
                    if (answer == null) {
                        return;
                    }
                    try {
                    answer_index = Integer.parseInt(answer);
                    if ((answer_index < 1) || (answer_index > 6)) {
                        throw new NumberFormatException();
                    }
                    // Cannot pick an empty answer, those are optional options.
                    if (options[answer_index-1].isEmpty()) {
                        JOptionPane.showMessageDialog(new JLabel(), "Please choose a non-empty answer", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                    // Answer is valid
                    validAnswer = true;
                    // Call function to insert question into database
                    if (mc_to_db(label, question, options, options[answer_index-1])) {
                        lblQuestion.setText("<html><body style='width: 133px'>"+question);
                        lblAnswer.setText("<html><body style='width: 237px'>Answer: "+options[answer_index-1]);
                        for (int i = 0; i < 6; i++) {
                        	rdbtn[i].setText(options[i]);
                        }
                    }
                    JOptionPane.showMessageDialog(new JLabel(), "Question was successfully created", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    } catch(NumberFormatException e) {
                        JOptionPane.showMessageDialog(new JLabel(), "Please input a number between 1-6", "Invalid input", JOptionPane.INFORMATION_MESSAGE);   
                    }
                }
                    
                
            
                
            }
        });
    }
}