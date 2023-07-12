package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.domain.models.Estado;
import br.com.schiavon.food.domain.repositories.EstadoRepository;
import br.com.schiavon.food.domain.services.EstadoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {
    private final EstadoRepository estadoRepository;
    private final EstadoService estadoService;

    public EstadoController(EstadoRepository estadoRepository, EstadoService estadoService) {
        this.estadoRepository = estadoRepository;
        this.estadoService = estadoService;
    }

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @GetMapping("/{idEstado}")
    public Estado buscar(@PathVariable Long idEstado) {
        return estadoService.buscaEstadoId(idEstado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado cadastro(@RequestBody Estado estado) {
        return estadoService.cadastro(estado);
    }

    @PutMapping("/{idEstado}")
    public Estado atualizar(@PathVariable Long idEstado, @RequestBody Estado estado) {
        return estadoService.atualizar(idEstado, estado);
    }

    @DeleteMapping("/{idEstado}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long idEstado) {
        estadoService.deletar(idEstado);
    }
}
