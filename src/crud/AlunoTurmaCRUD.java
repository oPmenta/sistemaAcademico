/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import model.*;
import util.DataUtil;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class AlunoTurmaCRUD extends CRUDManager<AlunoTurma> {

    private final AlunoCRUD alunoCRUD;
    private final TurmaCRUD turmaCRUD;

    public AlunoTurmaCRUD(AlunoCRUD alunoCRUD, TurmaCRUD turmaCRUD) {
        this.alunoCRUD = alunoCRUD;
        this.turmaCRUD = turmaCRUD;
    }

    public void vincularAlunoTurma(Scanner scanner) {
        System.out.print("ID do Aluno: ");
        int idAluno = validarID(scanner);
        Aluno aluno = alunoCRUD.buscarPorId(idAluno);
        if (aluno == null) {
            System.out.println("Erro: Aluno não encontrado!");
            return;
        }

        System.out.print("ID da Turma: ");
        int idTurma = validarID(scanner);
        Turma turma = turmaCRUD.buscarPorId(idTurma);
        if (turma == null) {
            System.out.println("Erro: Turma não encontrada!");
            return;
        }

        criar(new AlunoTurma(ultimoId + 1, aluno, turma));
        System.out.println("Aluno vinculado à turma com sucesso!");
    }

    public void moverAlunosEntreTurmas(Scanner scanner) {
        System.out.print("ID da Turma de Origem: ");
        int idOrigem = validarID(scanner);
        Turma origem = turmaCRUD.buscarPorId(idOrigem);
        if (origem == null) {
            System.out.println("Erro: Turma de origem inválida!");
            return;
        }

        System.out.print("ID da Turma de Destino: ");
        int idDestino = validarID(scanner);
        Turma destino = turmaCRUD.buscarPorId(idDestino);
        if (destino == null) {
            System.out.println("Erro: Turma de destino inválida!");
            return;
        }

        for (AlunoTurma relacao : registros) {
            if (relacao != null && relacao.getTurma().getId() == idOrigem) {
                relacao.setTurma(destino);
                relacao.setDataModificacao(DataUtil.getDataAtual());
            }
        }
        System.out.println("Alunos movidos com sucesso!");
    }

    private int validarID(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
