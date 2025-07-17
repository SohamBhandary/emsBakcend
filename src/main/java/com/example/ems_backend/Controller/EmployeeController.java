package com.example.ems_backend.Controller;

import com.example.ems_backend.DTO.EmployeeDto;
import com.example.ems_backend.Service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    //Add
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee=employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);



        }
    // get one
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDto employeeDto=  employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }
    // get all
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employees=employeeService.getAllEmployee();
        return ResponseEntity.ok(employees);


    }
    // update
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id")  Long employeeId, @RequestBody EmployeeDto updatedEmployee){
      EmployeeDto employeeDto=  employeeService.updateEmployee(employeeId,updatedEmployee);
      return  ResponseEntity.ok(employeeDto);

    }
    //delete
    @DeleteMapping({"/{id}"})
    public  ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee Deleted Succesfully");

    }




}
