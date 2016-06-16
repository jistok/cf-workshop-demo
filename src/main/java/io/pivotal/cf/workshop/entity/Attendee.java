package io.pivotal.cf.workshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The attendee JPA entity.
 * 
 * @author Brian Jimerson
 *
 */
@Entity
public class Attendee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String state;
	
	@Column(nullable = false)
	private String zipCode;
	
	@Column
	private String phoneNumber;
	
	@Column
	private String emailAddress;
	
	/**
	 * Hides the default constructor from JPA
	 */
	protected Attendee() {
		super(); 
	}
	
	/**
	 * Public constructor for the Attendee entity.
	 * @param firstName The first name of the attendee
	 * @param lastName The last name of the attendee
	 * @param address The address of the attendee
	 * @param city The city of the attendee
	 * @param state The state of the attendee
	 * @param zipCode The zip code of the attendee
	 * @param phoneNumber The phone number of the attendee
	 * @param emailAddress The email address of the attendee
	 */
	public Attendee(String firstName, String lastName, String address, String city,
			String state, String zipCode, String phoneNumber, String emailAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}

	/**
	 * Gets the id
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the first name
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name
	 * @param firstName the first name to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name
	 * @param lastName the last name to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the address
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the city
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the state
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the zip code
	 * @return the zip code
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Sets the zip code
	 * @param zipCode the zip code to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * Gets the phone number
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number
	 * @param phoneNumber the phone number to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the email address
	 * @return the email address
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Sets the email address
	 * @param emailAddress the email address to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	/**
	 * Overrides Object's toString method to return the
	 * state of the Attendee object.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Attendee = [firstName: ");
		sb.append(firstName);
		sb.append(", lastName: ");
		sb.append(lastName);
		sb.append(", address: ");
		sb.append(address);
		sb.append(", city: ");
		sb.append(city);
		sb.append(", state: ");
		sb.append(state);
		sb.append(", zipCode: ");
		sb.append(zipCode);
		sb.append(", phoneNumber: ");
		sb.append(phoneNumber);
		sb.append(", emailAddress: ");
		sb.append(emailAddress);
		sb.append("]");		
		return sb.toString();
	}
	
	

}
