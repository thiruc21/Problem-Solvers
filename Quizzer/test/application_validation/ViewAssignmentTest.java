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
//import org.assertj.swing.fixture.JRadioButtonFixture;
import org.assertj.swing.fixture.JScrollPaneFixture;
import org.assertj.swing.testing.AssertJSwingTestCaseTemplate;
import org.junit.Before;
import org.junit.Test;

import frontend.View_questions_gui;;

//This test will validate that userstory 3 is working
public class ViewAssignmentTest extends AssertJSwingTestCaseTemplate{

	protected FrameFixture frame;

    private JButtonFixture startAssignment;
    private JButtonFixture back;
    private JLabelFixture question;
    private JLabelFixture answer;
    private JLabelFixture selectQuestion;
//    private JRadioButtonFixture ans1;
//    private JRadioButtonFixture ans2;
//    private JRadioButtonFixture ans3;
//    private JRadioButtonFixture ans4;
//    private JRadioButtonFixture ans5;
//    private JRadioButtonFixture ans6;
    private JScrollPaneFixture scrollPane;
    
	@Before
	public final void setup() {
		this.setUpRobot();
		JFrame gui = GuiActionRunner.execute(new GuiQuery<JFrame>() {
			@Override
			protected JFrame executeInEDT() throws Exception {
				View_questions_gui app = new View_questions_gui();
				app.frame.setPreferredSize(new Dimension(850, 421));
				app.frame.pack();
		        app.frame.setVisible(true);
				return app.frame;
			}});
		this.frame = new FrameFixture(this.robot(), gui);
		this.frame.show();
		this.startAssignment = this.frame.button(JButtonMatcher.withText("Start assignment"));
		this.back = this.frame.button(JButtonMatcher.withText("Back"));
		this.question = this.frame.label(JLabelMatcher.withText("Question"));
		this.answer = this.frame.label(JLabelMatcher.withText(""));
		this.selectQuestion = this.frame.label(JLabelMatcher.withText("Select the question to view:"));
//		this.ans1 = this.frame.radioButton();
//		this.ans2 = this.frame.radioButton();
//		this.ans3 = this.frame.radioButton();
//		this.ans4 = this.frame.radioButton();
//		this.ans5 = this.frame.radioButton();
//		this.ans6 = this.frame.radioButton();
		this.scrollPane = this.frame.scrollPane();
	}
	
	@Test
	public void test() {
		startAssignment.requireVisible().requireEnabled();
		back.requireVisible().requireEnabled();
		question.requireVisible().requireEnabled();
		answer.requireVisible().requireEnabled();
		selectQuestion.requireVisible().requireEnabled();
//		ans1.requireVisible().requireEnabled();
//		ans2.requireVisible().requireEnabled();
//		ans3.requireVisible().requireEnabled();
//		ans4.requireVisible().requireEnabled();
//		ans5.requireVisible().requireEnabled();
//		ans6.requireVisible().requireEnabled();
		scrollPane.requireVisible().requireEnabled();
	}

}
