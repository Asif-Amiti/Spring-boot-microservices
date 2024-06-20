package com.microservice.organization.service.controller;


import com.microservice.organization.service.dto.OrganizationDTO;
import com.microservice.organization.service.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDTO> saveOrganization(@RequestBody OrganizationDTO organizationDTO) {
       OrganizationDTO response = organizationService.saveOrganization(organizationDTO);
       return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{organization-code}")
    public ResponseEntity<OrganizationDTO> getOrganization(@PathVariable("organization-code") String organizationCode) {
        OrganizationDTO response = organizationService.getOrganizationDTO(organizationCode);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
