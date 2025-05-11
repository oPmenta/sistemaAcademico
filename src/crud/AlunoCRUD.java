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
}

