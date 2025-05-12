/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDAO;

import model.*;
import util.DataUtil;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class AlunoTurmaDAO extends DAOManager<AlunoTurma> {

    private final AlunoDAO alunoDAO;
    private final TurmaDAO turmaCRUD;

    public AlunoTurmaDAO(AlunoDAO alunoDAO, TurmaDAO turmaCRUD) {
        this.alunoDAO = alunoDAO;
        this.turmaCRUD = turmaCRUD;
    }

    public void vincularAlunoTurma(Scanner scanner) {
        // Passo 1: Solicitar ID do aluno e validar
        System.out.print("ID do Aluno: ");
        int idAluno = validarID(scanner);
        if (idAluno <= 0) {
            System.out.println("Erro: ID do aluno inv�lido!");
            return;
        }

        Aluno aluno = alunoDAO.buscarPorId(idAluno);
        if (aluno == null) {
            System.out.println("Erro: Aluno n�o encontrado!");
            return;
        }

        // Passo 2: Solicitar ID da turma e validar
        System.out.print("ID da Turma: ");
        int idTurma = validarID(scanner);
        if (idTurma <= 0) {
            System.out.println("Erro: ID da turma inv�lido!");
            return;
        }

        Turma turma = turmaCRUD.buscarPorId(idTurma);
        if (turma == null) {
            System.out.println("Erro: Turma n�o encontrada!");
            return;
        }

        // Passo 3: Verificar se o aluno j� est� vinculado � turma
        boolean jaVinculado = registros.stream()
            .anyMatch(relacao -> relacao.getAluno().getId() == idAluno && relacao.getTurma().getId() == idTurma);

        if (jaVinculado) {
            System.out.println("Erro: Aluno j� est� vinculado a esta turma!");
            return;
        }

        // Passo 4: Criar o v�nculo
        criar(new AlunoTurma(ultimoId + 1, aluno, turma));
        System.out.println("Aluno vinculado � turma com sucesso! ID do v�nculo: " + ultimoId);
    }

    public void moverAlunosEntreTurmas(Scanner scanner) {
        System.out.print("ID da Turma de Origem: ");
        int idOrigem = validarID(scanner);
        Turma origem = turmaCRUD.buscarPorId(idOrigem);
        if (origem == null) {
            System.out.println("Erro: Turma de origem inv�lida!");
            return;
        }

        System.out.print("ID da Turma de Destino: ");
        int idDestino = validarID(scanner);
        Turma destino = turmaCRUD.buscarPorId(idDestino);
        if (destino == null) {
            System.out.println("Erro: Turma de destino inv�lida!");
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
