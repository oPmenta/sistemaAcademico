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

    public void criarViaConsole(Scanner scanner, Usuario usuarioLogado) {
        if (!usuarioLogado.getTipo().equals("ADMIN_ESCOLA")) {
            System.out.println("Acesso negado! Apenas ADMIN_ESCOLA.");
            return;
        }

        System.out.print("Nome da Turma: ");
        String nome = scanner.nextLine();
        if (nome.isBlank()) {
            System.out.println("Erro: Nome não pode ser vazio!");
            return;
        }

        Escola escola = usuarioLogado.getEscola(); // Escola vinculada ao usuário
        if (escola == null) {
            System.out.println("Erro: Escola não encontrada!");
            return;
        }

        System.out.print("ID do Curso: ");
        int idCurso = Integer.parseInt(scanner.nextLine());
        Curso curso = cursoCRUD.buscarPorId(idCurso);
        if (curso == null) {
            System.out.println("Erro: Curso não encontrado!");
            return;
        }

        criar(new Turma(ultimoId + 1, nome, curso, escola, "2024-1"));
        System.out.println("Turma criada com ID: " + ultimoId);
    }

    public void gerarRelatorioTurmas(Escola escola) {
        System.out.println("\n=== RELATÓRIO DE TURMAS ===");
        for (Turma turma : registros) {
            if (turma.getEscola().getId() == escola.getId()) {
                System.out.println("ID: " + turma.getId() + " | Nome: " + turma.getNome() + " | Curso: " + turma.getCurso().getNome());
            }
        }
    }
}
