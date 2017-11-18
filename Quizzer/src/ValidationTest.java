import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import application.*;
import frontend.*;

public class ValidationTest {
	@SuppressWarnings("rawtypes")
	public static void main(String args[]) {
		
		Quizzer q = new Quizzer();
		QuizzerStudent qs = new QuizzerStudent();
		Add_questions_gui aqg = new Add_questions_gui();
		Create_mc cm = new Create_mc();
		View_questions_gui vqg = new View_questions_gui();
		
		Component[] qcomps = q.frame.getContentPane().getComponents();
		Component[] qscomps = qs.frame.getContentPane().getComponents();
		Component[] aqgcomps = aqg.frame.getContentPane().getComponents();
		Component[] cmcomps = cm.frame.getContentPane().getComponents();
		Component[] vqgcomps = vqg.frame.getContentPane().getComponents();
		
		Component[][] components = new Component[5][];
		components[0] = qcomps;
		components[1] = qscomps;
		components[2] = aqgcomps;
		components[3] = cmcomps;
		components[4] = vqgcomps;
		
		int p = 0;
		int n = 0;
		String text = "";
		String component = "";
		for (Component[] comps: components) {
			p = 0;
			n = 0;
			if (comps.equals(qcomps)) {
				System.out.println("In class Quizzer: \n");
			} else if (comps.equals(qscomps)) {
				System.out.println("In class QuizzerStudent: \n");
			} else if (comps.equals(aqgcomps)) {
				System.out.println("In class Add_questions_gui: \n");
			} else if (comps.equals(cmcomps)) {
				System.out.println("In class Create_mc: \n");
			} else if (comps.equals(vqgcomps)) {
				System.out.println("In class View_questions_gui: \n");
			}
			for(Component comp: comps) {
				if (comp instanceof JButton) {
					text = ((JButton) comp).getText();				
					component = "JButton";
				} else if (comp instanceof JLabel) {
					text = ((JLabel) comp).getText();	
					component = "JLabel";
				} else if (comp instanceof JList) {

				} else if (comp instanceof JRadioButton) {
					text = ((JRadioButton) comp).getText();	
					component = "JRadioButton";
				} else if (comp instanceof JScrollPane) {

				}
				
				if (comp.isShowing()) {
					System.out.println("+" + component + " '" + text + "' is showing on the screen.");
					p++;
				} else {
					System.out.println("-" + component + " '" + text + "' is not showing on the screen.");
					n++;
				}
				if (comp.isValid()) {
					System.out.println("+" + component + " '" + text + "' is valid.");
					p++;
				} else {
					System.out.println("-" + component + " '" + text + "' is not valid.");
					n++;
				}
				if (comp.isVisible()) {
					System.out.println("+" + component + " '" + text + "' is visible.");
					p++;
				} else {
					System.out.println("-" + component + " '" + text + "' is not visible.");
					n++;
				}
				if (comp.isEnabled()) {
					System.out.println("+" + component + " '" + text + "' is enabled.");
					p++;
				} else {
					System.out.println("-" + component + " '" + text + "' is not enabled.");
					n++;
				}
				System.out.println("");
			}
			System.out.println("Positive test results: " + p);
			System.out.println("Negative test results: " + n);
			System.out.println("");
			System.out.println("");
		}	
	}
}