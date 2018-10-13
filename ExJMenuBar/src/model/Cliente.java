/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Willian Kaminski
 * @since 27/06/2018 - 10:40
 * @version Beta Version 10.0
 */
public class Cliente {
    
    private String nome;
    private double valorTotal;
    private String email;
    private int telefone;
    private ArrayList<String> receber;
    private String formaPagamento;

    public Cliente() {
    }

    public Cliente(String nome, double valorTotal, String email, int telefone, ArrayList<String> receber, String formaPagamento) {
        this.nome = nome;
        this.valorTotal = valorTotal;
        this.email = email;
        this.telefone = telefone;
        this.receber = receber;
        this.formaPagamento = formaPagamento;
    }

    public String getNome() {
        return nome;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public ArrayList<String> getReceber() {
        return receber;
    }

    public void setReceber(ArrayList<String> receber) {
        this.receber = receber;
    }
    
    public void calcularValores(){
        
    }

    @Override
    public String toString() {
        
        String receive = "";
        
        for(int i =0; i < receber.size(); i++){
            receive += " " + receber.get(i);
        }
        
        if(!receber.isEmpty()){
            return "\nNome = " + nome + 
                   "\nValor Total = " + valorTotal +
                   "\nForma de Pagamento = " + getFormaPagamento() +
                   "\nE-mail = " + email + 
                   "\nTelefone = " + telefone + 
                   "\nReceber = " + receber;
        }
        
        return "\nNome = " + nome + 
               "\nValor Total = " + valorTotal +
               "\nForma de Pagamento = " + getFormaPagamento() +
               "\nE-mail = " + email + 
               "\nTelefone = " + telefone;              
    }    
}
