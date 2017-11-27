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

import java.io.File;


public class Quizzer {
	
	public final static Color FOREGROUND = new Color(124,252,0);
	public final static Color BUTTON = Color.BLACK;
	public final static Color BACKGROUND = new Color(119, 136, 153);
	
	public final static int BTN_X = 158;
	public final static int BTN_Y = 53;
	
	public static final int DETAIL_BTN_X = 312;
	public static final int DETAIL_BTN_Y = 23;
  
	public static final int[] ROW_Y = {130, 230};
    
	public static final int[] COLUMN_X = {10, 196, 391};
  
	
	public final static Font QUIZZERFONT = new Font("Centaur", Font.PLAIN, 40);
	public final static Font BOLDQUIZZERFONT = new Font("Tahoma", Font.BOLD, 11);
	public final static Font BIGBOLDQUIZZERFONT = new Font("Tahoma", Font.BOLD, 18);
		
	private boolean setup;
	public JFrame frame;
	private boolean admin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
    
	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quizzer window = new Quizzer(true);
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
	public Quizzer(boolean admin) {
		this.admin = admin;
		File chk_exist = new File("quizzer.db");
		if (chk_exist.exists() && !chk_exist.isDirectory()) {
			setup = true;
		} else {
			setup = false;
		}
		initialize();
	}
	
	/**
	 * Create the application given that the database already exists.
	 */
	public Quizzer(boolean admin, boolean set) {
		this.admin = admin;
		setup = set;
		initialize();
	}
  
	//factory-like method to make buttons
	private JButton makeButton(JFrame target, String label, int x, int y, int w, int h, boolean enabled) {
		JButton newButton = new JButton(label);
		newButton.setBounds(x, y, w, h);
		newButton.setBackground(BUTTON);
		newButton.setForeground(FOREGROUND);
		newButton.setFocusable(false);
		newButton.setEnabled(enabled);
		target.getContentPane().add(newButton);
		return newButton;
	}

	private int getAssignmentIdFromUser(String question) {
		Object[] choices = backend.DataQueryTool.get_assignment_names().toArray();
				
		String assignmentName = (String)JOptionPane.showInputDialog(frame, question, "Choose assignment", JOptionPane.PLAIN_MESSAGE, null, choices, choices[0]);
		if (null == assignmentName) return -1;
        return backend.DataQueryTool.get_assignment_id(assignmentName);
		
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
		frame.setFocusable(true);
		// Helper function that creates KeyEvent listener to return to login if shift+Q is pressed.
		Quizzer.LoginListener(frame, setup);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(null);

		final JButton btnView = makeButton(frame, "View Assignment", COLUMN_X[2], ROW_Y[1], BTN_X, BTN_Y, setup);
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int assignment_id = getAssignmentIdFromUser("Which assignment to view?\n");
				if (-1 == assignment_id) return;
				View_questions_gui aq = new View_questions_gui(assignment_id);
				aq.student = false;
				aq.frame.setVisible(true);
				frame.dispose();
			}
		});
		
		if (admin) {
			JButton btnSetup = makeButton(frame, "Setup Database", COLUMN_X[0], ROW_Y[0], BTN_X, BTN_Y, true);
			frame.getContentPane().add(btnSetup);
			
			final JButton btnCreate = makeButton(frame, "New MC Question", COLUMN_X[1], ROW_Y[0], BTN_X, BTN_Y, setup);
			btnCreate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				Create_mc mc = new Create_mc();
				mc.frame.setVisible(true);
				frame.dispose();
				}
			});
			
			
			
			final JButton btnCreateA = makeButton(frame, "Create Assignment", COLUMN_X[0], ROW_Y[1], BTN_X, BTN_Y, setup);
			btnCreateA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String assignmentName = JOptionPane.showInputDialog(null, "What would you like to name the assignment??", "New assignment", JOptionPane.INFORMATION_MESSAGE);
					if (null == assignmentName) {
						JOptionPane.showMessageDialog(new JLabel(), "Please enter a name", "Name needed", JOptionPane.INFORMATION_MESSAGE);
					} else {
						try {
							int new_ass_id = backend.DataFillTool.createAssignment(assignmentName);
							
							Assign_questions_gui aq = new Assign_questions_gui(new_ass_id);
							aq.frame.setVisible(true);
							frame.dispose();
						} catch (IllegalArgumentException e){
							JOptionPane.showMessageDialog(new JLabel(), "Unable to create assignment.", "Error", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
		
			final JButton btnAssign = makeButton(frame, "Assign Questions", COLUMN_X[1], ROW_Y[1], BTN_X, BTN_Y, setup);
			btnAssign.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int assignment_id = getAssignmentIdFromUser("Which assignment to modify?\n");
					if (-1 == assignment_id) return;
					Assign_questions_gui aq = new Assign_questions_gui(assignment_id);
					aq.frame.setVisible(true);
					frame.dispose();
				}
			});
			
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
    
		JLabel lblReturn = new JLabel("Press Shift+Q to return to the Login screen.");
		lblReturn.setBounds(10, 306, 454, 46);
		lblReturn.setForeground(FOREGROUND);
		lblReturn.setFont(BOLDQUIZZERFONT);
		frame.getContentPane().add(lblReturn);
		
		JLabel lblQuizzer;
		if (admin) {
			lblQuizzer = new JLabel("Quizzer (Admin)");
			lblQuizzer.setBounds(165, 11, 356, 65);
		} else {
			lblQuizzer = new JLabel("Quizzer");
			lblQuizzer.setBounds(222, 11, 212, 65);
		}
		lblQuizzer.setFont(QUIZZERFONT);
		lblQuizzer.setForeground(FOREGROUND);
		
		frame.getContentPane().add(lblQuizzer);

    
		
		
	}
	// Switch to a Quizzer frame.
	public static void Start(JFrame old_frame, boolean admin, boolean setup) {
		Quizzer app = new Quizzer(admin, setup);
		app.frame.setVisible(true);
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
