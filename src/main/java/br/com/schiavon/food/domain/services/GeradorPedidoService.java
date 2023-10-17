package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.naoencontrada.PedidoNaoEncontradaException;
import br.com.schiavon.food.domain.models.Cidade;
import br.com.schiavon.food.domain.models.FormaPagamento;
import br.com.schiavon.food.domain.models.ItemPedido;
import br.com.schiavon.food.domain.models.Pedido;
import br.com.schiavon.food.domain.models.Produto;
import br.com.schiavon.food.domain.models.Restaurante;
import br.com.schiavon.food.domain.models.Usuario;
import br.com.schiavon.food.domain.models.enuns.StatusPedido;
import br.com.schiavon.food.domain.repositories.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GeradorPedidoService {
    private final PedidoRepository pedidoRepository;

    private final RestauranteService restauranteService;

    private final FormaPagamentoService formaPagamentoService;

    private final RestauranteProdutoService restauranteProdutoService;

    private final UsuarioService usuarioService;

    private final CidadeService cidadeService;

    public GeradorPedidoService(PedidoRepository pedidoRepository, RestauranteService restauranteService,
                                FormaPagamentoService formaPagamentoService,
                                RestauranteProdutoService restauranteProdutoService,
                                UsuarioService usuarioService, CidadeService cidadeService) {
        this.pedidoRepository = pedidoRepository;
        this.restauranteService = restauranteService;
        this.formaPagamentoService = formaPagamentoService;
        this.restauranteProdutoService = restauranteProdutoService;
        this.usuarioService = usuarioService;
        this.cidadeService = cidadeService;
    }

    @Transactional
    public Pedido cadastro(Pedido pedido, Long cidadeId) {
        //Cadastrando cliente
        Usuario cliente = usuarioService.buscarUsuarioId(1L);
        pedido.setCliente(cliente);

        //Cadastrando cidade e estado
        Cidade cidade = cidadeService.buscarCidadeId(cidadeId);
        pedido.getEnderecoEntrega().setCidade(cidade);

        //Cadastro restaurante
        Restaurante restaurante = restauranteService.buscarRestauranteId(pedido.getRestaurante().getId());
        pedido.setRestaurante(restaurante);

        //Cadastro taxaFrete
        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());

        //Cadastro forma pagamento
        FormaPagamento formaPagamento = formaPagamentoService.buscarFormaPagamentoID(pedido.getFormaPagamento().getId());
        pedido.verificaECadastraFormaDePagamento(formaPagamento);

        //Cadastro itens
        cadastroEValidacaoDeItens(pedido);

        //Calcula preço
        calculaPrecosItem(pedido);
        pedido.calculaPreco();

        //Atualiza status do pedido
        pedido.setStatusPedido(StatusPedido.CRIADO);

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void persisteItemPedido(Pedido pedido){
        pedido.getItensPedidos().forEach(itemPedido -> itemPedido.setPedido(pedido));
    }

    public Pedido buscarPedidoId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new PedidoNaoEncontradaException(id));
    }

    private void cadastroEValidacaoDeItens(Pedido pedido) {
        Restaurante restaurante = pedido.getRestaurante();
        List<ItemPedido> itensPedidos = pedido.getItensPedidos();

        itensPedidos.forEach(itemPedido -> {
            Produto produto = restauranteProdutoService.buscarProdutoID(restaurante.getId(), itemPedido.getProduto().getId());
            itemPedido.setProduto(produto);
        });
    }

    private void calculaPrecosItem(Pedido pedido){
        pedido.getItensPedidos().forEach(ItemPedido::calculaPreco);
    }
}
