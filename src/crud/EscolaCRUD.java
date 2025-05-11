/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import model.Escola;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class EscolaCRUD extends CRUDManager<Escola> {

    public void criarViaConsole(Scanner scanner) {
        System.out.print("Nome da Escola: ");
        String nome = scanner.nextLine();
        if (nome.isBlank()) {
            System.out.println("Erro: Nome nao pode ser vazio!");
            return;
        }

        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        if (cidade.isBlank()) {
            System.out.println("Erro: Cidade nao pode ser vazia!");
            return;
        }

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        if (telefone.isBlank()) {
            System.out.println("Erro: Telefone nao pode ser vazio!");
            return;
        }

        criar(new Escola(ultimoId + 1, nome, cidade, telefone));
        System.out.println("Escola criada com ID: " + ultimoId);
    }

    public void listarEscolas() {
        System.out.println("\n=== ESCOLAS CADASTRADAS ===");
        for (Escola escola : registros) {
            if (escola != null) {
                System.out.println("ID: " + escola.getId() + " | Nome: " + escola.getNome() + " | Cidade: " + escola.getCidade() + " | Telefone: " + escola.getTelefone());
            }
        }
    }

    public void atualizarViaConsole(Scanner scanner) {
        System.out.print("Digite o ID da escola que deseja atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Escola escola = buscarPorId(id);

        if (escola != null) {
            System.out.print("Novo nome da escola: ");
            String nome = scanner.nextLine();
            System.out.print("Nova cidade: ");
            String cidade = scanner.nextLine();
            System.out.print("Novo Telefone: ");
            String telefone = scanner.nextLine();

            escola.setNome(nome);
            escola.setCidade(cidade);
            escola.setTelefone(telefone);

            atualizar(escola); // chama o método herdado de CRUDManager
            System.out.println("Escola atualizada com sucesso!");
        } else {
            System.out.println("Escola com ID " + id + " nao encontrada.");
        }
    }

    public void deletarViaConsole(Scanner scanner) {
        System.out.print("Digite o ID da Escola que deseja deletar: ");
        int idParaDeletar = Integer.parseInt(scanner.nextLine());
        deletar(idParaDeletar);
        System.out.println("Escola deletada com sucesso!");
    }

}
