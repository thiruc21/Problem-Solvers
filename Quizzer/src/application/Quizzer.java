package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import frontend.Add_questions_gui;
import frontend.Create_mc;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Quizzer {
	private boolean setup = false;
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
		frame.getContentPane().setBackground(Color.BLUE);
		frame.setBounds(100, 100, 604, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSetup = new JButton("Setup database");
		btnSetup.setBackground(Color.YELLOW);
		btnSetup.setForeground(Color.BLACK);
		btnSetup.setBounds(10, 130, 158, 53);
		frame.getContentPane().add(btnSetup);
		
		final JButton btnCreate = new JButton("Create MC Question");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Create_mc mc = new Create_mc();
			mc.frame.setVisible(true);
			frame.dispose();
			}
		});
		if (!setup)
			btnCreate.setEnabled(false);
		btnCreate.setBackground(Color.YELLOW);
		btnCreate.setForeground(Color.BLACK);
		btnCreate.setBounds(196, 130, 158, 53);
		frame.getContentPane().add(btnCreate);
		
		JLabel lblQuizzer = new JLabel("Quizzer");
		lblQuizzer.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblQuizzer.setForeground(Color.YELLOW);
		lblQuizzer.setBounds(222, 11, 212, 65);
		frame.getContentPane().add(lblQuizzer);
		
		final JButton btnAssign = new JButton("Assign questions");
		btnAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Add_questions_gui aq = new Add_questions_gui();
				aq.frame.setVisible(true);
				frame.dispose();
			}
		});
		if (!setup)
			btnAssign.setEnabled(false);
		btnAssign.setBackground(Color.YELLOW);
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
	}
}
