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
import org.assertj.swing.fixture.JScrollPaneFixture;
import org.assertj.swing.testing.AssertJSwingTestCaseTemplate;
import org.junit.Before;
import org.junit.Test;

import frontend.Add_questions_gui;

//This test will validate that userstory 2 is working
public class AssignQuestionTest extends AssertJSwingTestCaseTemplate {

	protected FrameFixture frame;
	
	private JButtonFixture assignQuestions;
	private JButtonFixture back;
	private JLabelFixture selectQuestions;
	private JScrollPaneFixture questions;
	
	@Before
	public final void setup() {
		this.setUpRobot();
		JFrame gui = GuiActionRunner.execute(new GuiQuery<JFrame>() {
			@Override
			protected JFrame executeInEDT() throws Exception {
				Add_questions_gui app = new Add_questions_gui();
				app.frame.setPreferredSize(new Dimension(604, 402));
				app.frame.pack();
		        app.frame.setVisible(true);
				return app.frame;
			}});
		this.frame = new FrameFixture(this.robot(), gui);
		this.frame.show();
		this.assignQuestions = this.frame.button(JButtonMatcher.withText("Assign questions"));
		this.back = this.frame.button(JButtonMatcher.withText("Back"));
		this.selectQuestions = this.frame.label(JLabelMatcher.withText("Select the questions to assign:"));
		this.questions = this.frame.scrollPane();
	}
	
	@Test
	public void test() {
		assignQuestions.requireVisible().requireEnabled();
		back.requireVisible().requireEnabled();
		selectQuestions.requireVisible().requireEnabled();
		questions.requireVisible().requireEnabled();
	}

}
