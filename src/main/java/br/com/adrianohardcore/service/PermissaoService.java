package br.com.adrianohardcore.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adrianohardcore.model.Permissao;
import br.com.adrianohardcore.repository.PermissaoRepository;

@Service
public class PermissaoService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    PermissaoRepository permissaoRepository;



    public Permissao getUserById(long id) {
        return permissaoRepository.findById(id);//Optional.ofNullable(permissaoRepository.findOne(id));
    }

    public List<Permissao> getAllUsers() {
        log.info("Listagem de permissoes");
        return permissaoRepository.findAll();
    }

    public Permissao create(Permissao form) {
        log.info("Cadastrando ...");  
        return permissaoRepository.save(form);
    }
	
	    public Permissao update(Permissao form) {
        log.info("Atualizando ...");  
        return permissaoRepository.save(form);
    }

    public Optional<Permissao> getPermissaoByNomepermissao(String nomepermissao) {
        return permissaoRepository.findOneByNomePermissao(nomepermissao);
    }

    public Long getIdByNomePermissao(String nomePermissao) {
        return permissaoRepository.findByNomePermissao(nomePermissao).getId();
    }
}