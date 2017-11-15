package application;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import frontend.View_questions_gui;

public class QuizzerStudent {
	
	public final Color FOREGROUND = Color.GREEN;
	public final Color BUTTON = Color.BLACK;
	public final Color BACKGROUND = new Color(119, 136, 153);
		
  private boolean setup;
  public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
    
    EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizzerStudent window = new QuizzerStudent();
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
	public QuizzerStudent() {
    File chk_exist = new File("quizzer.db");
    if (chk_exist.exists() && !chk_exist.isDirectory()) {
      setup = true;
    } else {
      setup = false;
    }
		initialize();
	}
	/**
	 * Create the application.
	 */
	public QuizzerStudent(boolean set) {
		setup = set;
		initialize();
	}



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(BACKGROUND);
		frame.setBounds(100, 100, 604, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblQuizzer = new JLabel("Quizzer");
		lblQuizzer.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblQuizzer.setForeground(FOREGROUND);
		lblQuizzer.setBounds(222, 11, 212, 65);
		frame.getContentPane().add(lblQuizzer);
		
	    final JButton btnView = new JButton("View Assignment");
			btnView.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					View_questions_gui aq = new View_questions_gui();
					aq.student = true;
					aq.frame.setVisible(true);
					frame.dispose();
				}
			});
	    
	    if (!setup)
				btnView.setEnabled(false);
			btnView.setBackground(BUTTON);
			btnView.setForeground(FOREGROUND);
			btnView.setBounds(10, 130, 158, 53);
			frame.getContentPane().add(btnView); 

	}
}
