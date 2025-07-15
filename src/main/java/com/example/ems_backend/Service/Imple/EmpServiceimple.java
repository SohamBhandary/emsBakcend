package com.example.ems_backend.Service.Imple;

import com.example.ems_backend.DTO.EmployeeDto;
import com.example.ems_backend.Entity.Employee;
import com.example.ems_backend.Exception.ResourceNotFoundException;
import com.example.ems_backend.Mapper.EmployyeMapper;
import com.example.ems_backend.Repository.EmployeeRepo;
import com.example.ems_backend.Service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmpServiceimple implements EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployyeMapper.mapToEmployee(employeeDto);
       Employee savedEmployee= employeeRepo.save(employee);
       return EmployyeMapper.mapToEmployeeDto(savedEmployee);


    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

      Employee employee=  employeeRepo.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee doest not exists:"+employeeId));
      return EmployyeMapper.mapToEmployeeDto(employee);


    }
}
