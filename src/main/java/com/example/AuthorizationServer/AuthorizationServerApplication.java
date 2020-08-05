package com.example.AuthorizationServer;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
		"com.example"})
public class AuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
//		Hashtable<String, String> ldapEnv = new Hashtable<>();
//		ldapEnv.put( Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
//		ldapEnv.put(Context.PROVIDER_URL, "ldap://192.168.10.198:389/");
//		ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
//		ldapEnv.put(Context.SECURITY_PRINCIPAL , "cn=admin,dc=example,dc=com");
//		ldapEnv.put(Context.SECURITY_CREDENTIALS, "Global12$");
//		System.out.println(" success");
		
//		Properties initialProperties = new Properties();
//		initialProperties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
//		initialProperties.put(Context.PROVIDER_URL, "ldap://localhost:10389");
//		initialProperties.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
//		initialProperties.put(Context.SECURITY_CREDENTIALS, "secret");
//		try {
//			DirContext  context = new InitialDirContext(initialProperties);
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
	}

}
