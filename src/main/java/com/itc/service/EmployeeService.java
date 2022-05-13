package com.itc.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itc.entity.Employee;
import com.itc.exception.EmployeeNotFoundException;
import com.itc.repo.EmployeeRepo;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepo employeeRepo;
	@Autowired
	EntityManager em;

	public EmployeeRepo getEmployeeRepo() {
		return employeeRepo;
	}
	
	@Autowired
	public void setEmployeeRepo(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	public List<Employee> findAllEmployees(){
		return employeeRepo.findAll();
	}
	public Employee findEmployeeById(int id) throws EmployeeNotFoundException {
		Optional<Employee> optionalEmp = employeeRepo.findById(id);
		if(!optionalEmp.isPresent()) {
			throw new EmployeeNotFoundException("Employee id not found : "+id);
		}
		return optionalEmp.get();
	}
	public Employee save(Employee employee) {
		return employeeRepo.save(employee);
	}
	public Employee delete(int id) throws EmployeeNotFoundException {
		Optional<Employee> optionalEmp = employeeRepo.findById(id);
		if(!optionalEmp.isPresent()) {
			throw new EmployeeNotFoundException("Employee not existing to delete with id : "+id);
		}
		Employee employee = optionalEmp.get();
		employeeRepo.deleteById(id);
		return employee;
	}
	public Employee update(Employee employee) throws EmployeeNotFoundException {
		Optional<Employee> optionalEmp = employeeRepo.findById(employee.getId());
		if(!optionalEmp.isPresent()) {
			throw new EmployeeNotFoundException("Employee not existing to modify with id : "+employee.getId());
		}
		return employeeRepo.save(employee);
	
	}
	
	public List<Employee> findEmployeeByNameAndEmail(String name,String email)
	{
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> employee = cq.from(Employee.class);
		 Predicate userNamePredicate = cb.equal(employee.get("name"), name);
	     Predicate emailIdPredicate = cb.like(employee.get("email"), "%" + email + "%");
	     cq.where(userNamePredicate,emailIdPredicate);
	     TypedQuery<Employee> query = em.createQuery(cq);
	        return query.getResultList();
	}
}
	
