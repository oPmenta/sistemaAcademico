/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import model.Aluno;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class AlunoCRUD extends CRUDManager<Aluno> {

    public void criarViaConsole(Scanner scanner) {
        System.out.print("Nome do Aluno: ");
        String nome = scanner.nextLine();
        if (nome.isBlank()) {
            System.out.println("Erro: Nome não pode ser vazio!");
            return;
        }

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        if (cpf.isBlank() || !cpf.matches("\\d{11}")) {
            System.out.println("Erro: CPF inválido!");
            return;
        }

        System.out.print("Email: ");
        String email = scanner.nextLine();
        if (email.isBlank() || !email.contains("@")) {
            System.out.println("Erro: Email inválido!");
            return;
        }

        criar(new Aluno(ultimoId + 1, nome, cpf, email));
        System.out.println("Aluno criado com ID: " + ultimoId);
    }
    
    public void atualizarViaConsole(Scanner scanner) {
        System.out.print("Digite o ID do aluno que deseja atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Aluno aluno = buscarPorId(id);

        if (aluno != null) {
            System.out.print("Novo nome do aluno: ");
            String nome = scanner.nextLine();
            System.out.print("Novo CPF (apenas números, 11 dígitos): ");
            String cpf = scanner.nextLine();
            System.out.print("Novo email: ");
            String email = scanner.nextLine();

            // Validações
            if (cpf.length() != 11 || !cpf.matches("\\d+")) {
                System.out.println("Erro: CPF inválido!");
                return;
            }
            if (!email.contains("@")) {
                System.out.println("Erro: Email inválido!");
                return;
            }

            aluno.setNome(nome);
            aluno.setCpf(cpf);
            aluno.setEmail(email);
            atualizar(aluno);
            System.out.println("Aluno atualizado com sucesso!");
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    public void listarAlunos() {
        System.out.println("\n=== ALUNOS CADASTRADOS ===");
        for (Aluno aluno : registros) {
            if (aluno != null) {
                System.out.println(
                    "ID: " + aluno.getId() +
                    " | Nome: " + aluno.getNome() +
                    " | CPF: " + aluno.getCpf() +
                    " | Email: " + aluno.getEmail()
                );
            }
        }
    }

    public void deletarViaConsole(Scanner scanner) {
        System.out.print("Digite o ID do aluno que deseja deletar: ");
        int id = Integer.parseInt(scanner.nextLine());
        deletar(id);
        System.out.println("Aluno deletado com sucesso!");
    }
}
