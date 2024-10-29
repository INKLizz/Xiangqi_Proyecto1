/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package xianqi_other;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Laura Sabillon
 */
public class Tablero extends javax.swing.JFrame {

    private JButton[] buttons;
    private Manejo_usuarios user;
    String jugador2 = "";

    //MOVIMIENTO
    private ImageIcon piezaSeleccionado = null;
    private JButton seleccionadoBoton = null;
    private ImageIcon piezaComida = null;

    //DECLARACION DE FICHAS
    //EQUIPO BLACK
    private ImageIcon Tower = new ImageIcon(getClass().getResource("/Imagenes/tower.png"));
    private ImageIcon Horse = new ImageIcon(getClass().getResource("/Imagenes/horse.png"));
    private ImageIcon Elephant = new ImageIcon(getClass().getResource("/Imagenes/elephant.png"));
    private ImageIcon General = new ImageIcon(getClass().getResource("/Imagenes/general.png"));
    private ImageIcon Advisor = new ImageIcon(getClass().getResource("/Imagenes/advisor.png"));
    private ImageIcon Cannon = new ImageIcon(getClass().getResource("/Imagenes/cannon.png"));
    private ImageIcon Pawn = new ImageIcon(getClass().getResource("/Imagenes/pawn.png"));

    //EQUIPO RED
    private ImageIcon Tower_red = new ImageIcon(getClass().getResource("/Imagenes/Tower_Red.png"));
    private ImageIcon Horse_red = new ImageIcon(getClass().getResource("/Imagenes/Horse_Red.png"));
    private ImageIcon Elephant_red = new ImageIcon(getClass().getResource("/Imagenes/Elephant_Red.png"));
    private ImageIcon General_red = new ImageIcon(getClass().getResource("/Imagenes/General_Red.png"));
    private ImageIcon Advisor_red = new ImageIcon(getClass().getResource("/Imagenes/Advisor_Red.png"));
    private ImageIcon Cannon_red = new ImageIcon(getClass().getResource("/Imagenes/Cannon_Red.png"));
    private ImageIcon Pawn_red = new ImageIcon(getClass().getResource("/Imagenes/Pawn_Red.png"));

    //TURNO
    ImageIcon Black[] = {Tower, Horse, Elephant, General, Advisor, Cannon, Pawn};
    ImageIcon Red[] = {Tower_red, Horse_red, Elephant_red, General_red, Advisor_red, Cannon_red, Pawn_red};
    boolean turnoRed = true;
    private boolean endGame = false;

    ImageIcon Horse_F[] = {Horse, Horse_red};
    ImageIcon Tower_F[] = {Tower, Tower_red};
    ImageIcon Elephant_F[] = {Elephant, Elephant_red};
    ImageIcon General_F[] = {General, General_red};
    ImageIcon Advisor_F[] = {Advisor, Advisor_red};
    ImageIcon Cannon_F[] = {Cannon, Cannon_red};
    ImageIcon Pawn_F[] = {Pawn, Pawn_red};

    /**
     * Creates new form Tablero
     */
    public Tablero(Manejo_usuarios user, String jugador2) {
        this.user = user;
        this.jugador2 = jugador2;
        initComponents();
        Casillas();
    }

