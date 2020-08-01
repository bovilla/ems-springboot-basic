package in.dminc.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.dminc.springboot.model.Employ;

@Repository
public interface EmployRepository extends JpaRepository<Employ, Long> {

}
