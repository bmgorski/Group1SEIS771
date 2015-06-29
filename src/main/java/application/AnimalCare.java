package application;

import java.sql.Date;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Component;

@Entity
@Component
public class AnimalCare extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long animal_id;
	private Date care_date;
	private String care_notes;

	public AnimalCare() {
		this(null);
	}

	public AnimalCare(Long id) {
		this.setId(id);
	}

	public long getAnimal_id() {
		return animal_id;
	}

	public void setAnimal_id(long animal_id) {
		this.animal_id = animal_id;
	}

	public Date getCare_date() {
		return care_date;
	}

	public void setCare_date(Date care_date) {
		this.care_date = care_date;
	}

	public String getCare_notes() {
		return care_notes;
	}

	public void setCare_notes(String care_notes) {
		this.care_notes = care_notes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
