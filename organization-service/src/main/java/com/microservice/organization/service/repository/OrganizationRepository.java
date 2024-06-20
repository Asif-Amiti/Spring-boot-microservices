package com.microservice.organization.service.repository;

import com.microservice.organization.service.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    public Organization findByOrganizationCode(String name);
}
