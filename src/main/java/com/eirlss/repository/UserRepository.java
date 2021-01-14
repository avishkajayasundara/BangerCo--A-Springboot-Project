package com.eirlss.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eirlss.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

//	@Query(value = "SELECT * FROM user u WHERE u.username = ?1",nativeQuery = true)
//	Collection<User> findAllActiveUsersNative(@Param("userName") String userName);

	@Query(value = "SELECT * FROM user u WHERE u.username = ?1 AND u.password = ?2", nativeQuery = true)
	List<User> findUserByuserNAme(String userName, String password);

	@Query(value = "SELECT * FROM user u WHERE u.username = ?1", nativeQuery = true)
	List<User> findUserByuserNAme(String userName);

	@Query(value = "SELECT * FROM user u WHERE u.state = 'pending'", nativeQuery = true)
	List<User> getPendingUsers();

}
