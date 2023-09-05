package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.api.model.dto.input.CidadeInputDTO;
import br.com.schiavon.food.api.model.dto.output.CidadeDTO;
import br.com.schiavon.food.domain.exceptions.naoencontrada.EstadoNaoEncontradaException;
import br.com.schiavon.food.domain.exceptions.RelacionamentoEntidadeNaoEncontradoException;
import br.com.schiavon.food.domain.models.Cidade;
import br.com.schiavon.food.domain.repositories.CidadeRepository;
import br.com.schiavon.food.domain.services.CidadeService;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
    private final CidadeRepository cidadeRepository;
    private final CidadeService cidadeService;
    private final ModelMapper modelMapper;

    public CidadeController(CidadeRepository cidadeRepository, CidadeService cidadeService, ModelMapper modelMapper){
        this.cidadeRepository = cidadeRepository;
        this.cidadeService = cidadeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<CidadeDTO> listar(){
        return toCollectionDTO(cidadeRepository.findAll());
    }

    @GetMapping("/{cidadeId}")
    public CidadeDTO buscar(@PathVariable Long cidadeId){
        return toDTO(cidadeService.buscarCidadeId(cidadeId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDTO cadastro(@RequestBody @Valid CidadeInputDTO cidadeInputDTO){
        try{
            Cidade cidade = toModelDomain(cidadeInputDTO);
            return toDTO(cidadeService.cadastro(cidade));
        }catch (EstadoNaoEncontradaException e){
            throw new RelacionamentoEntidadeNaoEncontradoException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeDTO atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInputDTO cidadeInputDTO){
        try{
            Cidade cidade = toModelDomain(cidadeInputDTO);
            return toDTO(cidadeService.atualizar(cidadeId, cidade));
        }catch (EstadoNaoEncontradaException e){
            throw new RelacionamentoEntidadeNaoEncontradoException(e.getMessage(), e);
        }

    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long cidadeId){
            cidadeService.deletar(cidadeId);
    }

    private CidadeDTO toDTO(Cidade cidade){
        return modelMapper.map(cidade, CidadeDTO.class);
    }

    private List<CidadeDTO> toCollectionDTO(List<Cidade> cidades){
        return cidades.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private Cidade toModelDomain(CidadeInputDTO cidadeInputDTO){
        return modelMapper.map(cidadeInputDTO, Cidade.class);
    }
}
