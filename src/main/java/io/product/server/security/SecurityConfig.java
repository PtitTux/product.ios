package io.product.server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationEventPublisher publisher) throws Exception
	{
		http.getSharedObject(AuthenticationManagerBuilder.class).authenticationEventPublisher(publisher);

		return http
		        .cors().and()
						.csrf().disable()
		        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		        .authorizeRequests().antMatchers("/auth/**").permitAll()
		        .anyRequest().permitAll().and()
		        .build();
	}

	/**
	 * Desactivate default in memory user
	 * @return
	 */
	@Bean
	UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager();
	}
}
