package com.example.ems_backend.Repository;

import com.example.ems_backend.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository <Employee,Long>{



}
