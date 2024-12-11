package io.rapidtogo.rapidtogo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Value("${management.endpoints.web.base-path}")
  private String ACTUATOR_PATH;

  @Value("${springdoc.api-docs.path}")
  private String API_DOCS_PATH;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.csrf(AbstractHttpConfigurer::disable);
    http.cors(Customizer.withDefaults());
    http.headers(headers -> headers.frameOptions(
        HeadersConfigurer.FrameOptionsConfig::disable)); // H2-Console (if enabled in application.properties)

    http.authorizeHttpRequests((auth) -> auth
        .requestMatchers("/error", ACTUATOR_PATH + "/**").permitAll()
        .requestMatchers(API_DOCS_PATH + "/**", "/h2-console/**").hasRole("ADMIN")
        .requestMatchers("/v1/customers/**").hasRole("CUSTOMER")
        .requestMatchers("/v1/partners/**").hasRole("PARTNER")
        .anyRequest().authenticated());

    http.sessionManagement(
        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

    return http.build();
  }
}
