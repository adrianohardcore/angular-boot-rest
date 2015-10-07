package br.com.adrianohardcore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.adrianohardcore.model.Contato;
import br.com.adrianohardcore.repository.ContatoRepository;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
  @Autowired
  private ContatoRepository repo;
  
  @RequestMapping(method = RequestMethod.GET)
  public List<Contato> findContatos() {
    return repo.findAll();
  }
  
  @RequestMapping(method = RequestMethod.POST)
  public Contato addContato(@RequestBody Contato contato) {
    contato.setSerial(null);
    return repo.saveAndFlush(contato);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public Contato updateContato(@RequestBody Contato updatedContato, @PathVariable Integer id) {
    updatedContato.setId(id);
    return repo.saveAndFlush(updatedContato);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Contato exibir(@PathVariable Integer id) {    
    return repo.findOne(id);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteContato(@PathVariable Integer id) {
    repo.delete(id);
  }
}
