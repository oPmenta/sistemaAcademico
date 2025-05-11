/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author User
 */
public class Escola extends EntidadeBase {

    private String nome;
    private String cidade;
    private String telefone;

    public Escola(int id, String nome, String cidade, String telefone) {
        setId(id);
        this.nome = nome;
        this.cidade = cidade;
        this.telefone = telefone;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Escola{id=" + getId() + ", nome='" + nome + "', cidade='" + cidade + "', telefone='" + telefone + "'}";
    }
}
