import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;

import application.Quizzer;

public class ValidationTest {
	public static void main(String args[]) {
		Quizzer q = new Quizzer();
		Component[] comps = q.frame.getContentPane().getComponents();
		
		int p = 0;
		int n = 0;
		String text = "";
		String component = "";
		
		for(Component comp: comps) {
			if (comp instanceof JButton) {
				text = ((JButton) comp).getText();				
				component = "JButton";
			} else if (comp instanceof JLabel) {
				text = ((JLabel) comp).getText();	
				component = "JLabel";
			} else if (comp instanceof JList) {
				component = "JList";
			} else if (comp instanceof JRadioButton) {
				text = ((JRadioButton) comp).getText();	
				component = "JRadioButton";
			} 
			if (comp.isShowing()) {
				System.out.println("+" + text + " " + component + " is showing on the screen.");
				p++;
			} else {
				System.out.println("-" + text + " " + component + " is not showing on the screen.");
				n++;
			}
			if (comp.isValid()) {
				System.out.println("+" + text + " " + component + " is valid.");
				p++;
			} else {
				System.out.println("-" + text + " " + component + " is not valid.");
				n++;
			}
			if (comp.isVisible()) {
				System.out.println("+" + text + " " + component + " is visible.");
				p++;
			} else {
				System.out.println("-" + text + " " + component + " is not visible.");
				n++;
			}
			if (comp.isEnabled()) {
				System.out.println("+" + text + " " + component + " is enabled.");
				p++;
			} else {
				System.out.println("-" + text + " " + component + " is not enabled.");
				n++;
			}
			System.out.println("");
		}
		System.out.println("Positive test results: " + p);
		System.out.println("Negative test results: " + n);
	}
}
