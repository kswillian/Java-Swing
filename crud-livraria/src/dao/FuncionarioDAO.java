
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.FuncionarioVO;
import persistence.ConnectionDB;

/**
 *
 * @author Willian Kaminski 
 * @since 08/07/2018 14:30
 * @version 1.0
 */
public class FuncionarioDAO {
    
    public void cadastrarFuncionario(FuncionarioVO fVO) throws SQLException{
        
        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();
        
        try{
            String sql = "INSERT INTO funcionario(nome, sobrenome, sexo, idade, cidade , bairro, rua, numero, email, telefone1, telefone2, data_cadastro)"
                        + "VALUES('"+fVO.getNome()+"', '"+fVO.getSobrenome()+"', '"+fVO.getSexo()+"', "+fVO.getIdade()+", '"+fVO.getCidade()+"', '"+fVO.getBairro()+"', '"+fVO.getRua()+"', "+fVO.getNumero()+", '"+fVO.getEmail()+"', "+fVO.getTelefone1()+", "+fVO.getTelefone2()+", CURDATE())";
            stat.execute(sql);
            
        }catch(SQLException e){
            
            throw new SQLException("Erro ao cadastrar !!" + e.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
    
    public void alterarFuncionario(FuncionarioVO fVO) throws SQLException{
        
        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();
        
        try {
            String sql = "UPDATE funcionario SET "
                    + "nome= '"+fVO.getNome()+"', sobrenome= '"+fVO.getSobrenome()+"', idade= "+fVO.getIdade()+", "
                    + "sexo= '"+fVO.getSexo()+"', cidade= '"+fVO.getCidade()+"', bairro='"+fVO.getBairro()+"', rua='"+fVO.getRua()+"',"
                    + "numero= '"+fVO.getNumero()+"', email= '"+fVO.getEmail()+"', telefone1= "+fVO.getTelefone1()+", telefone2= "+fVO.getTelefone2()+"  WHERE idfuncionario= '" +fVO.getIdfundionario()+ "'";
            stat.execute(sql);
        } catch (Exception e) {
           throw new SQLException("Erro ao alterar funcionario!!" + e.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
    
    public void deletarFuncionario(long idfuncionario) throws SQLException{
        
        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();
        
        try {
            
            String slq = "DELETE FROM funcionario WHERE idfuncionario= " + idfuncionario;
            stat.execute(slq);
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar funcionario!!" + e.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
    
    public ArrayList<FuncionarioVO> buscarFuncionarios() throws SQLException{
        
        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();
        
        try {
            String sql = "SELECT * FROM funcionario";
            
            /* Executando o SQL  e armazenando
             o ResultSet em um objeto do tipo
             ResultSet chamado rs */
            ResultSet rs = stat.executeQuery(sql);
            
            /* Criando ArrayList para armazenar 
             objetos do tipo funcionario */
            ArrayList<FuncionarioVO> fli = new ArrayList<>();
            
            /* Enquanto houver uma próxima linha no 
             banco de dados o while roda */
            while(rs.next()){
                //Criando um novo objeto FuncionarioVO
                FuncionarioVO f = new FuncionarioVO();
                
                /*Mapeando a tabela do banco para objeto chamado fVO*/
                f.setIdfundionario(rs.getLong("idfuncionario"));
                f.setNome(rs.getString("nome"));
                f.setSobrenome(rs.getString("sobrenome"));
                f.setSexo(rs.getString("sexo"));
                f.setIdade(Integer.parseInt(rs.getString("idade")));
                f.setCidade(rs.getString("cidade"));
                f.setBairro(rs.getString("bairro"));
                f.setRua(rs.getString("rua"));
                f.setNumero(Integer.parseInt(rs.getString("numero")));
                f.setEmail(rs.getString("email"));
                f.setTelefone1(Integer.parseInt(rs.getString("telefone1")));
                f.setTelefone2(Integer.parseInt(rs.getString("telefone2")));
                /*Inserindo o objeto cVO no ArrayList*/
                fli.add(f);
            }
            
            //Retornando o ArrayList com todos os objetos
            return fli;
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar funcionarios! " + e.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
    
    public ArrayList<FuncionarioVO> filtrarFuncionarios(String query) throws SQLException{
        
        //Buscando uma conexão com o Banco de Dados
        Connection con = ConnectionDB.getConexao();
        //Criando obj. capaz de executar instruções SQL no banco de dados
        Statement stat = con.createStatement();
        
        try {
            String sql = "SELECT * FROM funcionario " + query;
            
            /* Executando o SQL  e armazenando
             o ResultSet em um objeto do tipo
             ResultSet chamado rs */
            ResultSet rs = stat.executeQuery(sql);
            
            /* Criando ArrayList para armazenar 
             objetos do tipo cliente */
            ArrayList<FuncionarioVO> fli = new ArrayList<>();
            
            /* Enquanto houver uma próxima linha no 
             banco de dados o while roda */
            while(rs.next()){
                //Criando um novo objeto ClienteVO
                FuncionarioVO f = new FuncionarioVO();
                
                /*Mapeando a tabela do banco para objeto chamado cVO*/
                f.setIdfundionario(rs.getLong("idfuncionario"));
                f.setNome(rs.getString("nome"));
                f.setSobrenome(rs.getString("sobrenome"));
                f.setSexo(rs.getString("sexo"));
                f.setIdade(Integer.parseInt(rs.getString("idade")));
                f.setCidade(rs.getString("cidade"));
                f.setBairro(rs.getString("bairro"));
                f.setRua(rs.getString("rua"));
                f.setNumero(Integer.parseInt(rs.getString("numero")));
                f.setEmail(rs.getString("email"));
                f.setTelefone1(Integer.parseInt(rs.getString("telefone1")));
                f.setTelefone2(Integer.parseInt(rs.getString("telefone2")));
                /*Inserindo o objeto cVO no ArrayList*/
                fli.add(f);
            }
            return fli;
            
        } catch (SQLException e) {
           throw new SQLException("Erro ao buscar cliente !" + e.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
    
}
