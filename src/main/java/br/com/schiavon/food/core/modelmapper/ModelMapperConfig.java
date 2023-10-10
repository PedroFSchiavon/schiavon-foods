package br.com.schiavon.food.core.modelmapper;

import br.com.schiavon.food.api.model.dto.input.ItemPedidoInputDTO;
import br.com.schiavon.food.api.model.dto.output.EnderecoDTO;
import br.com.schiavon.food.domain.models.Endereco;
import br.com.schiavon.food.domain.models.ItemPedido;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapperBean() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<Endereco, EnderecoDTO> typeMapEndereco = modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class);

        typeMapEndereco.addMapping((src) -> src.getCidade().getNome(), EnderecoDTO::setCidade);

        typeMapEndereco.addMapping((src) -> src.getCidade().getEstado().getNome(), EnderecoDTO::setEstado);

        modelMapper.createTypeMap(ItemPedidoInputDTO.class, ItemPedido.class).addMappings(mapper -> mapper.skip(ItemPedido::setId));

        return modelMapper;
    }
}
