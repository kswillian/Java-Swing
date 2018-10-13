package model;

/**
 *
 * @author Willian Kaminski dos Santos 
 * @since 03/07/20148 21:15
 * @version 1.0 Coffee
 */
public class LivroVO {
    
    private long idlivro;
    private String titulo;
    private String subtitulo;
    private int edicao;
    private String editora;
    private int quantidade;
    private long ISBN;
    private String autor;
    private String data_lancamento;
    private String data_alteracao;
    private double valor_venda;
    //Associação entre classes 
    private AutorVO s;

    public LivroVO() {
    }

    public long getIdlivro() {
        return idlivro;
    }

    public void setIdlivro(long idlivro) {
        this.idlivro = idlivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(String data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public String getData_alteracao() {
        return data_alteracao;
    }

    public void setData_alteracao(String data_alteracao) {
        this.data_alteracao = data_alteracao;
    }

    public double getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(double valor_venda) {
        this.valor_venda = valor_venda;
    }
    
    public AutorVO getS() {
        return s;
    }

    public void setS(AutorVO s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "ID Livro=" + idlivro + 
               "Titulo=" + titulo + 
               "Subtitulo=" + subtitulo + 
               "Edicao=" + edicao + 
               "Editora=" + editora + 
               "Ano=" + quantidade + 
               "ISBN=" + ISBN + 
               "Autor=" + autor + 
               "Data_lancamento=" + data_lancamento + 
               "Data_alteracao=" + data_alteracao + 
               "Valor_venda=" + valor_venda;
    }
}
