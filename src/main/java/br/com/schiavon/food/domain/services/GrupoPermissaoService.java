package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.naoencontrada.PermissaoNaoEncontradaException;
import br.com.schiavon.food.domain.models.Grupo;
import br.com.schiavon.food.domain.models.Permissao;
import br.com.schiavon.food.domain.repositories.PermissaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GrupoPermissaoService {
    private final GrupoService grupoService;

    private final PermissaoRepository permissaoRepository;

    public GrupoPermissaoService(GrupoService grupoService, PermissaoRepository permissaoRepository) {
        this.grupoService = grupoService;
        this.permissaoRepository = permissaoRepository;
    }

    @Transactional
    public void associarPermissao(Long idGrupo, Long idPermissao){
        Grupo grupo = grupoService.buscarGrupoId(idGrupo);
        Permissao permissao = buscarPermissaoID(idPermissao);

        grupo.getPermissoes().add(permissao);
    }

    @Transactional
    public void desassociarPermissao(Long idGrupo, Long idPermissao){
        Grupo grupo = grupoService.buscarGrupoId(idGrupo);
        Permissao permissao = buscarPermissaoID(idPermissao);

        grupo.getPermissoes().remove(permissao);
    }

    private Permissao buscarPermissaoID(Long id){
        return permissaoRepository.findById(id).orElseThrow(() ->
                new PermissaoNaoEncontradaException(id));
    }
}
