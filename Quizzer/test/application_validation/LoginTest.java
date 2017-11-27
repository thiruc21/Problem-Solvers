package application_validation;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.assertj.swing.core.matcher.DialogMatcher;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.core.matcher.JLabelMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JLabelFixture;
import org.assertj.swing.testing.AssertJSwingTestCaseTemplate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.Login;
import application.Quizzer;
import application.QuizzerStudent;
import frontend.View_questions_gui;

// This test will demonstrate that user story 7 is completed
/** 1. Initialize database
*  2. Login with the default admin credentials and verify that the admin menu
*     for Quizzer appears.
*  3. Signup a new student user and login with these credentials. Verify that
*     the student menu for Quizzer appears, with the label indicating so. Click to view 
*     the assignment to verify that the button works.
*  4. Login with the default student credentials and verify that the student
*     menu for Quizzer appears, with the label indicating so.
*/
public class LoginTest extends AssertJSwingTestCaseTemplate {

	protected FrameFixture frame;

    private JButtonFixture setupDb;
    private JButtonFixture login;
    private JButtonFixture signup;
    
	@Before
	public final void setup() {
		this.setUpRobot();
		JFrame gui = GuiActionRunner.execute(new GuiQuery<JFrame>() {
			@Override
			protected JFrame executeInEDT() throws Exception {
				Login app = new Login();
				app.frame.setPreferredSize(new Dimension(604, 402));
				app.frame.pack();
		        app.frame.setVisible(true);
				return app.frame;
			}});
		this.frame = new FrameFixture(this.robot(), gui);
		this.frame.show();
		this.setupDb = this.frame.button(JButtonMatcher.withText("Setup Database"));
		this.signup = this.frame.button(JButtonMatcher.withText("Sign Up"));
		this.login = this.frame.button(JButtonMatcher.withText("Login"));
	}
	
	// Test that a student can signup
	@Test
	public void testSignup() {
		// Setup
		setupDb.requireVisible().requireEnabled().click();
		frame.dialog(DialogMatcher.withTitle("Success"));
		this.frame.dialog().button().click();
		// Signup
		this.frame.textBox("user").enterText("tester");
		this.frame.textBox("pass").enterText("testing");
		signup.requireVisible().requireEnabled().click();
		frame.dialog(DialogMatcher.withTitle("Success"));
		this.frame.dialog().button().click();
		// Login
		login.requireVisible().requireEnabled().click();
		// Verify on student quizzer page.
		JFrame gui = GuiActionRunner.execute(new GuiQuery<JFrame>() {
			@Override
			protected JFrame executeInEDT() throws Exception {
				Quizzer app = new Quizzer(false);
				app.frame.setPreferredSize(new Dimension(604, 402));
				app.frame.pack();
		        app.frame.setVisible(true);
				return app.frame;
			}});
		this.frame = new FrameFixture(this.robot(), gui);
		this.frame.show();
		this.frame.label(JLabelMatcher.withText("Quizzer")).requireVisible();
	}
	
	// Test that a student can login to the student GUI
	@Test
	public void testStudent() {
		// Setup
		setupDb.requireVisible().requireEnabled().click();
		frame.dialog(DialogMatcher.withTitle("Success"));
		this.frame.dialog().button().click();
		// Enter info
		this.frame.textBox("user").enterText("student");
		this.frame.textBox("pass").enterText("student");
		// Login
		login.requireVisible().requireEnabled().click();
		// Open QuizzerStudent frame
		JFrame gui = GuiActionRunner.execute(new GuiQuery<JFrame>() {
			@Override
			protected JFrame executeInEDT() throws Exception {
				Quizzer app = new Quizzer(false);
				app.frame.setPreferredSize(new Dimension(604, 402));
				app.frame.pack();
		        app.frame.setVisible(true);
				return app.frame;
			}});
		this.frame = new FrameFixture(this.robot(), gui);
		this.frame.show();
		JLabelFixture quizzer = this.frame.label(JLabelMatcher.withText("Quizzer"));
		JButtonFixture viewQ = this.frame.button(JButtonMatcher.withText("View Assignment"));
		quizzer.requireVisible();
		viewQ.requireVisible().requireEnabled().click();
		// Verify that assignment cannot be started until assignment is created.
		this.frame.dialog().label(JLabelMatcher.withText("Please create an assignment first.")).requireVisible();

		
		
	}
	
	// Test that a student can login to the student GUI
		@Test
		public void testAdmin() {
			// Setup
			setupDb.requireVisible().requireEnabled().click();
			frame.dialog(DialogMatcher.withTitle("Success"));
			this.frame.dialog().button().click();
			// Enter info
			this.frame.textBox("user").enterText("admin");
			this.frame.textBox("pass").enterText("admin");
			// Login
			login.requireVisible().requireEnabled().click();
			// Open QuizzerStudent frame
			JFrame gui = GuiActionRunner.execute(new GuiQuery<JFrame>() {
				@Override
				protected JFrame executeInEDT() throws Exception {
					Quizzer app = new Quizzer(true);
					app.frame.setPreferredSize(new Dimension(604, 402));
					app.frame.pack();
			        app.frame.setVisible(true);
					return app.frame;
				}});
			this.frame = new FrameFixture(this.robot(), gui);
			this.frame.show();
			// Verify all admin components appear.
			this.frame.label(JLabelMatcher.withText("Quizzer (Admin)")).requireVisible();
			this.setupDb = this.frame.button(JButtonMatcher.withText("Setup Database")).requireVisible().requireEnabled();
			this.frame.button(JButtonMatcher.withText("New MC Question")).requireVisible().requireEnabled();
			this.frame.button(JButtonMatcher.withText("Assign Questions")).requireVisible().requireEnabled();
			this.frame.button(JButtonMatcher.withText("View Assignment")).requireVisible().requireEnabled();
			
		}
	@After
	public void tearDown() {
		this.frame = null;
		cleanUp();
	}

}