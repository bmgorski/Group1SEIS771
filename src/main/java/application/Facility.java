package application;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Facility extends AbstractPersistable<Long> {

	private int building;
	private String facility_size;
	private int capacity;
	private String animal_type;
	private static final long serialVersionUID = 1L;

	public Facility() {
		this(null);
	}

	public Facility(Long id) {
		this.setId(id);
	}

	public int getBuilding() {
		return building;
	}

	public void setBuilding(int building) {
		this.building = building;
	}

	public String getFacility_size() {
		return facility_size;
	}

	public void setFacility_size(String facility_size) {
		this.facility_size = facility_size;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getAnimal_type() {
		return animal_type;
	}

	public void setAnimal_type(String animal_type) {
		this.animal_type = animal_type;
	}

}
