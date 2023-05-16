package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.domain.models.Estado;
import br.com.schiavon.food.domain.repositories.EstadoRepository;
import br.com.schiavon.food.domain.services.EstadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {
    private final EstadoRepository estadoRepository;
    private final EstadoService estadoService;

    public EstadoController(EstadoRepository estadoRepository, EstadoService estadoService){
        this.estadoRepository = estadoRepository;
        this.estadoService = estadoService;
    }

    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> buscar(@PathVariable Long id){
        Optional<Estado> estadoOptional = estadoRepository.findById(id);
        return estadoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Estado> cadastro(@RequestBody Estado estado){
        return ResponseEntity.ok(estadoService.cadastro(estado));
    }


}
