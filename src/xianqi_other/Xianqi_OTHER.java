/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package xianqi_other;

/**
 *
 * @author Laura Sabillon
 */
public class Xianqi_OTHER {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Manejo_usuarios user = new Manejo_usuarios();
        MAIN_MENU menu =  new MAIN_MENU(user);
        menu.setVisible(true);
       
    }
    
}
