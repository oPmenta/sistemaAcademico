/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import model.VidaAcademica;
import model.Aluno;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class VidaAcademicaCRUD extends CRUDManager<VidaAcademica> {

    private final AlunoCRUD alunoCRUD;

    public VidaAcademicaCRUD(AlunoCRUD alunoCRUD) {
        this.alunoCRUD = alunoCRUD;
    }

    public void criarViaConsole(Scanner scanner) {
        System.out.print("ID do Aluno: ");
        int idAluno = validarID(scanner);
        Aluno aluno = alunoCRUD.buscarPorId(idAluno);
        if (aluno == null) {
            System.out.println("Erro: Aluno não encontrado!");
            return;
        }

        System.out.print("Tipo (OBSERVACAO, INCIDENTE, ADVERTENCIA, MERITO): ");
        String tipo = scanner.nextLine().toUpperCase();
        if (!tipo.matches("OBSERVACAO|INCIDENTE|ADVERTENCIA|MERITO")) {
            System.out.println("Erro: Tipo inválido!");
            return;
        }

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        if (descricao.isBlank()) {
            System.out.println("Erro: Descrição não pode ser vazia!");
            return;
        }

        criar(new VidaAcademica(ultimoId + 1, tipo, descricao, aluno));
        System.out.println("Registro acadêmico criado com ID: " + ultimoId);
    }

    private int validarID(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
