package in.dminc.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.dminc.springboot.exception.ResourceNotFoundException;
import in.dminc.springboot.model.Employ;
import in.dminc.springboot.repository.EmployRepository;

@RestController
@RequestMapping("/ems/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployController {

	@Autowired
	EmployRepository employRepository;

	@GetMapping("/employees")
	public List<Employ> getEmploys() {
		return employRepository.findAll();
	}

	// create a employ
	@PostMapping("/employees")
	public Employ createEmploy(@RequestBody Employ employ) {
		return employRepository.save(employ);
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employ> getEmployById(@PathVariable Long id) {
		Employ employ = employRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employ does not exist with id -> " + id));

		return ResponseEntity.ok(employ);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employ> updateEmploy(@PathVariable Long id, @RequestBody Employ employ) {
		//
		Employ emp = employRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employ does not exist with id -> " + id));

		emp.setFirstName(employ.getFirstName());
		emp.setLastName(employ.getLastName());
		emp.setEmailAddress(employ.getEmailAddress());

		Employ updatedEmp = employRepository.save(emp);
		return ResponseEntity.ok(updatedEmp);
	}
	
	@GetMapping("/delete-employ/{id}")
	public void deleteEmploy(@PathVariable Long id) {
		
		Employ employ = employRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employ does not exist with id -> " + id));
		
		employRepository.delete(employ);
	}

}
