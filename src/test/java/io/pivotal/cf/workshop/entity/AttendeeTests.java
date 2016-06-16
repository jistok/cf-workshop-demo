package io.pivotal.cf.workshop.entity;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * Unit test for the Attendee entity.
 * 
 * @author Brian Jimerson
 *
 */
public class AttendeeTests {

	private Attendee attendee;
	
	private static final String EXPECTED_TO_STRING = "Attendee = [firstName: %s, lastName: %s, address: %s, city: %s, state: %s, zipCode: %s, phoneNumber: %s, emailAddress: %s]";
	
	@Before
	public void setUp() throws Exception {
		attendee = new Attendee("First", "Last", "Address", "City", 
				"State", "Zip", "Phone", "Email");
	}

	@Test
	public void testToString() {
		String expectedString = String.format(EXPECTED_TO_STRING, attendee.getFirstName(),
				attendee.getLastName(), attendee.getAddress(), attendee.getCity(),
				attendee.getState(), attendee.getZipCode(), attendee.getPhoneNumber(),
				attendee.getEmailAddress());
		
		TestCase.assertEquals(expectedString, attendee.toString());
	}

}
