package be.g00glen00b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import be.g00glen00b.model.Contato;
import be.g00glen00b.repository.ContatoRepository;

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
  
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteContato(@PathVariable Integer id) {
    repo.delete(id);
  }
}
