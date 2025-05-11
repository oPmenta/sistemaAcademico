/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author User
 */
public class ValidacaoUtil {
    public static boolean validarTelefone(String telefone) {
        return telefone.matches("(\\(?\\d{2}\\)?\\s)?(\\d{4,5}-\\d{4})");
    }
}
