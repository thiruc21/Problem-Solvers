package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;

public class Add_questions_gui {

  private JFrame frame;

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
    frame.getContentPane().setLayout(null);
    
    JLabel lblSelectTheQuestions = new JLabel("Select the questions to add:");
    lblSelectTheQuestions.setBounds(30, 20, 166, 14);
    frame.getContentPane().add(lblSelectTheQuestions);
        
    String[] questions = new String[7];
    questions[0] = "a";
    questions[1] = "b";
    questions[2] = "c";
    questions[3] = "d";
    questions[4] = "e";
    questions[5] = "f";
    questions[6] = "g";
    showQuestionsList(questions);
    
    JButton btnNewButton = new JButton("Add questions");
    btnNewButton.setBounds(30, questions.length*30+80, 120, 23);
    frame.getContentPane().add(btnNewButton);
  }
  
  private void showQuestionsList(String questions[]) {
    JCheckBox newCheckBox;
    for(int i = 0; i < questions.length; i++) {
      newCheckBox= new JCheckBox(questions[i]);
      newCheckBox.setBounds(30, 50+i*30, 97, 23);
      frame.getContentPane().add(newCheckBox);
    }
  }
}

