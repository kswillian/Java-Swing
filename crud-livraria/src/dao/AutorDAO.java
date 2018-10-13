
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.AutorVO;
import persistence.ConnectionDB;

/**
 *
 * @author Willian Kaminski
 * @since 08/07/2018 18:34
 * @version 1.0
 */
public class AutorDAO {
    
    public void cadastrarAutor(AutorVO aVO) throws SQLException{
        
        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();
        
        try{
            
            String slq = "INSERT INTO autor(nome) VALUES ('"+aVO.getNome()+"')";
            stat.execute(slq);
            
        }catch(SQLException e){
            throw new SQLException("Erro ao cadastrar autor!!" + e.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
    
    public void alterarAutor(AutorVO aVO) throws SQLException{
        
        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();
        
        try {
            
            String slq = "UPDATE autor SET "
                    + "nome= '" + aVO.getNome() + "' WHERE idautor= '" +aVO.getIdautor()+ "'";
            stat.execute(slq);
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao alterar autor!!" + e.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
    
    public void deletarAutor(long idAutor) throws SQLException{
        
        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();
        
        try {
            
            String slq = "DELETE FROM autor WHERE idautor= " + idAutor;
            stat.execute(slq);
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar autor!!" + e.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
    
    
    public ArrayList<AutorVO> buscarAutores() throws SQLException {

        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();

        try {
            String sql;
            sql = "select * from autor ";
            ResultSet rs = stat.executeQuery(sql);
            ArrayList<AutorVO> autores = new ArrayList<>();

            /* Enquanto houver uma próxima linha no 
             banco de dados o while roda */
            while (rs.next()) {
                //Criando um novo obj. ProdutoVO
                AutorVO aVO = new AutorVO();

                /* Mapeando a tabela do banco para objeto
                 chamado pVO */
                aVO.setIdautor(rs.getLong("idautor"));
                aVO.setNome(rs.getString("nome"));
                autores.add(aVO);
            }//Fecha while

            //Retornando o ArrayList com todos objetos
            return autores;

        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar dados do Banco! " + e.getMessage());
        } finally {
            stat.close();
            con.close();
        }//fecha finally
    }//
    
    public ArrayList<AutorVO> filtrarAutores(String query) throws SQLException{
        
        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();
        
        try {
            String sql = "SELECT * FROM autor " + query;
            
            /* Executando o SQL  e armazenando
             o ResultSet em um objeto do tipo
             ResultSet chamado rs */
            ResultSet rs = stat.executeQuery(sql);
            
            /* Criando ArrayList para armazenar 
             objetos do tipo cliente */
            ArrayList<AutorVO> ali = new ArrayList<>();
            
            /* Enquanto houver uma próxima linha no 
             banco de dados o while roda */
            while(rs.next()){
                //Criando um novo objeto FuncionarioVO
                AutorVO l = new AutorVO();
                
                /*Mapeando a tabela do banco para objeto chamado fVO*/
                l.setIdautor(rs.getLong("idautor"));
                l.setNome(rs.getString("nome"));
                /*Inserindo o objeto cVO no ArrayList*/
                ali.add(l);
            }
            return ali;
            
        } catch (SQLException e) {
           throw new SQLException("Erro ao buscar autor !" + e.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
    
}
