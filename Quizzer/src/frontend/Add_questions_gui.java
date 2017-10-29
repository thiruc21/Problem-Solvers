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

public class Add_questions_gui {

  private JFrame frame;
  private JTextField textField;

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
    
    @SuppressWarnings("rawtypes")
    JList list = new JList();
    list.setBounds(40, 11, 356, 164);
    frame.getContentPane().add(list);
    
    String[] q = new String[3];
    q[0] = "a";
    q[1] = "b";
    q[2] = "c";
    showQuestionsList(list, q);
    
    textField = new JTextField();
    textField.setBounds(40, 230, 86, 20);
    frame.getContentPane().add(textField);
    textField.setColumns(10);
    
    JLabel lblEnter = new JLabel("Enter the numbers of the questions you want to insert:");
    lblEnter.setBounds(40, 205, 356, 14);
    frame.getContentPane().add(lblEnter);
  }
  
  @SuppressWarnings("unchecked")
  private void showQuestionsList(@SuppressWarnings("rawtypes") JList list, String questions[]) {
    @SuppressWarnings("rawtypes")
    DefaultListModel dlm = new DefaultListModel();
    for(int i = 1; i < questions.length+1; i++) {
      dlm.addElement(i + ". " + questions[i-1]);
    }
    list.setModel(dlm);
  }
}

