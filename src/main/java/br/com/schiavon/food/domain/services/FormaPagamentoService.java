package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.repositories.FormaPagamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class FormaPagamentoService {
    private final FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamentoService(FormaPagamentoRepository formaPagamentoRepository){
        this.formaPagamentoRepository = formaPagamentoRepository;
    }

}
