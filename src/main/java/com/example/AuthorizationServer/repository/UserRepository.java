package com.example.AuthorizationServer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AuthorizationServer.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	/**
	 * Fetching the userdetails from data base
	 * @param userName
	 * @return
	 */
	Optional<User> findByUserName(String userName);
	
}
