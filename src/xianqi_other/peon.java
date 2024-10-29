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
public class peon extends Piezas {

    public peon(ImageIcon icon, int fila1, int col1) {
        super(icon, fila1, col1);
    }

    public boolean MovimientoValido(JButton[] buttons,int fila1, int fila2, int column1, int column2, boolean esRojo) {
        if (esRojo) {
            if (fila1 >= super.Rio_black) {
                return fila2 == fila1 - 1 && column1 == column2;
            } else {
                return (fila2 == fila1 - 1 && column1 == column2) || 
                       (fila2 == fila1 && (column2 - column1 == 1 || column2 - column1 == -1));
            }
        } else {
            if (fila1 <= super.Rio_rojo) {
                return fila2 == fila1 + 1 && column1 == column2;
            } else {
                return (fila2 == fila1 + 1 && column1 == column2) || 
                       (fila2 == fila1 && (column2 - column1 == 1 || column2 - column1 == -1));
            }
        }
    }
}
