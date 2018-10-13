/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.LivroVO;
import persistence.ConnectionDB;

/**
 *
 * @author Willian Kaminski
 * @since 08/07/2018 19:57
 * @version 1.0
 */
public class LivroDAO {
    
    public void cadastrarLivro(LivroVO lVO) throws SQLException{
        
        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();
        
        try {
            String sql = "INSERT INTO livro(titulo, subtitulo, edicao, editora, isbn, id_autor, data_lancamento, preco_venda)"
                    + "VALUES('"+lVO.getTitulo()+"','"+lVO.getSubtitulo()+"',"+lVO.getEdicao()+",'"+lVO.getEditora()+"',"
                    + ""+lVO.getISBN()+", "+lVO.getS().getIdautor()+", '"+lVO.getData_lancamento()+"', "+lVO.getValor_venda()+")";
            
            stat.execute(sql);
          
        } catch (SQLException e) {
            
            throw new SQLException("Erro ao cadastrar livro!!" + e.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
    
    public void alterarLivro(LivroVO lVO) throws SQLException{
        
        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();
        
        try {
            String sql = "UPDATE livro SET "
                    + "titulo= '"+lVO.getTitulo()+"', subtitulo= '"+lVO.getSubtitulo()+"', edicao= "+lVO.getEdicao()+", "
                    + "editora= '"+lVO.getEditora()+"', isbn= '"+lVO.getISBN()+"', id_autor='"+lVO.getS().getIdautor()+"', data_lancamento='"+lVO.getData_lancamento()+"',"
                    + "preco_venda= "+lVO.getValor_venda()+"  WHERE idlivro= '" +lVO.getIdlivro()+ "'";
            stat.execute(sql);
            
        } catch (Exception e) {
           throw new SQLException("Erro ao alterar livro!!" + e.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
    
    public void deletarLivro(long idlivro) throws SQLException{
        
        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();
        
        try {
            
            String slq = "DELETE FROM livro WHERE idlivro= " + idlivro;
            stat.execute(slq);
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar livro!!" + e.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
    
    public ArrayList<LivroVO> buscarLivros() throws SQLException{
        
        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();
        
        try {
            String sql = "SELECT * FROM livro "
                        + "INNER JOIN autor "
                        + "ON livro.id_autor = autor.idautor";
            
            /* Executando o SQL  e armazenando
             o ResultSet em um objeto do tipo
             ResultSet chamado rs */
            ResultSet rs = stat.executeQuery(sql);
            
            /* Criando ArrayList para armazenar 
             objetos do tipo funcionario */
            ArrayList<LivroVO> lli = new ArrayList<>();
            
            /* Enquanto houver uma próxima linha no 
             banco de dados o while roda */
            while(rs.next()){
                //Criando um novo objeto FuncionarioVO
                LivroVO l = new LivroVO();
                
                /*Mapeando a tabela do banco para objeto chamado fVO*/
                l.setIdlivro(rs.getLong("idlivro"));
                l.setTitulo(rs.getString("titulo"));
                l.setSubtitulo(rs.getString("subtitulo"));
                l.setEdicao(Integer.parseInt(rs.getString("edicao")));
                l.setEditora(rs.getString("editora"));
                l.setISBN(Long.parseLong(rs.getString("isbn")));
                l.setData_lancamento(rs.getString("data_lancamento"));
                l.setAutor(rs.getString("nome"));
                l.setValor_venda(Double.parseDouble(rs.getString("preco_venda")));
                
                /*Inserindo o objeto cVO no ArrayList*/
                lli.add(l);
            }
            
            //Retornando o ArrayList com todos os objetos
            return lli;
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar funcionarios! " + e.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
    
    public ArrayList<LivroVO> filtrarLivros(String query) throws SQLException{
        
        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();
        
        try {
            //String sql = "SELECT * FROM livro " + query;
            String sql = "SELECT * FROM livro "
                        + "INNER JOIN autor "
                        + "ON livro.id_autor = autor.idautor " + query;
            
            /* Executando o SQL  e armazenando
             o ResultSet em um objeto do tipo
             ResultSet chamado rs */
            ResultSet rs = stat.executeQuery(sql);
            
            /* Criando ArrayList para armazenar 
             objetos do tipo cliente */
            ArrayList<LivroVO> lli = new ArrayList<>();
            
            /* Enquanto houver uma próxima linha no 
             banco de dados o while roda */
            while(rs.next()){
                //Criando um novo objeto FuncionarioVO
                LivroVO l = new LivroVO();
                
                /*Mapeando a tabela do banco para objeto chamado fVO*/
                l.setIdlivro(rs.getLong("idlivro"));
                l.setTitulo(rs.getString("titulo"));
                l.setEdicao(Integer.parseInt(rs.getString("edicao")));
                l.setEditora(rs.getString("editora"));
                l.setISBN(Long.parseLong(rs.getString("isbn")));
                l.setData_lancamento(rs.getString("data_lancamento"));
                l.setAutor(rs.getString("nome"));
                l.setValor_venda(Double.parseDouble(rs.getString("preco_venda")));
                /*Inserindo o objeto cVO no ArrayList*/
                lli.add(l);
            }
            return lli;
            
        } catch (SQLException e) {
           throw new SQLException("Erro ao buscar livro !" + e.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
    
}
