package com.iclassq.video.repository;

import com.iclassq.video.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
    Boolean existsByName(String name);
}
