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
public class advisor extends Piezas {

    public advisor(ImageIcon icon, int fila1, int col1) {
        super(icon, fila1, col1);
    }

    public boolean MovimientoValido(JButton[] buttons,int fila1, int fila2, int column1, int column2, boolean isRed) {
        if (isRed) {
            if (!super.enPalacio(fila2, column2, super.filasRojo)) {
                return false;
            }
        } else {
            if (!super.enPalacio(fila2, column2, super.filasBlack)) {
                return false;
            }
        }
        return (fila2 - fila1 == 1 || fila2 - fila1 == -1) && (column2 - column1 == 1 || column2 - column1 == -1);
    }

}