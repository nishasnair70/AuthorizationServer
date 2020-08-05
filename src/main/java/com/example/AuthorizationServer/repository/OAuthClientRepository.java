package com.example.AuthorizationServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AuthorizationServer.entity.ClientDetailsEntity;

@Repository
public interface OAuthClientRepository extends JpaRepository<ClientDetailsEntity, Long> {

	/**
	 * Fetching the client details from database
	 * @param clientId
	 * @return
	 */
	ClientDetailsEntity findByclientId(String clientId);
}
