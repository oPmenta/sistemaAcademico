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
public class CursoCRUD extends CRUDManager<Curso> {

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

    public void listarCursos() {
        System.out.println("\n=== CURSOS CADASTRADOS ===");
        for (Curso curso : registros) {
            if (curso != null) {
                System.out.println("ID: " + curso.getId() + " | " + curso.getNome() + " (" + curso.getSigla() + ") - " + curso.getTipo());
            }
        }
    }
}
