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
	 * Create the application given that the database already exists.
	 */
	public Quizzer(boolean set) {
		setup = set;
		initialize();
	}
  
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
		
		JLabel lblQuizzer = new JLabel("Quizzer");
		lblQuizzer.setFont(QUIZZERFONT);
		lblQuizzer.setForeground(FOREGROUND);
		lblQuizzer.setBounds(222, 11, 212, 65);
		frame.getContentPane().add(lblQuizzer);
		
		final JButton btnView = makeButton(frame, "View Assignment", COLUMN_X[0], ROW_Y[1], BTN_X, BTN_Y, setup);
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				View_questions_gui aq = new View_questions_gui();
				aq.student = false;
				aq.frame.setVisible(true);
				frame.dispose();
			}
		});
    
		final JButton btnAssign = makeButton(frame, "Assign Questions", COLUMN_X[2], ROW_Y[0], BTN_X, BTN_Y, setup);
		btnAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Assign_questions_gui aq = new Assign_questions_gui();
				aq.frame.setVisible(true);
				frame.dispose();
			}
		});
    
		JLabel lblReturn = new JLabel("Press Shift+Q to return to the Login screen.");
		lblReturn.setBounds(10, 306, 454, 46);
		lblReturn.setForeground(FOREGROUND);
		lblReturn.setFont(BOLDQUIZZERFONT);
		frame.getContentPane().add(lblReturn);
    
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
