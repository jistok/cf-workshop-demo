package io.pivotal.cf.workshop.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import io.pivotal.cf.workshop.entity.Attendee;
import junit.framework.TestCase;

/**
 * Integration tests for the <code>AttendeeRepository</code>
 * JPA repository interface.
 * 
 * The <code>SpringApplicationConfiguration</code> annotation
 * ensures that the embedded database is started and configured
 * for the integration tests.
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
public class AttendeeRepositoryTests {
	
	@Autowired
	private AttendeeRepository attendeeRepository;
	private Attendee attendee;

	/**
	 * Sets up the test case
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		attendee = new Attendee("First", "Last", "Address", "City", 
				"State", "Zip", "Phone", "Email");
	}
	
	/**
	 * Tests the repository's findAll method, by asserting that
	 * there is more than 0 attendees returned.
	 */
	@Test
	public void testFindAll() {
		Iterable<Attendee> attendees = attendeeRepository.findAll();
		TestCase.assertTrue(
				"Find all should return at least 1 result.",
				attendees.iterator().hasNext());
	}
	
	/**
	 * Tests the firstName is not nullable
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public void testFirstNameNotNullable() {
		attendee.setFirstName(null);
		attendeeRepository.save(attendee);
	}
	
	/**
	 * Tests the lastName is not nullable
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public void testLastNameNotNullable() {
		attendee.setLastName(null);
		attendeeRepository.save(attendee);
	}
	
	/**
	 * Tests the address is not nullable
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public void testAddressNotNullable() {
		attendee.setAddress(null);
		attendeeRepository.save(attendee);
	}
	
	/**
	 * Tests the city is not nullable
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public void testCityNotNullable() {
		attendee.setCity(null);
		attendeeRepository.save(attendee);
	}
	
	/**
	 * Tests the state is not nullable
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public void testStateNotNullable() {
		attendee.setState(null);
		attendeeRepository.save(attendee);
	}
	
	/**
	 * Tests the zipCode is not nullable
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public void testZipCodeNotNullable() {
		attendee.setZipCode(null);
		attendeeRepository.save(attendee);
	}
	
	/**
	 * Tests the phoneNumber is nullable
	 */
	@Test()
	public void testPhoneNumberNullable() {
		attendee.setPhoneNumber(null);
		try {
			attendeeRepository.save(attendee);
		} catch (DataIntegrityViolationException dive) {
			Assert.fail("Phone number should be nullable.");
		}
	}

	/**
	 * Tests the emailAddress is nullable
	 */
	@Test()
	public void testEmailAddressNullable() {
		attendee.setEmailAddress(null);
		try {
			attendeeRepository.save(attendee);
		} catch (DataIntegrityViolationException dive) {
			Assert.fail("Email address should be nullable.");
		}
	}

}
