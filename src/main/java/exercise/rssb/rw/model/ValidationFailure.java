package exercise.rssb.rw.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ValidationFailure.
 *
 * @author Christian Iradukunda
 */
public class ValidationFailure {

	private Integer rowNumber;

	private String fullName;

	private String gender;

	private String phoneNumber;

	private String email;

	private String identificationNumber;

	private List<String> errorMessages = new ArrayList<>();

	public ValidationFailure() {
	}

	public ValidationFailure(Integer rowNumber, String fullName, String gender, String phoneNumber, String email,
			String identificationNumber) {
		this.rowNumber = rowNumber;
		this.fullName = fullName;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.identificationNumber = identificationNumber;
	}

	public Integer getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public void addErrorMessages(String errorMessage) {
		this.errorMessages.add(errorMessage);
	}
}