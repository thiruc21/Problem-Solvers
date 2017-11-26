package application_validation;

import java.awt.Dimension;

import javax.swing.JFrame;


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

import application.Quizzer;
import frontend.Create_mc;

// This test will demonstrate that user story 1 is completed
/** 1. Initialize database
 *  2. Create a multiple choice question
 */
public class CreateMCTest extends AssertJSwingTestCaseTemplate {
	
	protected FrameFixture frame;
	
	private JButtonFixture back;
	

    
    private JButtonFixture setupDb;
    private JButtonFixture createMC;
	@SuppressWarnings("unused")
	private JButtonFixture viewQ;
    @SuppressWarnings("unused")
	private JButtonFixture assgn;
    private JButtonFixture createNew;

	private JLabelFixture question;

	private JLabelFixture answer;


	@Before
	public final void setup() {
		this.setUpRobot();
		// Open quizzer
		JFrame gui = GuiActionRunner.execute(new GuiQuery<JFrame>() {
			@Override
			protected JFrame executeInEDT() throws Exception {
				Quizzer app = new Quizzer();
				app.frame.setPreferredSize(new Dimension(604, 402));
				app.frame.pack();
		        app.frame.setVisible(true);
				return app.frame;
			}});
		this.frame = new FrameFixture(this.robot(), gui);
		this.frame.show();
		
		this.setupDb = this.frame.button(JButtonMatcher.withText("Setup Database"));
		this.createMC = this.frame.button(JButtonMatcher.withText("New MC Question"));
		this.assgn = this.frame.button(JButtonMatcher.withText("Assign Questions"));
		this.viewQ = this.frame.button(JButtonMatcher.withText("View Assignment"));
		
		setupDb.click();
		this.frame.dialog().button().click();
		
	}
	
	@Test
	public void test_create() {
		createMC.click();
		// Open create mc
		JFrame gui = GuiActionRunner.execute(new GuiQuery<JFrame>() {
			@Override
			protected JFrame executeInEDT() throws Exception {
				Create_mc app = new Create_mc();
				app.frame.setPreferredSize(new Dimension(604, 402));
				app.frame.pack();
		        app.frame.setVisible(true);
				return app.frame;
			}});
		this.frame = new FrameFixture(this.robot(), gui);
		this.frame.show();
		this.createNew = this.frame.button(JButtonMatcher.withText("Create New"));
		this.back = this.frame.button(JButtonMatcher.withText("Back"));
		this.question = this.frame.label(JLabelMatcher.withText("Question"));
		this.answer = this.frame.label(JLabelMatcher.withText("Answer: "));
		createNew.requireVisible().requireEnabled();
		back.requireVisible().requireEnabled();
		question.requireVisible().requireEnabled();
		answer.requireVisible().requireEnabled();	
		createNew.click();
		this.frame.dialog().textBox().enterText("0\n");
		this.frame.dialog().textBox().enterText("What's your name?\n");
		this.frame.dialog().textBox().enterText("Abe\n");
		this.frame.dialog().textBox().enterText("Brian\n");
		this.frame.dialog().textBox().enterText("Chengli\n");
		this.frame.dialog().textBox().enterText("Daniel\n");
		this.frame.dialog().textBox().enterText("Emily\n");
		this.frame.dialog().textBox().enterText("Frank\n");
		this.frame.dialog().textBox().enterText("3\n");
		this.frame.dialog().label(JLabelMatcher.withText("Question was successfully created")).requireVisible();
		this.frame.dialog().button().click();
	}
	
	@After
	public void tearDown() {
		this.frame = null;
		cleanUp();
	}
}
