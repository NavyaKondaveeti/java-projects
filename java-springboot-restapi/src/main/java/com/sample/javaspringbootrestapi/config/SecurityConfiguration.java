package com.sample.javaspringbootrestapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfiguration{
	

	@Value("${api.securityUsername}")
	private String username;

	@Value("${api.securityPassword}")
	private String password;
	
	@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	    	.csrf(AbstractHttpConfigurer::disable)
	        .authorizeHttpRequests((requests) -> requests
	        		.requestMatchers("/actuator/**").permitAll()	
	        		.anyRequest().authenticated()
	        		)
	        .httpBasic(Customizer.withDefaults());
	    return http.build();
	}
	
	@Bean
    public UserDetailsService userDetailsService(){

        UserDetails user = User
                //.withUsername(Base64.getDecoder().decode(getUsername()).toString())
                //.password(passwordEncoder().encode(Base64.getDecoder().decode(getPassword()).toString()))
        		.withUsername(getUsername())
        		.password(passwordEncoder().encode(getPassword()))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider ap = new DaoAuthenticationProvider();
		ap.setUserDetailsService(userDetailsService());
		ap.setPasswordEncoder(passwordEncoder());
		return ap;
		
	}
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

    public void setPassword(String password) {
		this.password = password;
	}
	
}
