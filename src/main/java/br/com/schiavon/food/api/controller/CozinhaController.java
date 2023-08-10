package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.api.model.CozinhasXMLWrapper;
import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.repositories.CozinhaRepository;
import br.com.schiavon.food.domain.services.CozinhaService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/{idCozinha}")
    public Cozinha buscar(@PathVariable Long idCozinha){
        return cozinhaService.buscarCozinhaId(idCozinha);
        
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
//
//        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha cadastro(@RequestBody @Valid Cozinha cozinha){
        return cozinhaService.cadastro(cozinha);
    }

    @PutMapping("/{idCozinha}")
    public Cozinha atualizar(@PathVariable Long idCozinha, @RequestBody Cozinha cozinha){
            return cozinhaService.atualizar(idCozinha, cozinha);
    }

    @DeleteMapping("/{idCozinha}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long idCozinha){
        cozinhaService.deletar(idCozinha);
    }

}
