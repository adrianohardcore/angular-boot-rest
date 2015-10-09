package br.com.adrianohardcore.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.adrianohardcore.model.Permissao;
import br.com.adrianohardcore.repository.PermissaoRepository;


//@Controller
//@PreAuthorize("hasRole('USER')")
//@RestController
//@RequestMapping("/permissoes")
@Controller
public class PermissaoController {			   
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired    
	public PermissaoRepository repo;

	@RequestMapping(value = "/permissao", method = RequestMethod.GET)  
	public String index() {
		log.info("Pagina inicial permissao!");
		return "/permissao/index";
	} 	
 
	@RequestMapping(value = "/permissoes",method = RequestMethod.GET)
	@ResponseBody
	public List<Permissao> findItems() {
		return repo.findAll();
	}

	@RequestMapping(value = "/permissoes",method = RequestMethod.POST)
	@ResponseBody
	public Permissao addItem(@RequestBody Permissao permissao) {
		permissao.setId(null);
		return repo.saveAndFlush(permissao);
	}

	@RequestMapping(value = "/permissoes/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Permissao updateItem(@RequestBody Permissao updatedItem, @PathVariable Long id) {
		updatedItem.setId(id);
		return repo.saveAndFlush(updatedItem);
	}

	@RequestMapping(value = "/permissoes/{id}", method = RequestMethod.DELETE)
	@ResponseBody
		public void deleteItem(@PathVariable Long id) {
		repo.delete(id);
	}
}
