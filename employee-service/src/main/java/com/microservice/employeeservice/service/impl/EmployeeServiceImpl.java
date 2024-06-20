package com.microservice.employeeservice.service.impl;

import com.microservice.employeeservice.dto.ApiResponseDTO;
import com.microservice.employeeservice.dto.DepartmentDTO;
import com.microservice.employeeservice.dto.EmployeeDTO;
import com.microservice.employeeservice.dto.OrganizationDTO;
import com.microservice.employeeservice.entity.Employee;
import com.microservice.employeeservice.repositoty.EmployeeRepository;
import com.microservice.employeeservice.service.ApiClient;
import com.microservice.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    /*@Autowired
    private RestTemplate restTemplate;*/
    @Autowired
    private WebClient webClient;

//    @Autowired
//    private ApiClient apiClient;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
       Employee emp = new Employee(
               employeeDTO.getId(),
               employeeDTO.getFirstName(),
               employeeDTO.getLastName(),
               employeeDTO.getEmail(),
               employeeDTO.getDepartmentCode(),
               employeeDTO.getOrganizationCode()
       );

        Employee employee = employeeRepository.save(emp);

        return new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode(),
                employeeDTO.getOrganizationCode()
        );
    }

    @CircuitBreaker(name="${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    //@Retry(name="${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public ApiResponseDTO getEmployeeById(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);



        if(employee.isPresent()){
            Employee employee1 = employee.get();
            //ResponseEntity<DepartmentDTO> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/"+employee1.getDepartmentCode(), DepartmentDTO.class);
            //DepartmentDTO departmentDTO = responseEntity.getBody();

            DepartmentDTO departmentDTO = webClient.get()
                     .uri("http://localhost:8080/api/departments/"+employee1.getDepartmentCode())
                    .retrieve()
                    .bodyToMono(DepartmentDTO.class)
                    .block();

          // DepartmentDTO departmentDTO = apiClient.getDepartment(employee1.getDepartmentCode());

           EmployeeDTO employeeDTO = new EmployeeDTO(
                    employee1.getId(),
                    employee1.getFirstName(),
                    employee1.getLastName(),
                    employee1.getEmail(),
                    employee1.getDepartmentCode(),
                   employee1.getOrganizationCode()
            );
            ApiResponseDTO responseDTO = new ApiResponseDTO();
            responseDTO.setEmployee(employeeDTO);
            responseDTO.setDepartment(departmentDTO);

            OrganizationDTO organizationDTO = webClient.get()
                    .uri("http://localhost:8083/api/organization/"+employee1.getOrganizationCode())
                    .retrieve()
                    .bodyToMono(OrganizationDTO.class)
                    .block();

            responseDTO.setOrganization(organizationDTO);

            return responseDTO;
        }

        return new ApiResponseDTO();
    }

    public ApiResponseDTO getDefaultDepartment(Long employeeId, Exception exception){
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isPresent()){
            Employee employee1 = employee.get();
            DepartmentDTO departmentDTO = new DepartmentDTO(
                    1L, "R&D Department", "R001", "Reasearch and Development Department"
            );
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    employee1.getId(),
                    employee1.getFirstName(),
                    employee1.getLastName(),
                    employee1.getEmail(),
                    employee1.getDepartmentCode(),
                    employee1.getOrganizationCode()
            );

            ApiResponseDTO responseDTO = new ApiResponseDTO();
            responseDTO.setEmployee(employeeDTO);
            responseDTO.setDepartment(departmentDTO);
            return responseDTO;
        }

        return new ApiResponseDTO();
    }


}

