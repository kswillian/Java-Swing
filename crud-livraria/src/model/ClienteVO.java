package model;

/**
 *
 * @author Willian Kaminski dos Santos 
 * @since 06/07/20148 20:45
 * @version 1.0 Coffee
 */
public class ClienteVO {
    
    private long idcliente;
    private String nome;
    private String sobrenome;
    private String sexo;
    private int idade;
    
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;
    
    private String email;
    private int telefone1;
    private int telefone2;
    
    private String data_cadastro;
    private String data_alteracao;
    //Associação entre classe
    private EnderecoVO e;
    //private ContatoVO c;

    public ClienteVO() {
    }

    public ClienteVO(long idcliente, String nome, String sobrenome, String sexo, int idade, String cidade, String bairro, String rua, int numero, String email, int telefone1, int telefone2, String data_cadastro, String data_alteracao, EnderecoVO e) {
        this.idcliente = idcliente;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.idade = idade;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.email = email;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.data_cadastro = data_cadastro;
        this.data_alteracao = data_alteracao;
        this.e = e;
    }

    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(int telefone1) {
        this.telefone1 = telefone1;
    }

    public int getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(int telefone2) {
        this.telefone2 = telefone2;
    }
    
    public String getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(String data_cadastro) {
        this.data_cadastro = data_cadastro;
    }
    
    public String getData_alteracao() {
        return data_alteracao;
    }

    public void setData_alteracao(String data_alteracao) {
        this.data_alteracao = data_alteracao;
    }

    public EnderecoVO getE() {
        return e;
    }

    public void setE(EnderecoVO e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return "ID Cliente=" + idcliente +
               "Nome=" + nome + 
               "Sobrenome=" + sobrenome + 
               "Sexo=" + sexo + 
               "Idade=" + idade + 
               "Cidade=" + cidade + 
               "Bairro=" + bairro + 
               "Rua=" + rua + 
               "Numero=" + numero + 
               "Email=" + email + 
               "Telefone1=" + telefone1 + 
               "Telefone2=" + telefone2 + 
               "Data Cadastro= " + data_cadastro + 
               "Data alteração= " + data_alteracao;
    }
}
