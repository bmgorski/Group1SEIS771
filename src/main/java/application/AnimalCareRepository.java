package application;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalCareRepository extends JpaRepository<AnimalCare, Long> {

}
