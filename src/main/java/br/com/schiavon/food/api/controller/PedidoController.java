package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.domain.repositories.PedidoRepository;
import br.com.schiavon.food.domain.services.PedidoService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
