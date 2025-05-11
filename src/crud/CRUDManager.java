/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import model.EntidadeBase;
import util.DataUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public abstract class CRUDManager<T extends EntidadeBase> {
    protected List<T> registros;
    protected int ultimoId = 0;

    public CRUDManager() {
        registros = new ArrayList<>();
    }

    public void criar(T entidade) {
        entidade.setId(++ultimoId);
        entidade.setDataCriacao(DataUtil.getDataAtual());
        registros.add(entidade);
    }

    public T buscarPorId(int id) {
        for (T registro : registros) {
            if (registro.getId() == id) return registro;
        }
        return null;
    }

    public void atualizar(T entidade) {
        entidade.setDataModificacao(DataUtil.getDataAtual());
        for (int i = 0; i < registros.size(); i++) {
            if (registros.get(i).getId() == entidade.getId()) {
                registros.set(i, entidade);
                break;
            }
        }
    }

    public void deletar(int id) {
        registros.removeIf(registro -> registro.getId() == id);
    }

    public List<T> getRegistros() { return registros; }
}

