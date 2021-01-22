package com.eirlss.repository;

import com.eirlss.model.FlaggedLicenceHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlaggedLicenceHolderRepository extends JpaRepository<FlaggedLicenceHolder, String> {
}
