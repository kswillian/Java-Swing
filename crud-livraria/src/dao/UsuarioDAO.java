package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.UsuarioVO;
import persistence.ConnectionDB;

/**
 *
 * @author windows
 */
public class UsuarioDAO {
    
    public void cadastrarUsuario(UsuarioVO uVO) throws SQLException {

        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        /*Criando obj. capaz de executar instruções
         SQL no banco de dados*/
        Statement stat = con.createStatement();

        try {
            //String que receberá instrução SQL
            String sql;

            /* Montando a instrução INSERT para inserir
             um objeto pVO no Banco MySQL */
            sql = "insert into usuario(idusuario, login, senha)"
                    + "values(null, '" + uVO.getLogin() + "', '" + uVO.getSenha() + "')";

            stat.execute(sql);

        } catch (SQLException se) {
            throw new SQLException("Erro ao inserir usuário! " + se.getMessage());
        } finally {
            con.close();
            stat.close();
        }
    }
    
    public ArrayList<UsuarioVO> buscarUsuarios() throws SQLException {

        Connection con = ConnectionDB.getConexao();
        Statement stat = con.createStatement();

        try {
            String sql;

            //Montando o sql            
            sql = "select * from usuario";

            /* Executando o SQL  e armazenando
             o ResultSet em um objeto do tipo
             ResultSet chamado rs */
            ResultSet rs = stat.executeQuery(sql);

            /* Criando ArrayList para armazenar 
             objetos do tipo produto */
            ArrayList<UsuarioVO> users = new ArrayList<>();

            /* Enquanto houver uma próxima linha no 
             banco de dados o while roda */
            while (rs.next()) {
                //Criando um novo obj. ProdutoVO
                UsuarioVO u = new UsuarioVO();

                /* Mapeando a tabela do banco para objeto
                 chamado pVO */
                u.setIdusuario(rs.getLong("idusuario"));
                u.setLogin(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));

                /* Inserindo o objeto pVO no ArrayList */
                users.add(u);
            }

            //Retornando o ArrayList com todos objetos
            return users;

        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar usuários! " + se.getMessage());
        } finally {
            con.close();
            stat.close();
        }
    }

    public boolean validarSenha(UsuarioVO u) throws SQLException {                  

        Connection conexao = ConnectionDB.getConexao();
        Statement stat = conexao.createStatement();

        String sql;

        sql = "select count(*)"
                + " from usuario"
                + " where login ='" + u.getLogin() + "' and"
                + " senha = '" + u.getSenha() + "' ";

        ResultSet rs = stat.executeQuery(sql);
        int retorno = -2;

        while (rs.next()) {
            retorno = rs.getInt(1);
        }

        if (retorno == 1) {
            stat.close();
            return true;
        } else {
            return false;
        }
    }
}
