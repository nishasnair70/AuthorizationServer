package com.example.AuthorizationServer.models;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

public class OAuthClientDetails implements ClientDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String clientId;
	private Set<String> resourceId;
	private String clientSecret;
	private Set<String> scope;
	private Set<String> authorizedGrantTypes;
	private Set<String> registeredRedirectUri;
	private List<GrantedAuthority> authorities;
	private int accessTokenValiditySeconds;
	private int refreshTokenValiditySeconds;
	private Map<String, Object> additionalInformation;
	
	
	
	public OAuthClientDetails(String clientId,Set<String> resourceId,String clientSecret,Set<String> authorizedGrantTypes,
			Set<String> registeredRedirectUri,Set<String> scope,int accessTokenValiditySeconds,int refreshTokenValiditySeconds,List<GrantedAuthority> authorities) {
		
		this.clientId = clientId;
		this.resourceId = resourceId;
		this.clientSecret = clientSecret;
		this.authorizedGrantTypes= authorizedGrantTypes;
		this.registeredRedirectUri=registeredRedirectUri;
		this.scope=scope;
		this.accessTokenValiditySeconds=accessTokenValiditySeconds;
		this.refreshTokenValiditySeconds=refreshTokenValiditySeconds;
		this.authorities = authorities;
	}

	public OAuthClientDetails() {
		
	}

	@Override
	public String getClientId() {
		return this.clientId;
	}

	@Override
	public Set<String> getResourceIds() {
		return this.resourceId;
	}

	@Override
	public boolean isSecretRequired() {
		return true;
	}

	@Override
	public String getClientSecret() {
		return clientSecret;
	}

	@Override
	public boolean isScoped() {
		return true;
	}

	@Override
	public Set<String> getScope() {
		return this.scope;
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return this.authorizedGrantTypes;
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		return this.registeredRedirectUri;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return this.accessTokenValiditySeconds;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return this.refreshTokenValiditySeconds;
	}

	@Override
	public boolean isAutoApprove(String scope) {
		return true;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		return this.additionalInformation;
	}

}
