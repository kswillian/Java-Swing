
package model;

/**
 *
 * @author Willian Kamisnki 
 * @since 08/07/2018 18:26
 * @version 1.0
 */
public class AutorVO {
    
    private long idautor;
    private String nome;

    public AutorVO() {
    }

    public AutorVO(long idautor, String nome) {
        this.idautor = idautor;
        this.nome = nome;
    }

    public long getIdautor() {
        return idautor;
    }

    public void setIdautor(long idautor) {
        this.idautor = idautor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return  nome;
    }
}
