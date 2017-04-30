package io.pivotal.workshop.repository;

import org.springframework.data.repository.CrudRepository;

import io.pivotal.workshop.entity.Note;

/**
 * JPA repository for the Note entity.
 * @author Brian Jimerson
 *
 */
public interface NoteRepository extends CrudRepository<Note, Long> {

}
