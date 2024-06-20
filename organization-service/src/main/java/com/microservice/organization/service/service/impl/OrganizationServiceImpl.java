package com.microservice.organization.service.service.impl;

import com.microservice.organization.service.dto.OrganizationDTO;
import com.microservice.organization.service.entity.Organization;
import com.microservice.organization.service.mapper.OrganizationMapper;
import com.microservice.organization.service.repository.OrganizationRepository;
import com.microservice.organization.service.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDTO saveOrganization(OrganizationDTO organizationDTO) {
        Organization org = OrganizationMapper.mapToOrganization(organizationDTO);
        org = organizationRepository.save(org);
        return OrganizationMapper.mapToOrganizationDTO(org);
    }

    @Override
    public OrganizationDTO getOrganizationDTO(String organizationCode) {
        Organization org = organizationRepository.findByOrganizationCode(organizationCode);
        OrganizationDTO dto = OrganizationMapper.mapToOrganizationDTO(org);
        return dto;
    }
}
