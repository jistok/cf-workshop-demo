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

import io.pivotal.cf.workshop.entity.Note;
import io.pivotal.cf.workshop.repository.NoteRepository;

/**
 * REST controller for working with notes
 * @author Brian Jimerson
 *
 */
@RestController
@RequestMapping(value = "/notes")
public class NoteController {
	
	@Autowired
	NoteRepository noteRepository;
	
	private static final Log log = LogFactory.getLog(NoteController.class);
	
	/**
	 * Gets a list of all notes
	 * @return A list of all notes
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Note> getAllNotes() {
		
		Iterable<Note> notes = noteRepository.findAll();
		List<Note> noteList = new ArrayList<>();
		for (Note note : notes) {
			noteList.add(note);
		}
		log.debug(String.format("All notes = [%s].", noteList));
		return noteList;
		
	}
	
	/**
	 * Gets an note by the id
	 * @param noteId The id of the note
	 * @return The note with the specified id
	 */
	@RequestMapping(value = "/{noteId}", method = RequestMethod.GET)
	public @ResponseBody Note getNote(@PathVariable Long noteId) {
		
		Note note = noteRepository.findOne(noteId);
		log.debug(String.format("Note with ID %d = [%s].", noteId, note));
		return note;
		
	}
	
	/**
	 * Saves an note
	 * @param note The note to save
	 * @return The note saved
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody Note saveNote(@RequestBody Note note) {
		
		note = noteRepository.save(note);
		log.debug(String.format("Note saved = [%s]", note));
		return note;
		
	}
	
	/**
	 * Deletes an note
	 * @param note The note to delete
	 */
	@RequestMapping(value = "/{noteId}", method = RequestMethod.DELETE)
	public void deleteNote(@PathVariable Long noteId) {
		
		noteRepository.delete(noteId);
		log.debug(String.format("Deleted note with id %d", noteId));
		
	}

}
