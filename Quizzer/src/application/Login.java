package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import backend.DataFillTool;
import backend.DataQueryTool;

import java.awt.Color;

public class Login {

	public JFrame frame;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private boolean setup;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
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
	public Login(boolean set) {
		setup = set;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Quizzer.BACKGROUND);
		frame.setBounds(100, 100, 604, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Quizzer Login");
		lblLogin.setFont(Quizzer.QUIZZERFONT);
		lblLogin.setForeground(Quizzer.FOREGROUND);
		lblLogin.setBounds(186, 11, 310, 65);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUsername.setBounds(47, 111, 212, 43);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword.setBounds(47, 199, 212, 43);
		frame.getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (validInputs()) {
					String role = DataQueryTool.user_query(txtUsername.getText().trim().toUpperCase(), txtPassword.getText().trim());
					if (role.equals("a")) {
						Quizzer.Start(frame, true, setup);
					} else if (role.equals("u")) {
						Quizzer.Start(frame, false, setup);	
					} else if (role.equals("f")) {
						JOptionPane.showMessageDialog(new JLabel(), "Incorrect username or password", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(new JLabel(), "Invalid inputs. Verify that username and password are 4-12 characters long.", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnLogin.setBounds(420, 276, 158, 53);
		btnLogin.setBackground(Quizzer.BUTTON);
		btnLogin.setForeground(Quizzer.FOREGROUND);
		btnLogin.setFont(Quizzer.BOLDQUIZZERFONT);
		btnLogin.setEnabled(setup);
		frame.getContentPane().add(btnLogin);
		
		txtUsername = new JTextField();
		txtUsername.setName("user");
		txtUsername.setBounds(204, 111, 213, 43);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setName("pass");
		txtPassword.setBounds(204, 202, 213, 43);
		frame.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		

		// Student user signup
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!validInputs()) {
					JOptionPane.showMessageDialog(new JLabel(), "Invalid inputs. Verify that username and password are 4-12 characters long.", "Error", JOptionPane.INFORMATION_MESSAGE);
				// modify to only search under user combination
				} else if (DataQueryTool.user_exists(txtUsername.getText().trim().toUpperCase())) {
					JOptionPane.showMessageDialog(new JLabel(), "Invalid inputs. User already exists!", "Error", JOptionPane.INFORMATION_MESSAGE);
				} else {
					DataFillTool.addStudentUser(txtUsername.getText().trim().toUpperCase(), txtPassword.getText().trim());
					JOptionPane.showMessageDialog(new JLabel(), "User successfully created!", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnSignUp.setForeground(new Color(124, 252, 0));
		btnSignUp.setBackground(Color.BLACK);
		btnSignUp.setFont(Quizzer.BOLDQUIZZERFONT);
		btnSignUp.setBounds(235, 276, 158, 53);
		btnSignUp.setEnabled(setup);
		frame.getContentPane().add(btnSignUp);
		
		JButton btnSetup = new JButton("Setup Database");
		btnSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSetup.setFocusPainted(false);
				try {
					if (backend.DataSetupTool.initialize()) {
						JOptionPane.showMessageDialog(new JLabel(), "Database successfully setup", "Success", JOptionPane.INFORMATION_MESSAGE);
						btnLogin.setEnabled(true);
						btnSignUp.setEnabled(true);
						setup = true;
					} else {
						JOptionPane.showMessageDialog(new JLabel(), "Database setup was not successful", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new JLabel(), "Database setup was not successful", "Error", JOptionPane.INFORMATION_MESSAGE);
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
				}
			}
		});
		btnSetup.setForeground(new Color(124, 252, 0));
		btnSetup.setBackground(Color.BLACK);
		btnSetup.setFont(Quizzer.BOLDQUIZZERFONT);
		btnSetup.setBounds(47, 276, 158, 53);
		frame.getContentPane().add(btnSetup);
		
	}
	
	// Checks if username and password textfield are valid inputs
	public boolean validInputs() {
		String user = txtUsername.getText().trim();
		String pass = txtPassword.getText().trim();
		if (!(user.isEmpty()) && (!pass.isEmpty())) {
				return((user.length() >= 4 && user.length() <= 12) && (pass.length() >= 4 && pass.length() <= 12));
		}
		return false;
	}
		
		
}


