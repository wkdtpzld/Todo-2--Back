package com.todo.todoP.Config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class AuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider(){
        return new SpringSecurityAuditImpl();
    }

}

@Slf4j
class SpringSecurityAuditImpl implements AuditorAware<String>{

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken){
            log.info("failed");
            return Optional.empty();
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.ofNullable(principal.toString());
    }
}