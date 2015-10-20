package br.com.adrianohardcore.controller;


import br.com.adrianohardcore.model.User;
import br.com.adrianohardcore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired

    public UserRepository repository;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<User> listausers() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User exibir(@PathVariable Long id) {
        return repository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteContato(@PathVariable Long id) {
        repository.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User inserir(@RequestBody User user) {

        return repository.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public User attualiza(@RequestBody User user) {

        return repository.saveAndFlush(user);
    }
}
