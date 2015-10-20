package br.com.adrianohardcore.controller;


import br.com.adrianohardcore.model.Post;
import br.com.adrianohardcore.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/posts") 
public class PostController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public PostRepository repository;
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Post> listaposts(){
    	return repository.findAll();
    }
   
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Post exibir(@PathVariable Long id) {    
      return repository.findOne(id);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteContato(@PathVariable Long id) {
    	repository.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Post inserir(@RequestBody Post post) {
        return repository.save(post);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public Post attualiza(@RequestBody Post post) {
      
      return repository.saveAndFlush(post);
    }   
}
