package exercise.rssb.rw.model;

import java.util.List;

/**
 * The Class UploadedDataDTO.
 *
 * @author Christian Iradukunda
 */
public class UploadedDataDTO {

	private List<Customer> customers;
	private List<ValidationFailure> failedValidations;

	public UploadedDataDTO() {
	}

	public UploadedDataDTO(List<Customer> customers, List<ValidationFailure> failedValidations) {
		this.customers = customers;
		this.failedValidations = failedValidations;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<ValidationFailure> getFailedValidations() {
		return failedValidations;
	}

	public void setFailedValidations(List<ValidationFailure> failedValidations) {
		this.failedValidations = failedValidations;
	}
}