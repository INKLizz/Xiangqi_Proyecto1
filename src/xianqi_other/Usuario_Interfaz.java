/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xianqi_other;

import java.util.Calendar;

/**
 *
 * @author Laura Sabillon
 */
public interface Usuario_Interfaz {

    // GETTERS
    String getUsuario();
    String getPassword();
    int getPoints();
    boolean getActivo();
    Calendar getFecha();

    // SETTERS
    void setPassword(String password);
    void setActivo(boolean Activo);

    //FUNCION
    void addPoints();
}
