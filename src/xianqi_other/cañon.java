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
public class cañon extends Piezas {

    public cañon(ImageIcon icon, int fila1, int col1) {
        super(icon, fila1, col1);
    }

    @Override
    public boolean MovimientoValido(JButton[] buttons, int fila1, int fila2, int column1, int column2, boolean isRed) {
        if (fila1 == fila2 || column1 == column2) {
            if (PiezasSalto(buttons, fila1, fila2, column1, column2)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean PiezasSalto(JButton[] buttons, int fila1, int fila2, int columna1, int columna2) {
        int mayorF = fila1 > fila2 ? fila1 : fila2;
        int menorF = fila1 > fila2 ? fila2 : fila1;
        int mayorC = columna1 > columna2 ? columna1 : columna2;
        int menorC = columna1 > columna2 ? columna2 : columna1;

        int enMedioP = 0;

        for (JButton icon : buttons) {
            int lineas = (int) icon.getClientProperty("linea");
            int columnas = (int) icon.getClientProperty("columna");

            if (icon.getIcon() == null) {
                continue;
            }
            if ((lineas < mayorF && lineas > menorF && columnas == columna1)
                    || (columnas < mayorC && columnas > menorC && lineas == fila1)) {
                enMedioP++;
            }
        }
        if (enMedioP == 0 && (fila1 == fila2 || columna1 == columna2) && buttons[fila2 * 9 + columna2].getIcon() == null) {
            return true;
        }

        if (enMedioP == 1 && buttons[fila2 * 9 + columna2].getIcon() != null) {
            return true;
        }
        return false;
    }
}