package io.pivotal.cf.workshop.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.pivotal.cf.workshop.entity.Note;
import junit.framework.TestCase;


/**
 * Integration tests for the <code>noteController</code> class.
 * 
 * @author Brian Jimerson
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class NoteControllerTests {
	
	private static final Log log = LogFactory.getLog(NoteControllerTests.class);
		
	@Autowired
	EmbeddedWebApplicationContext server;
	
	private MockMvc mvc;
	
	/**
	 * Sets up this test suite.
	 */
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(server).build();
	}

	/**
	 * Tests the get all notes method of the controller.
	 */
	@Test
	public void testGetAllNotes() {
		try {
			this.mvc.perform(
					MockMvcRequestBuilders.get("/notes/"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
		} catch (Exception e) {
			log.error(e);
			TestCase.fail(e.getMessage());
		}
	}
	
	/**
	 * Tests the get note by id method of the controller.
	 */
	@Test
	public void testGetNoteById() {
		try {
			this.mvc.perform(
					MockMvcRequestBuilders.get("/notes/5001"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
					.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasEntry("text", "Text 1")));
		} catch (Exception e) {
			log.error(e);
			TestCase.fail(e.getMessage());
		}
	}
	
	/**
	 * Tests the save note method of the controller.
	 */
	@Test
	public void testSaveNote() {
		Note note = new Note("Test note text");
		try {
			this.mvc.perform(
					MockMvcRequestBuilders.post("/notes/")
					.contentType("application/json")
					.content(new ObjectMapper().writeValueAsString(note))
			)
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.noteId").exists());
		} catch (Exception e) {
			log.error(e);
			TestCase.fail(e.getMessage());
		}
	}
	
	/**
	 * Tests the delete note method of the controller.
	 */
	@Test
	public void testDeleteNote() {
		try {
			this.mvc.perform(
					MockMvcRequestBuilders.delete("/notes/5001"))
					.andExpect(MockMvcResultMatchers.status().isOk());
					
		} catch (Exception e) {
			log.error(e);
			TestCase.fail(e.getMessage());			
		}
	}
	
}
