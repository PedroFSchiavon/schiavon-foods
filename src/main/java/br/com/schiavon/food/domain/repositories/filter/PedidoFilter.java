package br.com.schiavon.food.domain.repositories.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
public class PedidoFilter {
    private Long clienteId;
    private Long restauranteId;
    private OffsetDateTime dataCriacaoInicio;
    private OffsetDateTime dataCriacaoFinal;

    public boolean isNull(){
        if (clienteId == null && restauranteId == null && dataCriacaoInicio == null && dataCriacaoFinal == null){
            return true;
        }else {
            return false;
        }
    }
}
