package br.com.adrianohardcore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adrianohardcore.model.Permissao;


public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

    Permissao findByNomePermissao(String user);


	//Optional<Permissao> findOneByEmail(String email);
	
	
	Permissao findById(Long id);
	Optional<Permissao> findOneByNomePermissao(String nomePermissao);

}
