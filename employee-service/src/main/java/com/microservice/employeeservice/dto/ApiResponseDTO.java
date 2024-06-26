package com.microservice.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDTO {

    private EmployeeDTO employee;
    private DepartmentDTO department;
    private OrganizationDTO organization;
}
