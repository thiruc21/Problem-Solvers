package backend;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import backend.DataFillTool;
import backend.DataQueryTool;
import backend.DataSetupTool;

public class DataQueryToolTest {

	@Before
	public void setup() {
		
		DataSetupTool.initialize();
		
		String[] options1 = {"one", "two", "three", "four", "five", "six"};
		String[] options2 = {"one1", "two2", "three3", "four4", "five5", "six6"};
		String[] options3 = {"111", "222", "333", "444", "555", "666"};
		
		DataFillTool.insert("Q1", "answer is one", options1, "one");
		DataFillTool.insert("Q2", "answer is two", options2, "two2");
		DataFillTool.insert("Q3", "answer is three", options3, "404"); //answer does not exist
		
	}
	
	@Test
	public void QuestionQueryTest() {
		
		int result = DataQueryTool.question_query("Q1");
		
		assertEquals("Question Queried correctly",result,1);
		
	}
	
	@Test
	public void answerQueryOneTest() {
		
		int result = DataQueryTool.answer_query("one");
		
		//System.out.print(result);
		
		assertEquals("correct answer queried", result, 2);
	}
	
	@Test
	public void answerQueryTwoTest() {
		
		int result = DataQueryTool.answer_query("two");
		
		//System.out.print(result);
		
		assertEquals("correct answer queried", result, 3);
	}
	
	@Test
	public void StartUnassignedTest() {
		
		List<String> result_1 = DataQueryTool.get_unassigned(1);
		List<String> result_2 = DataQueryTool.get_unassigned(2);
		
		//System.out.println(result_1);
		//System.out.println(result_2);
		
		assertEquals("All questions start unassigned", result_1, result_2 );
	}
	
	@Test
	public void StartAssignedTest() {
		
		List<String> result_1 = DataQueryTool.get_assigned(1);
		List<String> result_2 = DataQueryTool.get_assigned(2);
		
		assertEquals("All assignments start empty", result_1.isEmpty() && result_2.isEmpty(), true);
		
	}
	
	@Test
	public void AssignmentTest() {
		
		int q1_id = DataQueryTool.question_query("Q1");
		
		DataFillTool.addAssignedQuestion(1, Integer.toString(q1_id));
		
		List<String> result = DataQueryTool.get_assigned(1);
		
		//System.out.println(result);
		
		assertTrue("Questions are assigned correctly",result.contains(Integer.toString(q1_id)));
		
	}
	
	@Test
	public void MultipleAssignmentTest() {
		
		int q1_id = DataQueryTool.question_query("Q1");
		
		int q2_id = DataQueryTool.question_query("Q2");
		
		DataFillTool.addAssignedQuestion(1, Integer.toString(q1_id));
		
		DataFillTool.addAssignedQuestion(1, Integer.toString(q2_id));
		
		List<String> result = DataQueryTool.get_assigned(1);
		
		//System.out.println(result);
		
		assertTrue("Multiple Questions are assigned correctly",result.contains(Integer.toString(q1_id))&&result.contains(Integer.toString(q2_id)));
		
	}
	
	@Test
	public void QuestionTextOneTest() {
		
		int q1_id = DataQueryTool.question_query("Q1");
		
		assertEquals("Question Text properly queried",DataQueryTool.question_text_query(q1_id),"answer is one");
		
	}
	
	@Test
	public void QuestionTextTwoTest() {
		
		int q2_id = DataQueryTool.question_query("Q2");
		
		assertEquals("Question Text properly queried",DataQueryTool.question_text_query(q2_id),"answer is two");
		
	}
	
	@Test
	public void QuestionAnswersQueries() {
		
		int q1_id = DataQueryTool.question_query("Q1");
		
		List<Integer> result = DataQueryTool.get_question_answer_ids(q1_id);
		
		List<Integer> expected_result = new ArrayList<Integer>();
		expected_result = Arrays.asList(2, 3, 4, 5, 6, 7);
		
		//System.out.println(result);
		
		assertEquals("All answer IDs are properly returned",result, expected_result);
		
	}
	
	@Test
	public void GetAnswerTextTest() {
		
		try {
			
			int q1_id = DataQueryTool.question_query("Q1");
			
			List<Integer> result = DataQueryTool.get_question_answer_ids(q1_id);
			
			//System.out.println(result);
			
			assertEquals("Answer 1 text properly queried",DataQueryTool.get_answer_text(result.get(0)),"one");
			assertEquals("Answer 2 text properly queried",DataQueryTool.get_answer_text(result.get(1)),"two");
			assertEquals("Answer 3 text properly queried",DataQueryTool.get_answer_text(result.get(2)),"three");
			assertEquals("Answer 4 text properly queried",DataQueryTool.get_answer_text(result.get(3)),"four");
			
		} catch (Exception e) {
			fail("unexpected error occurred when querying answer texts");
		}
	}
	
	@Test
	public void GetCorrectAnswerIDTest() {
		
		int q1_id = DataQueryTool.question_query("Q1");
		
		//System.out.println(DataQueryTool.get_correct_answer_id(q1_id));
		
		int result = DataQueryTool.get_correct_answer_id(q1_id);
		assertEquals("Correct answer ID is correct",result, 2);
		
	}
	
	@Test
	public void AnsweridExists() {
		
		int q1_id = DataQueryTool.question_query("Q1");
		int ans_id = DataQueryTool.get_correct_answer_id(q1_id);
		List<Integer> possible_answer_ids = DataQueryTool.get_question_answer_ids(q1_id);
		assertTrue("The correct answer is in fact one of the answers",possible_answer_ids.contains(ans_id));
	}
	
}
