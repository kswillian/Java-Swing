/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.DAOFactory;
import dao.UsuarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.UsuarioVO;

/**
 *
 * @author Willian Kaminski dos Santos 
 * @since 07/07/2018 09:45
 * @version 1.0 Coffee
*/
public class UsuarioService {
    
    public void cadastrarUsuario(UsuarioVO uVO) throws SQLException{
        UsuarioDAO uDAO = DAOFactory.getUsuarioDAO();
        uDAO.cadastrarUsuario(uVO);
    }
    
    public ArrayList<UsuarioVO> buscarUsuarios() throws SQLException {
        UsuarioDAO uDAO = DAOFactory.getUsuarioDAO();
        return uDAO.buscarUsuarios();
    }
    
    public boolean validarSenha(UsuarioVO u) throws SQLException {
        UsuarioDAO uDAO = DAOFactory.getUsuarioDAO();
        return uDAO.validarSenha(u);
    }
}
