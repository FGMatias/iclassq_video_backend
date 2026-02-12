package com.iclass.video.repository;

import com.iclass.video.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Boolean existsByName(String name);

    Boolean existsByRuc(String ruc);
}
