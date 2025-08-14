package br.com.leonardoraupp.apibancaria.infrastructure.config;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
//import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/h2-console/**", "/auth/*", "/account/**").permitAll()
//                        .anyRequest().authenticated())
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers("/h2-console/**", "/auth/*"))
//                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
//                .httpBasic(Customizer.withDefaults())
//                .oauth2ResourceServer(conf -> conf.jwt(Customizer.withDefaults()));
//        return http.build();
//    }
}
