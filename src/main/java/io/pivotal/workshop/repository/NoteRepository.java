package io.pivotal.workshop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.pivotal.workshop.entity.Note;

/**
 * JPA repository for the Note entity.
 * @author Brian Jimerson
 *
 */
public interface NoteRepository extends CrudRepository<Note, Long> {

	public List<Note> findNoteByText(String text);
}
