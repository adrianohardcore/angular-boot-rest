package br.com.adrianohardcore.service;

import br.com.adrianohardcore.domain.User;
import br.com.adrianohardcore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Classe responsável por fazer uma importação inicial dos dados para o bnco
 */

@Named
public class DataImporter implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LoggerFactory
        .getLogger(DataImporter.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (log.isInfoEnabled()) {
            log.info("importing data into database...");
        }

        Long usersCount = userRepository.count();
        if (usersCount == 0) {
            if (log.isDebugEnabled()) {
                log.debug("import users data into database...");
            }
            userRepository.save(
                new User().builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .name("Administrator")
                .role("ADMIN")
                .build()
            );
            userRepository.save(
                 new User().builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .name("Test User")
                .role("USER")
                .build()
            );

        }

    }

}
