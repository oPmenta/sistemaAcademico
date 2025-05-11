/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import model.RegistroProfessor;
import model.Usuario;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class RegistroProfessorCRUD extends CRUDManager<RegistroProfessor> {

    private final UsuarioCRUD usuarioCRUD;

    public RegistroProfessorCRUD(UsuarioCRUD usuarioCRUD) {
        this.usuarioCRUD = usuarioCRUD;
    }

    public void criarViaConsole(Scanner scanner, Usuario professorLogado) {
        if (professorLogado == null || !professorLogado.getTipo().equals("PROFESSOR")) {
            System.out.println("Erro: Apenas professores podem criar registros!");
            return;
        }

        System.out.print("Disciplina: ");
        String disciplina = scanner.nextLine();
        if (disciplina.isBlank()) {
            System.out.println("Erro: Disciplina não pode ser vazia!");
            return;
        }

        System.out.print("Período (ex: 1º SEMESTRE): ");
        String periodo = scanner.nextLine();
        if (periodo.isBlank()) {
            System.out.println("Erro: Período não pode ser vazio!");
            return;
        }

        criar(new RegistroProfessor(ultimoId + 1, professorLogado, disciplina, periodo, null));
        System.out.println("Registro criado com ID: " + ultimoId);
    }
}
