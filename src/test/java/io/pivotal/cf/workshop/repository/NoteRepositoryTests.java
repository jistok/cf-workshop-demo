package io.pivotal.cf.workshop.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import io.pivotal.cf.workshop.entity.Note;
import junit.framework.TestCase;

/**
 * Integration tests for the <code>NoteRepository</code>
 * JPA repository interface.
 * 
 * Most of the methods tested (<code>findOne</code>, <code>save</code>),
 * are provided by the base CrudRepository class.
 * 
 * @author Brian Jimerson
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class NoteRepositoryTests {
	
	@Autowired
	private NoteRepository noteRepository;
	private Note note;

	/**
	 * Sets up the test case
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		note = new Note("Test note text.");
	}
	
	/**
	 * Tests the repository's findAll method, by asserting that
	 * there is more than 0 attendees returned.
	 */
	@Test
	public void testFindAll() {
		Iterable<Note> notes = noteRepository.findAll();
		TestCase.assertTrue(
				"Find all should return at least 1 result.",
				notes.iterator().hasNext());
	}
	
	/**
	 * Tests the text is not nullable
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public void testTextNotNullable() {
		note.setText(null);
		noteRepository.save(note);
	}
	
}
