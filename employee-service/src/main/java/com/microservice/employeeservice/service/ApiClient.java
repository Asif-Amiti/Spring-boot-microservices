package com.microservice.employeeservice.service;

import com.microservice.employeeservice.dto.DepartmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="department-service")
public interface ApiClient {

    @GetMapping("api/departments/{departmentCode}")
    DepartmentDTO getDepartment(@PathVariable("departmentCode") String departmentCode);

}
