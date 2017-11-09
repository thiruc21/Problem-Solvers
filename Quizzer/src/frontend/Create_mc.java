package frontend;

import java.awt.EventQueue;

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
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(119, 136, 153));
        frame.setBounds(100, 100, 510, 421);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        final JButton btnCreate = new JButton("Create New");
        btnCreate.setFont(new Font("Tahoma", Font.BOLD, 11));
    
        btnCreate.setBackground(new Color(0, 0, 0));
        btnCreate.setForeground(new Color(124, 252, 0));
        btnCreate.setBounds(109, 321, 133, 50);
        frame.getContentPane().add(btnCreate);
        
        final JRadioButton rdbtnA2 = new JRadioButton("Answer 2");
        rdbtnA2.setForeground(new Color(124, 252, 0));
        rdbtnA2.setBackground(new Color(0, 0, 0));
        rdbtnA2.setFont(new Font("Tahoma", Font.BOLD, 11));
        rdbtnA2.setBounds(109, 138, 312, 23);
        frame.getContentPane().add(rdbtnA2);
        
        final JRadioButton rdbtnA1 = new JRadioButton("Answer 1");
        rdbtnA1.setBackground(new Color(0, 0, 0));
        rdbtnA1.setForeground(new Color(124, 252, 0));
        rdbtnA1.setFont(new Font("Tahoma", Font.BOLD, 11));
        rdbtnA1.setBounds(109, 102, 312, 23);
        frame.getContentPane().add(rdbtnA1);
        
        final JRadioButton rdbtnA3 = new JRadioButton("Answer 3");
        rdbtnA3.setForeground(new Color(124, 252, 0));
        rdbtnA3.setBackground(new Color(0, 0, 0));
        rdbtnA3.setFont(new Font("Tahoma", Font.BOLD, 11));
        rdbtnA3.setBounds(109, 170, 312, 23);
        frame.getContentPane().add(rdbtnA3);
        
        final JRadioButton rdbtnA4 = new JRadioButton("Answer 4");
        rdbtnA4.setForeground(new Color(124, 252, 0));
        rdbtnA4.setBackground(new Color(0, 0, 0));
        rdbtnA4.setFont(new Font("Tahoma", Font.BOLD, 11));
        rdbtnA4.setBounds(109, 206, 312, 23);
        frame.getContentPane().add(rdbtnA4);
        
        final JRadioButton rdbtnA5 = new JRadioButton("Answer 5");
        rdbtnA5.setForeground(new Color(124, 252, 0));
        rdbtnA5.setBackground(new Color(0, 0, 0));
        rdbtnA5.setFont(new Font("Tahoma", Font.BOLD, 11));
        rdbtnA5.setBounds(109, 242, 312, 23);
        frame.getContentPane().add(rdbtnA5);
        
        final JRadioButton rdbtnA6 = new JRadioButton("Answer 6");
        rdbtnA6.setForeground(new Color(124, 252, 0));
        rdbtnA6.setBackground(new Color(0, 0, 0));
        rdbtnA6.setFont(new Font("Tahoma", Font.BOLD, 11));
        rdbtnA6.setBounds(109, 277, 312, 23);
        frame.getContentPane().add(rdbtnA6);
        
        final JLabel lblQuestion = new JLabel("Question");
        lblQuestion.setVerticalAlignment(SwingConstants.TOP);
        lblQuestion.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblQuestion.setForeground(new Color(173, 255, 47));
        lblQuestion.setBounds(109, 11, 350, 84);
        frame.getContentPane().add(lblQuestion);
        
        final JLabel lblAnswer = new JLabel("Answer: ");
        lblAnswer.setForeground(new Color(124, 252, 0));
        lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAnswer.setBounds(252, 321, 237, 50);
        frame.getContentPane().add(lblAnswer);
        
        final JButton btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Quizzer app = new Quizzer(true);
                app.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton.setBackground(new Color(0, 0, 0));
        btnNewButton.setForeground(new Color(124, 252, 0));
        btnNewButton.setBounds(10, 321, 78, 50);
        frame.getContentPane().add(btnNewButton);
        
        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String[] options = new String[6];
                boolean validAnswer = false;
                String label = "";
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
                validAnswer = false;
                while (!validAnswer) {
                    options[0] = JOptionPane.showInputDialog(null, "What is the first option?", "Option 1", JOptionPane.INFORMATION_MESSAGE);
                    if (options[0] == null) {
                        return;
                    }
                    if (options[0].isEmpty()) {
                        JOptionPane.showMessageDialog(new JLabel(), "First option is mandatory.", "First option needed", JOptionPane.INFORMATION_MESSAGE);
                        validAnswer = false;
                        
                    } else {
                        validAnswer = true;
                    }
                }
                validAnswer = false;
                while (!validAnswer) {
                options[1] = JOptionPane.showInputDialog(null, "What is the second option?", "Option 2", JOptionPane.INFORMATION_MESSAGE);
                if (options[1] == null) {
                    return;
                }
                if (options[1].isEmpty()) {
                    JOptionPane.showMessageDialog(new JLabel(), "Second option is mandatory.", "Second option needed", JOptionPane.INFORMATION_MESSAGE);
                    validAnswer = false;
                    
                } else {
                    validAnswer = true;
                }   
                }
                validAnswer = false;
                options[2] = JOptionPane.showInputDialog(null, "What is the third option? (optional)", "Option 3", JOptionPane.INFORMATION_MESSAGE);
                if (options[2] == null) {
                    return;
                }
                options[3] = JOptionPane.showInputDialog(null, "What is the fourth option? (optional)", "Option 4", JOptionPane.INFORMATION_MESSAGE);
                if (options[3] == null) {
                    return;
                }
                options[4] = JOptionPane.showInputDialog(null, "What is the fifth option? (optional)", "Option 5", JOptionPane.INFORMATION_MESSAGE);
                if (options[4] == null) {
                    return;
                }
                options[5] = JOptionPane.showInputDialog(null, "What is the sixth option? (optional)", "Option 6", JOptionPane.INFORMATION_MESSAGE);
                if (options[5] == null) {
                    return;
                }
                while (!validAnswer) {
                    String answer = JOptionPane.showInputDialog(null, "What is the answer? Pick a number 1-6 as below.\n 1:" + options[0] + "\n 2:" + options[1] + "\n 3:" + options[2] + "\n 4:" + options[3] + "\n 5:" + options[4] + "\n 6:" + options[5], "Pick an answer", JOptionPane.INFORMATION_MESSAGE);
                    if (answer == null) {
                        return;
                    }
                    try {
                    int answer_index = Integer.parseInt(answer);
                    if ((answer_index < 1) || (answer_index > 6)) {
                        throw new NumberFormatException();
                    }
                    validAnswer = true;
                    
                    // Call function to insert into database using back-end here 
                    if (mc_to_db(label, question, options, options[answer_index-1])) {
                        lblQuestion.setText("<html><body style='width: 133px'>"+question);
                        lblAnswer.setText("<html><body style='width: 237px'>Answer: "+options[answer_index-1]);
                        rdbtnA1.setText(options[0]);
                        rdbtnA2.setText(options[1]);
                        rdbtnA3.setText(options[2]);
                        rdbtnA4.setText(options[3]);
                        rdbtnA5.setText(options[4]);
                        rdbtnA6.setText(options[5]);
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