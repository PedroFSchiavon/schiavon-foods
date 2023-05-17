package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.domain.exceptions.RelacionamentoEntidadeNaoEncontradoException;
import br.com.schiavon.food.domain.models.Cidade;
import br.com.schiavon.food.domain.repositories.CidadeRepository;
import br.com.schiavon.food.domain.services.CidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
    private final CidadeRepository cidadeRepository;
    private final CidadeService cidadeService;

    public CidadeController(CidadeRepository cidadeRepository, CidadeService cidadeService){
        this.cidadeRepository = cidadeRepository;
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public List<Cidade> listar(){
        return cidadeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long id){
        Optional<Cidade> cidadeOptional = cidadeRepository.findById(id);
        return cidadeOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> cadastro(@RequestBody Cidade cidade){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.cadastro(cidade));
        }catch (RelacionamentoEntidadeNaoEncontradoException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
