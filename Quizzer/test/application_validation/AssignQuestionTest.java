package application_validation;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JList;


import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.core.matcher.JLabelMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JLabelFixture;
import org.assertj.swing.fixture.JListFixture;

import org.assertj.swing.fixture.JScrollPaneFixture;
import org.assertj.swing.testing.AssertJSwingTestCaseTemplate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.Quizzer;
import frontend.Assign_questions_gui;
import frontend.Create_mc;

//This test will validate that userstory 2 is working
/** 1. Initialize database and create questions
 *  2. View questions, and assign one.
 */
public class AssignQuestionTest extends AssertJSwingTestCaseTemplate {

		protected FrameFixture frame;
		
		private JButtonFixture assignQuestions;
		private JButtonFixture back;
		private JLabelFixture selectQuestions;
		private JScrollPaneFixture questions;
	    
	    private JButtonFixture setupDb;
	    private JButtonFixture createMC;
		@SuppressWarnings("unused")
		private JButtonFixture viewQ;
	    private JButtonFixture assgn;
	    private JButtonFixture createNew;
	    private JList<?> assignList;
	    @SuppressWarnings("unused")
		private JListFixture assignListItem;
	    private JButtonFixture assignButton;

	    @SuppressWarnings("unused")
		private JListFixture viewListItem;


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
			createMC.click();
			// Open create mc
			gui = GuiActionRunner.execute(new GuiQuery<JFrame>() {
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
			this.frame.dialog().button().click();
			this.back = this.frame.button(JButtonMatcher.withText("Back"));
			back.click();
			
			gui = GuiActionRunner.execute(new GuiQuery<JFrame>() {
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
			
	}
	
	@Test
	public void test_assign() {
		this.assgn = this.frame.button(JButtonMatcher.withText("Assign Questions"));
		assgn.click();
		JFrame gui = GuiActionRunner.execute(new GuiQuery<JFrame>() {
		@Override
		protected JFrame executeInEDT() throws Exception {
			Assign_questions_gui app = new Assign_questions_gui();
			app.frame.setPreferredSize(new Dimension(604, 402));
			app.frame.pack();
			assignList = app.list;
	        app.frame.setVisible(true);
			return app.frame;
		}});
		this.frame = new FrameFixture(this.robot(), gui);
		this.frame.show();
		// Assigning what is your name? question
		this.assignListItem = new JListFixture(this.robot(), assignList).clickItem(0);
		this.assignButton = this.frame.button(JButtonMatcher.withText("Assign questions"));
		assignButton.click();
		// Verify components are visible
		this.assignQuestions = this.frame.button(JButtonMatcher.withText("Assign questions"));
		this.back = this.frame.button(JButtonMatcher.withText("Back"));
		this.selectQuestions = this.frame.label(JLabelMatcher.withText("Select the questions to assign:"));
		this.questions = this.frame.scrollPane();
		assignQuestions.requireVisible().requireEnabled();
		back.requireVisible().requireEnabled();
		selectQuestions.requireVisible().requireEnabled();
		questions.requireVisible().requireEnabled();
	
	}
	
	@After
	public void tearDown() {
		this.frame = null;
		cleanUp();
	}
}
