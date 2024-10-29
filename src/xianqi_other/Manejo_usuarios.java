/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xianqi_other;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Laura Sabillon
 */
public final class Manejo_usuarios implements Manejo_Interfaz {

    private ArrayList< Usuarios> usuarios;
    private ArrayList<String> Log;
    private ArrayList<String> Ranking;

    public Manejo_usuarios() {
        usuarios = new ArrayList<>();
        Log = new ArrayList<>();
        Ranking = new ArrayList<>();
    }

    public Usuarios Buscar(String usuario) {
        for (Usuarios use : usuarios) {
            if (use != null && use.getUsuario().equals(usuario)) {
                return use;
            }
        }
        return null;
    }

    public boolean AgregarUsers(String usuario, String password) {
        if (Buscar(usuario) == null) {
            usuarios.add(new Usuarios(usuario, password));
            return true;
        }
        return false;
    }

    public boolean Login(String usuario, String password) {
        Usuarios user = Buscar(usuario);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                user.setActivo(true);
                return true;
            }
        }
        return false;
    }

    public String getUsernameInSession() {
        for (Usuarios indice : usuarios) {
            if (indice != null && indice.getActivo()) {
                return indice.getUsuario();
            }
        }
        return null;
    }

    public Usuarios InSession() {
        for (Usuarios indice : usuarios) {
            if (indice != null && indice.getActivo()) {
                return indice;
            }
        }
        return null;
    }

    public ArrayList<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void Ranking() {
        Ranking.clear();
        OrdenarRank(0);
        int lugarActual = 1;

        for (int indice = 0; indice < usuarios.size(); indice++) {
            Usuarios user = usuarios.get(indice);
            int points = user.getPoints();
            String lugar = lugarActual + ".- " + user.getUsuario() + " : " + points + " puntos";
            Ranking.add(lugar);

            while (indice + 1 < usuarios.size() && usuarios.get(indice + 1).getPoints() == points) {
                indice++;
                lugar += ", " + usuarios.get(indice).getUsuario() + " - " + points + " puntos";
            }
            Ranking.set(Ranking.size() - 1, lugar);
            lugarActual++;
        }
    }

    private void OrdenarRank(int index) {
        if (index >= usuarios.size() - 1) {
            return;
        }

        for (int i = 0; i < usuarios.size() - 1 - index; i++) {
            Usuarios primero = usuarios.get(i);
            Usuarios segundo = usuarios.get(i + 1);

            if (primero.getPoints() < segundo.getPoints()) {
                usuarios.set(i, segundo);
                usuarios.set(i + 1, primero);
            }
        }

        OrdenarRank(index + 1);
    }

    public void History_Log(String jugador1, String jugador2, boolean gano) {
        refrescarLog();

        String currentUser = getUsernameInSession();
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String fechaString = dateFormat.format(fecha.getTime());

        String resultado;

        if (currentUser.equals(jugador1)) {
            resultado = "Fecha: " + fechaString + " | Jugador: " + jugador1
                    + (gano ? " vencio a " : " perdio contra ") + "Jugador: " + jugador2;
        } else if (currentUser.equals(jugador2)) {
            resultado = "Fecha: " + fechaString + " | Jugador: " + jugador2
                    + (gano ? " vencio a " : " perdio contra ") + "Jugador: " + jugador1;
        } else {
            return;
        }

        Log.add(0, resultado);
    }

    public String Imprimir_Historia() {
        StringBuilder resultado = new StringBuilder();
        String currentUser = getUsernameInSession();

        refrescarLog();

        for (String contenido : Log) {
            String jugador2 = ExisteJugador2(contenido);

            if (Buscar(jugador2) == null) {
                continue;
            }

            if (contenido.contains(currentUser)) {
                resultado.append(contenido).append("\n");
            }
        }

        return resultado.length() > 0 ? resultado.toString() : "No hay historial de partidas para este usuario.";
    }

    private void refrescarLog() {
        for (int indice = 0; indice < Log.size(); indice++) {
            String contenido = Log.get(indice);
            String jugador2 = ExisteJugador2(contenido);

            if (Buscar(jugador2) == null) {
                Log.remove(indice);
                indice--;
            }
        }
    }

    private String ExisteJugador2(String contenido) {
        int index = contenido.lastIndexOf("Jugador: ");
        return (index != -1) ? contenido.substring(index + 9).trim() : "";
    }

    public ArrayList<String> getRanking() {
        return Ranking;
    }
}
