package com.microservice.organization.service.mapper;

import com.microservice.organization.service.dto.OrganizationDTO;
import com.microservice.organization.service.entity.Organization;

public class OrganizationMapper {

    public static OrganizationDTO mapToOrganizationDTO(Organization organization) {
            return  new OrganizationDTO(
                    organization.getId(),
                    organization.getOrganizationName(),
                    organization.getOrganizationDescription(),
                    organization.getOrganizationName(),
                    organization.getCreatedDate()
            );
    }

    public static Organization mapToOrganization(OrganizationDTO organizationDTO) {
        return  new Organization(
                organizationDTO.getId(),
                organizationDTO.getOrganizationName(),
                organizationDTO.getOrganizationDescription(),
                organizationDTO.getOrganizationCode(),
                organizationDTO.getCreatedDate()
        );

    }
}
