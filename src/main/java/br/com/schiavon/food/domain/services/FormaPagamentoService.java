package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.FormaPagamentoNaoEncontradaException;
import br.com.schiavon.food.domain.models.FormaPagamento;
import br.com.schiavon.food.domain.repositories.FormaPagamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FormaPagamentoService {
    private final FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamentoService(FormaPagamentoRepository formaPagamentoRepository){
        this.formaPagamentoRepository = formaPagamentoRepository;
    }

    @Transactional
    public FormaPagamento cadastrar(FormaPagamento formaPagamento){
        return formaPagamentoRepository.save(formaPagamento);
    }

    public FormaPagamento buscarFormaPagamentoID(Long id){
        return formaPagamentoRepository.findById(id).orElseThrow(() -> new FormaPagamentoNaoEncontradaException(id));
    }
}
