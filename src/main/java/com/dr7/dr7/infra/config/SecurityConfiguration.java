package com.dr7.dr7.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration  {
    FilterValidation filterValidation;
    SecurityConfiguration(FilterValidation filterValidation){
        this.filterValidation = filterValidation;
    }
   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(e->e.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(e->e.requestMatchers("/v3/api-docs/**","/swagger-ui.html","/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/zapdai/v1/usuario/auth").permitAll()
                        .requestMatchers(HttpMethod.POST,"/zapdai/v1/usuario/registro").permitAll()
                        .requestMatchers(HttpMethod.GET,"/categorias/imagens/*").permitAll()
                        .requestMatchers(HttpMethod.POST,"/process_payment/webhook/*").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/zapdai/v1/usuario/verification").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/zapdai/v1/usuario/code").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/zapdai/v1/usuario/newpasswd").permitAll()
                        .requestMatchers(HttpMethod.GET,"/zapdai/v1/usuario/avatar/*").permitAll()
                        .requestMatchers(HttpMethod.GET,"/zapdai/v1/produtos/unit/*").permitAll()
                        .requestMatchers(HttpMethod.GET,"/zapdai/v1/empresas/lista").permitAll()
                        .requestMatchers(HttpMethod.GET,"/zapdai/v1/empresas/categorias").permitAll()

                        .requestMatchers(HttpMethod.GET,"/zapdai/v1/produtos/avatar/*").permitAll()
                        .requestMatchers(HttpMethod.GET,"/funcao/lista").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/process_payment/v1/*").permitAll()
                        .requestMatchers(HttpMethod.GET,"/zapdai/v1/planos-current/lista").permitAll()
                        .requestMatchers(HttpMethod.GET,"/categorias/lista").permitAll()
                        .anyRequest().authenticated()
                )

                .addFilterBefore(filterValidation, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
       return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }
}
