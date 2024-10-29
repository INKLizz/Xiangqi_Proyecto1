/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package xianqi_other;

/**
 *
 * @author Laura Sabillon
 */
public interface Manejo_Interfaz {
    
    void Ranking();
    void History_Log(String jugador1, String jugador2, boolean gano);
    String Imprimir_Historia();
    boolean AgregarUsers(String usuario, String password);
    boolean Login(String usuario, String password);
    String getUsernameInSession();
}
