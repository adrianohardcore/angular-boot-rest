package br.com.adrianohardcore.model.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.adrianohardcore.model.Usuario;
import br.com.adrianohardcore.repository.UsuarioRepository;
import br.com.adrianohardcore.service.UsuarioService;

@Component
public class UsuarioValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioValidator.class);

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioValidator(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Usuario.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senhaForm", "required", "Senha vazia");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmSenhaForm", "required", "Confirmação de senha vazia");

        Usuario usuario = (Usuario) target;

        if (!(usuario.getSenhaForm().equals(usuario.getConfirmSenhaForm()))) {
            errors.rejectValue("senhaForm", "notmatch", "Comfirmação de senha não combina com a senha");
        }

        if (usuarioService.getUserByEmail(usuario.getEmail()).isPresent() && usuario.getId() != usuarioService.getIdByEmail(usuario.getEmail())) {
            errors.reject("email", "Este email já está sendo usado");
        }

        if (usuarioService.getUsuarioByNomeusuario(usuario.getNomeusuario()).isPresent() && usuario.getId() != usuarioService.getIdByNomeusuario(usuario.getNomeusuario()))
        {
            errors.reject("nomeusuario", "Este usuário já está sendo usado");
        }

    }
}

