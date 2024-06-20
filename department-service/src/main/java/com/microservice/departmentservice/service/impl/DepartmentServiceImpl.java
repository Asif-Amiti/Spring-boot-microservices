package com.microservice.departmentservice.service.impl;

import com.microservice.departmentservice.dto.DepartmentDTO;
import com.microservice.departmentservice.entity.Department;
import com.microservice.departmentservice.repository.DepartmentRepository;
import com.microservice.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {

        //convert dep dto to dept JPA entity

        Department department = new Department(
                departmentDTO.getId(),
                departmentDTO.getDepartmentName(),
                departmentDTO.getDepartmentDescription(),
                departmentDTO.getDepartmentCode()
        );

        Department savedDep = departmentRepository.save(department);
        return new DepartmentDTO(
                savedDep.getId(),
                savedDep.getDepartmentName(),
                savedDep.getDepartmentDescription(),
                savedDep.getDepartmentCode()
        );
    }

    @Override
    public DepartmentDTO getDepartmentByCode(String code) {

        Department department = departmentRepository.findByDepartmentCode(code);

        if(department != null) {
            return new DepartmentDTO(
                    department.getId(),
                    department.getDepartmentName(),
                    department.getDepartmentDescription(),
                    department.getDepartmentCode()
            );
        }
        return new DepartmentDTO();
    }
}
