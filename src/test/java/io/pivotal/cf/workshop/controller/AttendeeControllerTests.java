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

import io.pivotal.cf.workshop.entity.Attendee;
import junit.framework.TestCase;


/**
 * Integration tests for the <code>TimeEntryController</code> class.
 * 
 * The <code>SpringApplicationConfiguration</code>
 * annotation ensures that the proper configuration (i.e.
 * embedded database and data source) is applied.  The 
 * <code>IntegrationTest</code> annotation starts the 
 * embedded Tomcat server for the controller.
 * 
 * @author Brian Jimerson
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AttendeeControllerTests {
	
	private static final Log log = LogFactory.getLog(AttendeeControllerTests.class);
		
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
	 * Tests the get all attendees method of the controller.
	 */
	@Test
	public void testGetAllAttendees() {
		try {
			this.mvc.perform(
					MockMvcRequestBuilders.get("/attendees/"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
		} catch (Exception e) {
			log.error(e);
			TestCase.fail(e.getMessage());
		}
	}
	
	/**
	 * Tests the get attendee by id method of the controller.
	 */
	@Test
	public void testGetAttendeesById() {
		try {
			this.mvc.perform(
					MockMvcRequestBuilders.get("/attendees/5001"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
					.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasEntry("firstName", "John")));
		} catch (Exception e) {
			log.error(e);
			TestCase.fail(e.getMessage());
		}
	}
	
	/**
	 * Tests the save attendee method of the controller.
	 */
	@Test
	public void testSaveAttendee() {
		Attendee entry = new Attendee("First", "Last", "Address", "City", 
				"State", "Zip", "Phone", "Email");
		try {
			this.mvc.perform(
					MockMvcRequestBuilders.post("/attendees/")
					.contentType("application/json")
					.content(new ObjectMapper().writeValueAsString(entry))
			)
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.attendeeId").exists());
		} catch (Exception e) {
			log.error(e);
			TestCase.fail(e.getMessage());
		}
	}
	
	/**
	 * Tests the delete attendee method of the controller.
	 */
	@Test
	public void testDeleteAttendee() {
		try {
			this.mvc.perform(
					MockMvcRequestBuilders.delete("/attendees/5001"))
					.andExpect(MockMvcResultMatchers.status().isOk());
					
		} catch (Exception e) {
			log.error(e);
			TestCase.fail(e.getMessage());			
		}
	}
	
}
