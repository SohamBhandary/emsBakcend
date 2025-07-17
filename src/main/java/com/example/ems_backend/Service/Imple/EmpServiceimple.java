package com.example.ems_backend.Service.Imple;

import com.example.ems_backend.DTO.EmployeeDto;
import com.example.ems_backend.Entity.Employee;
import com.example.ems_backend.Exception.ResourceNotFoundException;
import com.example.ems_backend.Mapper.EmployyeMapper;
import com.example.ems_backend.Repository.EmployeeRepo;
import com.example.ems_backend.Service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<EmployeeDto> getAllEmployee() {
      List <Employee> employees=  employeeRepo.findAll();
      return employees.stream().map((employee)->EmployyeMapper.mapToEmployeeDto(employee))
              .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
      Employee employee=  employeeRepo.findById(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("Employee doesnt exists"+employeeId));
      employee.setFirstName(updatedEmployee.getFirstName());
      employee.setLastName(updatedEmployee.getLastName());
      employee.setEmail(updatedEmployee.getEmail());
      Employee updatedEmployeeObj= employeeRepo.save(employee);
      return EmployyeMapper.mapToEmployeeDto(updatedEmployeeObj);

    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee=  employeeRepo.findById(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("Employee doesnt exists"+employeeId));
        employeeRepo.deleteById(employeeId);

    }
}
