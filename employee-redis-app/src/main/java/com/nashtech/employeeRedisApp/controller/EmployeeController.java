package com.nashtech.employeeRedisApp.controller;

import com.assignment.employeeLibrary.model.Employee;
import com.nashtech.employeeRedisApp.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return service.create(employee);
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public Employee update(
            @PathVariable Long id,
            @RequestBody Employee employee) {
        return service.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}