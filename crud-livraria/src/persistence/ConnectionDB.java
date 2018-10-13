
package persistence;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Willian Kaminski
 * @since 06/07/2018 21:30
 * @version 1.0 Coffee
 */
public class ConnectionDB {
    
    //Atributos estáticos com os dados do Banco de Dados
    private static String URL = "jdbc:mysql://localhost:3306/livraria";
    private static String USUARIO = "root";
    private static String SENHA = "root";

    //Método que efetua a conexão com o MySQL
    public static java.sql.Connection getConexao() throws SQLException {
        java.sql.Connection c = null;
        try {
            c = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException se) {
            throw new SQLException("Erro ao conectar! " + se.getMessage());
        }//fecha catch
        return c;
    }//fecha metodo
}
