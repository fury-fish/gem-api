package com.gemapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gemapi.entity.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
} 