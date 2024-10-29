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
public class Usuarios implements Usuario_Interfaz {
    
    private final String usuario;
    private String password;
    private final Calendar fecha;
    private boolean Activo;
    private int puntos;

    public Usuarios(String usuario,  String password) {
        this.usuario = usuario;
        this.password = password;
        fecha = Calendar.getInstance();  
        Activo = true;
        puntos = 0;
    }
    
    @Override
    public final String getUsuario() { 
        return usuario; 
    }
    
    @Override
    public final String getPassword() { 
        return password; 
    }
    
    @Override
    public final Calendar getFecha(){
        return fecha;
    }
    
    @Override
    public final  int getPoints(){
        return puntos;
    }

    @Override
    public final boolean getActivo (){
        return Activo;
    }

    @Override
    public void setPassword(String password){
        this.password = password;
    }
    
    @Override
    public void setActivo(boolean Activo) { 
        this.Activo = Activo; 
    }

    @Override
    public void addPoints(){
        this.puntos += 3;
    }
}