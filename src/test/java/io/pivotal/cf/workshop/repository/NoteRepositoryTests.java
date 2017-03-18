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
 * are provided by the base CrudRepository interface.
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
	 * there is more than 0 notes returned.
	 */
	@Test
	public void testFindAll() {
		Iterable<Note> notes = noteRepository.findAll();
		TestCase.assertTrue(
				"Find all should return at least 1 result.",
				notes.iterator().hasNext());
	}

	/**
	 * Tests the repository's findOne method.
	 */
	@Test
	public void testFindOne() {
		Note note = noteRepository.findOne(5001L);
		TestCase.assertNotNull("ID 5001 should return a Note.", note);
	}

	/**
	 * Tests the repository's save method, by saving a note and
	 * asserting that there's an ID on the note returned.
	 */
	@Test
	public void testSave() {
		note = noteRepository.save(note);
		TestCase.assertNotNull("Saving a note should set the Note's ID.", note.getNoteId());
	}

	/**
	 * Tests the repository's save method for an existing Note.
	 */
	@Test
	public void testUpdate() {
		Note note = noteRepository.findOne(5001L);
		note.setText("Changed note text.");
		note = noteRepository.save(note);
		TestCase.assertEquals("Note text should be updated.", "Changed note text.", note.getText());
		TestCase.assertEquals("Note ID shouldn't change.", 5001L, note.getNoteId().longValue());
	}

	/**
	 * Tests the repository's delete method.
	 */
	@Test
	public void testDelete() {
		noteRepository.delete(5001L);
		Note note = noteRepository.findOne(5001L);
		TestCase.assertNull("Note with ID 5001 shouldn't exist after delete.", note);
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
