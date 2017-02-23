package io.pivotal.cf.workshop.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

/**
 * Entity object for a note.
 * @author Brian Jimerson
 *
 */
@Entity
public class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long noteId;
	
	@Column(nullable = false)
	private Date created;

	@Column(nullable = false)
	private String text;
	
	/**
	 * Hides the default empty constructor
	 */
	protected Note() {
		super();
	}
	
	/**
	 * Constructs a new note with the specified text
	 * @param text The text of the note
	 */
	public Note(String text) {
		this.text = text;
	}

	/**
	 * Gets the id of this note
	 * @return The id of this note
	 */
	public Long getNoteId() {
		return noteId;
	}

	/**
	 * Sets the id of this note
	 * @param noteId The id of this note
	 */
	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}

	/**
	 * Gets when this note was created
	 * @return When this note was created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * Sets when this note was created
	 * @param created When this note was created
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Gets the text of this note
	 * @return The text of this note
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text of this note
	 * @param text The text of this note
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	public String toString() {
		return String.format("Note = [id: %1$d, created:%2$tD %2$tT, text: %3$s]", 
				this.getNoteId(),
				this.getCreated(),
				this.getText());
	}
	
	/**
	 * Sets the created timestamp at persist time
	 */
	@PrePersist
	void setCreatedTimestamp() {
		this.created = new Date();
	}

}
