package be.g00glen00b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import be.g00glen00b.model.Operadora;
import be.g00glen00b.repository.OperadoraRepository;

@RestController
@RequestMapping("/operadoras")
//Operadoras de telefonia
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
