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
public abstract class Piezas {

    protected int linea;
    protected int columna;
    protected ImageIcon Icon;
    protected final int[] filasRojo = {7, 8, 9};
    protected final int[] filasBlack = {0, 1, 2};
    protected final int Rio_rojo = 4;
    protected final int Rio_black = 5;

    public Piezas(ImageIcon Icon, int coordinate_x, int coordinate_y) {
        linea = coordinate_x;
        columna = coordinate_y;
        this.Icon = Icon;
    }

    public abstract boolean MovimientoValido(JButton[] buttons, int fila1, int fila2, int column1, int column2, boolean isRed);

    public boolean PiezasSalto(JButton[] buttons, int fila1, int fila2, int columna1, int columna2) {
        int mayorF = (fila1 > fila2) ? fila1 : fila2;
        int menorF = (fila1 > fila2) ? fila2 : fila1;
        int mayorC = (columna1 > columna2) ? columna1 : columna2;
        int menorC = (columna1 > columna2) ? columna2 : columna1;

        for (JButton icon : buttons) {
            int lineas = (int) icon.getClientProperty("linea");
            int columnas = (int) icon.getClientProperty("columna");

            if ((lineas < mayorF && lineas > menorF && columnas == columna1)
                    || (columnas < mayorC && columnas > menorC && lineas == fila1)) {
                if (icon.getIcon() != null) {
                    return true;

                }
            }
        }
        return false;
    }

    public boolean enPalacio(int fila, int column, int[] palaceRows) {
        for (int lineaP : palaceRows) {
            if (fila == lineaP && (column >= 3 && column <= 5)) {
                return true;
            }
        }
        return false;
    }
}