    //CASILLAS
    private void Casillas() {
        buttons = new JButton[]{
            boton1, boton2, boton3, boton4, boton5, boton6, boton7, boton8, boton9,
            boton10, boton11, boton12, boton13, boton14, boton15, boton16, boton17, boton18,
            boton19, boton20, boton21, boton22, boton23, boton24, boton25, boton26, boton27,
            boton28, boton29, boton30, boton31, boton32, boton33, boton34, boton35, boton36,
            boton37, boton38, boton39, boton40, boton41, boton42, boton43, boton44, boton45,
            boton46, boton47, boton48, boton49, boton50, boton51, boton52, boton53, boton54,
            boton55, boton56, boton57, boton58, boton59, boton60, boton61, boton62, boton63,
            boton64, boton65, boton66, boton67, boton68, boton69, boton70, boton71, boton72,
            boton73, boton74, boton75, boton76, boton77, boton78, boton79, boton80, boton81,
            boton82, boton83, boton84, boton85, boton86, boton87, boton88, boton89, boton90
        };

        for (int indice = 0; indice < buttons.length; indice++) {
            int linea = indice / 9;
            int columna = indice % 9;

            buttons[indice].putClientProperty("linea", linea);
            buttons[indice].putClientProperty("columna", columna);
        }
        for (JButton button : buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    realizarMovimiento(button);
                }
            });
        }
    }

    //MOVIMIENTOS
    private void realizarMovimiento(JButton button) {
        if (piezaSeleccionado == null && button.getIcon() != null) {
            seleccionarPieza(button);
        } else if (piezaSeleccionado != null && seleccionadoBoton == button) {
            deseleccionarPieza(button);
            resetearResaltado();
        } else if (piezaSeleccionado != null && seleccionadoBoton != button) {
            if (mismoEquipo((ImageIcon) button.getIcon())) {
                return;
            }

            if (!endGame) {
                int fila1 = (int) seleccionadoBoton.getClientProperty("linea");
                int columna1 = (int) seleccionadoBoton.getClientProperty("columna");
                int fila2 = (int) button.getClientProperty("linea");
                int columna2 = (int) button.getClientProperty("columna");

                if (validarMovimiento(piezaSeleccionado, fila1, columna1, fila2, columna2, buttons)) {
                    moverPieza(button);
                    resetearResaltado();

                    if (isCheckmate(buttons)) {
                        endGame = true;
                        MENU_PRINCIPAL menu = new MENU_PRINCIPAL(this.user);
                        menu.setVisible(true);
                        this.dispose();
                    }
                }
            }
        }
    }

    // SELECCION && DESELECCION && MOVIMIENTO
    private void seleccionarPieza(JButton button) {
        if (TurnoRojo((ImageIcon) button.getIcon()) && !turnoRed) {
            JOptionPane.showMessageDialog(null, "Es el turno del jugador 2.");
            return;
        } else if (!TurnoRojo((ImageIcon) button.getIcon()) && turnoRed) {
            JOptionPane.showMessageDialog(null, "Es el turno del jugador 1.");
            return;
        }

        piezaSeleccionado = (ImageIcon) button.getIcon();
        seleccionadoBoton = button;
        button.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED, 2));

        int fila1 = (int) button.getClientProperty("linea");
        int columna1 = (int) button.getClientProperty("columna");
        highlightValidMoves(piezaSeleccionado, fila1, columna1, buttons);
    }

    private void deseleccionarPieza(JButton button) {
        button.setBorder(null);
        piezaSeleccionado = null;
        seleccionadoBoton = null;
    }

    private void moverPieza(JButton button) {
        if (button.getIcon() != null) {
            piezaComida = (ImageIcon) button.getIcon();
            PiezaCapturada(button, turnoRed);
        }
        button.setIcon(piezaSeleccionado);
        seleccionadoBoton.setIcon(null);
        seleccionadoBoton.setBorder(null);
        piezaSeleccionado = null;
        seleccionadoBoton = null;
        turnoRed = !turnoRed;

    }

    private boolean validarMovimiento(ImageIcon piezaSeleccionada, int fila1, int columna1, int fila2, int columna2, JButton[] buttons) {

        boolean esRojo = TurnoRojo(piezaSeleccionada);
        Piezas piezaMovimiento = null;

        if (esPeon(piezaSeleccionada)) {
            piezaMovimiento = new peon(piezaSeleccionada, fila1, columna1);
        } else if (esTorre(piezaSeleccionada)) {
            piezaMovimiento = new torre(piezaSeleccionada, fila1, columna1);
        } else if (esGeneral(piezaSeleccionada)) {
            piezaMovimiento = new general(piezaSeleccionada, fila1, columna1);
        } else if (esAdvisor(piezaSeleccionada)) {
            piezaMovimiento = new advisor(piezaSeleccionada, fila1, columna1);
        } else if (esElefante(piezaSeleccionada)) {
            piezaMovimiento = new elefante(piezaSeleccionada, fila1, columna1);
        } else if (esCaballo(piezaSeleccionada)) {
            piezaMovimiento = new caballo(piezaSeleccionada, fila1, columna1);
        } else if (esCañon(piezaSeleccionada)) {
            piezaMovimiento = new cañon(piezaSeleccionada, fila1, columna1);
        }

        if (!piezaMovimiento.MovimientoValido(buttons, fila1, fila2, columna1, columna2, esRojo)) {
            return false;
        }

        if (GeneralContra()) {
            JButton origen = buttons[fila1 * 9 + columna1];
            JButton destino = buttons[fila2 * 9 + columna2];
            ImageIcon piezaAnterior = (ImageIcon) destino.getIcon();

            destino.setIcon(piezaSeleccionada);
            origen.setIcon(null);

            boolean canProtectKing = !GeneralContra();

            destino.setIcon(piezaAnterior);
            origen.setIcon(piezaSeleccionada);
            if (esGeneral(piezaSeleccionada) && GeneralContra()) {
                return false;
            }
            if (!canProtectKing) {
                return false;
            }
        }

        return true;
    }

    private void highlightValidMoves(ImageIcon piezaSeleccionada, int fila1, int columna1, JButton[] buttons) {
        boolean esRojo = TurnoRojo(piezaSeleccionada);
        Piezas piezaMovimiento = null;

        if (esPeon(piezaSeleccionada)) {
            piezaMovimiento = new peon(piezaSeleccionada, fila1, columna1);
        } else if (esTorre(piezaSeleccionada)) {
            piezaMovimiento = new torre(piezaSeleccionada, fila1, columna1);
        } else if (esGeneral(piezaSeleccionada)) {
            piezaMovimiento = new general(piezaSeleccionada, fila1, columna1);
        } else if (esAdvisor(piezaSeleccionada)) {
            piezaMovimiento = new advisor(piezaSeleccionada, fila1, columna1);
        } else if (esElefante(piezaSeleccionada)) {
            piezaMovimiento = new elefante(piezaSeleccionada, fila1, columna1);
        } else if (esCaballo(piezaSeleccionada)) {
            piezaMovimiento = new caballo(piezaSeleccionada, fila1, columna1);
        } else if (esCañon(piezaSeleccionada)) {
            piezaMovimiento = new cañon(piezaSeleccionada, fila1, columna1);
        }

        for (int fila = 0; fila < 10; fila++) {
            for (int col = 0; col < 9; col++) {
                if (validarMovimiento(piezaSeleccionada, fila1, columna1, fila, col, buttons)) {
                    JButton destino = buttons[fila * 9 + col];

                    if (destino.getIcon() == null || !mismoEquipo((ImageIcon) destino.getIcon())) {
                        destino.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
                    }
                }
            }
        }
    }

    private void resetearResaltado() {
        for (JButton button : buttons) {
            button.setBorder(null);
        }
    }

    //ES PIEZA
    private boolean esPieza(ImageIcon pieza, ImageIcon[] piezas) {
        for (ImageIcon icono : piezas) {
            if (pieza.toString().equals(icono.toString())) {
                return true;
            }
        }
        return false;
    }

    private boolean esPeon(ImageIcon pieza) {
        return esPieza(pieza, Pawn_F);
    }

    private boolean esElefante(ImageIcon pieza) {
        return esPieza(pieza, Elephant_F);
    }

    private boolean esGeneral(ImageIcon pieza) {
        return esPieza(pieza, General_F);
    }

    private boolean esCañon(ImageIcon pieza) {
        return esPieza(pieza, Cannon_F);
    }

    private boolean esCaballo(ImageIcon pieza) {
        return esPieza(pieza, Horse_F);
    }

    private boolean esAdvisor(ImageIcon pieza) {
        return esPieza(pieza, Advisor_F);
    }

    private boolean esTorre(ImageIcon pieza) {
        return esPieza(pieza, Tower_F);
    }

    //RESTRICCION DE TURNO Y EQUIPOS
    private boolean mismoEquipo(ImageIcon piece) {
        ImageIcon[] equipoActual = turnoRed ? Red : Black;

        for (ImageIcon icon : equipoActual) {
            if (piece != null && piece.toString().equals(icon.toString())) {
                return true;
            }
        }
        return false;
    }

    private void actualizarTurno() {
        if (turnoRed) {
            turnos1.setText("Jugador 1");
        } else {
            turnos1.setText("Jugador 2");
        }
    }

    private boolean TurnoRojo(ImageIcon piece) {
        if (piece == null) {
            return false;
        }
        for (ImageIcon redIcon : Red) {
            if (piece.toString().equals(redIcon.toString())) {
                return true;
            }
        }
        return false;
    }

    //ESCOGER EL GANADOR
    private JButton findKingPosition(boolean isRedTurn) {
        ImageIcon kingIcon = isRedTurn ? General_red : General;
        for (JButton button : buttons) {
            ImageIcon buttonIcon = (ImageIcon) button.getIcon();
            if (buttonIcon != null && buttonIcon.getDescription().equals(kingIcon.getDescription())) {
                return button;
            }
        }
        return null;
    }

    private boolean GeneralContra() {
        JButton redKing = findKingPosition(true);
        JButton blackKing = findKingPosition(false);

        if (redKing == null || blackKing == null) {
            return false;
        }

        int redCol = (int) redKing.getClientProperty("columna");
        int blackCol = (int) blackKing.getClientProperty("columna");

        if (redCol != blackCol) {
            return false;
        }

        int startRow = Math.min((int) redKing.getClientProperty("linea"), (int) blackKing.getClientProperty("linea")) + 1;
        int endRow = Math.max((int) redKing.getClientProperty("linea"), (int) blackKing.getClientProperty("linea"));

        return CaminoVacio(redCol, startRow, endRow);
    }

    private boolean CaminoVacio(int col, int fila1, int fila2) {
        if (fila1 >= fila2) {
            return true;
        }

        JButton button = buttons[fila1 * 9 + col];

        if (button.getIcon() != null) {
            return false;
        }

        return CaminoVacio(col, fila1 + 1, fila2);
    }

    private boolean BloquearContra(int fila1, int columna1, int fila2, int columna2, JButton[] buttons) {
        JButton origen = buttons[fila1 * 9 + columna1];
        JButton destino = buttons[fila2 * 9 + columna2];
        ImageIcon piezaSeleccionada = (ImageIcon) origen.getIcon();
        ImageIcon piezaAnterior = (ImageIcon) destino.getIcon();

        destino.setIcon(piezaSeleccionada);
        origen.setIcon(null);

        boolean canBlockCheck = false;

        if (!GeneralContra()) {
            canBlockCheck = true;
        }

        destino.setIcon(piezaAnterior);
        origen.setIcon(piezaSeleccionada);

        return canBlockCheck;
    }

    private boolean isCheckmate(JButton[] buttons) {
        if (endGame) {
            return false;
        }

        JButton redKing = findKingPosition(true);
        JButton blackKing = findKingPosition(false);

        if (redKing == null) {
            Ganador(false);
            endGame = true;
            return true;
        } else if (blackKing == null) {
            Ganador(true);
            endGame = true;
            return true;
        }

        boolean GeneralJaque = GeneralContra();

        if (GeneralJaque) {
            boolean puedeBloquear = bloquearJaque(turnoRed, buttons);

            if (!puedeBloquear) {
                Ganador(!turnoRed);
                endGame = true;
                return true;
            }
        }

        return false;
    }

    private boolean bloquearJaque(boolean esRojo, JButton[] buttons) {
        if (GeneralContra()) {

            boolean canBlock = false;
            for (int fila = 0; fila < 10; fila++) {
                for (int col = 0; col < 9; col++) {
                    if (buttons[fila * 9 + col].getIcon() != null) {
                        ImageIcon pieza = (ImageIcon) buttons[fila * 9 + col].getIcon();
                        boolean esRojoPieza = TurnoRojo(pieza);

                        if (esRojoPieza == esRojo) {
                            if (puedeMover(fila, col, pieza, buttons)) {
                                canBlock = true;
                            }
                        }
                    }
                }
            }

            if (!canBlock) {
                return false;
            }
        }
        return true;
    }

    private boolean puedeMover(int fila, int col, ImageIcon pieza, JButton[] buttons) {
        boolean esRojo = TurnoRojo(pieza);
        for (int filaDestino = 0; filaDestino < 10; filaDestino++) {
            for (int colDestino = 0; colDestino < 9; colDestino++) {
                if (validarMovimiento(pieza, fila, col, filaDestino, colDestino, buttons)) {

                    ImageIcon piezaDestino = (ImageIcon) buttons[filaDestino * 9 + colDestino].getIcon();
                    if (piezaDestino == null || TurnoRojo(piezaDestino) != esRojo) {
                        buttons[filaDestino * 9 + colDestino].setIcon(pieza);
                        buttons[fila * 9 + col].setIcon(null);

                        if (!GeneralContra()) {
                            buttons[fila * 9 + col].setIcon(pieza);
                            buttons[filaDestino * 9 + colDestino].setIcon(piezaDestino);
                            return true;
                        }

                        buttons[fila * 9 + col].setIcon(pieza);
                        buttons[filaDestino * 9 + colDestino].setIcon(piezaDestino);
                    }
                }
            }
        }
        return false;
    }

    private void Ganador(boolean esRojo) {
        Usuarios use = user.InSession();
        String username = user.getUsernameInSession();
        ArrayList<Usuarios> jugadores = user.getUsuarios();

        if (esRojo) {
            JOptionPane.showMessageDialog(null, "¡Jugador 1: " + username + " venció a Jugador 2: " + jugador2 + " ! Gano 3 puntos.");
            for (Usuarios list : jugadores) {
                if (list.getUsuario().equals(username)) {
                    list.addPoints();
                }
            }
            user.History_Log(username, jugador2, true);
        } else {
            JOptionPane.showMessageDialog(null, "¡Jugador 2: " + jugador2 + " venció a Jugador 1: " + username + " ! Gano 3 puntos.");
            for (Usuarios list : jugadores) {
                if (list.getUsuario().equals(jugador2)) {
                    list.addPoints();
                }
            }
            user.History_Log(username, jugador2, false);
        }
    }

    private void PiezaCapturada(JButton boton, boolean isRed) {
        if (!isRed) {
            JLabel label = new JLabel();
            label.setIcon(piezaComida);
            eaten_RedI.add(label);

            eaten_RedI.setLayout(new FlowLayout());

            eaten_RedI.revalidate();
            eaten_RedI.repaint();
        } else {
            JLabel label = new JLabel();
            label.setIcon(piezaComida);
            eaten_Black.add(label);

            eaten_Black.setLayout(new FlowLayout());

            eaten_Black.revalidate();
            eaten_Black.repaint();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boton2 = new javax.swing.JButton();
        boton3 = new javax.swing.JButton();
        boton5 = new javax.swing.JButton();
        boton1 = new javax.swing.JButton();
        boton4 = new javax.swing.JButton();
        boton11 = new javax.swing.JButton();
        boton12 = new javax.swing.JButton();
        boton14 = new javax.swing.JButton();
        boton10 = new javax.swing.JButton();
        boton13 = new javax.swing.JButton();
        boton20 = new javax.swing.JButton();
        boton21 = new javax.swing.JButton();
        boton23 = new javax.swing.JButton();
        boton19 = new javax.swing.JButton();
        boton22 = new javax.swing.JButton();
        boton29 = new javax.swing.JButton();
        boton30 = new javax.swing.JButton();
        boton32 = new javax.swing.JButton();
        boton28 = new javax.swing.JButton();
        boton31 = new javax.swing.JButton();
        boton38 = new javax.swing.JButton();
        boton39 = new javax.swing.JButton();
        boton41 = new javax.swing.JButton();
        boton37 = new javax.swing.JButton();
        boton40 = new javax.swing.JButton();
        boton7 = new javax.swing.JButton();
        boton8 = new javax.swing.JButton();
        boton6 = new javax.swing.JButton();
        boton9 = new javax.swing.JButton();
        boton16 = new javax.swing.JButton();
        boton17 = new javax.swing.JButton();
        boton15 = new javax.swing.JButton();
        boton18 = new javax.swing.JButton();
        boton25 = new javax.swing.JButton();
        boton26 = new javax.swing.JButton();
        boton24 = new javax.swing.JButton();
        boton27 = new javax.swing.JButton();
        boton34 = new javax.swing.JButton();
        boton35 = new javax.swing.JButton();
        boton33 = new javax.swing.JButton();
        boton36 = new javax.swing.JButton();
        boton43 = new javax.swing.JButton();
        boton44 = new javax.swing.JButton();
        boton42 = new javax.swing.JButton();
        boton45 = new javax.swing.JButton();
        boton47 = new javax.swing.JButton();
        boton48 = new javax.swing.JButton();
        boton50 = new javax.swing.JButton();
        boton46 = new javax.swing.JButton();
        boton49 = new javax.swing.JButton();
        boton56 = new javax.swing.JButton();
        boton57 = new javax.swing.JButton();
        boton59 = new javax.swing.JButton();
        boton55 = new javax.swing.JButton();
        boton58 = new javax.swing.JButton();
        boton65 = new javax.swing.JButton();
        boton66 = new javax.swing.JButton();
        boton68 = new javax.swing.JButton();
        boton64 = new javax.swing.JButton();
        boton67 = new javax.swing.JButton();
        boton74 = new javax.swing.JButton();
        boton75 = new javax.swing.JButton();
        boton77 = new javax.swing.JButton();
        boton73 = new javax.swing.JButton();
        boton76 = new javax.swing.JButton();
        boton83 = new javax.swing.JButton();
        boton84 = new javax.swing.JButton();
        boton86 = new javax.swing.JButton();
        boton82 = new javax.swing.JButton();
        boton85 = new javax.swing.JButton();
        boton52 = new javax.swing.JButton();
        boton53 = new javax.swing.JButton();
        boton51 = new javax.swing.JButton();
        boton54 = new javax.swing.JButton();
        boton61 = new javax.swing.JButton();
        boton62 = new javax.swing.JButton();
        boton60 = new javax.swing.JButton();
        boton63 = new javax.swing.JButton();
        boton70 = new javax.swing.JButton();
        boton71 = new javax.swing.JButton();
        boton69 = new javax.swing.JButton();
        boton72 = new javax.swing.JButton();
        boton79 = new javax.swing.JButton();
        boton80 = new javax.swing.JButton();
        boton78 = new javax.swing.JButton();
        boton81 = new javax.swing.JButton();
        boton88 = new javax.swing.JButton();
        boton89 = new javax.swing.JButton();
        boton87 = new javax.swing.JButton();
        boton90 = new javax.swing.JButton();
        turnos = new javax.swing.JLabel();
        jugador1 = new javax.swing.JLabel();
        RETIRAR = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        player2 = new javax.swing.JLabel();
        turnos1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        eaten_RedI = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        eaten_Black = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        boton2.setBackground(new java.awt.Color(255, 255, 255));
        boton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/horse.png"))); // NOI18N
        getContentPane().add(boton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, 65, 65));

        boton3.setBackground(new java.awt.Color(255, 255, 255));
        boton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/elephant.png"))); // NOI18N
        getContentPane().add(boton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 65, 65));

        boton5.setBackground(new java.awt.Color(204, 102, 0));
        boton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/general.png"))); // NOI18N
        boton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton5ActionPerformed(evt);
            }
        });
        getContentPane().add(boton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, 65, 65));

        boton1.setBackground(new java.awt.Color(255, 255, 255));
        boton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tower.png"))); // NOI18N
        boton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton1ActionPerformed(evt);
            }
        });
        getContentPane().add(boton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 65, 65));

        boton4.setBackground(new java.awt.Color(204, 102, 0));
        boton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/advisor.png"))); // NOI18N
        boton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton4ActionPerformed(evt);
            }
        });
        getContentPane().add(boton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, 65, 65));

        boton11.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 65, 65));

        boton12.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 120, 65, 65));

        boton14.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 65, 65));

        boton10.setBackground(new java.awt.Color(255, 255, 255));
        boton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton10ActionPerformed(evt);
            }
        });
        getContentPane().add(boton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, 65, 65));

        boton13.setBackground(new java.awt.Color(204, 102, 0));
        boton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton13ActionPerformed(evt);
            }
        });
        getContentPane().add(boton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 120, 65, 65));

        boton20.setBackground(new java.awt.Color(255, 255, 255));
        boton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cannon.png"))); // NOI18N
        getContentPane().add(boton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, 65, 65));

        boton21.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 65, 65));

        boton23.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 190, 65, 65));

        boton19.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 190, 65, 65));

        boton22.setBackground(new java.awt.Color(204, 102, 0));
        boton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton22ActionPerformed(evt);
            }
        });
        getContentPane().add(boton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 190, 65, 65));

        boton29.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, 65, 65));

        boton30.setBackground(new java.awt.Color(255, 255, 255));
        boton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pawn.png"))); // NOI18N
        getContentPane().add(boton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 260, 65, 65));

        boton32.setBackground(new java.awt.Color(255, 255, 255));
        boton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pawn.png"))); // NOI18N
        getContentPane().add(boton32, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 260, 65, 65));

        boton28.setBackground(new java.awt.Color(255, 255, 255));
        boton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pawn.png"))); // NOI18N
        getContentPane().add(boton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, 65, 65));

        boton31.setBackground(new java.awt.Color(255, 255, 255));
        boton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton31ActionPerformed(evt);
            }
        });
        getContentPane().add(boton31, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, 65, 65));

        boton38.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton38, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 330, 65, 65));

        boton39.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton39, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 330, 65, 65));

        boton41.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton41, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, 65, 65));

        boton37.setBackground(new java.awt.Color(255, 255, 255));
        boton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton37ActionPerformed(evt);
            }
        });
        getContentPane().add(boton37, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 330, 65, 65));

        boton40.setBackground(new java.awt.Color(255, 255, 255));
        boton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton40ActionPerformed(evt);
            }
        });
        getContentPane().add(boton40, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 330, 65, 65));

        boton7.setBackground(new java.awt.Color(255, 255, 255));
        boton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/elephant.png"))); // NOI18N
        getContentPane().add(boton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 50, 65, 65));

        boton8.setBackground(new java.awt.Color(255, 255, 255));
        boton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/horse.png"))); // NOI18N
        getContentPane().add(boton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 50, 65, 65));

        boton6.setBackground(new java.awt.Color(204, 102, 0));
        boton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/advisor.png"))); // NOI18N
        boton6.setToolTipText("");
        boton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton6ActionPerformed(evt);
            }
        });
        getContentPane().add(boton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 50, 65, 65));

        boton9.setBackground(new java.awt.Color(255, 255, 255));
        boton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tower.png"))); // NOI18N
        boton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton9ActionPerformed(evt);
            }
        });
        getContentPane().add(boton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 50, 65, 65));

        boton16.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 120, 65, 65));

        boton17.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 120, 65, 65));

        boton15.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 120, 65, 65));

        boton18.setBackground(new java.awt.Color(255, 255, 255));
        boton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton18ActionPerformed(evt);
            }
        });
        getContentPane().add(boton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 120, 65, 65));

        boton25.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 190, 65, 65));

        boton26.setBackground(new java.awt.Color(255, 255, 255));
        boton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cannon.png"))); // NOI18N
        getContentPane().add(boton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 190, 65, 65));

        boton24.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 190, 65, 65));

        boton27.setBackground(new java.awt.Color(255, 255, 255));
        boton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton27ActionPerformed(evt);
            }
        });
        getContentPane().add(boton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 190, 65, 65));

        boton34.setBackground(new java.awt.Color(255, 255, 255));
        boton34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pawn.png"))); // NOI18N
        getContentPane().add(boton34, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 260, 65, 65));

        boton35.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton35, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 260, 65, 65));

        boton33.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton33, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 260, 65, 65));

        boton36.setBackground(new java.awt.Color(255, 255, 255));
        boton36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pawn.png"))); // NOI18N
        boton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton36ActionPerformed(evt);
            }
        });
        getContentPane().add(boton36, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 260, 65, 65));

        boton43.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton43, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 330, 65, 65));

        boton44.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton44, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 330, 65, 65));

        boton42.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton42, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 330, 65, 65));

        boton45.setBackground(new java.awt.Color(255, 255, 255));
        boton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton45ActionPerformed(evt);
            }
        });
        getContentPane().add(boton45, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 330, 65, 65));

        boton47.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton47, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, 65, 65));

        boton48.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton48, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 420, 65, 65));

        boton50.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton50, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 420, 65, 65));

        boton46.setBackground(new java.awt.Color(204, 102, 0));
        boton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton46ActionPerformed(evt);
            }
        });
        getContentPane().add(boton46, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 420, 65, 65));

        boton49.setBackground(new java.awt.Color(204, 102, 0));
        boton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton49ActionPerformed(evt);
            }
        });
        getContentPane().add(boton49, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 420, 65, 65));

        boton56.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton56, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 490, 65, 65));

        boton57.setBackground(new java.awt.Color(204, 102, 0));
        boton57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pawn_Red.png"))); // NOI18N
        getContentPane().add(boton57, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 490, 65, 65));

        boton59.setBackground(new java.awt.Color(204, 102, 0));
        boton59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pawn_Red.png"))); // NOI18N
        getContentPane().add(boton59, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 490, 65, 65));

        boton55.setBackground(new java.awt.Color(204, 102, 0));
        boton55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pawn_Red.png"))); // NOI18N
        getContentPane().add(boton55, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 490, 65, 65));

        boton58.setBackground(new java.awt.Color(204, 102, 0));
        boton58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton58ActionPerformed(evt);
            }
        });
        getContentPane().add(boton58, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 490, 65, 65));

        boton65.setBackground(new java.awt.Color(204, 102, 0));
        boton65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cannon_Red.png"))); // NOI18N
        getContentPane().add(boton65, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 560, 65, 65));

        boton66.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton66, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 560, 65, 65));

        boton68.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton68, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 560, 65, 65));

        boton64.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton64, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 560, 65, 65));

        boton67.setBackground(new java.awt.Color(255, 255, 255));
        boton67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton67ActionPerformed(evt);
            }
        });
        getContentPane().add(boton67, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 560, 65, 65));

        boton74.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton74, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 630, 65, 65));

        boton75.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton75, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 630, 65, 65));

        boton77.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton77, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 630, 65, 65));

        boton73.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton73, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 630, 65, 65));

        boton76.setBackground(new java.awt.Color(255, 255, 255));
        boton76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton76ActionPerformed(evt);
            }
        });
        getContentPane().add(boton76, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 630, 65, 65));

        boton83.setBackground(new java.awt.Color(204, 102, 0));
        boton83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Horse_Red.png"))); // NOI18N
        getContentPane().add(boton83, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 700, 65, 65));

        boton84.setBackground(new java.awt.Color(204, 102, 0));
        boton84.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Elephant_Red.png"))); // NOI18N
        getContentPane().add(boton84, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 700, 65, 65));

        boton86.setBackground(new java.awt.Color(255, 255, 255));
        boton86.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/General_Red.png"))); // NOI18N
        boton86.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton86ActionPerformed(evt);
            }
        });
        getContentPane().add(boton86, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 700, 65, 65));

        boton82.setBackground(new java.awt.Color(204, 102, 0));
        boton82.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Tower_Red.png"))); // NOI18N
        getContentPane().add(boton82, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 700, 65, 65));

        boton85.setBackground(new java.awt.Color(255, 255, 255));
        boton85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Advisor_Red.png"))); // NOI18N
        boton85.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton85ActionPerformed(evt);
            }
        });
        getContentPane().add(boton85, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 700, 65, 65));

        boton52.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton52, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 420, 65, 65));

        boton53.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton53, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 420, 65, 65));

        boton51.setBackground(new java.awt.Color(204, 102, 0));
        boton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton51ActionPerformed(evt);
            }
        });
        getContentPane().add(boton51, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 420, 65, 65));

        boton54.setBackground(new java.awt.Color(204, 102, 0));
        boton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton54ActionPerformed(evt);
            }
        });
        getContentPane().add(boton54, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 420, 65, 65));

        boton61.setBackground(new java.awt.Color(204, 102, 0));
        boton61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pawn_Red.png"))); // NOI18N
        getContentPane().add(boton61, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 490, 65, 65));

        boton62.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton62, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 490, 65, 65));

        boton60.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton60, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 490, 65, 65));

        boton63.setBackground(new java.awt.Color(204, 102, 0));
        boton63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pawn_Red.png"))); // NOI18N
        boton63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton63ActionPerformed(evt);
            }
        });
        getContentPane().add(boton63, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 490, 65, 65));

        boton70.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton70, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 560, 65, 65));

        boton71.setBackground(new java.awt.Color(204, 102, 0));
        boton71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cannon_Red.png"))); // NOI18N
        getContentPane().add(boton71, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 560, 65, 65));

        boton69.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton69, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 560, 65, 65));

        boton72.setBackground(new java.awt.Color(204, 102, 0));
        boton72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton72ActionPerformed(evt);
            }
        });
        getContentPane().add(boton72, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 560, 65, 65));

        boton79.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton79, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 630, 65, 65));

        boton80.setBackground(new java.awt.Color(204, 102, 0));
        getContentPane().add(boton80, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 630, 65, 65));

        boton78.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(boton78, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 630, 65, 65));

        boton81.setBackground(new java.awt.Color(204, 102, 0));
        boton81.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton81ActionPerformed(evt);
            }
        });
        getContentPane().add(boton81, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 630, 65, 65));

        boton88.setBackground(new java.awt.Color(204, 102, 0));
        boton88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Elephant_Red.png"))); // NOI18N
        getContentPane().add(boton88, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 700, 65, 65));

        boton89.setBackground(new java.awt.Color(204, 102, 0));
        boton89.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Horse_Red.png"))); // NOI18N
        getContentPane().add(boton89, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 700, 65, 65));

        boton87.setBackground(new java.awt.Color(255, 255, 255));
        boton87.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Advisor_Red.png"))); // NOI18N
        getContentPane().add(boton87, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 700, 65, 65));

        boton90.setBackground(new java.awt.Color(204, 102, 0));
        boton90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Tower_Red.png"))); // NOI18N
        boton90.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton90ActionPerformed(evt);
            }
        });
        getContentPane().add(boton90, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 700, 65, 65));

        turnos.setBackground(new java.awt.Color(0, 0, 0));
        turnos.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        turnos.setForeground(new java.awt.Color(255, 255, 255));
        turnos.setText("TURNO DE JUGADOR:");
        turnos.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                turnosAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(turnos, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 210, 60));

        jugador1.setBackground(new java.awt.Color(0, 0, 0));
        jugador1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jugador1.setForeground(new java.awt.Color(0, 0, 0));
        jugador1.setText("Jugador 1 : ");
        jugador1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jugador1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(jugador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 410, 350, 60));

        RETIRAR.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        RETIRAR.setForeground(new java.awt.Color(255, 255, 255));
        RETIRAR.setText("BOTON DE RETIRAR");
        RETIRAR.setContentAreaFilled(false);
        RETIRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RETIRARActionPerformed(evt);
            }
        });
        getContentPane().add(RETIRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 240, 60));

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 400, 620, 10));

        player2.setBackground(new java.awt.Color(0, 0, 0));
        player2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        player2.setForeground(new java.awt.Color(0, 0, 0));
        player2.setText("Jugador 2 : ");
        player2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                player2AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(player2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 40, 320, 60));

        turnos1.setBackground(new java.awt.Color(0, 0, 0));
        turnos1.setFont(new java.awt.Font("Viner Hand ITC", 1, 24)); // NOI18N
        turnos1.setForeground(new java.awt.Color(255, 255, 255));
        turnos1.setText("  Jugador 1");
        turnos1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                turnos1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(turnos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, 150, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/button_yellow2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/button_yellow2.jpg"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, -1, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/button_yellow2.jpg"))); // NOI18N
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 240, -1));

        eaten_RedI.setOpaque(false);
        eaten_RedI.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                eaten_RedIAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout eaten_RedILayout = new javax.swing.GroupLayout(eaten_RedI);
        eaten_RedI.setLayout(eaten_RedILayout);
        eaten_RedILayout.setHorizontalGroup(
            eaten_RedILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        eaten_RedILayout.setVerticalGroup(
            eaten_RedILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );

        getContentPane().add(eaten_RedI, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 110, 390, 260));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/file.jpg"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 90, 440, -1));

        eaten_Black.setOpaque(false);

        javax.swing.GroupLayout eaten_BlackLayout = new javax.swing.GroupLayout(eaten_Black);
        eaten_Black.setLayout(eaten_BlackLayout);
        eaten_BlackLayout.setHorizontalGroup(
            eaten_BlackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        eaten_BlackLayout.setVerticalGroup(
            eaten_BlackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );

        getContentPane().add(eaten_Black, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 480, 400, 280));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/file.jpg"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 470, 430, 300));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/koi_fish.jpg"))); // NOI18N
        jLabel6.setOpaque(true);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1670, 900));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton4ActionPerformed

    private void boton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton13ActionPerformed

    private void boton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton22ActionPerformed

    private void boton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton31ActionPerformed

    private void boton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton40ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton40ActionPerformed

    private void boton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton9ActionPerformed

    private void boton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton18ActionPerformed

    private void boton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton27ActionPerformed

    private void boton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton36ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton36ActionPerformed

    private void boton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton45ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton45ActionPerformed

    private void boton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton6ActionPerformed

    private void boton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton49ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton49ActionPerformed

    private void boton58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton58ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton58ActionPerformed

    private void boton67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton67ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton67ActionPerformed

    private void boton76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton76ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton76ActionPerformed

    private void boton85ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton85ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton85ActionPerformed

    private void boton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton51ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton51ActionPerformed

    private void boton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton54ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton54ActionPerformed

    private void boton63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton63ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton63ActionPerformed

    private void boton72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton72ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton72ActionPerformed

    private void boton81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton81ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton81ActionPerformed

    private void boton90ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton90ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton90ActionPerformed

    private void RETIRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RETIRARActionPerformed
        // TODO add your handling code here:
        Usuarios use = user.InSession();
        String username = user.getUsernameInSession();
        ArrayList< Usuarios> jugadores = user.getUsuarios();

        int option = JOptionPane.showConfirmDialog(null, "Desea Retirarse del juego? ", "RETIRAR", JOptionPane.YES_NO_OPTION);
        if (turnoRed && option == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Jugador 2" + jugador2 + " vencio a " + username + " gano 3 puntos");
            for (Usuarios list : jugadores) {
                if (list.getUsuario().equals(jugador2)) {
                    list.addPoints();
                }
            }
            user.History_Log(username , jugador2, false);
            MENU_PRINCIPAL main = new MENU_PRINCIPAL(this.user);
            main.setVisible(true);
            this.dispose();

        } else if (!turnoRed && option == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null ,"Jugador 1 : " + username + " vencio a " + jugador2 + " gano 3 puntos");
            for (Usuarios list : jugadores) {
                if (list.getUsuario().equals(username)) {
                    list.addPoints();
                }
            }
            user.History_Log(username , jugador2, true);
            MENU_PRINCIPAL main = new MENU_PRINCIPAL(this.user);
            main.setVisible(true);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(null, "Se cancelo el retiro.");
        }
    }//GEN-LAST:event_RETIRARActionPerformed

    private void turnosAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_turnosAncestorAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_turnosAncestorAdded

    private void turnos1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_turnos1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_turnos1AncestorAdded

    private void jugador1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jugador1AncestorAdded
        // TODO add your handling code here:
        String usuario = user.getUsernameInSession();
        if (usuario != null) {
            jugador1.setText("Jugador 1: " + usuario);
        } else {
            jugador1.setText("NoN");
        }
    }//GEN-LAST:event_jugador1AncestorAdded

    private void player2AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_player2AncestorAdded
        // TODO add your handling code here:
        if (jugador2 != null) {
            player2.setText("Jugador 2: " + jugador2);
        } else {
            player2.setText("NoN");
        }
    }//GEN-LAST:event_player2AncestorAdded

    private void boton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton10ActionPerformed

    private void boton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton37ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton37ActionPerformed

    private void boton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton46ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton46ActionPerformed

    private void boton86ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton86ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton86ActionPerformed

    private void boton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton1ActionPerformed

    private void boton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton5ActionPerformed

    private void eaten_RedIAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_eaten_RedIAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_eaten_RedIAncestorAdded

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        Manejo_usuarios user = new Manejo_usuarios();
        String jugador2 = "";

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tablero(user, jugador2).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RETIRAR;
    private javax.swing.JButton boton1;
    private javax.swing.JButton boton10;
    private javax.swing.JButton boton11;
    private javax.swing.JButton boton12;
    private javax.swing.JButton boton13;
    private javax.swing.JButton boton14;
    private javax.swing.JButton boton15;
    private javax.swing.JButton boton16;
    private javax.swing.JButton boton17;
    private javax.swing.JButton boton18;
    private javax.swing.JButton boton19;
    private javax.swing.JButton boton2;
    private javax.swing.JButton boton20;
    private javax.swing.JButton boton21;
    private javax.swing.JButton boton22;
    private javax.swing.JButton boton23;
    private javax.swing.JButton boton24;
    private javax.swing.JButton boton25;
    private javax.swing.JButton boton26;
    private javax.swing.JButton boton27;
    private javax.swing.JButton boton28;
    private javax.swing.JButton boton29;
    private javax.swing.JButton boton3;
    private javax.swing.JButton boton30;
    private javax.swing.JButton boton31;
    private javax.swing.JButton boton32;
    private javax.swing.JButton boton33;
    private javax.swing.JButton boton34;
    private javax.swing.JButton boton35;
    private javax.swing.JButton boton36;
    private javax.swing.JButton boton37;
    private javax.swing.JButton boton38;
    private javax.swing.JButton boton39;
    private javax.swing.JButton boton4;
    private javax.swing.JButton boton40;
    private javax.swing.JButton boton41;
    private javax.swing.JButton boton42;
    private javax.swing.JButton boton43;
    private javax.swing.JButton boton44;
    private javax.swing.JButton boton45;
    private javax.swing.JButton boton46;
    private javax.swing.JButton boton47;
    private javax.swing.JButton boton48;
    private javax.swing.JButton boton49;
    private javax.swing.JButton boton5;
    private javax.swing.JButton boton50;
    private javax.swing.JButton boton51;
    private javax.swing.JButton boton52;
    private javax.swing.JButton boton53;
    private javax.swing.JButton boton54;
    private javax.swing.JButton boton55;
    private javax.swing.JButton boton56;
    private javax.swing.JButton boton57;
    private javax.swing.JButton boton58;
    private javax.swing.JButton boton59;
    private javax.swing.JButton boton6;
    private javax.swing.JButton boton60;
    private javax.swing.JButton boton61;
    private javax.swing.JButton boton62;
    private javax.swing.JButton boton63;
    private javax.swing.JButton boton64;
    private javax.swing.JButton boton65;
    private javax.swing.JButton boton66;
    private javax.swing.JButton boton67;
    private javax.swing.JButton boton68;
    private javax.swing.JButton boton69;
    private javax.swing.JButton boton7;
    private javax.swing.JButton boton70;
    private javax.swing.JButton boton71;
    private javax.swing.JButton boton72;
    private javax.swing.JButton boton73;
    private javax.swing.JButton boton74;
    private javax.swing.JButton boton75;
    private javax.swing.JButton boton76;
    private javax.swing.JButton boton77;
    private javax.swing.JButton boton78;
    private javax.swing.JButton boton79;
    private javax.swing.JButton boton8;
    private javax.swing.JButton boton80;
    private javax.swing.JButton boton81;
    private javax.swing.JButton boton82;
    private javax.swing.JButton boton83;
    private javax.swing.JButton boton84;
    private javax.swing.JButton boton85;
    private javax.swing.JButton boton86;
    private javax.swing.JButton boton87;
    private javax.swing.JButton boton88;
    private javax.swing.JButton boton89;
    private javax.swing.JButton boton9;
    private javax.swing.JButton boton90;
    private javax.swing.JPanel eaten_Black;
    private javax.swing.JPanel eaten_RedI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jugador1;
    private javax.swing.JLabel player2;
    private javax.swing.JLabel turnos;
    private javax.swing.JLabel turnos1;
    // End of variables declaration//GEN-END:variables
}
