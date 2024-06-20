package com.microservice.employeeservice.controller;

import com.microservice.employeeservice.dto.ApiResponseDTO;
import com.microservice.employeeservice.dto.EmployeeDTO;
import com.microservice.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO resp = employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponseDTO> getEmployeeById(@PathVariable Long id) {
        ApiResponseDTO resp = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
