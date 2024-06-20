package com.microservice.organization.service.service;


import com.microservice.organization.service.dto.OrganizationDTO;

public interface OrganizationService {

    OrganizationDTO saveOrganization(OrganizationDTO organizationDTO);

    OrganizationDTO getOrganizationDTO(String organizationCode);
}
