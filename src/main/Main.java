/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import model.*;
import crud.*;
import java.util.Scanner;

/**
 *
 * @author User
 */

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static PessoaCRUD pessoaCRUD = new PessoaCRUD();
    private static EscolaCRUD escolaCRUD = new EscolaCRUD();
    private static CursoCRUD cursoCRUD = new CursoCRUD();
    private static TurmaCRUD turmaCRUD = new TurmaCRUD(escolaCRUD, cursoCRUD);
    private static UsuarioCRUD usuarioCRUD = new UsuarioCRUD(pessoaCRUD, escolaCRUD);
    private static AlunoCRUD alunoCRUD = new AlunoCRUD();
    private static AlunoTurmaCRUD alunoTurmaCRUD = new AlunoTurmaCRUD(alunoCRUD, turmaCRUD);
    private static Usuario usuarioLogado;

    public static void main(String[] args) {
        exibirTelaBoasVindas();
        while (true) {
            if (usuarioLogado == null) exibirLogin();
            else exibirMenuPrincipal();
        }
    }

    private static void exibirTelaBoasVindas() {
        System.out.println("=== BEM-VINDO AO SISTEMA ACADÊMICO ===");
    }

    private static void exibirLogin() {
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Autenticação simplificada (admin padrão)
        if (login.equals("admin") && senha.equals("admin123")) {
            Pessoa admin = new Pessoa(1, "Admin", "admin", "admin123");
            pessoaCRUD.criar(admin);
            usuarioLogado = new Usuario(1, admin, null, "ADMIN_GERAL");
            usuarioCRUD.criar(usuarioLogado);
            System.out.println("Login realizado!");
        }
    }

    private static void exibirMenuPrincipal() {
        switch (usuarioLogado.getTipo()) {
            case "ADMIN_GERAL":
                exibirMenuAdminGeral();
                break;
            case "ADMIN_ESCOLA":
                exibirMenuAdminEscola();
                break;
            case "FUNCIONARIO":
                exibirMenuFuncionario();
                break;
        }
    }

    private static void exibirMenuAdminGeral() {
        System.out.println("\n=== MENU ADMIN GERAL ===");
        System.out.println("1 - Criar Escola");
        System.out.println("2 - Criar Administrador de Escola");
        System.out.println("3 - Listar Escolas");
        System.out.print("Escolha: ");
        int opcao = Integer.parseInt(scanner.nextLine());
        switch (opcao) {
            case 1:
                escolaCRUD.criarViaConsole(scanner, usuarioLogado);
                break;
            case 2:
                usuarioCRUD.criarViaConsole(scanner, usuarioLogado);
                break;
            case 3:
                escolaCRUD.listarEscolas();
                break;
        }
    }

    private static void exibirMenuAdminEscola() {
        System.out.println("\n=== MENU ADMIN ESCOLA ===");
        System.out.println("1 - Criar Turma");
        System.out.println("2 - Vincular Aluno à Turma");
        System.out.println("3 - Listar Turmas");
        System.out.print("Escolha: ");
        int opcao = Integer.parseInt(scanner.nextLine());
        switch (opcao) {
            case 1:
                turmaCRUD.criarViaConsole(scanner, usuarioLogado);
                break;
            case 2:
                alunoTurmaCRUD.vincularAlunoTurma(scanner);
                break;
            case 3:
                turmaCRUD.gerarRelatorioTurmas(usuarioLogado.getEscola());
                break;
        }
    }

    private static void exibirMenuFuncionario() {
        System.out.println("\n=== MENU FUNCIONÁRIO ===");
        System.out.println("1 - Editar Aluno");
        System.out.println("2 - Gerar Relatório de Turmas");
        System.out.print("Escolha: ");
        int opcao = Integer.parseInt(scanner.nextLine());
        switch (opcao) {
            case 1:
                // Implementar edição de aluno
                break;
            case 2:
                turmaCRUD.gerarRelatorioTurmas(usuarioLogado.getEscola());
                break;
        }
    }
}