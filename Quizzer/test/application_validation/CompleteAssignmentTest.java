package application_validation;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.core.matcher.JLabelMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JLabelFixture;
import org.assertj.swing.testing.AssertJSwingTestCaseTemplate;
import org.junit.Before;
import org.junit.Test;

import application.*;
import frontend.Add_questions_gui;
import frontend.Create_mc;
import frontend.Do_assignment;

// This test will verify that userstory 4 is working
public class CompleteAssignmentTest extends AssertJSwingTestCaseTemplate {

	protected FrameFixture frame;
	
    private JButtonFixture setupDb;
    private JButtonFixture createMC;
    @SuppressWarnings("unused")
	private JButtonFixture viewQ;
    private JButtonFixture assgn;
    private JButtonFixture createNew;
    private JButtonFixture back;
    private JButtonFixture next;
    private JLabelFixture question;
    private JLabelFixture answer;
    private JButtonFixture assignQuestions;
		
	@Before
	public final void setup() {
		this.setUpRobot();
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
		
		JFrame gui1 = GuiActionRunner.execute(new GuiQuery<JFrame>() {
			@Override
			protected JFrame executeInEDT() throws Exception {
				Create_mc app = new Create_mc();
				app.frame.setPreferredSize(new Dimension(604, 402));
				app.frame.pack();
		        app.frame.setVisible(true);
				return app.frame;
			}});
		this.frame = new FrameFixture(this.robot(), gui1);
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
		
		createNew.click();
		this.frame.dialog().textBox().enterText("1\n");
		this.frame.dialog().textBox().enterText("What's your age?\n");
		this.frame.dialog().textBox().enterText("1\n");
		this.frame.dialog().textBox().enterText("2\n");
		this.frame.dialog().textBox().enterText("3\n");
		this.frame.dialog().textBox().enterText("4\n");
		this.frame.dialog().textBox().enterText("5\n");
		this.frame.dialog().textBox().enterText("20\n");
		this.frame.dialog().textBox().enterText("6\n");
		this.frame.dialog().button().click();
		this.back = this.frame.button(JButtonMatcher.withText("Back"));
		back.click();
		
		this.assgn = this.frame.button(JButtonMatcher.withText("Assign Questions"));
		assgn.click();

		JFrame gui2 = GuiActionRunner.execute(new GuiQuery<JFrame>() {
			@Override
			protected JFrame executeInEDT() throws Exception {
				Add_questions_gui app = new Add_questions_gui();
				app.frame.setPreferredSize(new Dimension(604, 402));
				app.frame.pack();
		        app.frame.setVisible(true);
				return app.frame;
			}});
		this.frame = new FrameFixture(this.robot(), gui2);
		new FrameFixture(this.robot(), gui2).show();
		
		this.frame.radioButton().click();
		this.assignQuestions = this.frame.button(JButtonMatcher.withText("Assign questions"));
		assignQuestions.click();
		
		List<Integer> q_ids = new ArrayList<Integer>();
		q_ids.add(0);
		
//		JFrame gui3 = GuiActionRunner.execute(new GuiQuery<JFrame>() {			
//			@Override
//			protected JFrame executeInEDT() throws Exception {
//				Do_assignment app = new Do_assignment(q_ids);
//				app.frame.setPreferredSize(new Dimension(550, 421));
//				app.frame.pack();
//		        app.frame.setVisible(true);
//				return app.frame;
//			}});
//		this.frame = new FrameFixture(this.robot(), gui3);
//		this.frame.show();
//		this.back = this.frame.button(JButtonMatcher.withText("Back"));
//		this.next = this.frame.button(JButtonMatcher.withText("Next"));
//		this.question = this.frame.label(JLabelMatcher.withText("Question"));
//		this.answer = this.frame.label(JLabelMatcher.withText("Answer: "));
	}
	
	@Test
	public void test() {
		back.requireVisible().requireEnabled();
		next.requireVisible().requireEnabled();
		question.requireVisible().requireEnabled();
		answer.requireVisible().requireEnabled();
	}
}
