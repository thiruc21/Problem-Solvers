package backend;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import backend.*;

public class DataFillToolTest {

	@Before
	public void setup() {
		
		DataSetupTool.initialize();
		
	}
	
	@Test
	public void insertTest() {
		
		String[] options = {"one", "two", "three", "four", "five", "six"};
		
		boolean result = DataFillTool.insert("Title", "Question", options, "one");
		
		assertEquals("inserted into DB", result, true);
		
	}
	
	@Test
	public void insertTwoTest() {
		
		String[] options = {"one", "two", "three", "four", "five", "six"};
		
		boolean result_1 = DataFillTool.insert("Title1", "Question1", options, "one");
		
		boolean result_2 = DataFillTool.insert("Title2", "Question2", options, "two");
		
		assertEquals("inserted both into DB", result_1 && result_2, true);
		
	}
	
	@Test
	public void insertAgainTest() {
		
		boolean thrown = false;
		
		try {
			
			String[] options = {"one", "two", "three", "four", "five", "six"};
			
			DataFillTool.insert("Title", "Question1", options, "one");
			
			DataFillTool.insert("Title", "Question2", options, "two");
			
			
		} catch (Exception e) {
			
			thrown = true;
			
		}
		assertEquals("Exception correctly thrown when same label used twice", thrown, true);
	}
	
	@Test
	public void assignTest() {
		
		String[] options = {"one", "two", "three", "four", "five", "six"};

		String title = "Title"; 
		
		DataFillTool.insert(title, "Question", options, "one");
		
		String q_id = Integer.toString(DataQueryTool.question_query(title));
		
		DataFillTool.addAssignedQuestion(1, q_id);
		
		List<String> assigned = DataQueryTool.get_assigned(1);
		
		int assigned_qid = DataQueryTool.question_query(title);
		
		//System.out.println(assigned.size());
		
		//System.out.println(assigned.get(0));
		
		try {
		
			assertEquals("property assigned", assigned.get(0), Integer.toString(assigned_qid));
		
		} catch (Exception e) {
		
			System.out.println( e.getClass().getName() + ": " + e.getMessage() );
			fail();
		
		}
	}
	
	@Test
	public void notAssignedTest() {
		
		String[] options = {"one", "two", "three", "four", "five", "six"};

		String title = "Title"; 
		
		DataFillTool.insert(title, "Question", options, "one");
		
		//String q_id = Integer.toString(DataQueryTool.question_query(title));
		
		List<String> assigned = DataQueryTool.get_assigned(1);
		
		//System.out.println(assigned.size());
		
		try {
		
			assertEquals("property (not) assigned", assigned.size(), 0);
		
		} catch (Exception e) {
		
			System.out.println( e.getClass().getName() + ": " + e.getMessage() );
			fail();
		
		}
		
	}
	
	@Test
	public void assignMultipleTest() {
		
		String[] options = {"one", "two", "three", "four", "five", "six"};

		String title = "Title"; 
		
		DataFillTool.insert(title, "Question", options, "one");
		
		String q_id = Integer.toString(DataQueryTool.question_query(title));
		
		DataFillTool.addAssignedQuestion(1, q_id);
		
		DataFillTool.addAssignedQuestion(2, q_id);
		
		List<String> assigned_1 = DataQueryTool.get_assigned(1);
		
		List<String> assigned_2 = DataQueryTool.get_assigned(2);
		
		int assigned_qid = DataQueryTool.question_query(title);
		
		//System.out.println(assigned.size());
		
		//System.out.println(assigned.get(0));
		
		try {
		
			assertEquals("property assigned to A1", assigned_1.get(0), Integer.toString(assigned_qid));
			assertEquals("property assigned to A2", assigned_2.get(0), Integer.toString(assigned_qid));
		
		} catch (Exception e) {
		
			fail();
		
		}
	}

}
