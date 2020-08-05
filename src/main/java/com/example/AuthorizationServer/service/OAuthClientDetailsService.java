package com.example.AuthorizationServer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import com.example.AuthorizationServer.entity.ClientDetailsEntity;
import com.example.AuthorizationServer.models.OAuthClientDetails;
import com.example.AuthorizationServer.repository.OAuthClientRepository;

@Service
public class OAuthClientDetailsService implements ClientDetailsService{

	@Autowired
	OAuthClientRepository authClientRepo;
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		ClientDetailsEntity clientDetailsObj = authClientRepo.findByclientId(clientId);
		if(clientDetailsObj != null) {
			
			String clientID = clientDetailsObj.getClientId();
			String clientSecret = clientDetailsObj.getClientSecret();
			Set<String> resourceId = Arrays.asList(clientDetailsObj.getResourceId().split(",")).stream().collect(Collectors.toSet());
			Set<String> scope = Arrays.asList(clientDetailsObj.getScope().split(",")).stream().collect(Collectors.toSet());
			Set<String> grantTypes = Arrays.asList(clientDetailsObj.getAuthorizedGrantTypes().split(",")).stream().collect(Collectors.toSet());
			Set<String> regUri = Arrays.asList(clientDetailsObj.getRegisteredRedirectUri().split(",")).stream().collect(Collectors.toSet());
			List<String> collect = Arrays.asList(clientDetailsObj.getAuthorities().split(",")).stream().collect(Collectors.toList());
			for(String authority : collect) {
				authorities.add(new SimpleGrantedAuthority(authority));
			}
			int accessTokenValiditySeconds = clientDetailsObj.getAccessTokenValiditySeconds();
			int refreshTokenValiditySeconds = clientDetailsObj.getRefreshTokenValiditySeconds();
			return new OAuthClientDetails(clientID, resourceId, clientSecret, grantTypes, regUri, scope, accessTokenValiditySeconds, refreshTokenValiditySeconds,authorities);
		
		}
		return null;
	}

}
