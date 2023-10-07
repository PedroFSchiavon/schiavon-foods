package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.api.model.dto.input.PedidoInputDTO;
import br.com.schiavon.food.api.model.dto.output.PedidoDTO;
import br.com.schiavon.food.api.model.dto.output.PedidoResumoDTO;
import br.com.schiavon.food.domain.models.Pedido;
import br.com.schiavon.food.domain.repositories.PedidoRepository;
import br.com.schiavon.food.domain.services.PedidoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    private final PedidoRepository pedidoRepository;

    private final ModelMapper modelMapper;

    public PedidoController(PedidoService pedidoService, PedidoRepository pedidoRepository, ModelMapper modelMapper) {
        this.pedidoService = pedidoService;
        this.pedidoRepository = pedidoRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<PedidoResumoDTO> listar(){
        return toCollectionResumoDTO(pedidoRepository.findAll());
    }

    @GetMapping("/{idPedido}")
    public PedidoDTO buscar(@PathVariable Long idPedido){
        return toDTO(pedidoService.buscarPedidoId(idPedido));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO cadastro(@RequestBody PedidoInputDTO pedidoInputDTO){
        return toDTO(pedidoService.cadastro(pedidoInputDTO));
    }

    private PedidoDTO toDTO(Pedido pedido){
        return modelMapper.map(pedido, PedidoDTO.class);
    }

    private PedidoResumoDTO toResumoDTO(Pedido pedido){
        return modelMapper.map(pedido, PedidoResumoDTO.class);
    }

    private List<PedidoResumoDTO> toCollectionResumoDTO(List<Pedido> pedidos){
        return pedidos.stream().map(this::toResumoDTO).collect(Collectors.toList());
    }

    private Pedido toDomainModel(PedidoInputDTO pedidoInputDTO){
        return modelMapper.map(pedidoInputDTO, Pedido.class);
    }
}
