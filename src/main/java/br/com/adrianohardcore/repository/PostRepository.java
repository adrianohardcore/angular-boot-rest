package br.com.adrianohardcore.repository;

import br.com.adrianohardcore.model.Post;
import br.com.adrianohardcore.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, QueryDslPredicateExecutor<Post> {

}
