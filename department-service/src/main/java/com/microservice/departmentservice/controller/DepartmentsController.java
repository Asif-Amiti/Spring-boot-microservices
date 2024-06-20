package com.microservice.departmentservice.controller;

import com.microservice.departmentservice.dto.DepartmentDTO;
import com.microservice.departmentservice.entity.Department;
import com.microservice.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
public class DepartmentsController {

    @Autowired
    DepartmentService departmentService;


    @PostMapping
    public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO savedDep = departmentService.saveDepartment(departmentDTO);

        return new ResponseEntity<>(savedDep, HttpStatus.OK);
    }

    @GetMapping("{departmentCode}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable String departmentCode) {
        DepartmentDTO dep = departmentService.getDepartmentByCode(departmentCode);

        return new ResponseEntity<>(dep, HttpStatus.OK);
    }
}
