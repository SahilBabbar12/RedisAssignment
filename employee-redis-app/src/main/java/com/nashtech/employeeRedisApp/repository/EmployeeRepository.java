package com.nashtech.employeeRedisApp.repository;
import com.nashtech.employeeRedisApp.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}