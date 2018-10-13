/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author QI
 */
public class Pessoa {
 
    private String nome;
    private double peso;
    private int idade;
    private String sexo;

    public Pessoa(String nome, double peso, int idade, String sexo) {
        this.nome = nome;
        this.peso = peso;
        this.idade = idade;
        this.sexo = sexo;
    }

    public Pessoa() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", peso=" + peso + ", idade=" + idade + ", sexo=" + sexo + '}';
    }

    
}
