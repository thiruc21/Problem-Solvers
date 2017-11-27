package application_validation;

import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JList;

import org.assertj.swing.core.matcher.JButtonMatcher;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JListFixture;
import org.assertj.swing.fixture.JRadioButtonFixture;
import org.assertj.swing.testing.AssertJSwingTestCaseTemplate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.*;
import frontend.Assign_questions_gui;


// This test will verify that userstory 8 is working
/** 1. Initialize database 
 *  2. Create two assignments
 * 	3. Click button to view assignment
 *  4. Using the drop down menu, select the second assignment 
 */
public class MultipleAssignmentTest extends AssertJSwingTestCaseTemplate {

	protected FrameFixture frame;
	
    private JButtonFixture setupDb;
	private JButtonFixture viewQ;
    @SuppressWarnings("unused")
	private JButtonFixture assgn;
    private JButtonFixture back;
    @SuppressWarnings("unused")
	private JList<?> assignList;
    @SuppressWarnings("unused")
	private JListFixture viewListItem;
	@SuppressWarnings("unused")
	private JRadioButtonFixture displayListItem;

	
	private JButtonFixture createAssignment;

	@Before
	public final void setup() {
		this.setUpRobot();
		// Open quizzer
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
		// Setup the database
		this.setupDb = this.frame.button(JButtonMatcher.withText("Setup Database"));
		
		setupDb.click();
		this.frame.dialog().button().click();
		
		
		
	}
	
	@Test
	public void test_creating() {
		
		// Create assignment
		this.createAssignment = this.frame.button(JButtonMatcher.withText("Create Assignment"));
		this.createAssignment.requireVisible().requireEnabled().click();
		
		this.frame.dialog().textBox().enterText("Alpha\n");
		JFrame gui = GuiActionRunner.execute(new GuiQuery<JFrame>() {
			@Override
			protected JFrame executeInEDT() throws Exception {
				Assign_questions_gui app = new Assign_questions_gui(1);
				app.frame.setPreferredSize(new Dimension(604, 402));
				app.frame.pack();
				assignList = app.list; 
		        app.frame.setVisible(true);
				return app.frame;
			}});
		this.frame = new FrameFixture(this.robot(), gui);
		this.back = this.frame.button(JButtonMatcher.withText("Back"));
		back.click();
		
		gui = GuiActionRunner.execute(new GuiQuery<JFrame>() {
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
		// Click view assignment, verify only one assignment shows.
		this.viewQ = this.frame.button(JButtonMatcher.withText("View Assignment"));
		viewQ.click();
		this.frame.dialog().comboBox().requireItemCount(1);
		this.frame.dialog().comboBox().requireSelection("Alpha");
		this.frame.dialog().button(JButtonMatcher.withText("Cancel")).click();
		
		// Create second assignment
		this.createAssignment = this.frame.button(JButtonMatcher.withText("Create Assignment"));
		this.createAssignment.requireVisible().requireEnabled().click();
		// Assign questions to second assignment
		this.frame.dialog().textBox().enterText("Beta\n");
		gui = GuiActionRunner.execute(new GuiQuery<JFrame>() {
			@Override
			protected JFrame executeInEDT() throws Exception {
				Assign_questions_gui app = new Assign_questions_gui(1);
				app.frame.setPreferredSize(new Dimension(604, 402));
				app.frame.pack();
				assignList = app.list; 
		        app.frame.setVisible(true);
				return app.frame;
			}});
		this.frame = new FrameFixture(this.robot(), gui);
		this.back = this.frame.button(JButtonMatcher.withText("Back"));
		back.click();
		
		gui = GuiActionRunner.execute(new GuiQuery<JFrame>() {
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
		// Select to view the second assignment, verify 2 options are available then finish.
		this.viewQ = this.frame.button(JButtonMatcher.withText("View Assignment"));
		viewQ.click();
		this.frame.dialog().comboBox().requireItemCount(2);
		this.frame.dialog().comboBox().selectItem(1);
		this.frame.dialog().comboBox().requireSelection("Beta");
		this.frame.dialog().button(JButtonMatcher.withText("Cancel")).click();
		
	}
	
	@After
	public void tearDown() {
		this.frame = null;
		cleanUp();
	}

}
