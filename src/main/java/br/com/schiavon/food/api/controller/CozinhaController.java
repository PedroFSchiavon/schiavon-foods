package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.api.model.CozinhasXMLWrapper;
import br.com.schiavon.food.domain.exceptions.EntidadeEmUsoException;
import br.com.schiavon.food.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.repositories.CozinhaRepository;
import br.com.schiavon.food.domain.services.CozinhaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    private final CozinhaRepository cozinhaRepository;
    private final CozinhaService cozinhaService;

    public CozinhaController(CozinhaRepository cozinhaRepository, CozinhaService cozinhaService){
        this.cozinhaRepository = cozinhaRepository;
        this.cozinhaService = cozinhaService;
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
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id){
        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
        return cozinha.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
//
//        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

    @PostMapping
    public ResponseEntity<Cozinha> cadastro(@RequestBody Cozinha cozinha){
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaService.cadastro(cozinha));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha){
        try{
            return ResponseEntity.ok(cozinhaService.atualizar(id, cozinha));
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cozinha> deletar(@PathVariable Long id){
        try{
            cozinhaService.deletar(id);
            return ResponseEntity.noContent().build();
        }catch (EntidadeEmUsoException e){
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch (EntidadeNaoEncontradaException e){
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
            return ResponseEntity.notFound().build();
        }
    }

}
