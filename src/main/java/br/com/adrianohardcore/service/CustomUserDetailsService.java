package br.com.adrianohardcore.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.adrianohardcore.model.CustomUserDetails;
import br.com.adrianohardcore.model.Permissao;
import br.com.adrianohardcore.model.Usuario;
import br.com.adrianohardcore.repository.UsuarioRepository;

@Service
@Qualifier("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsuarioRepository userRepository;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        Usuario	 user = userRepository.findByNomeusuario(username);
        Usuario usuario = null;

        List<GrantedAuthority> authorities = buildUserAuthority(user.getPermissoes());

        return buildUserForAuthentication(user, authorities);



    }

    private UserDetails buildUserForAuthentication(Usuario user,List<GrantedAuthority> authorities) {
        return new CustomUserDetails(user.getNomeusuario(), user.getSenha(),true,true,true,true, authorities,user.getId(),user.getNome(),user.getEmail());
    }

    private List<GrantedAuthority> buildUserAuthority(List<Permissao> list) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        for (Permissao userRole : list) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getNomePermissao() ));
        }

        return new ArrayList<GrantedAuthority>(setAuths);
    }
}