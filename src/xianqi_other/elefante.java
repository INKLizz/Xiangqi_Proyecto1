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
public class elefante extends Piezas {

    public elefante(ImageIcon icon, int fila1, int col1) {
        super(icon, fila1, col1);
    }

    public boolean MovimientoValido(JButton[] buttons, int fila1, int fila2, int column1, int column2, boolean isRed) {
        boolean CruzoRio = isRed ? (fila2 >= super.Rio_black) : (fila2 <= super.Rio_rojo);
        if (!CruzoRio) {
            return false; 
        }
        boolean Movimiento = (fila2 == fila1 - 2 && (column1 == column2 - 2 || column1 == column2 + 2))
                || (fila2 == fila1 + 2 && (column1 == column2 - 2 || column1 == column2 + 2));

        if (!Movimiento) {
            return false;
        }

        return !PiezasSalto(buttons, fila1, fila2, column1, column2);
    }

    public boolean PiezasSalto(JButton[] buttons, int fila1, int fila2, int columna1, int columna2) {
        int mayorF = (fila1 > fila2) ? fila1 : fila2;
        int menorF = (fila1 > fila2) ? fila2 : fila1;
        int mayorC = (columna1 > columna2) ? columna1 : columna2;
        int menorC = (columna1 > columna2) ? columna2 : columna1;

        for (JButton icon : buttons) {
            int lineas = (int) icon.getClientProperty("linea");
            int columnas = (int) icon.getClientProperty("columna");

            if (lineas > menorF && lineas < mayorF && columnas > menorC && columnas < mayorC) {
                if (icon.getIcon() != null) { 
                    return true;
                }
            }
        }
        return false;
    }
}
