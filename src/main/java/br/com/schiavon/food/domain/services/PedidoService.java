package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.api.model.dto.input.EnderecoInputDTO;
import br.com.schiavon.food.api.model.dto.input.ItemPedidoInputDTO;
import br.com.schiavon.food.api.model.dto.input.PedidoInputDTO;
import br.com.schiavon.food.domain.exceptions.naoencontrada.PedidoNaoEncontradaException;
import br.com.schiavon.food.domain.models.Endereco;
import br.com.schiavon.food.domain.models.FormaPagamento;
import br.com.schiavon.food.domain.models.ItemPedido;
import br.com.schiavon.food.domain.models.Pedido;
import br.com.schiavon.food.domain.models.Restaurante;
import br.com.schiavon.food.domain.models.Usuario;
import br.com.schiavon.food.domain.repositories.ItemPedidoRepository;
import br.com.schiavon.food.domain.repositories.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    private final RestauranteService restauranteService;

    private final FormaPagamentoService formaPagamentoService;

    private final RestauranteProdutoService restauranteProdutoService;

    private final ItemPedidoRepository itemPedidoRepository;

    private final ModelMapper modelMapper;

    public PedidoService(PedidoRepository pedidoRepository, RestauranteService restauranteService,
                         FormaPagamentoService formaPagamentoService, ModelMapper modelMapper,
                         RestauranteProdutoService restauranteProdutoService, ItemPedidoRepository itemPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.restauranteService = restauranteService;
        this.formaPagamentoService = formaPagamentoService;
        this.restauranteProdutoService = restauranteProdutoService;
        this.itemPedidoRepository = itemPedidoRepository;
        this.modelMapper = modelMapper;
    }

    public Pedido cadastro(Pedido pedido) {
        System.out.println(pedido);

        return pedido;
    }

    public Pedido buscarPedidoId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new PedidoNaoEncontradaException(id));
    }
}
