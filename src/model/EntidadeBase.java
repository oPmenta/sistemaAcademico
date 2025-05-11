/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author User
 */
public abstract class EntidadeBase {
    private int id;
    private String dataCriacao;
    private String dataModificacao;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(String dataCriacao) { this.dataCriacao = dataCriacao; }
    public String getDataModificacao() { return dataModificacao; }
    public void setDataModificacao(String dataModificacao) { this.dataModificacao = dataModificacao; }
}
