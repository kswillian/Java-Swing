
package service;

import dao.ClienteDAO;
import dao.DAOFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ClienteVO;

/**
 *
 * @author Willian Kaminski dos Santos 
 * @since 07/07/2018 08:45
 * @version 1.0 Coffee
*/

public class ClienteService {
    
    public void cadastrarCliente(ClienteVO cVO) throws SQLException{
        ClienteDAO cDAO = DAOFactory.getClienteDAO();
        cDAO.cadastrarCliente(cVO);
    }
    
    public ArrayList<ClienteVO> buscarCliente() throws SQLException{
        ClienteDAO cDAO = DAOFactory.getClienteDAO();
        return cDAO.buscarClientes();
    }
    
    public void alterarCliente(ClienteVO cVO) throws SQLException{
        ClienteDAO cDAO = DAOFactory.getClienteDAO();
        cDAO.alterarCliente(cVO);
    }
    
    public void deletarCliente(long idCliente) throws SQLException{
        ClienteDAO cDAO = DAOFactory.getClienteDAO();
        cDAO.deletarCliente(idCliente);
    }
    
    public ArrayList<ClienteVO> filtra(String query) throws SQLException{
        ClienteDAO cDAO = DAOFactory.getClienteDAO();
        return cDAO.filtrarClientes(query);
    }
}
