/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import java.util.ArrayList;
import java.util.List;
import model.EntidadeBase;
import util.DataUtil;

/**
 *
 * @author User
 * @param <T>
 */
public abstract class CRUDManager<T extends EntidadeBase> {

    protected List<T> registros = new ArrayList<>();
    protected int ultimoId = 0;

    public CRUDManager() {
    }

    // Adicione este método para acessar os registros
    public List<T> getRegistros() {
        return registros;
    }

    public void criar(T entidade) {
        entidade.setId(++ultimoId);
        entidade.setDataCriacao(DataUtil.getDataAtual());
        registros.add(entidade);               // antes era registros[ultimoId-1] = entidade;
    }

    public T buscarPorId(int id) {
        for (T registro : registros) {         // a iteração por List<T> funciona igual
            if (registro != null && registro.getId() == id) {
                return registro;
            }
        }
        return null;
    }

    public void atualizar(T entidade) {
        entidade.setDataModificacao(DataUtil.getDataAtual());
        // antes: registros[entidade.getId()-1] = entidade;
        // agora substituímos o elemento na posição correta:
        int idAtualizar = entidade.getId() - 1;
        if (idAtualizar >= 0 && idAtualizar < registros.size()) {
            registros.set(idAtualizar, entidade);
        }
    }

    public void deletar(int id) {
        int idDeletar = id - 1;
        if (idDeletar >= 0 && idDeletar < registros.size()) {
            registros.remove(idDeletar);              // remove e ?encolhe? a lista
        }
    }

}
