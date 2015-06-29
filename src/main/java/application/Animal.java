package application;

import java.sql.Date;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Animal extends AbstractPersistable<Long> {

	private int facility_id;
	private String name;
	private String animal_type;
	private Date birthdate;
	private Date death_date;
	private String notes;
	private int weight;
	private String breed;
	private String animal_size;
	private static final long serialVersionUID = 1L;

	public Animal() {
		this(null);
	}

	public Animal(Long id) {
		this.setId(id);
	}

	public int getFacility_id() {
		return facility_id;
	}

	public void setFacility_id(int facility_id) {
		this.facility_id = facility_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAnimal_type() {
		return animal_type;
	}

	public void setAnimal_type(String animal_type) {
		this.animal_type = animal_type;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getDeath_date() {
		return death_date;
	}

	public void setDeath_date(Date death_date) {
		this.death_date = death_date;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getAnimal_size() {
		return animal_size;
	}

	public void setAnimal_size(String animal_size) {
		this.animal_size = animal_size;
	}

}
