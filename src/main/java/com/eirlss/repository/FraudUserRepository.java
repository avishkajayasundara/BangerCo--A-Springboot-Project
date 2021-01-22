package com.eirlss.repository;

import com.eirlss.model.FraudUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudUserRepository extends CrudRepository<FraudUser, String> {
}
