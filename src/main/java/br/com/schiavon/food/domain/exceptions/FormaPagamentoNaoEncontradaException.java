package br.com.schiavon.food.domain.exceptions;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException{
    private final static long serialVersionUID = 1L;

    public static final String FORMA_PAGAMENTO_NAO_ENCONTRADA = "Forma pagamento de id %d não foi encontrada.";
    public FormaPagamentoNaoEncontradaException(Long id) {
        super(String.format(FORMA_PAGAMENTO_NAO_ENCONTRADA, id));
    }
}
