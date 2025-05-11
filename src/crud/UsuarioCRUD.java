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
public class UsuarioCRUD extends CRUDManager<Usuario> {

    private final PessoaCRUD pessoaCRUD;
    private final EscolaCRUD escolaCRUD;

    public UsuarioCRUD(PessoaCRUD pessoaCRUD, EscolaCRUD escolaCRUD) {
        this.pessoaCRUD = pessoaCRUD;
        this.escolaCRUD = escolaCRUD;
    }

    public void criarViaConsole(Scanner scanner) {
        System.out.print("ID da Pessoa: ");
        int idPessoa = validarID(scanner);
        Pessoa pessoa = pessoaCRUD.buscarPorId(idPessoa);
        if (pessoa == null) {
            System.out.println("Erro: Pessoa não encontrada!");
            return;
        }

        System.out.print("ID da Escola: ");
        int idEscola = validarID(scanner);
        Escola escola = escolaCRUD.buscarPorId(idEscola);
        if (escola == null) {
            System.out.println("Erro: Escola não encontrada!");
            return;
        }

        System.out.print("Tipo (ADMIN_GERAL, ADMIN_ESCOLA, PROFESSOR): ");
        String tipo = scanner.nextLine().toUpperCase();
        if (!tipo.matches("ADMIN_GERAL|ADMIN_ESCOLA|PROFESSOR")) {
            System.out.println("Erro: Tipo inválido!");
            return;
        }

        criar(new Usuario(ultimoId + 1, pessoa, escola, tipo));
        System.out.println("Usuário criado com ID: " + ultimoId);

        /*
        System.out.println("Pessoa: " + pessoa);  // Debug
        System.out.println("Escola: " + escola);  // Debug
         */
    }

    private int validarID(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void listarUsuarios() {
        System.out.println("\n=== USUARIOS CADASTRADOS ===");
        for (Usuario usuario : registros) {
            if (usuario != null && usuario.getPessoa() != null && usuario.getEscola() != null) {
                System.out.println("ID (Pessoa): " + usuario.getPessoa().getId() + " | ID (Escola): " + usuario.getEscola().getId() + " | Tipo: " + usuario.getTipo());
            } else {
                System.out.println("Erro: Dados inválidos ou nulos para usuário com ID: " + usuario.getId());
            }
        }

    }

    public void atualizarViaConsole(Scanner scanner) {
        System.out.print("Digite o ID do usuário que deseja atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Usuario usuario = buscarPorId(id);
        if (usuario == null) {
            System.out.println("Erro: Usuário não encontrado!");
            return;
        }

        System.out.print("Novo ID da Pessoa: ");
        int idPessoa = Integer.parseInt(scanner.nextLine());
        Pessoa pessoa = pessoaCRUD.buscarPorId(idPessoa);
        if (pessoa == null) {
            System.out.println("Erro: Pessoa não encontrada!");
            return;
        }

        System.out.print("Novo ID da Escola: ");
        int idEscola = Integer.parseInt(scanner.nextLine());
        Escola escola = escolaCRUD.buscarPorId(idEscola);
        if (escola == null) {
            System.out.println("Erro: Escola não encontrada!");
            return;
        }

        System.out.print("Novo Tipo: ");
        String tipo = scanner.nextLine().toUpperCase();
        if (!tipo.matches("ADMIN_GERAL|ADMIN_ESCOLA|PROFESSOR")) {
            System.out.println("Erro: Tipo inválido!");
            return;
        }

        usuario.setPessoa(pessoa);
        usuario.setEscola(escola);
        usuario.setTipo(tipo);

        atualizar(usuario);
        System.out.println("Usuário atualizado com sucesso!");
    }

    public void deletarViaConsole(Scanner scanner) {
        System.out.print("Digite o ID do Usuario que deseja deletar: ");
        int idParaDeletar = Integer.parseInt(scanner.nextLine());
        deletar(idParaDeletar);
        System.out.println("Usuario deletado com sucesso!");
    }
}
