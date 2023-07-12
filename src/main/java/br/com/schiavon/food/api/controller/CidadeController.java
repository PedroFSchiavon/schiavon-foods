package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.domain.exceptions.EstadoNaoEncontradaException;
import br.com.schiavon.food.domain.exceptions.RelacionamentoEntidadeNaoEncontradoException;
import br.com.schiavon.food.domain.models.Cidade;
import br.com.schiavon.food.domain.repositories.CidadeRepository;
import br.com.schiavon.food.domain.services.CidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{cidadeId}")
    public Cidade buscar(@PathVariable Long cidadeId){
        return cidadeService.buscarCidadeId(cidadeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade cadastro(@RequestBody Cidade cidade){
        try{
            return cidadeService.cadastro(cidade);
        }catch (EstadoNaoEncontradaException e){
            throw new RelacionamentoEntidadeNaoEncontradoException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public Cidade atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade){
        try{
            return cidadeService.atualizar(cidadeId, cidade);
        }catch (EstadoNaoEncontradaException e){
            throw new RelacionamentoEntidadeNaoEncontradoException(e.getMessage(), e);
        }

    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long cidadeId){
            cidadeService.deletar(cidadeId);
    }
}
