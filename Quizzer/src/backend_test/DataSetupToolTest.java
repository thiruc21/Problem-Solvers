package backend_test;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.DataSetupTool;

public class DataSetupToolTest {

	@Test
	public void initializeTest() {
		assertEquals("Database correctly initialized", DataSetupTool.initialize(), true);
	}

}
