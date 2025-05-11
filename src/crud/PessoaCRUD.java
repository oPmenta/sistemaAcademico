/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import model.Pessoa;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class PessoaCRUD extends CRUDManager<Pessoa> {

    public void criarViaConsole(Scanner scanner) {
        System.out.print("Nome da Pessoa: ");
        String nome = scanner.nextLine();
        if (nome.isBlank()) {
            System.out.println("Erro: Nome não pode ser vazio!");
            return;
        }

        System.out.print("Login: ");
        String login = scanner.nextLine();
        if (login.isBlank()) {
            System.out.println("Erro: Login não pode ser vazio!");
            return;
        }

        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        if (senha.isBlank()) {
            System.out.println("Erro: Senha não pode ser vazia!");
            return;
        }

        criar(new Pessoa(ultimoId + 1, nome, login, senha));
        System.out.println("Pessoa criada com ID: " + ultimoId);
    }

    public void listarPessoas() {
        System.out.println("\n=== PESSOAS CADASTRADAS ===");
        for (Pessoa pessoa : registros) {
            if (pessoa != null) {
                System.out.println("ID: " + pessoa.getId() + " | Nome: " + pessoa.getNome() + " | Login: " + pessoa.getLogin());
            }
        }
    }
}
