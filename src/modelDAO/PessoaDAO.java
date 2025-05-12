/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDAO;

import model.Pessoa;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class PessoaDAO extends DAOManager<Pessoa> {

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
        for (Pessoa p : registros) {      // ou getRegistros()
            System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Login: " + p.getLogin());
        }
    }

    public void atualizarViaConsole(Scanner scanner) {
        System.out.print("Digite o ID da pessoa que deseja atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Pessoa pessoa = buscarPorId(id);

        if (pessoa != null) {
            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();
            System.out.print("Novo login: ");
            String login = scanner.nextLine();
            System.out.print("Nova senha: ");
            String senha = scanner.nextLine();

            pessoa.setNome(nome);
            pessoa.setLogin(login);
            pessoa.setSenha(senha);

            atualizar(pessoa); // chama o método herdado de CRUDManager
            System.out.println("Pessoa atualizada com sucesso!");
        } else {
            System.out.println("Pessoa com ID " + id + " não encontrada.");
        }
    }

    public void deletarViaConsole(Scanner scanner) {
        System.out.print("Digite o ID da pessoa que deseja deletar: ");
        int idParaDeletar = Integer.parseInt(scanner.nextLine());
        deletar(idParaDeletar);
        System.out.println("Pessoa deletada com sucesso!");
    }

}
