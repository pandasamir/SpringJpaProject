package com.itc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.itc.entity.Employee;
import com.itc.exception.EmployeeNotFoundException;
import com.itc.service.EmployeeService;
import com.itc.views.Views;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	EmployeeService employeeService;

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("details")
	public ResponseEntity<List<Employee>> findAll() {
		List<Employee> employee = employeeService.findAllEmployees();
		ResponseEntity<List<Employee>> responseEntity = new ResponseEntity<List<Employee>>(employee, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("names")
	@JsonView(Views.MyResponseViews.class)
	public ResponseEntity<List<Employee>> findAllOnlyName() {
		List<Employee> employee = employeeService.findAllEmployees();
		ResponseEntity<List<Employee>> responseEntity = new ResponseEntity<List<Employee>>(employee, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> findById(@PathVariable("id") int id) throws EmployeeNotFoundException {
		Employee employee = employeeService.findEmployeeById(id);
		ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(employee, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/{name}/{email}")
	public ResponseEntity<List<Employee>> findByNameAndId(@PathVariable("name") String name,
			@PathVariable("email") String email) throws EmployeeNotFoundException {
		List<Employee> employee = employeeService.findEmployeeByNameAndEmail(name, email);
		ResponseEntity<List<Employee>> responseEntity = new ResponseEntity<List<Employee>>(employee, HttpStatus.OK);
		return responseEntity;
	}

	@PostMapping
	public ResponseEntity<Employee> save(@RequestBody Employee employee) {
		Employee emp = employeeService.save(employee);
		ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(emp, HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping
	public ResponseEntity<Employee> edit(@RequestBody Employee employee) throws EmployeeNotFoundException {
		Employee emp = employeeService.update(employee);
		ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(emp, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> delete(@PathVariable("id") int id) throws EmployeeNotFoundException {
		Employee employee = employeeService.delete(id);
		ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(employee, HttpStatus.OK);
		return responseEntity;
	}
}
