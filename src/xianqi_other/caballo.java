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
public class caballo extends Piezas {

    public caballo(ImageIcon icon, int fila1, int col1) {
        super(icon, fila1, col1);
    }

    @Override
    public boolean MovimientoValido(JButton[] buttons, int fila1, int fila2, int columna1, int columna2, boolean isRed) {
        if (PiezasSalto(buttons, fila1, fila2, columna1, columna2)) {
            return false;
        }
        if ((fila2 == fila1 + 2 || fila2 == fila1 - 2) && (columna2 == columna1 + 1 || columna2 == columna1 - 1)) {
            return true;
        }
        if ((columna2 == columna1 + 2 || columna2 == columna1 - 2) && (fila2 == fila1 + 1 || fila2 == fila1 - 1)) {
            return true;
        }
        return false;
    }

    public boolean PiezasSalto(JButton[] buttons, int fila1, int fila2, int columna1, int columna2) {
        int DistanciaX = columna2 - columna1; 
        int DistanciaY = fila2 - fila1; 

        boolean CaballoM = (DistanciaX == 1 && DistanciaY == 2) || (DistanciaX == -1 && DistanciaY == 2)
                || (DistanciaX == 2 && DistanciaY == 1) || (DistanciaX == -2 && DistanciaY == 1)
                || (DistanciaX == 1 && DistanciaY == -2) || (DistanciaX == -1 && DistanciaY == -2)
                || (DistanciaX == 2 && DistanciaY == -1) || (DistanciaX == -2 && DistanciaY == -1);

        if (!CaballoM) {
            return false;
        }

        int LineaAdaycente = fila1 + (DistanciaY == 2 ? 1 : (DistanciaY == -2 ? -1 : 0));
        int ColumnAdyacente = columna1 + (DistanciaX == 2 ? 1 : (DistanciaX == -2 ? -1 : 0));

        for (JButton icon : buttons) {
            if (icon.getClientProperty("linea").equals(LineaAdaycente)
                    && icon.getClientProperty("columna").equals(ColumnAdyacente)
                    && icon.getIcon() != null) {
                return true; 
            }
        }
        return false;
    }
}