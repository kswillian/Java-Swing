package model;

/**
 *
 * @author Willian Kaminski dos Santos 
 * @since 03/07/20148 21:15
 * @version 1.0 Coffee
 */
public class FuncionarioVO {
    
    private long idfundionario;
    private String nome;
    private String sobrenome;
    private String sexo;
    private int idade;
    
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;
    private String funcao;
    
    private String email;
    private int telefone1;
    private int telefone2;
    
    private String data_admicao;
    private String data_alteracao;

    public FuncionarioVO() {
    }

    public FuncionarioVO(long idfuncionario, String nome, String sobrenome, String sexo, int idade, String cidade, String bairro, String rua, int numero, String funcao, String email, int telefone1, int telefone2, String data_admicao, String data_alteracao) {
        this.idfundionario = idfuncionario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.idade = idade;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.funcao = funcao;
        this.email = email;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.data_admicao = data_admicao;
        this.data_alteracao = data_alteracao;
    }

    public long getIdfundionario() {
        return idfundionario;
    }

    public void setIdfundionario(long idfundionario) {
        this.idfundionario = idfundionario;
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

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
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

    public String getData_admicao() {
        return data_admicao;
    }

    public void setData_admicao(String data_admicao) {
        this.data_admicao = data_admicao;
    }

    public String getData_alteracao() {
        return data_alteracao;
    }

    public void setData_alteracao(String data_alteracao) {
        this.data_alteracao = data_alteracao;
    }

    @Override
    public String toString() {
        return "ID Funcionario=" + idfundionario +
               "Nome=" + nome + 
               "Sobrenome=" + sobrenome + 
               "Sexo=" + sexo + 
               "Idade=" + idade + 
               "Função= " + funcao + 
               "Cidade=" + cidade + 
               "Bairro=" + bairro + 
               "Rua=" + rua + 
               "Numero=" + numero + 
               "Email=" + email + 
               "Telefone1=" + telefone1 + 
               "Telefone2=" + telefone2 + 
               "Data_admicao=" + data_admicao + 
               "Data_alteracao=" + data_alteracao;
    }
}
