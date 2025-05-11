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
        while (true) {
            if (usuarioLogado == null) {
                exibirTelaBoasVindas();
                exibirLogin();
            } else {
                exibirMenuPrincipal();
            }
        }
    }

    private static void exibirTelaBoasVindas() {
        System.out.println("\n=== BEM-VINDO AO SISTEMA ACADÊMICO ===");
    }

    private static void exibirLogin() {
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Simulação de autenticação (admin padrão)
        if (login.equals("admin") && senha.equals("admin123")) {
            // Cria uma pessoa admin (para exemplo)
            Pessoa admin = new Pessoa(1, "Admin", "admin", "admin123");
            pessoaCRUD.criar(admin);
            // Cria escola admin (para exemplo)
            Escola escolaAdmin = new Escola(1, "Escola Admin", "Cidade Admin", "Telefone Admin");
            escolaCRUD.criar(escolaAdmin);
            usuarioLogado = new Usuario(1, admin, escolaAdmin, "ADMIN_GERAL");
            usuarioCRUD.criar(usuarioLogado);
            System.out.println("Login realizado!");
        }
    }

    private static void exibirMenuPrincipal() {
        if (usuarioLogado.getTipo().equals("ADMIN_GERAL")) {
            System.out.println("\n=== MENU ADMIN GERAL ===");
            System.out.println("1 - Menu de Pessoa");
            System.out.println("2 - Menu de Escola");
            System.out.println("3 - Menu de Usuario");
            System.out.println("4 - Menu de Curso");
            System.out.println("5 - Menu de Turma");
            System.out.println("6 - Voltar");
            System.out.print("Escolha: ");

            int opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 1:
                    int flag = 0;
                    while (flag == 0) {
                        System.out.println("\n=== MENU PESSOA ===");
                        System.out.println("1 - Criar Pessoa");
                        System.out.println("2 - Atualizar Pessoa");
                        System.out.println("3 - Mostrar Pessoa");
                        System.out.println("4 - Deletar Pessoa");
                        System.out.println("5 - Voltar");
                        System.out.print("Escolha: ");
                        int opcaoMenuPessoa = Integer.parseInt(scanner.nextLine());
                        switch (opcaoMenuPessoa) {
                            case 1:
                                pessoaCRUD.criarViaConsole(scanner);
                                break;
                            case 2:
                                pessoaCRUD.atualizarViaConsole(scanner);
                                break;
                            case 3:
                                pessoaCRUD.listarPessoas();
                                break;
                            case 4:
                                pessoaCRUD.deletarViaConsole(scanner);
                                break;
                            case 5:
                                flag = 1;
                                break;
                        }
                    }
                    break;
                case 2:
                    flag = 0;
                    while (flag == 0) {
                        System.out.println("\n=== MENU ESCOLA ===");
                        System.out.println("1 - Criar Escola");
                        System.out.println("2 - Atualizar Escola");
                        System.out.println("3 - Mostrar Escola");
                        System.out.println("4 - Deletar Escola");
                        System.out.println("5 - Voltar");
                        System.out.print("Escolha: ");
                        int opcaoMenuPessoa = Integer.parseInt(scanner.nextLine());
                        switch (opcaoMenuPessoa) {
                            case 1:
                                escolaCRUD.criarViaConsole(scanner);
                                break;
                            case 2:
                                escolaCRUD.atualizarViaConsole(scanner);
                                break;
                            case 3:
                                escolaCRUD.listarEscolas();
                                break;
                            case 4:
                                escolaCRUD.deletarViaConsole(scanner);
                                break;
                            case 5:
                                flag = 1;
                                break;
                        }
                    }
                    break;
                case 3:
                    flag = 0;
                    while (flag == 0) {
                        System.out.println("\n=== MENU USUARIO ===");
                        System.out.println("1 - Criar Usuario");
                        System.out.println("2 - Atualizar Usuario");
                        System.out.println("3 - Mostrar Usuario");
                        System.out.println("4 - Deletar Usuario");
                        System.out.println("5 - Voltar");
                        System.out.print("Escolha: ");
                        int opcaoMenuPessoa = Integer.parseInt(scanner.nextLine());
                        switch (opcaoMenuPessoa) {
                            case 1:
                                usuarioCRUD.criarViaConsole(scanner);
                                break;
                            case 2:
                                usuarioCRUD.atualizarViaConsole(scanner);
                                break;
                            case 3:
                                usuarioCRUD.listarUsuarios();
                                break;
                            case 4:
                                usuarioCRUD.deletarViaConsole(scanner);
                                break;
                            case 5:
                                flag = 1;
                                break;
                        }
                    }
                    break;
                case 4:
                    cursoCRUD.criarViaConsole(scanner);
                    break;
                case 5:
                    turmaCRUD.criarViaConsole(scanner);
                    break;
                case 6:
                    usuarioLogado = null;
                    break;
            }
        }
    }
}
