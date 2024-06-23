import java.util.*;
import java.sql.Connection;

import model.Usuario;
import model.dao.ConexaoFactory;
import model.dao.IUsuarioDAO;
import model.dao.UsuarioDAO;
import model.reposirory.*;
import services.UsuarioService;

public class MvcApp {
    public static void main(String[] args) throws Exception {

        /*
         * Este conjunto de instruções inicializaram as dependencias 
         * para o uso do serviço de contatos utilizando o repositório
         * com o SGBD MySQL.
         */
        Connection conexao = ConexaoFactory.getConexao();
        IUsuarioDAO dao = new UsuarioDAO(conexao);
        UsuarioRepository reposirory = new UsuarioRepositoryMySQLImpl(dao);
        UsuarioService service = new UsuarioService(reposirory);
        
        /*
         * Utilize as leituras em console se preferir.
         * Scanner in = new Scanner(System.in);
         * System.out.println("Digite o nome: ");
         * String nome = in.nextLine();
         * System.out.println("Digite o e-mail: ");
         * String email = in.nextLine();
         * System.out.println("Digite o senha: ");
         * String senha = in.nextLine();
         */
        
            //cria usuarios
            //TODO: Criar mais 2 usuarios
        Usuario u1 = new Usuario(null, "Esdras", "esdras_TP@email.com", "cubomagico");
        Usuario u2 = new Usuario(null, "Orlando", "orlando_DW@email.com", "cafe");
        Usuario u3 = new Usuario(null, "Tiago", "tiago_BDNR@email.com", "mongoDB");

         // Chamada do metodo de persistencia
         //TODO: salvar mais 2 usuarios
        service.criar(u1);
        service.criar(u2);
        service.criar(u3);


        //TODO: Exibir os usuarios cadastrados
        System.out.println("Usuários inseridos:");
        List<Usuario> usuarios = service.obterTodos();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }

        // TODO: Remover o primeiro usuario criado
        service.excluir(u1.getId());


        // TODO: Buscar e exibir o segundo usuario criado com base no e-mail
        Usuario usuarioBuscado = service.buscarPorEmail("orlando_DW@email.com");
        System.out.println("Usuário buscado: " + usuarioBuscado.getNome() + " - " + usuarioBuscado.getEmail());

        // TODO: Exibir os usuarios cadastrado
        System.out.println("Todos usuários: ");
        usuarios = service.obterTodos();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }


        // TODO: Altere o repositório MySQL pelo repositório em memória  
        
        //Esta dando um erro
        UsuarioRepository memoriaRepository = new UsuarioMemoriaRepositoryImpl();
        UsuarioService memoriaService = new UsuarioService(memoriaRepository);
        
        List<Usuario> memoriaUsu = service.obterTodos();
        for (Usuario usuario : usuarios) {
             service.criar(usuario);
        }
        System.out.println("Usuários em memória:"); //se eu uso o usuario igual no Exibir user cadastrado ele nao funciona
        usuarios = service.obterTodos();
        for (Usuario usuario : memoriaUsu) {
            System.out.println(usuario);
        }

    }

}

