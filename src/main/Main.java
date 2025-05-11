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

        // Buscar em todas as Pessoas cadastradas
        for (Pessoa pessoa : pessoaCRUD.getRegistros()) {
            if (pessoa != null && pessoa.getLogin().equals(login) && pessoa.getSenha().equals(senha)) {
                // Verificar se há um Usuário vinculado a esta Pessoa
                for (Usuario usuario : usuarioCRUD.getRegistros()) {
                    if (usuario.getPessoa().getId() == pessoa.getId()) {
                        usuarioLogado = usuario;
                        System.out.println("Login realizado como: " + usuario.getTipo());
                        return;
                    }
                }
            }
        }

        // Se não encontrou, verifica o admin padrão (para exemplo inicial)
        if (login.equals("admin") && senha.equals("admin123")) {
            Pessoa admin = new Pessoa(1, "Admin", "admin", "admin123");
            pessoaCRUD.criar(admin);
            usuarioLogado = new Usuario(1, admin, null, "ADMIN_GERAL");
            usuarioCRUD.criar(usuarioLogado);
            System.out.println("Login realizado como ADMIN_GERAL");
        } else {
            System.out.println("Login ou senha inválidos!");
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
                    flag = 0;
                    while (flag == 0) {
                        System.out.println("\n=== MENU CURSO ===");
                        System.out.println("1 - Criar Curso");
                        System.out.println("2 - Atualizar Curso");
                        System.out.println("3 - Mostrar Curso");
                        System.out.println("4 - Deletar Curso");
                        System.out.println("5 - Voltar");
                        System.out.print("Escolha: ");
                        int opcaoMenuPessoa = Integer.parseInt(scanner.nextLine());
                        switch (opcaoMenuPessoa) {
                            case 1:
                                cursoCRUD.criarViaConsole(scanner);
                                break;
                            case 2:
                                cursoCRUD.atualizarViaConsole(scanner);
                                break;
                            case 3:
                                cursoCRUD.listarCursos();
                                break;
                            case 4:
                                cursoCRUD.deletarViaConsole(scanner);
                                break;
                            case 5:
                                flag = 1;
                                break;
                        }
                    }
                    break;
                case 5:
                    flag = 0;
                    while (flag == 0) {
                        System.out.println("\n=== MENU TURMA ===");
                        System.out.println("1 - Criar Turma");
                        System.out.println("2 - Atualizar Turma");
                        System.out.println("3 - Mostrar Turma");
                        System.out.println("4 - Deletar Turma");
                        System.out.println("5 - Voltar");
                        System.out.print("Escolha: ");
                        int opcaoMenuPessoa = Integer.parseInt(scanner.nextLine());
                        int escolaId = usuarioLogado.getEscola().getId();
                        switch (opcaoMenuPessoa) {
                            case 1:
                                turmaCRUD.criarViaConsole(scanner, escolaId);
                                break;
                            case 2:
                                turmaCRUD.atualizarViaConsole(scanner, escolaId);
                                break;
                            case 3:
                                turmaCRUD.listarTurmasDaEscola(escolaId);
                                break;
                            case 4:
                                turmaCRUD.deletarViaConsole(scanner, escolaId);
                                break;
                            case 5:
                                flag = 1;
                                break;
                        }
                    }
                    break;
                case 6:
                    usuarioLogado = null;
                    break;
            }
        } else if (usuarioLogado.getTipo().equals("ADMIN_ESCOLA")) {
            System.out.println("\n=== MENU ADMIN ESCOLA ===");
            System.out.println("1 - Menu de Aluno");
            System.out.println("2 - Menu de Usuarios da Escola");
            System.out.println("3 - Menu de Curso");
            System.out.println("4 - Menu de Turma");
            System.out.println("5 - Menu vincular Alunos às Turmas");
            System.out.println("6 - Voltar");
            System.out.print("Escolha: ");

            int opcao = Integer.parseInt(scanner.nextLine());
            int escolaId = usuarioLogado.getEscola().getId();
            int flag;
            switch (opcao) {
                case 1:
                    int flagAluno = 0;
                    while (flagAluno == 0) {
                        System.out.println("\n=== MENU ALUNO ===");
                        System.out.println("1 - Criar Aluno");
                        System.out.println("2 - Atualizar Aluno");
                        System.out.println("3 - Listar Alunos");
                        System.out.println("4 - Deletar Aluno");
                        System.out.println("5 - Voltar");
                        System.out.print("Escolha: ");

                        // Leia a NOVA opção do menu interno
                        int opcaoMenuAluno = Integer.parseInt(scanner.nextLine());

                        switch (opcaoMenuAluno) { // Use a nova variável aqui
                            case 1:
                                alunoCRUD.criarViaConsole(scanner);
                                break;
                            case 2:
                                alunoCRUD.atualizarViaConsole(scanner);
                                break;
                            case 3:
                                alunoCRUD.listarAlunos();
                                break;
                            case 4:
                                alunoCRUD.deletarViaConsole(scanner);
                                break;
                            case 5:
                                flagAluno = 1; // Sair do loop
                                break;
                            default:
                                System.out.println("Opção inválida!");
                        }
                    }
                    break;
                case 2:
                    flag = 0;
                    while (flag == 0) {
                        System.out.println("\n=== MENU USUARIOS DA ESCOLA ===");
                        System.out.println("1 - Vincular Usuário à Escola");
                        System.out.println("2 - Listar Usuários da Escola");
                        System.out.println("3 - Remover Vínculo de Usuário");
                        System.out.println("4 - Voltar (Logout)");
                        System.out.print("Escolha: ");

                        int opcaoMenuUsuario = Integer.parseInt(scanner.nextLine());

                        switch (opcaoMenuUsuario) {
                            case 1:
                                usuarioCRUD.vincularUsuarioEscola(scanner, escolaId);
                                break;
                            case 2:
                                usuarioCRUD.listarUsuariosDaEscola(escolaId);
                                break;
                            case 3:
                                usuarioCRUD.deletarUsuarioEscola(scanner);
                                break;
                            case 4:
                                flag = 1;
                                break;
                        }
                    }
                    break;
                case 3:
                    flag = 0;
                    while (flag == 0) {
                        System.out.println("\n=== MENU CURSO ===");
                        System.out.println("1 - Criar Curso");
                        System.out.println("2 - Atualizar Curso");
                        System.out.println("3 - Mostrar Curso");
                        System.out.println("4 - Deletar Curso");
                        System.out.println("5 - Voltar");
                        System.out.print("Escolha: ");
                        int opcaoMenuPessoa = Integer.parseInt(scanner.nextLine());
                        switch (opcaoMenuPessoa) {
                            case 1:
                                cursoCRUD.criarViaConsole(scanner);
                                break;
                            case 2:
                                cursoCRUD.atualizarViaConsole(scanner);
                                break;
                            case 3:
                                cursoCRUD.listarCursos();
                                break;
                            case 4:
                                cursoCRUD.deletarViaConsole(scanner);
                                break;
                            case 5:
                                flag = 1;
                                break;
                        }
                    }
                    break;
                case 4:
                    flag = 0;
                    while (flag == 0) {
                        System.out.println("\n=== MENU TURMA ===");
                        System.out.println("1 - Criar Turma");
                        System.out.println("2 - Atualizar Turma");
                        System.out.println("3 - Mostrar Turma");
                        System.out.println("4 - Deletar Turma");
                        System.out.println("5 - Voltar");
                        System.out.print("Escolha: ");
                        int opcaoMenuPessoa = Integer.parseInt(scanner.nextLine());

                        switch (opcaoMenuPessoa) {
                            case 1:
                                turmaCRUD.criarViaConsole(scanner, escolaId);
                                break;
                            case 2:
                                turmaCRUD.atualizarViaConsole(scanner, escolaId);
                                break;
                            case 3:
                                turmaCRUD.listarTurmasDaEscola(escolaId);
                                break;
                            case 4:
                                turmaCRUD.deletarViaConsole(scanner, escolaId);
                                break;
                            case 5:
                                flag = 1;
                                break;
                        }
                    }
                    break;
                case 5:
                    flag = 0;
                    while (flag == 0) {
                        System.out.println("\n=== MENU VINCULAR ALUNOS ÀS TURMAS ===");
                        System.out.println("1 - Vincular Aluno a Turma");
                        System.out.println("2 - Mover todos Alunos para outra Turma");
                        System.out.println("3 - Voltar");
                        System.out.print("Escolha: ");
                        int opcaoMenuPessoa = Integer.parseInt(scanner.nextLine());

                        switch (opcaoMenuPessoa) {
                            case 1:
                                alunoTurmaCRUD.vincularAlunoTurma(scanner);
                                break;
                            case 2:
                                alunoTurmaCRUD.moverAlunosEntreTurmas(scanner);
                                break;
                            case 3:
                                flag = 1;
                                break;
                        }
                    }
                    break;
                case 6:
                    usuarioLogado = null;
                    break;
            }
        }
    }
}
