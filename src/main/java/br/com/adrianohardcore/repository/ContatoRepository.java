package br.com.adrianohardcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adrianohardcore.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {

}
