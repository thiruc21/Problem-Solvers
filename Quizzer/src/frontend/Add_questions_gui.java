package gui;

import java.awt.EventQueue;
import javax.swing.*;

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
        
    String[] questions = new String[10];
    questions[0] = "a";
    questions[1] = "b";
    questions[2] = "c";
    questions[3] = "d";
    questions[4] = "e";
    questions[5] = "f";
    questions[6] = "g";
    questions[7] = "h";
    questions[8] = "i";
    questions[9] = "j";
    //checkBoxes(questions);
    listBox(questions);
  }
  
  private void listBox(String questions[]) {  
    @SuppressWarnings({"rawtypes", "unchecked"})
    JList list = new JList(questions);
    list.setVisibleRowCount(5);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane scrollPane = new JScrollPane(list);
    scrollPane.setBounds(30, 50, 200, 139);
    frame.getContentPane().add(scrollPane);
     
    JButton btnNewButton = new JButton("Add questions");
    btnNewButton.setBounds(30, 200, 120, 23);
    frame.getContentPane().add(btnNewButton);
       
  }
}

