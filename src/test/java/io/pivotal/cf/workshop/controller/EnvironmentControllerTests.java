package io.pivotal.cf.workshop.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import io.pivotal.cf.workshop.CFWorkshopDemoApplication;
import junit.framework.TestCase;


/**
 * Integration tests for the <code>EnvironmentController</code> class.
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
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CFWorkshopDemoApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class EnvironmentControllerTests {
	
	private static final Log log = LogFactory.getLog(EnvironmentControllerTests.class);
		
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
	 * Tests the get environment info method of the controller.
	 */
	@Test
	public void testGetEnvironmentInfo() {
		try {
			this.mvc.perform(
					MockMvcRequestBuilders.get("/environment/info"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
		} catch (Exception e) {
			log.error(e);
			TestCase.fail(e.getMessage());
		}
	}
	
	
	
}
