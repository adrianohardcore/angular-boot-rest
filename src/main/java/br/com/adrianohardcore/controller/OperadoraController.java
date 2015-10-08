package br.com.adrianohardcore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.adrianohardcore.model.Operadora;
import br.com.adrianohardcore.repository.OperadoraRepository;
import java.util.ArrayList;
import java.lang.*;

@RestController
@RequestMapping("/operadoras")
public class OperadoraController {
  @Autowired
  private OperadoraRepository repo;
  
  @RequestMapping(method = RequestMethod.GET)
  public List<Operadora> findOperadoras() {      			
    return repo.findAll();
  }
  
  @RequestMapping(method = RequestMethod.POST)
  public Operadora addOperadora(@RequestBody Operadora Operadora) {
    Operadora.setId(null);
    return repo.saveAndFlush(Operadora);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public Operadora updateOperadora(@RequestBody Operadora updatedOperadora, @PathVariable Integer id) {
    updatedOperadora.setId(id);
    return repo.saveAndFlush(updatedOperadora);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteOperadora(@PathVariable Integer id) {
    repo.delete(id);
  }
}
