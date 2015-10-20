package br.com.adrianohardcore.controller;


import br.com.adrianohardcore.model.Comment;
import br.com.adrianohardcore.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comments")
public class CommentController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired

    public CommentRepository repository;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Comment> listacomments() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Comment exibir(@PathVariable Long id) {
        return repository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteContato(@PathVariable Long id) {
        repository.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Comment inserir(@RequestBody Comment comment) {

        return repository.save(comment);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Comment attualiza(@RequestBody Comment comment) {

        return repository.saveAndFlush(comment);
    }
}
