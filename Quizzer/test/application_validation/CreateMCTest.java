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
import org.junit.Before;
import org.junit.Test;

import frontend.Create_mc;

// This test will demonstrate that user story 1 is completed
public class CreateMCTest extends AssertJSwingTestCaseTemplate {
	
	protected FrameFixture frame;

    private JButtonFixture createNew;
    private JButtonFixture back;
    private JLabelFixture question;
    private JLabelFixture answer;
    
	@Before
	public final void setup() {
		this.setUpRobot();
		JFrame gui = GuiActionRunner.execute(new GuiQuery<JFrame>() {
			@Override
			protected JFrame executeInEDT() throws Exception {
				Create_mc app = new Create_mc();
				app.frame.setPreferredSize(new Dimension(510, 421));
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
	}
	
	@Test
	public void test() {
		createNew.requireVisible().requireEnabled();
		back.requireVisible().requireEnabled();
		question.requireVisible().requireEnabled();
		answer.requireVisible().requireEnabled();
	}
}
