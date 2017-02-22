package io.pivotal.cf.workshop.repository;

import org.springframework.data.repository.CrudRepository;

import io.pivotal.cf.workshop.entity.Note;

/**
 * JPA repository for the Note entity.
 * @author Brian Jimerson
 *
 */
public interface NoteRepository extends CrudRepository<Note, Long> {

}
