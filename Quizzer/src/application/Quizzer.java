package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import frontend.Assign_questions_gui;
import frontend.Create_mc;
import frontend.View_questions_gui;

import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;


public class Quizzer extends Window {
	
	public final static Color FOREGROUND = new Color(124,252,0);
	public final static Color BUTTON = Color.BLACK;
	public final static Color BACKGROUND = new Color(119, 136, 153);
	
	public final static int BTN_X = 158;
	public final static int BTN_Y = 53;
	
	public static final int DETAIL_BTN_X = 312;
	public static final int DETAIL_BTN_Y = 23;
	
	public final static Font QUIZZERFONT = new Font("Centaur", Font.PLAIN, 40);
	public final static Font BOLDQUIZZERFONT = new Font("Tahoma", Font.BOLD, 11);
	public final static Font BIGBOLDQUIZZERFONT = new Font("Tahoma", Font.BOLD, 18);
		

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
		super();
	}
    
	/**
	 * Create the application given that the database already exists.
	 */
	public Quizzer(boolean set) {
		super(set);
	}



	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(BACKGROUND);
		frame.setBounds(100, 100, 604, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setFocusable(true);
		// Helper function that creates KeyEvent listener to return to login if shift+Q is pressed.
		Quizzer.LoginListener(frame, setup);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(null);
		
		//btnSetup is a button that allows you to setup or clear the database
		JButton btnSetup = new JButton("Setup Database");
		btnSetup.setBackground(BUTTON);
		btnSetup.setForeground(FOREGROUND);
		btnSetup.setBounds(10, 130, BTN_X, BTN_Y);
		btnSetup.setFocusable(false);
		frame.getContentPane().add(btnSetup);
		
		//btnCreate creates a button to let you create a new multiple choice question
		final JButton btnCreate = new JButton("New MC Question");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Create_mc mc = new Create_mc();
			//create new window for MC question, then switch to it.
			mc.frame.setVisible(true);
			frame.dispose();
			}
		});
		
		//If the database hasn't been setup, btnCreate is greyed out to the user
		btnCreate.setEnabled(setup);
		btnCreate.setBackground(BUTTON);
		btnCreate.setForeground(FOREGROUND);
		btnCreate.setBounds(196, 130, BTN_X, BTN_Y);
		btnCreate.setFocusable(false);
		frame.getContentPane().add(btnCreate);
		
		//Title Label at top
		JLabel lblQuizzer = new JLabel("Quizzer");
		lblQuizzer.setFont(QUIZZERFONT);
		lblQuizzer.setForeground(FOREGROUND);
		lblQuizzer.setBounds(222, 11, 212, 65);
		frame.getContentPane().add(lblQuizzer);
		
		//btnView creates a button that opens a new window to view assignment
		final JButton btnView = new JButton("View Assignment");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//create new gui to view questions
				View_questions_gui aq = new View_questions_gui();
				aq.student = false;
				aq.frame.setVisible(true);
				frame.dispose();
			}
		});
		
		//btnAssign creates a button that lets you assign questions to students
		final JButton btnAssign = new JButton("Assign Questions");
		btnAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Assign_questions_gui aq = new Assign_questions_gui();
				aq.frame.setVisible(true);
				frame.dispose();
			}
		});
    
		//btnView creates a button that lets you view assigned questions
		btnView.setBackground(BUTTON);
		btnView.setForeground(FOREGROUND);
		btnView.setBounds(10, 230, BTN_X, BTN_Y);
		btnView.setFocusable(false);
		//If the database hasn't been setup, btnView is greyed out to the user
		btnView.setEnabled(setup);
		frame.getContentPane().add(btnView);  
		
		//If the database hasn't been setup, btnAssign is greyed out to the user
		btnAssign.setEnabled(setup);
		btnAssign.setBackground(BUTTON);
		btnAssign.setForeground(FOREGROUND);
		btnAssign.setFocusable(false);
		btnAssign.setBounds(391, 130, 166, 53);
		frame.getContentPane().add(btnAssign);
		
		//A label to tell the user how to return to login screen
		JLabel lblReturn = new JLabel("Press Shift+Q to return to the Login screen.");
		lblReturn.setBounds(10, 306, 454, 46);
		lblReturn.setForeground(FOREGROUND);
		lblReturn.setFont(BOLDQUIZZERFONT);
		frame.getContentPane().add(lblReturn);
	
		//adds functionality to database setup button
		btnSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnSetup.setFocusPainted(false);
					if (backend.DataSetupTool.initialize()) {
					JOptionPane.showMessageDialog(new JLabel(), "Database successfully setup", "Success", JOptionPane.INFORMATION_MESSAGE);
					btnCreate.setEnabled(true);
					btnAssign.setEnabled(true);
					btnView.setEnabled(true);
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
	// Switch to a Quizzer frame.
	public static void Start(JFrame old_frame, boolean setup) {
		Quizzer app = new Quizzer(setup);
        app.frame.setVisible(setup);
        old_frame.dispose();
	}
	
	// Listen to key inputs to return to login screen.
	public static void LoginListener(JFrame old_frame, boolean set) {
		// Remove focus from all other components inside the frame so only the frame needs a keylistener
		for (Component c: old_frame.getContentPane().getComponents()) {
			c.setFocusable(false);
		}
		old_frame.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent ke) {
				// If the input is a 'Q' then return to login.
				if (ke.getKeyChar()==('Q')) {
					Login app = new Login(set);
					app.frame.setVisible(true);
					old_frame.dispose();
				}
			}

			@Override
			public void keyReleased(KeyEvent ke) {
				// Empty stub, only keyPressed is required
			}

			@Override
			public void keyTyped(KeyEvent ke) {
				// Empty stub, only keyPressed is required
			}});
	}
	
}
