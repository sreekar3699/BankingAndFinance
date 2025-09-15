package com.JFSD.SDP.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	      return http
	          .authorizeHttpRequests(auth -> {
	              auth.requestMatchers(new AntPathRequestMatcher("/")).permitAll();
	              auth.requestMatchers(new AntPathRequestMatcher("/favicon.ico")).permitAll();
	              auth.requestMatchers(new AntPathRequestMatcher("/user/**")).authenticated();
	          })
	          .oauth2Login(withDefaults())
	          .formLogin(withDefaults())
	          
	          .logout(logout -> logout
	              .logoutSuccessUrl("/") // Redirect to the home page after logout
	              .permitAll()
	          )
	          .build();
	  }
}
