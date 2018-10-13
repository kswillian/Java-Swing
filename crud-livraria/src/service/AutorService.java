
package service;

import dao.AutorDAO;
import dao.DAOFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import model.AutorVO;

/**
 *
 * @author Willian Kaminski
 * @since 08/07/2017 18:40
 * @version 1.0
 */
public class AutorService {
    
    public void cadastrarAutor(AutorVO aVO) throws SQLException{
        AutorDAO aDAO = DAOFactory.getAutorDAO();
        aDAO.cadastrarAutor(aVO);
    }
    
    public ArrayList<AutorVO> buscarAutores() throws SQLException {
        AutorDAO aDAO = DAOFactory.getAutorDAO();
        return aDAO.buscarAutores();
    }
    
    public void alterarAutor(AutorVO aVO) throws SQLException{
        AutorDAO aDAO = DAOFactory.getAutorDAO();
        aDAO.alterarAutor(aVO);
    }
    
    public void deletarAutor(long idAutor) throws SQLException{
        AutorDAO aDAO = DAOFactory.getAutorDAO();
        aDAO.deletarAutor(idAutor);
    }
    
    public ArrayList<AutorVO> filtra(String query) throws SQLException{
        AutorDAO fDAO = DAOFactory.getAutorDAO();
        return fDAO.filtrarAutores(query);
    }
}
