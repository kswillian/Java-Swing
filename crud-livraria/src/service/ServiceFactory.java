
package service;

/**
 *
 * @author Willian Kaminski dos Santos 
 * @since 07/07/2018 09:50
 * @version 1.0 Coffee
*/
public class ServiceFactory {
    
    private static final ClienteService clienteservice = new ClienteService();
    private static final FuncionarioService funcionarioservice = new FuncionarioService();
    private static final LivroService livroservice = new LivroService();
    private static final UsuarioService usuarioservice = new UsuarioService();
    private static final AutorService autoservice = new AutorService();
    
    public static ClienteService getClienteService(){
        return clienteservice;
    }
    
    public static FuncionarioService getFuncionarioService(){
        return funcionarioservice;
    }
    
    public static LivroService getLivroService(){
        return livroservice;
    }
    
    public static UsuarioService getUsuarioService(){
        return usuarioservice;
    }
    
    public static AutorService getAutorService(){
        return autoservice;
    }
    
}
