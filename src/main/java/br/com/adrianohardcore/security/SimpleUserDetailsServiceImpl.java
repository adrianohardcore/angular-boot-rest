package br.com.adrianohardcore.security;

import br.com.adrianohardcore.domain.User;
import br.com.adrianohardcore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//@Named
public class SimpleUserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(SimpleUserDetailsServiceImpl.class);

    //@Inject
    private UserRepository userRepository;

    public SimpleUserDetailsServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.debug("Erro2!");
            throw new UsernameNotFoundException("username not found:" + username);
        }
        log.debug("found by username @" + username);

        log.debug("Teste!");
        if (log.isDebugEnabled()) {
            log.debug("found by username @" + username);
        }

        return user;

    }

}
