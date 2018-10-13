
package service;

import dao.DAOFactory;
import dao.FuncionarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.FuncionarioVO;

/**
 *
 * @author Willian Kaminski dos Santos 
 * @since 07/07/2018 9:15
 * @version 1.0 Coffee
 */
public class FuncionarioService {
    
    public void cadastrarCliente(FuncionarioVO fVO) throws SQLException{
        FuncionarioDAO fDAO = DAOFactory.getFuncionarioDAO();
        fDAO.cadastrarFuncionario(fVO);
    }
    
    public void alterarFuncionario(FuncionarioVO fVO) throws SQLException{
        FuncionarioDAO fDAO = DAOFactory.getFuncionarioDAO();
        fDAO.alterarFuncionario(fVO);
    }
    
    public void deletarFuncioanrio(long idFuncionario) throws SQLException{
        FuncionarioDAO cDAO = DAOFactory.getFuncionarioDAO();
        cDAO.deletarFuncionario(idFuncionario);
    }
    
    public ArrayList<FuncionarioVO> buscarFuncioanrio() throws SQLException{
        FuncionarioDAO fDAO = DAOFactory.getFuncionarioDAO();
        return fDAO.buscarFuncionarios();
    }
    
    public ArrayList<FuncionarioVO> filtra(String query) throws SQLException{
        FuncionarioDAO fDAO = DAOFactory.getFuncionarioDAO();
        return fDAO.filtrarFuncionarios(query);
    }
}
