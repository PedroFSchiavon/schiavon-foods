package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.EntidadeEmUsoException;
import br.com.schiavon.food.domain.exceptions.FormaPagamentoNaoEncontradaException;
import br.com.schiavon.food.domain.models.FormaPagamento;
import br.com.schiavon.food.domain.repositories.FormaPagamentoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FormaPagamentoService {
    private final FormaPagamentoRepository formaPagamentoRepository;

    private final String FORMA_PAGAMENTO_ID_EM_USO = "Não foi possível deletar a forma de pagamento com o id %d, " +
            "pois esta em uso no momento.";

    public FormaPagamentoService(FormaPagamentoRepository formaPagamentoRepository){
        this.formaPagamentoRepository = formaPagamentoRepository;
    }

    @Transactional
    public FormaPagamento cadastrar(FormaPagamento formaPagamento){
        return formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional
    public FormaPagamento atualizar(Long id, FormaPagamento formaPagamento){
        if(formaPagamentoRepository.existsById(id)){
            formaPagamento.setId(id);
            return formaPagamentoRepository.save(formaPagamento);
        }
        throw new FormaPagamentoNaoEncontradaException(id);
    }

    @Transactional
    public void deletar(Long id){
        FormaPagamento formaPagamento = buscarFormaPagamentoID(id);
        try{
            formaPagamentoRepository.delete(formaPagamento);
            formaPagamentoRepository.flush();
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format(FORMA_PAGAMENTO_ID_EM_USO, id));
        }
    }

    public FormaPagamento buscarFormaPagamentoID(Long id){
        return formaPagamentoRepository.findById(id).orElseThrow(() -> new FormaPagamentoNaoEncontradaException(id));
    }
}
