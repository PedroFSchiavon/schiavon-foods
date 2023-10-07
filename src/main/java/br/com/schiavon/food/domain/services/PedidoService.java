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

    private final ModelMapper modelMapper;

    public PedidoService(PedidoRepository pedidoRepository, RestauranteService restauranteService,
                         FormaPagamentoService formaPagamentoService, ModelMapper modelMapper,
                         RestauranteProdutoService restauranteProdutoService) {
        this.pedidoRepository = pedidoRepository;
        this.restauranteService = restauranteService;
        this.formaPagamentoService =formaPagamentoService;
        this.restauranteProdutoService = restauranteProdutoService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Pedido cadastro(PedidoInputDTO pedido){
        Pedido novoPedido = new Pedido();
        //Por hora o pedido sempre terá o usuário de id 1
        novoPedido.setCliente(new Usuario());
        novoPedido.getCliente().setId(1);

        Restaurante restaurante = restauranteService.buscarRestauranteId(pedido.getRestaurante().getId());
        novoPedido.setRestaurante(restaurante);
        novoPedido.setTaxaFrete(restaurante.getTaxaFrete());

        FormaPagamento formaPagamento = formaPagamentoService.buscarFormaPagamentoID(pedido.getFormaPagamento().getId());
        novoPedido.setFormaPagamento(formaPagamento);

        novoPedido.setEnderecoEntrega(toDomainModelEndereco(pedido.getEnderecoEntrega()));

        List<ItemPedido> itensPedidosGerados = pedido.getItens()
                .stream()
                .map(item -> criaItemPedido(item, restaurante.getId()))
                .collect(Collectors.toList());
        novoPedido.setItensPedidos(itensPedidosGerados);

        novoPedido.calculaPreco();

        return pedidoRepository.save(novoPedido);
    }

    public Pedido buscarPedidoId(Long id){
        return pedidoRepository.findById(id).orElseThrow(() -> new PedidoNaoEncontradaException(id));
    }

    private Endereco toDomainModelEndereco(EnderecoInputDTO enderecoInputDTO){
        return modelMapper.map(enderecoInputDTO, Endereco.class);
    }

    private ItemPedido criaItemPedido(ItemPedidoInputDTO itemPedidoInputDTO, Long idRestaurante){
        ItemPedido itemPedido = modelMapper.map(itemPedidoInputDTO, ItemPedido.class);
        itemPedido.setProduto(restauranteProdutoService.buscarProdutoID(idRestaurante, itemPedidoInputDTO.getProdutoId()));

        itemPedido.calculaPreco();

        return itemPedido;
    }
}
