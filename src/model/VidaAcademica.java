/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author User
 */
public class VidaAcademica extends EntidadeBase {
    private String tipo; // OBSERVACAO, INCIDENTE, ADVERTENCIA, MERITO
    private String descricao;
    private Aluno aluno;

    public VidaAcademica(int id, String tipo, String descricao, Aluno aluno) {
        setId(id);
        this.tipo = tipo;
        this.descricao = descricao;
        this.aluno = aluno;
    }

    // Getters e Setters
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
}
