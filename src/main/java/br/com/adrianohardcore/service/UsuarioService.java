package br.com.adrianohardcore.service;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.adrianohardcore.model.CustomUserDetails;
import br.com.adrianohardcore.model.Permissao;
import br.com.adrianohardcore.model.Usuario;
import br.com.adrianohardcore.repository.PermissaoRepository;
import br.com.adrianohardcore.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PermissaoRepository permissaoRepository;


    public Usuario getUserById(long id) {
        return usuarioRepository.findById(id);//Optional.ofNullable(usuarioRepository.findOne(id));
    }

    public Optional<Usuario> getUserByEmail(String email) {
        log.info("Getting user by email={}", email.replaceFirst("@.*", "@***"));
        return usuarioRepository.findOneByEmail(email);
    }

    public List<Usuario> getAllUsers() {
        log.info("Usuarios cadastrados");


        CustomUserDetails usuarioAtual = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("ID: " + usuarioAtual.getId());
        log.info("Email: " + usuarioAtual.getEmail());
        log.info("Permissoes: " + usuarioAtual.getAuthorities());


        return usuarioRepository.findAll();
    }

    public Usuario create(Usuario form) {
        log.info("Cadastrando ...");
        Usuario user = form;

        log.info("Permissão encontrada: " + permissaoRepository.findByNomePermissao("USER"));
        Permissao permissao = permissaoRepository.findByNomePermissao("USER");

        if (permissao == null) {
            permissao = new Permissao();
            permissao.setNomePermissao("USER");
        }
        List<Permissao> permissoes = new ArrayList();
        permissoes.add(permissao);
        user.setPermissoes(permissoes);

        log.info("Senha digitada: " + form.getSenhaForm());
        user.setSenha(new BCryptPasswordEncoder().encode(form.getSenhaForm()));
        log.info("Senha criptografada: " + user.getSenhaForm());
        return usuarioRepository.save(user);
    }

    public void update(Usuario usuarioForm ) {
        Usuario usuario = usuarioRepository.findById(usuarioForm.getId());
        usuario.setEmail(usuarioForm.getEmail());
        usuario.setNomeusuario(usuarioForm.getNomeusuario());
        usuario.setNome(usuarioForm.getNome());



        for (Permissao permissoes : usuario.getPermissoes()) {
            log.info(format("Permissao %s", permissoes.getNomePermissao()));
        }


        log.info("Senha " + usuarioForm.getSenhaForm());

        if (!usuarioForm.getSenhaForm().trim().isEmpty()){
            log.info("Atualizando a senha do usuario, com a senha " + usuarioForm.getSenhaForm());
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioForm.getSenhaForm()));
        }

        log.info("Editando usuario " + usuario.getNome());
        usuarioRepository.saveAndFlush(usuario);
    }

    public Long getIdByEmail(String email) {
        return usuarioRepository.findByEmail(email).getId();
    }


    public Optional<Usuario> getUsuarioByNomeusuario(String nomeusuario) {
        return usuarioRepository.findOneByNomeusuario(nomeusuario);
    }

    public Long getIdByNomeusuario(String nomeusuario) {
        return usuarioRepository.findByNomeusuario(nomeusuario).getId();
    }
}