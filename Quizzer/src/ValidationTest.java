import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JList;

import application.Quizzer;

public class ValidationTest {
	public static void main(String args[]) {
		Quizzer q = new Quizzer();
		Component[] comps = q.frame.getContentPane().getComponents();
		
		int i = 0;
		int p = 0;
		int n = 0;
	
		for(Component comp: comps) {		
			if (comp.isShowing()) {
				System.out.println("+Component " + i + " is showing on the screen.");
				System.out.println(comp.getName());
				p++;
			} else {
				System.out.println("-Component " + i + " is not showing on the screen.");
				System.out.println(comp.getName());
				n++;
			}
			if (comp.isValid()) {
				System.out.println("+Component " + i + " is valid.");
				System.out.println(comp.getName());
				p++;
			} else {
				System.out.println("-Component " + i + " is not valid.");
				System.out.println(comp.getName());
				n++;
			}
			if (comp.isVisible()) {
				System.out.println("+Component " + i + " is visible.");
				System.out.println(comp.getName());
				p++;
			} else {
				System.out.println("-Component " + i + " is not visible.");
				System.out.println(comp.getName());
				n++;
			}
			if (comp.isEnabled()) {
				System.out.println("+Component " + i + " is enabled.");
				System.out.println(comp.getName());
				p++;
			} else {
				System.out.println("-Component " + i + " is not enabled.");
				System.out.println(comp.getName());
				n++;
			}
			
			if (comp instanceof JButton) {
				System.out.println("WOW");
			} else if (comp instanceof JList) {
				
			} 
			i++;
		}
		System.out.println("Positive test results: " + p);
		System.out.println("Negative test results: " + n);
	}
}
