package com.nashtech.employeeRedisApp.service;

import com.assignment.employeeLibrary.model.Employee;
import com.nashtech.employeeRedisApp.entity.EmployeeEntity;
import com.nashtech.employeeRedisApp.repository.EmployeeRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee create(Employee employee) {

        EmployeeEntity entity = new EmployeeEntity(
                null,
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment()
        );

        return toEmployee(repository.save(entity));
    }

    @Cacheable(value = "employees", key = "#id")
    public Employee getById(Long id) {
        EmployeeEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("EmployeeEntity not found: " + id));

        return toEmployee(entity);
    }

    public List<Employee> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toEmployee)
                .toList();
    }

    @CachePut(value = "employees" , key = "#id")
    public Employee update(Long id, Employee employee) {

        EmployeeEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("EmployeeEntity not found: " + id));

        entity.setName(employee.getName());
        entity.setEmail(employee.getEmail());
        entity.setDepartment(employee.getDepartment());

        return toEmployee(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    @CacheEvict(value = "employees" , key = "#id")
    private Employee toEmployee(EmployeeEntity entity) {
        return new Employee(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getDepartment()
        );
    }
}