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
public class TurmaCRUD extends CRUDManager<Turma> {

    private final EscolaCRUD escolaCRUD;
    private final CursoCRUD cursoCRUD;

    public TurmaCRUD(EscolaCRUD escolaCRUD, CursoCRUD cursoCRUD) {
        this.escolaCRUD = escolaCRUD;
        this.cursoCRUD = cursoCRUD;
    }

    public void criarViaConsole(Scanner scanner) {
        System.out.print("Nome da Turma: ");
        String nome = scanner.nextLine();
        if (nome.isBlank()) {
            System.out.println("Erro: Nome não pode ser vazio!");
            return;
        }

        System.out.print("ID da Escola: ");
        int idEscola = validarID(scanner);
        Escola escola = escolaCRUD.buscarPorId(idEscola);
        if (escola == null) {
            System.out.println("Erro: Escola não encontrada!");
            return;
        }

        System.out.print("ID do Curso: ");
        int idCurso = validarID(scanner);
        Curso curso = cursoCRUD.buscarPorId(idCurso);
        if (curso == null) {
            System.out.println("Erro: Curso não encontrado!");
            return;
        }

        criar(new Turma(ultimoId + 1, nome, curso, escola, "2024-1"));
        System.out.println("Turma criada com ID: " + ultimoId);
    }

    private int validarID(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
