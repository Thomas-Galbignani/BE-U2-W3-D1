package thomasgalbignani.BE_U2_W3_D1.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // serve per definire la classe di Speciale di Spring Security
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.formLogin(formLogin -> formLogin.disable());// No Auth di Spring Security abbiamo la nostra custom

        httpSecurity.csrf(csrf -> csrf.disable());// no protezione csrf

        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // no session siccome autenticazione è basata su Token

        httpSecurity.authorizeHttpRequests(req -> req.requestMatchers("/**").permitAll()); // disabilitiamo il 401 di default


        return httpSecurity.build();
    }

}
