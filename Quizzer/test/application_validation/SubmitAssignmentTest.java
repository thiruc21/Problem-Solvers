package application_validation;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JRadioButton;

import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.core.matcher.JLabelMatcher;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JLabelFixture;
import org.assertj.swing.fixture.JListFixture;
import org.assertj.swing.fixture.JRadioButtonFixture;
import org.assertj.swing.testing.AssertJSwingTestCaseTemplate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.*;
import frontend.Assign_questions_gui;
import frontend.Create_mc;
import frontend.Do_assignment;
import frontend.View_questions_gui;

// This test will verify that userstory 5 is working
public class SubmitAssignmentTest extends AssertJSwingTestCaseTemplate {

	protected FrameFixture frame;
	
    private JButtonFixture setupDb;
    private JButtonFixture createMC;
	private JButtonFixture viewQ;
    private JButtonFixture assgn;
    private JButtonFixture createNew;
    private JButtonFixture back;
    private JButtonFixture next;
    private JLabelFixture question;
    private JLabelFixture answer;
    private JList<?> assignList;
    private JListFixture assignListItem;
    private JButtonFixture assignButton;
    private JList<String> viewList;
    @SuppressWarnings("unused")
	private JListFixture viewListItem;
    private JRadioButton rdb;
	@SuppressWarnings("unused")
	private JRadioButtonFixture displayListItem;
    private JButtonFixture startAssgn;

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
		this.frame.dialog().textBox().enterText("1\n");
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
		this.assgn = this.frame.button(JButtonMatcher.withText("Assign Questions"));
		assgn.click();
		gui = GuiActionRunner.execute(new GuiQuery<JFrame>() {
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
		// Assigning what is your name? question
		this.assignListItem = new JListFixture(this.robot(), assignList).clickItem(0);
		this.assignButton = this.frame.button(JButtonMatcher.withText("Assign questions"));
		assignButton.click();
		// Assign other question, what is your age?
		assignListItem.clickItem(0);
		assignButton.click();
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
		this.viewQ = this.frame.button(JButtonMatcher.withText("View Assignment"));
		viewQ.click();
		gui = GuiActionRunner.execute(new GuiQuery<JFrame>() {
			@Override
			protected JFrame executeInEDT() throws Exception {
				View_questions_gui app = new View_questions_gui();
				app.frame.setPreferredSize(new Dimension(604, 402));
				app.frame.pack(); 
				viewList = app.list;
				rdb = app.rdbtn[0];
		        app.frame.setVisible(true);
				return app.frame;
			}});
		this.frame = new FrameFixture(this.robot(), gui);
		this.frame.show();
		this.viewListItem = new JListFixture(this.robot(), viewList).clickItem(0);
		this.displayListItem = new JRadioButtonFixture(this.robot(), rdb);
		this.startAssgn = this.frame.button(JButtonMatcher.withText("Start assignment"));
		//z
		startAssgn.click();
		List<Integer> q_ids = new ArrayList<Integer>();
		q_ids.add(1);
		q_ids.add(2);
		gui = GuiActionRunner.execute(new GuiQuery<JFrame>() {			
			@Override
			protected JFrame executeInEDT() throws Exception {
				Do_assignment app = new Do_assignment(q_ids);
				app.frame.setPreferredSize(new Dimension(550, 421));
				app.frame.pack();
				rdb = app.rdbtn[0];
		        app.frame.setVisible(true);
				return app.frame;
			}});
		this.frame = new FrameFixture(this.robot(), gui);
		this.frame.show();
		this.next = this.frame.button(JButtonMatcher.withText("Next"));
		rdb.doClick();
		next.click();
		rdb.doClick();
	}
	
	@Test
	public void test_answering() {
		this.next = this.frame.button(JButtonMatcher.withText("Submit")).requireVisible();
		this.next.click();
		this.back = this.frame.button(JButtonMatcher.withText("Back"));
		this.question = this.frame.label(JLabelMatcher.withName("lblQuestion"));
		this.answer = this.frame.label(JLabelMatcher.withText("You scored: 1/2  (50.00%)"));
		back.requireVisible().requireEnabled();
		next.requireVisible().requireDisabled();
		question.requireVisible().requireEnabled();
		answer.requireVisible().requireEnabled();
		
	}
	
	@After
	public void tearDown() {
		this.frame = null;
		cleanUp();
	}

}