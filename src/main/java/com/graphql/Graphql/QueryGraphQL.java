package com.graphql.Graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class QueryGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private UsuarioRepository usuarioRepo;
    public Usuario usuario(Long id) {
        return usuarioRepo.findById(id).orElse(null);
    }
    @Autowired
    private ReclamacaoRepository reclamacaoRepo;
    public Reclamacao reclamacao(Long id) {
        return reclamacaoRepo.findById(id).orElse(null);
    }

    @Autowired
    private AreaRepository areaRepo;
    public Area area(Long id) {
        return areaRepo.findById(id).orElse(null);
    }

    public List<Usuario> usuarios() {
        return usuarioRepo.findAll();
    }
    public List<Reclamacao> reclamacoes() {
        return reclamacaoRepo.findAll();
    }
    public List<Area> areas() {
        return areaRepo.findAll();
    }

    @Transactional
    public Usuario saveUsuario(Long id, String nome, String email, String senha, String telefoneFixo, Boolean usuarioOuvidor){
        Usuario user = new Usuario();
        user.setId(id);
        user.setNome(nome);
        user.setEmail(email);
        user.setSenha(senha);
        user.setTelefoneFixo(telefoneFixo);
        user.setUsuarioOuvidor(usuarioOuvidor);
        return usuarioRepo.save(user);
    }

    @Transactional
    public Area saveArea(Long id, String nome, String descricao){
        Area area = new Area();
        area.setId(id);
        area.setNome(nome);
        area.setDescricao(descricao);
        return areaRepo.save(area);
    }

    @Transactional
    public Reclamacao saveReclamacao(Long id,String titulo,String descricao,String idUsuarioAbertura,String idAreaResponsavel,String idUsuarioOuvidor,Boolean concluido,String dtAbertura){
        Reclamacao recl = new Reclamacao();
        recl.setId(id);
        recl.setTitulo(titulo);
        recl.setDescricao(descricao);
        recl.setIdUsuarioAbertura(idUsuarioAbertura);
        recl.setIdAreaResponsavel(idAreaResponsavel);
        recl.setIdUsuarioOuvidor(idUsuarioOuvidor);
        recl.setConcluido(concluido);
        recl.setDtAbertura(dtAbertura);
        return reclamacaoRepo.save(recl);
    }

    @Transactional
    public Boolean deleteUsuario(Long id) {
        if(usuarioRepo.findById(id).isPresent()) {
            usuarioRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Boolean deleteArea(Long id) {
        if(areaRepo.findById(id).isPresent()) {
            areaRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Boolean deleteReclamacao(Long id) {
        if(reclamacaoRepo.findById(id).isPresent()) {
            reclamacaoRepo.deleteById(id);
            return true;
        }
        return false;
    }
}