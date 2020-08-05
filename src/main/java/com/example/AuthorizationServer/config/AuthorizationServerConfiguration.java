package com.example.AuthorizationServer.config;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.example.AuthorizationServer.endpoints.SubjectAttributeUserTokenConverter;
import com.example.AuthorizationServer.service.OAuthClientDetailsService;

/**
 * 
 * The class will create and return json web token when the client
 * is properly authenticates.
 *
 */
/**
 * 
 * Tell Spring to activate the authorization server.
 *
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	
	
	@Value("${security.oauth2.authorizationserver.jwt.enabled}") 
	private Boolean jwtEnabled;
	
	AuthenticationManager authenticationManager;
	
	KeyPair keyPair;

	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	OAuthClientDetailsService clientDetailsService;
	
	

	public AuthorizationServerConfiguration(AuthenticationConfiguration authenticationConfiguration, KeyPair keyPair
			) throws Exception {

		this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
		this.keyPair = keyPair;
	}

	/**
	 * Function configure specifies what are the credentials that has to be given.
	 * 
	 * ||clients.inMemory() specifies that we are going to store the services in memory. 
	 * ||withClient ("client")is the user with whom we will identify.
	 * ||authorizedGrantTypes specify services that configure for the defined user.
	 * ||scopes ("read", "write") is the scope of the service. 
	 * ||secret(passwordEncoder (). encode ("password")) is the password of the client.
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientDetailsService);
	}

	/**
	 * Function specifies which authentication controller and store of identifiers
	 * should use the end points.
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.authenticationManager(this.authenticationManager).tokenStore(tokenStore());

		if (jwtEnabled) {
			
				endpoints.accessTokenConverter(accessTokenConverter());
			
		}
	}

	@Bean
	public TokenStore tokenStore() {
		if (jwtEnabled) {
			
				return new JwtTokenStore(accessTokenConverter());
		} 
		
		else {
			return new InMemoryTokenStore();
		}
		
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setKeyPair(this.keyPair);

		DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
		accessTokenConverter.setUserTokenConverter(new SubjectAttributeUserTokenConverter());
		
		converter.setAccessTokenConverter(accessTokenConverter);

		return converter;
	}
	
}
