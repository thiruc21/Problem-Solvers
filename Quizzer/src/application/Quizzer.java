package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import frontend.Add_questions_gui;
import frontend.Create_mc;
import frontend.View_questions_gui;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;


public class Quizzer {
	
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
					Quizzer window = new Quizzer();
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
	public Quizzer() {
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
	public Quizzer(boolean set) {
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
		
		JButton btnSetup = new JButton("Setup Database");
		btnSetup.setBackground(BUTTON);
		btnSetup.setForeground(FOREGROUND);
		btnSetup.setBounds(10, 130, 158, 53);
		frame.getContentPane().add(btnSetup);
		
		final JButton btnCreate = new JButton("New MC Question");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Create_mc mc = new Create_mc();
			mc.frame.setVisible(true);
			frame.dispose();
			}
		});
		if (!setup)
			btnCreate.setEnabled(false);
		btnCreate.setBackground(BUTTON);
		btnCreate.setForeground(FOREGROUND);
		btnCreate.setBounds(196, 130, 158, 53);
		frame.getContentPane().add(btnCreate);
		
		JLabel lblQuizzer = new JLabel("Quizzer");
		lblQuizzer.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblQuizzer.setForeground(FOREGROUND);
		lblQuizzer.setBounds(222, 11, 212, 65);
		frame.getContentPane().add(lblQuizzer);
		
		final JButton btnAssign = new JButton("Assign Questions");
		btnAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Add_questions_gui aq = new Add_questions_gui();
				aq.frame.setVisible(true);
				frame.dispose();
			}
		});
    
		if (!setup)
			btnAssign.setEnabled(false);
		btnAssign.setBackground(BUTTON);
		btnAssign.setForeground(FOREGROUND);
		btnAssign.setBounds(391, 130, 166, 53);
		frame.getContentPane().add(btnAssign);
		
		btnSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (backend.DataSetupTool.initialize()) {
					JOptionPane.showMessageDialog(new JLabel(), "Database successfully setup", "Success", JOptionPane.INFORMATION_MESSAGE);
					btnCreate.setEnabled(true);
					btnAssign.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(new JLabel(), "Database setup was not successful", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(new JLabel(), "Database setup was not successful", "Error", JOptionPane.INFORMATION_MESSAGE);
					System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				}
			}
		});
    
    
    final JButton btnView = new JButton("View Assignment");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				View_questions_gui aq = new View_questions_gui();
				aq.student = false;
				aq.frame.setVisible(true);
				frame.dispose();
			}
		});
    
    if (!setup)
			btnView.setEnabled(false);
		btnView.setBackground(BUTTON);
		btnView.setForeground(FOREGROUND);
		btnView.setBounds(10, 230, 158, 53);
		frame.getContentPane().add(btnView);
		
    
    
    
	}
}
