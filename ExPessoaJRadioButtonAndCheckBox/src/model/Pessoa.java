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
 * @since 25/07/2018 08:47
 * @version 1.0 Café
 */
public class Pessoa {
    private String nome;
    private String sexo;
    private ArrayList<String> prefs;

    public Pessoa() {
    }
    /**
     * 
     * @param nome Pega o nome digitado pelo usuario
     * @param sexo Pega o sexo selecionado pelo usuario
     */
    public Pessoa(String nome, String sexo) {
        this.nome = nome;
        this.sexo = sexo;
    }

    public ArrayList<String> getPrefs() {
        return prefs;
    }

    public void setPrefs(ArrayList<String> prefs) {
        this.prefs = prefs;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        
        String pre = "";
        for(int i = 0; i < prefs.size(); i++){
            pre += "  " + prefs.get(i);
        }
        
        if(prefs.size() != 0){
            return "\nNome: " + nome + 
               "\nSexo: " + sexo + 
               "\nPreferencias: " + pre;
            
        }
        
        return "\nNome: " + nome + 
               "\nSexo: " + sexo;
        
    }    
}
