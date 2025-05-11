/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import model.Escola;
import model.Usuario;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class EscolaCRUD extends CRUDManager<Escola> {

    public void criarViaConsole(Scanner scanner, Usuario usuarioLogado) {
        if (!usuarioLogado.getTipo().equals("ADMIN_GERAL")) {
            System.out.println("Acesso negado! Apenas ADMIN_GERAL.");
            return;
        }

        System.out.print("Nome da Escola: ");
        String nome = scanner.nextLine();
        if (nome.isBlank()) {
            System.out.println("Erro: Nome não pode ser vazio!");
            return;
        }

        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        if (cidade.isBlank()) {
            System.out.println("Erro: Cidade não pode ser vazia!");
            return;
        }

        criar(new Escola(ultimoId + 1, nome, cidade));
        System.out.println("Escola criada com ID: " + ultimoId);
    }

    public void listarEscolas() {
        System.out.println("\n=== ESCOLAS CADASTRADAS ===");
        for (Escola escola : registros) {
            System.out.println("ID: " + escola.getId() + " | Nome: " + escola.getNome() + " | Cidade: " + escola.getCidade());
        }
    }
}
