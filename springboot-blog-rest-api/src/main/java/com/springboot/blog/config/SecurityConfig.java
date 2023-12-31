package com.springboot.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.blog.security.JwtAuthenticationEnrtyPoint;
import com.springboot.blog.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private UserDetailsService userDetailsService;

	private JwtAuthenticationEnrtyPoint jwtAuthenticationEnrtyPoint;

	private JwtAuthenticationFilter authenticationFilter;

	public SecurityConfig(UserDetailsService userDetailsService,
			JwtAuthenticationEnrtyPoint jwtAuthenticationEnrtyPoint, JwtAuthenticationFilter authenticationFilter) {
		super();
		this.userDetailsService = userDetailsService;
		this.jwtAuthenticationEnrtyPoint = jwtAuthenticationEnrtyPoint;
		this.authenticationFilter = authenticationFilter;
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

		return configuration.getAuthenticationManager();

	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeHttpRequests((authorize) ->
		// authorize.anyRequest().authenticated()
		authorize.antMatchers(HttpMethod.GET, "/api/**").permitAll().antMatchers("/api/auth/**").permitAll()
				.anyRequest().authenticated())
				.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEnrtyPoint))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	/*
	 * @Bean public UserDetailsService userDetailsService() {
	 * 
	 * UserDetails priyanka = User.builder(). username("priyanka").
	 * password(passwordEncoder().encode("priyanka")). roles("USER").build();
	 * UserDetails admin = User.builder(). username("admin").
	 * password(passwordEncoder().encode("admin")). roles("ADMIN").build();
	 * 
	 * return new InMemoryUserDetailsManager(priyanka, admin); }
	 */

}
