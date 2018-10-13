
package model;

/**
 *
 * @author Willian kaminski 
 * @since 09/007/2018 14:14
 * @version 1.0
 */
public class EnderecoVO {
    
    public long idendereco;
    public String cidade;
    public String bairro;
    public String rua;
    public int numero;

    public EnderecoVO() {
    }

    public EnderecoVO(long idendereco, String cidade, String bairro, String rua, int numero) {
        this.idendereco = idendereco;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

    public long getIdendereco() {
        return idendereco;
    }

    public void setIdendereco(long idendereco) {
        this.idendereco = idendereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "ID" + idendereco + ", cidade=" + cidade + ", bairro=" + bairro + ", rua=" + rua + ", numero=" + numero + '}';
    }
}
