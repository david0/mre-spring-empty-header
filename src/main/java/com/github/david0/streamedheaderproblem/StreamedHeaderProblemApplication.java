package com.github.david0.streamedheaderproblem;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@SpringBootApplication
@EnableWebSecurity

public class StreamedHeaderProblemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamedHeaderProblemApplication.class, args);
	}

	@Bean
	SecurityFilterChain allowAll(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(antMatcher("/**")).permitAll());
		http.csrf(AbstractHttpConfigurer::disable);
		return http.build();
	}
}
