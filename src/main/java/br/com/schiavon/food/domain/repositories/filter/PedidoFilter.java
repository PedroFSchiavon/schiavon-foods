package br.com.schiavon.food.domain.repositories.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
public class PedidoFilter {
    private Long clienteId;

    private Long restauranteId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoInicio;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoFinal;

    public boolean isNull(){
        if (clienteId == null && restauranteId == null && dataCriacaoInicio == null && dataCriacaoFinal == null){
            return true;
        }else {
            return false;
        }
    }
}
