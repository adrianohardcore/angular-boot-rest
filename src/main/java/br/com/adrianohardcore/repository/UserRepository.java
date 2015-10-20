package br.com.adrianohardcore.repository;

import br.com.adrianohardcore.model.User;
import br.com.adrianohardcore.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface UserRepository extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User> {

}
