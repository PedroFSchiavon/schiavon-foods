package br.com.schiavon.food.domain.models;

import br.com.schiavon.food.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estado implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(groups = Groups.IdEstado.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;
}
