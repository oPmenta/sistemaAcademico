package crud;

import model.*;
import java.util.Scanner;

public class UsuarioCRUD extends CRUDManager<Usuario> {
    private final PessoaCRUD pessoaCRUD;
    private final EscolaCRUD escolaCRUD;

    public UsuarioCRUD(PessoaCRUD pessoaCRUD, EscolaCRUD escolaCRUD) {
        this.pessoaCRUD = pessoaCRUD;
        this.escolaCRUD = escolaCRUD;
    }

    public void criarViaConsole(Scanner scanner, Usuario usuarioLogado) {
        if (!usuarioLogado.getTipo().equals("ADMIN_GERAL")) {
            System.out.println("Acesso negado! Apenas ADMIN_GERAL.");
            return;
        }

        // ID da Pessoa
        int idPessoa;
        try {
            System.out.print("ID da Pessoa: ");
            idPessoa = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Erro: ID deve ser um n�mero!");
            return;
        }

        Pessoa pessoa = pessoaCRUD.buscarPorId(idPessoa);
        if (pessoa == null) {
            System.out.println("Erro: Pessoa n�o encontrada!");
            return;
        }

        // ID da Escola
        int idEscola;
        try {
            System.out.print("ID da Escola: ");
            idEscola = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Erro: ID deve ser um n�mero!");
            return;
        }

        Escola escola = escolaCRUD.buscarPorId(idEscola);
        if (escola == null) {
            System.out.println("Erro: Escola n�o encontrada!");
            return;
        }

        // Tipo do Usu�rio
        System.out.print("Tipo (ADMIN_ESCOLA ou FUNCIONARIO): ");
        String tipo = scanner.nextLine().toUpperCase();
        if (!tipo.matches("ADMIN_ESCOLA|FUNCIONARIO")) {
            System.out.println("Erro: Tipo inv�lido!");
            return;
        }

        criar(new Usuario(ultimoId + 1, pessoa, escola, tipo));
        System.out.println("Usu�rio criado com ID: " + ultimoId);
    }
}