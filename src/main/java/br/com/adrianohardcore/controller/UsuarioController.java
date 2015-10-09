package br.com.adrianohardcore.controller;


import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.adrianohardcore.model.Usuario;
import br.com.adrianohardcore.model.validator.UsuarioValidator;
import br.com.adrianohardcore.repository.UsuarioRepository;
import br.com.adrianohardcore.service.UsuarioService;


@RestController
@RequestMapping("/usuarios") 
public class UsuarioController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());



    @Autowired
    public UsuarioService service;

    @Autowired
    public UsuarioRepository repository;

    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Usuario> listausuarios(){
    	return repository.findAll();
    }
   
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Usuario exibir(@PathVariable Long id) {    
      return repository.findOne(id);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteContato(@PathVariable Long id) {
    	repository.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Usuario inserir(@RequestBody Usuario usuario) {
      
      return repository.saveAndFlush(usuario);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public Usuario attualiza(@RequestBody Usuario usuario) {
      
      return repository.saveAndFlush(usuario);
    }   
}
