package application;

import java.sql.Date;

import javax.persistence.Entity;

import org.hibernate.annotations.Proxy;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Component;

@Entity
@Component
@Proxy(lazy = false)
public class SubmissionApplication extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	public static final String STATUS_APPROVED = "approved";
	public static final String STATUS_REJECTED = "rejected";
	public static final String STATUS_PENDING = "pending";
	public static final String STATUS_APPROVED_RECEIVED = "approved and received";

	private static final long serialVersionUID = 1L;
	private long customer_id;
	private long employee_id;
	private Long animal_id;
	private Date date_submitted;
	private String reported_condition;
	private String requests;
	private String application_status;

	private String rejection_notes;

	public SubmissionApplication() {
		this(null);
	}

	public SubmissionApplication(Long id) {
		this.setId(id);
	}

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(long employee_id) {
		this.employee_id = employee_id;
	}

	public String getReported_condition() {
		return reported_condition;
	}

	public void setReported_condition(String reported_condition) {
		this.reported_condition = reported_condition;
	}

	public String getApplication_status() {
		return application_status;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public Long getAnimal_id() {
		return animal_id;
	}

	public void setAnimal_id(Long animalId) {
		this.animal_id = animalId;
	}

	public Date getDate_submitted() {
		return date_submitted;
	}

	public void setDate_submitted(Date date_submitted) {
		this.date_submitted = date_submitted;
	}

	public String getReportedCondition() {
		return reported_condition;
	}

	public void setReportedCondition(String reported_condition) {
		this.reported_condition = reported_condition;
	}

	public String getRequests() {
		return requests;
	}

	public void setRequests(String requests) {
		this.requests = requests;
	}

	public String getApplicationStatus() {
		return application_status;
	}

	public void setApplication_status(String application_status) {
		this.application_status = application_status;
	}

	public String getRejection_notes() {
		return rejection_notes;
	}

	public void setRejection_notes(String rejection_notes) {
		this.rejection_notes = rejection_notes;
	}

	public boolean isPending() {
		return getApplicationStatus().equals(STATUS_PENDING);
	}

	public boolean isRejected() {
		return getApplicationStatus().equals(STATUS_REJECTED);
	}

	public boolean isApproved() {
		return getApplicationStatus().equals(STATUS_APPROVED);
	}

	public boolean isApprovedReceived() {
		return getApplicationStatus().equals(STATUS_APPROVED_RECEIVED);
	}
}
