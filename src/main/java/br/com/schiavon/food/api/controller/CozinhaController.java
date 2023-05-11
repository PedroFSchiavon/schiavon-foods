package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.api.model.CozinhasXMLWrapper;
import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.repositories.CozinhaRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    private final CozinhaRepository cozinhaRepository;

    public CozinhaController(CozinhaRepository cozinhaRepository){
        this.cozinhaRepository = cozinhaRepository;
    }

    @GetMapping
    public List<Cozinha> listar(){
        return cozinhaRepository.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXMLWrapper listarXML(){
        return new CozinhasXMLWrapper(cozinhaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id){
        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
        if(cozinha.isPresent())
            return ResponseEntity.ok(cozinha);

        return ResponseEntity.notFound().build();

//        return ResponseEntity.status(HttpStatus.OK).body(cozinha);    
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
//
//        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
}
