
package model;

/**
 *
 * @author Willian Kaminski dos Santos 
 * @since 03/07/20148 21:15
 * @version 1.0 Coffee
 */
public class UsuarioVO {
    
    private long idusuario;
    private String login;
    private String senha;

    public UsuarioVO() {
    }

    public UsuarioVO(long idusuario, String login, String senha) {
        this.idusuario = idusuario;
        this.login = login;
        this.senha = senha;
    }

    public long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(long idusuario) {
        this.idusuario = idusuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "ID Usuario=" + idusuario + 
               "Login=" + login + 
               "Senha=" + senha ;
    }   
}
