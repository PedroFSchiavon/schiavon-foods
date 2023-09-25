package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.api.model.dto.output.PedidoDTO;
import br.com.schiavon.food.domain.models.Pedido;
import br.com.schiavon.food.domain.repositories.PedidoRepository;
import br.com.schiavon.food.domain.services.PedidoService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<PedidoDTO> listar(){
        return toCollectionDTO(pedidoRepository.findAll());
    }

    private PedidoDTO toDTO(Pedido pedido){
        return modelMapper.map(pedido, PedidoDTO.class);
    }

    private List<PedidoDTO> toCollectionDTO(List<Pedido> pedidos){
        return pedidos.stream().map(this::toDTO).collect(Collectors.toList());
    }
}