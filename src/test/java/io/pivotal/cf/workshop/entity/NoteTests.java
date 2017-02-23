package io.pivotal.cf.workshop.entity;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * Unit test for the Note entity.
 * 
 * @author Brian Jimerson
 *
 */
public class NoteTests {

	private Note note;
	
	private static final String EXPECTED_TO_STRING = "Note = [id: %1$d, created:%2$tD %2$tT, text: %3$s]";
	
	@Before
	public void setUp() throws Exception {
		note = new Note("Test note text");
	}

	@Test
	public void testToString() {
		String expectedString = String.format(EXPECTED_TO_STRING, 
				note.getNoteId(), 
				note.getCreated(), 
				note.getText());
		
		TestCase.assertEquals(expectedString, note.toString());
	}

}
