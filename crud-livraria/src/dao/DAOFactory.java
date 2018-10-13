
package dao;

/**
 *
 * @author Willian Kaminski dos Santos 
 * @since 07/07/2018 08:45
 * @version 1.0 Coffee
 */
public class DAOFactory {
    
    private static final ClienteDAO clienteDAO = new ClienteDAO();
    private static final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private static final LivroDAO livroDAO = new LivroDAO();
    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static final AutorDAO autorDAO = new AutorDAO();
    
    public static ClienteDAO getClienteDAO(){
        return clienteDAO;
    }
    
    public static FuncionarioDAO getFuncionarioDAO(){
        return funcionarioDAO;
    }
    
    public static LivroDAO getLivroDAO(){
        return livroDAO;
    }
    
    public static UsuarioDAO getUsuarioDAO(){
        return usuarioDAO;
    }
    
    public static AutorDAO getAutorDAO(){
        return autorDAO;
    }
}
