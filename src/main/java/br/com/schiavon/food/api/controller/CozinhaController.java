package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.api.model.CozinhasXMLWrapper;
import br.com.schiavon.food.api.model.dto.input.CozinhaInputDTO;
import br.com.schiavon.food.api.model.dto.output.CozinhaDTO;
import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.repositories.CozinhaRepository;
import br.com.schiavon.food.domain.services.CozinhaService;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    private final CozinhaRepository cozinhaRepository;
    private final CozinhaService cozinhaService;
    private final ModelMapper modelMapper;

    public CozinhaController(CozinhaRepository cozinhaRepository, CozinhaService cozinhaService, ModelMapper modelMapper){
        this.cozinhaRepository = cozinhaRepository;
        this.cozinhaService = cozinhaService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<CozinhaDTO> listar(){
        return toCollectionDTO(cozinhaRepository.findAll());
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXMLWrapper listarXML(){
        return new CozinhasXMLWrapper(cozinhaRepository.findAll());
    }

    @GetMapping("/{idCozinha}")
    public CozinhaDTO buscar(@PathVariable Long idCozinha){
        return toDTO(cozinhaService.buscarCozinhaId(idCozinha));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaDTO cadastro(@RequestBody @Valid CozinhaInputDTO cozinhaInputDTO){
        Cozinha cozinha = toDomainModel(cozinhaInputDTO);
        return toDTO(cozinhaService.cadastro(cozinha));
    }

    @PutMapping("/{idCozinha}")
    public CozinhaDTO atualizar(@PathVariable Long idCozinha, @RequestBody @Valid CozinhaInputDTO cozinhaInputDTO){
        Cozinha cozinha = toDomainModel(cozinhaInputDTO);
        return toDTO(cozinhaService.atualizar(idCozinha, cozinha));
    }

    @DeleteMapping("/{idCozinha}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long idCozinha){
        cozinhaService.deletar(idCozinha);
    }

    private CozinhaDTO toDTO(Cozinha cozinha){
        return modelMapper.map(cozinha, CozinhaDTO.class);
    }

    private List<CozinhaDTO> toCollectionDTO(List<Cozinha> cozinhas){
        return cozinhas.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private Cozinha toDomainModel(CozinhaInputDTO cozinhaInputDTO){
        return modelMapper.map(cozinhaInputDTO, Cozinha.class);
    }
}
