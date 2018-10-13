/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.ClienteVO;
import persistence.ConnectionDB;

/**
 *
 * @author Willian Kaminski 
 * @since 08/07/2018 13:44
 * @version 1.0
 */
public class ClienteDAO {
    
    public void cadastrarCliente(ClienteVO cVO) throws SQLException{
        
        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();
        PreparedStatement stmt1, stmt2;
        
        try{
            
            String sql = "INSERT INTO cliente(nome, sobrenome, sexo, idade, cidade ,"
                        + " bairro, rua, numero, email, telefone1, telefone2, data_cadastro)"
                        + "VALUES('"+cVO.getNome()+"', '"+cVO.getSobrenome()+"', '"+cVO.getSexo()+"', "+cVO.getIdade()+", '"+cVO.getCidade()+"', "
                        + "'"+cVO.getBairro()+"', '"+cVO.getRua()+"', "+cVO.getNumero()+", '"+cVO.getEmail()+"', "
                        + ""+cVO.getTelefone1()+", "+cVO.getTelefone2()+", CURDATE())";
            stat.execute(sql);
            
        }catch(SQLException e){   
            throw new SQLException("Erro ao cadastrar !!" + e.getMessage());
        }finally{
            con.close();
            stat.close();
        }   
    }
    
    public void alterarCliente(ClienteVO cVO) throws SQLException{
        
        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();
        
        try {
            String sql = "UPDATE cliente SET "
                    + "nome= '"+cVO.getNome()+"', sobrenome= '"+cVO.getSobrenome()+"', idade= "+cVO.getIdade()+", "
                    + "sexo= '"+cVO.getSexo()+"', cidade= '"+cVO.getCidade()+"', bairro='"+cVO.getBairro()+"', rua='"+cVO.getRua()+"',"
                    + "numero= '"+cVO.getNumero()+"', email= '"+cVO.getEmail()+"', telefone1= "+cVO.getTelefone1()+", telefone2= "+cVO.getTelefone2()+" WHERE idcliente= '" +cVO.getIdcliente()+ "'";
            stat.execute(sql);
        } catch (Exception e) {
           throw new SQLException("Erro ao alterar cliente!!" + e.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
    
    public void deletarCliente(long idcliente) throws SQLException{
        
        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();
        
        try {
            
            String slq = "DELETE FROM cliente WHERE idcliente= " + idcliente;
            stat.execute(slq);
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar cliente!!" + e.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
    
    public ArrayList<ClienteVO> buscarClientes() throws SQLException{
        
        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();
        
        try {
            String sql = "SELECT * FROM cliente";
            
            /* Executando o SQL  e armazenando
             o ResultSet em um objeto do tipo
             ResultSet chamado rs */
            ResultSet rs = stat.executeQuery(sql);
            
            /* Criando ArrayList para armazenar 
             objetos do tipo cliente */
            ArrayList<ClienteVO> cli = new ArrayList<>();
            
            /* Enquanto houver uma próxima linha no 
             banco de dados o while roda */
            while(rs.next()){
                //Criando um novo objeto ClienteVO
                ClienteVO c = new ClienteVO();
                
                /*Mapeando a tabela do banco para objeto chamado cVO*/
                c.setIdcliente(rs.getLong("idcliente"));
                c.setNome(rs.getString("nome"));
                c.setSobrenome(rs.getString("sobrenome"));
                c.setSexo(rs.getString("sexo"));
                c.setIdade(Integer.parseInt(rs.getString("idade")));
                c.setCidade(rs.getString("cidade"));
                c.setBairro(rs.getString("bairro"));
                c.setRua(rs.getString("rua"));
                c.setNumero(Integer.parseInt(rs.getString("numero")));
                c.setEmail(rs.getString("email"));
                c.setTelefone1(Integer.parseInt(rs.getString("telefone1")));
                c.setTelefone2(Integer.parseInt(rs.getString("telefone2")));
                /*Inserindo o objeto cVO no ArrayList*/
                cli.add(c);
            }
            
            //Retornando o ArrayList com todos os objetos
            return cli;
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar clientes! " + e.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
    
    public ArrayList<ClienteVO> filtrarClientes(String query) throws SQLException{
        
        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();
        
        try {
            String sql = "SELECT * FROM cliente " + query;
            
            /* Executando o SQL  e armazenando
             o ResultSet em um objeto do tipo
             ResultSet chamado rs */
            ResultSet rs = stat.executeQuery(sql);
            
            /* Criando ArrayList para armazenar 
             objetos do tipo cliente */
            ArrayList<ClienteVO> cli = new ArrayList<>();
            
            /* Enquanto houver uma próxima linha no 
             banco de dados o while roda */
            while(rs.next()){
                
                //Criando um novo objeto ClienteVO
                ClienteVO c = new ClienteVO();
                /*Mapeando a tabela do banco para objeto chamado cVO*/
                c.setIdcliente(rs.getLong("idcliente"));
                c.setNome(rs.getString("nome"));
                c.setSobrenome(rs.getString("sobrenome"));
                c.setSexo(rs.getString("sexo"));
                c.setIdade(Integer.parseInt(rs.getString("idade")));
                c.setCidade(rs.getString("cidade"));
                c.setBairro(rs.getString("bairro"));
                c.setRua(rs.getString("rua"));
                c.setNumero(Integer.parseInt(rs.getString("numero")));
                c.setEmail(rs.getString("email"));
                c.setTelefone1(Integer.parseInt(rs.getString("telefone1")));
                c.setTelefone2(Integer.parseInt(rs.getString("telefone2")));
                /*Inserindo o objeto cVO no ArrayList*/
                cli.add(c);
            }
            return cli;
            
        } catch (SQLException e) {
           throw new SQLException("Erro ao buscar cliente !" + e.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
}
