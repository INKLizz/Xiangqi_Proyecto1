/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xianqi_other;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Laura Sabillon
 */
public class torre extends Piezas {

    public torre(ImageIcon icon, int fila1, int col1) {
        super(icon, fila1, col1);
    }

    public boolean MovimientoValido(JButton[] buttons, int fila1, int fila2, int column1, int column2, boolean isRed) {
        if (fila1 == fila2 || column1 == column2) {
            if (!super.PiezasSalto(buttons, fila1, fila2, column1, column2)) {
                return true;
            }
        }
        return false;
    }
}