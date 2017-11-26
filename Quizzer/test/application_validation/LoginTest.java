package application_validation;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.assertj.swing.core.matcher.DialogMatcher;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.testing.AssertJSwingTestCaseTemplate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.Login;

public class LoginTest extends AssertJSwingTestCaseTemplate {

	protected FrameFixture frame;

    private JButtonFixture setupDb;
    private JButtonFixture login;
    
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
		this.login = this.frame.button(JButtonMatcher.withText("Login"));
	}
	
	@Test
	public void testMainMenu() {
		setupDb.requireVisible().requireEnabled().click();
		frame.dialog(DialogMatcher.withTitle("Success"));
		this.frame.dialog().button().click();
		login.requireVisible().requireEnabled().click();
		frame.dialog(DialogMatcher.withTitle("Error"));
		this.frame.dialog().button().click();
	}
	
	@After
	public void tearDown() {
		this.frame = null;
		cleanUp();
	}

}