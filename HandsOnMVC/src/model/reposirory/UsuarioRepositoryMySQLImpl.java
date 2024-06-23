package model.reposirory;

import java.util.List;

import model.Usuario;
import model.dao.IUsuarioDAO;

public class UsuarioRepositoryMySQLImpl implements UsuarioRepository{

    private final IUsuarioDAO dao;

    public UsuarioRepositoryMySQLImpl(IUsuarioDAO dao){
        this.dao = dao;
    }

    @Override
    public void criar(Usuario usuario) throws Exception{
        dao.salvar(usuario);
    }

    @Override
         // TODO: Retornar um usuário existente com base no e-mail recebido como parametro
    public Usuario buscarPorEmail(String email){
        return dao.buscarPorEmail(email);
    }

    @Override
    public List<Usuario> obterTodos(){
        return dao.buscarTodos();
    }

    @Override
    public Usuario atualizar(Usuario usuario){   // TODO: Buscar usuario existente e atualizar os dados, persistindo a alteração
        Usuario usuarioExistente = dao.buscarPorEmail(usuario.getEmail());
        if (usuarioExistente != null){ //TODO: Atualizar o usuario
            usuarioExistente.setNome(usuario.getNome());
            usuarioExistente.setSenha(usuario.getSenha());
            dao.atualizar(usuarioExistente);
            return usuarioExistente;
        }
        return null;
    }

    @Override
    public void excluir(Integer id){
        dao.excluir(id);
    }
}
