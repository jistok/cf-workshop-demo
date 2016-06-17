package io.pivotal.cf.workshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.cf.workshop.entity.Attendee;
import io.pivotal.cf.workshop.repository.AttendeeRepository;

/**
 * REST controller for working with attendees
 * @author Brian Jimerson
 *
 */
@RestController
@RequestMapping(value = "/attendees")
public class AttendeeController {
	
	@Autowired
	AttendeeRepository attendeeRepository;
	
	private static final Log log = LogFactory.getLog(AttendeeController.class);
	
	/**
	 * Gets a list of all attendees
	 * @return A list of all attendees
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Attendee> getAllAttendees() {
		
		Iterable<Attendee> attendees = attendeeRepository.findAll();
		List<Attendee> attendeeList = new ArrayList<>();
		for (Attendee attendee : attendees) {
			attendeeList.add(attendee);
		}
		log.debug(String.format("All attendees = [%s].", attendeeList));
		return attendeeList;
		
	}
	
	/**
	 * Gets an attendee by the id
	 * @param attendeeId The id of the attendee
	 * @return The attendee with the specified id
	 */
	@RequestMapping(value = "/{attendeeId}", method = RequestMethod.GET)
	public @ResponseBody Attendee getAttendee(@PathVariable Long attendeeId) {
		
		Attendee attendee = attendeeRepository.findOne(attendeeId);
		log.debug(String.format("Attendee with ID %d = [%s].", attendeeId, attendee));
		return attendee;
		
	}
	
	/**
	 * Saves an attendee
	 * @param attendee The attendee to save
	 * @return The attendee saved
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody Attendee saveAttendee(@RequestBody Attendee attendee) {
		
		attendee = attendeeRepository.save(attendee);
		log.debug(String.format("Attendee saved = [%s]", attendee));
		return attendee;
		
	}
	
	/**
	 * Deletes an attendee
	 * @param attendee The attendee to delete
	 */
	@RequestMapping(value = "/{attendeeId}", method = RequestMethod.DELETE)
	public void deleteAttendee(@PathVariable Long attendeeId) {
		
		attendeeRepository.delete(attendeeId);
		log.debug(String.format("Deleted attendee with id %d", attendeeId));
		
	}

}
