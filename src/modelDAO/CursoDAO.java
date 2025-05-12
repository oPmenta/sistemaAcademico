/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDAO;

import model.*;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class CursoDAO extends DAOManager<Curso> {

    public void criarViaConsole(Scanner scanner) {
        System.out.print("Nome do Curso: ");
        String nome = scanner.nextLine();
        if (nome.isBlank()) {
            System.out.println("Erro: Nome não pode ser vazio!");
            return;
        }

        System.out.print("Sigla: ");
        String sigla = scanner.nextLine();
        if (sigla.isBlank()) {
            System.out.println("Erro: Sigla não pode ser vazia!");
            return;
        }

        System.out.print("Tipo (SUPERIOR, INTEGRADO, CONCOMITANTE): ");
        String tipo = scanner.nextLine().toUpperCase();
        if (!tipo.matches("SUPERIOR|INTEGRADO|CONCOMITANTE")) {
            System.out.println("Erro: Tipo inválido!");
            return;
        }

        criar(new Curso(ultimoId + 1, nome, sigla, tipo));
        System.out.println("Curso criado com ID: " + ultimoId);
    }
    
    public void atualizarViaConsole(Scanner scanner) {
        System.out.print("Digite o ID do curso que deseja atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Curso curso = buscarPorId(id);

        if (curso != null) {
            System.out.print("Novo nome do curso: ");
            String nome = scanner.nextLine();
            System.out.print("Nova sigla: ");
            String sigla = scanner.nextLine();
            System.out.print("Novo tipo (SUPERIOR, INTEGRADO, CONCOMITANTE): ");
            String tipo = scanner.nextLine().toUpperCase();

            curso.setNome(nome);
            curso.setSigla(sigla);
            curso.setTipo(tipo);
            atualizar(curso);
            System.out.println("Curso atualizado com sucesso!");
        } else {
            System.out.println("Curso não encontrado!");
        }
    }

    public void listarCursos() {
        System.out.println("\n=== CURSOS CADASTRADOS ===");
        for (Curso curso : registros) {
            if (curso != null) {
                System.out.println("ID: " + curso.getId() + " | Nome: " + curso.getNome() + " (" + curso.getSigla() + ")");
            }
        }
    }

    public void deletarViaConsole(Scanner scanner) {
        System.out.print("Digite o ID do curso que deseja deletar: ");
        int id = Integer.parseInt(scanner.nextLine());
        deletar(id);
        System.out.println("Curso deletado com sucesso!");
    }
}
