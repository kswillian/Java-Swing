/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Willian Kaminski
 * @since 19/06/2018 08:49
 * @version 1.0 Café
 */
public class Pessoa {
    private String nome;
    private float peso;
    private int idade;

    public Pessoa() {
    }
    /**
     * 
     * @param nome Recebe o nome da pessoa
     * @param peso Recebe o peso da pessoa
     * @param idade recebe a idade da pessoa
     */
    public Pessoa(String nome, float peso, int idade) {
        this.nome = nome;
        this.peso = peso;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    /**
     * 
     * @return Retorna a idade em meses 
     */
    public int calcularIdadeMeses(){
        return idade * 12;
    }
    /**
     * 
     * @return Retorna a idade em semanas 
     */
    public int calcularIdadeSemanas(){
        return idade * 52;
    }
    /**
     * 
     * @return Retorna a idade em dias 
     */
    public int calcularIdadeDias(){
        return idade * 365;
    }
    
    @Override
    public String toString() {
        return "\nNome = " + nome + 
               "\nPeso = " + peso + 
               "\nIdade = " + idade + 
               "\nIdade em meses = " + calcularIdadeMeses() +
               "\nIdade em semanas = " + calcularIdadeSemanas() + 
               "\nIdade em dias = " + calcularIdadeDias();
    }
}
