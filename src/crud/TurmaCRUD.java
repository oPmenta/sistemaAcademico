/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import model.*;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class TurmaCRUD extends CRUDManager<Turma> {

    private final EscolaCRUD escolaCRUD;
    private final CursoCRUD cursoCRUD;

    public TurmaCRUD(EscolaCRUD escolaCRUD, CursoCRUD cursoCRUD) {
        this.escolaCRUD = escolaCRUD;
        this.cursoCRUD = cursoCRUD;
    }

    public void criarViaConsole(Scanner scanner, int escolaId) {
        Escola escola = escolaCRUD.buscarPorId(escolaId); // Escola fixa do ADMIN_ESCOLA
        System.out.print("Nome da Turma: ");
        String nome = scanner.nextLine();
        if (nome.isBlank()) {
            System.out.println("Erro: Nome não pode ser vazio!");
            return;
        }

        System.out.print("ID do Curso: ");
        int idCurso = validarID(scanner);
        Curso curso = cursoCRUD.buscarPorId(idCurso);
        if (curso == null) {
            System.out.println("Erro: Curso não encontrado!");
            return;
        }

        criar(new Turma(ultimoId + 1, nome, curso, escola, "2024-1"));
        System.out.println("Turma criada com ID: " + ultimoId);
    }

    public void atualizarViaConsole(Scanner scanner, int escolaId) {
        System.out.print("ID da Turma para atualizar: ");
        int idTurma = Integer.parseInt(scanner.nextLine());
        Turma turma = buscarPorId(idTurma);

        // Verifica se a turma pertence à escola do ADMIN_ESCOLA
        if (turma == null || turma.getEscola().getId() != escolaId) {
            System.out.println("Turma não encontrada ou não pertence à sua escola!");
            return;
        }

        System.out.print("Novo nome da Turma: ");
        String novoNome = scanner.nextLine();
        turma.setNome(novoNome);
        atualizar(turma);
        System.out.println("Turma atualizada com sucesso!");
    }

    public void listarTurmasDaEscola(int escolaId) {
        System.out.println("\n=== TURMAS DA ESCOLA ===");
        for (Turma turma : registros) {
            if (turma != null && turma.getEscola().getId() == escolaId) {
                System.out.println("ID: " + turma.getId() + " | Nome: " + turma.getNome());
            }
        }
    }

    public void deletarViaConsole(Scanner scanner, int escolaId) {
        System.out.print("ID da Turma para deletar: ");
        int idTurma = Integer.parseInt(scanner.nextLine());
        Turma turma = buscarPorId(idTurma);

        // Verifica se a turma pertence à escola do ADMIN_ESCOLA
        if (turma == null || turma.getEscola().getId() != escolaId) {
            System.out.println("Turma não encontrada ou não pertence à sua escola!");
            return;
        }

        deletar(idTurma);
        System.out.println("Turma deletada com sucesso!");
    }

    private int validarID(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
