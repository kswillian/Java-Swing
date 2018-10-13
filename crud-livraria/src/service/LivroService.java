
package service;

import dao.DAOFactory;
import dao.LivroDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.LivroVO;

/**
 *
 * @author Willian Kaminski dos Santos 
 * @since 07/07/2018 09:30
 * @version 1.0 Coffee
 */
public class LivroService {
    
    public void cadastrarLivro(LivroVO lVO) throws SQLException{
        LivroDAO lDAO = DAOFactory.getLivroDAO();
        lDAO.cadastrarLivro(lVO);
    }
    
    public void alterarLivro(LivroVO lVO) throws SQLException{
        LivroDAO lDAO = DAOFactory.getLivroDAO();
        lDAO.alterarLivro(lVO);
    }
    
    public void deletarLivro(long idLivro) throws SQLException{
        LivroDAO lDAO = DAOFactory.getLivroDAO();
        lDAO.deletarLivro(idLivro);
    }
    
    public ArrayList<LivroVO> buscarLivro() throws SQLException{
        LivroDAO lDAO = DAOFactory.getLivroDAO();
        return lDAO.buscarLivros();
    }
    
    public ArrayList<LivroVO> filtra(String query) throws SQLException{
        LivroDAO lDAO = DAOFactory.getLivroDAO();
        return lDAO.filtrarLivros(query);
    }
}
