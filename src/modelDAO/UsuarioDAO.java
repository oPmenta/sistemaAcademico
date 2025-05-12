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
public class UsuarioDAO extends DAOManager<Usuario> {

    private final PessoaDAO pessoaCRUD;
    private final EscolaDAO escolaCRUD;

    public UsuarioDAO(PessoaDAO pessoaCRUD, EscolaDAO escolaCRUD) {
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
            if (usuario != null) {
                // Verifica se pessoa e escola não são nulos
                if (usuario.getPessoa() == null || usuario.getEscola() == null) {
                    System.out.println("Erro: Dados incompletos para usuário com ID: " + usuario.getId());
                    continue; // Pula para o próximo usuário
                }

                System.out.println(
                        "ID: " + usuario.getId()
                        + " | Pessoa: " + usuario.getPessoa().getNome()
                        + " | Escola: " + usuario.getEscola().getNome()
                        + " | Tipo: " + usuario.getTipo()
                );
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

        System.out.print("Novo Tipo (ADMIN_GERAL, ADMIN_ESCOLA, PROFESSOR): ");
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

    public void vincularUsuarioEscola(Scanner scanner, int escolaId) {
        System.out.print("ID da Pessoa a ser vinculada: ");
        int pessoaId = Integer.parseInt(scanner.nextLine());
        Pessoa pessoa = pessoaCRUD.buscarPorId(pessoaId);

        if (pessoa == null) {
            System.out.println("Erro: Pessoa não encontrada!");
            return;
        }

        System.out.print("Tipo do Usuário (ADMIN_ESCOLA, PROFESSOR): ");
        String tipo = scanner.nextLine().toUpperCase();

        // Cria o usuário vinculado à escola do ADMIN_ESCOLA
        criar(new Usuario(ultimoId + 1, pessoa, escolaCRUD.buscarPorId(escolaId), tipo));
        System.out.println("Usuário vinculado à escola com ID: " + ultimoId);
    }

    public void listarUsuariosDaEscola(int escolaId) {
        System.out.println("\n=== USUÁRIOS VINCULADOS À ESCOLA ===");
        registros.stream()
                .filter(u -> u.getEscola().getId() == escolaId)
                .forEach(u -> System.out.println(
                "ID: " + u.getId()
                + " | Nome: " + u.getPessoa().getNome()
                + " | Tipo: " + u.getTipo()
        ));
    }

    public void deletarUsuarioEscola(Scanner scanner) {
        System.out.print("ID do Usuário para remover vínculo: ");
        int id = Integer.parseInt(scanner.nextLine());
        deletar(id);
        System.out.println("Vínculo removido com sucesso!");
    }
}
