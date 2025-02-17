/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package xianqi_other;

import javax.swing.JOptionPane;

/**
 *
 * @author Laura Sabillon
 */
public class Crear_Cuenta extends javax.swing.JFrame {
    Manejo_usuarios user;
    /**
     * Creates new form Crear_Cuenta
     */
    public Crear_Cuenta(Manejo_usuarios user) {
        this.user = user;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        enter = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        usuario = new javax.swing.JTextField();
        confirmacion = new javax.swing.JPasswordField();
        contraseña = new javax.swing.JPasswordField();
        showpass = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Viner Hand ITC", 1, 70)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setText("CREAR CUENTA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 610, -1));

        jLabel2.setFont(new java.awt.Font("Viner Hand ITC", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 0));
        jLabel2.setText("Usuario: ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, -1, -1));

        jLabel3.setFont(new java.awt.Font("Viner Hand ITC", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 0));
        jLabel3.setText("Confirmar Contraseña:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 500, -1, -1));

        jLabel4.setFont(new java.awt.Font("Viner Hand ITC", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 0));
        jLabel4.setText("Contrseña: ");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 400, -1, -1));

        enter.setFont(new java.awt.Font("Viner Hand ITC", 1, 24)); // NOI18N
        enter.setForeground(new java.awt.Color(255, 255, 255));
        enter.setText("ENTER");
        enter.setContentAreaFilled(false);
        enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterActionPerformed(evt);
            }
        });
        getContentPane().add(enter, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 600, 190, 60));

        exit.setFont(new java.awt.Font("Viner Hand ITC", 1, 24)); // NOI18N
        exit.setForeground(new java.awt.Color(255, 255, 255));
        exit.setText("EXIT");
        exit.setContentAreaFilled(false);
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        getContentPane().add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 600, 190, 60));

        usuario.setBackground(new java.awt.Color(102, 0, 0));
        usuario.setForeground(new java.awt.Color(255, 255, 255));
        usuario.setAutoscrolls(false);
        usuario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 0, 0), new java.awt.Color(102, 0, 0), new java.awt.Color(51, 0, 0), new java.awt.Color(51, 0, 0)));
        getContentPane().add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 300, 250, 40));

        confirmacion.setBackground(new java.awt.Color(102, 0, 0));
        confirmacion.setForeground(new java.awt.Color(255, 255, 255));
        confirmacion.setAutoscrolls(false);
        confirmacion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 0, 0), new java.awt.Color(102, 0, 0), new java.awt.Color(51, 0, 0), new java.awt.Color(51, 0, 0)));
        getContentPane().add(confirmacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 490, 250, 40));

        contraseña.setBackground(new java.awt.Color(102, 0, 0));
        contraseña.setForeground(new java.awt.Color(255, 255, 255));
        contraseña.setAutoscrolls(false);
        contraseña.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 0, 0), new java.awt.Color(102, 0, 0), new java.awt.Color(51, 0, 0), new java.awt.Color(51, 0, 0)));
        getContentPane().add(contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 400, 250, 40));

        showpass.setBackground(new java.awt.Color(204, 204, 204));
        showpass.setFont(new java.awt.Font("Viner Hand ITC", 1, 14)); // NOI18N
        showpass.setForeground(new java.awt.Color(102, 0, 0));
        showpass.setText("Mostrar Contraseña");
        showpass.setContentAreaFilled(false);
        showpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showpassActionPerformed(evt);
            }
        });
        getContentPane().add(showpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 550, 170, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/button_red.jpg"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 600, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/button_red.jpg"))); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 600, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CREAR_PARCH.jpg"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, 780, 590));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mounttttt.jpg"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-90, -310, -1, 1340));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        MAIN_MENU main = new MAIN_MENU(this.user);
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_exitActionPerformed

    private void showpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showpassActionPerformed
        // TODO add your handling code here:
        if (showpass.isSelected()){
            contraseña.setEchoChar((char)0);
            confirmacion.setEchoChar((char)0);
        }else{
            contraseña.setEchoChar('*');
            confirmacion.setEchoChar('*');
        }
    }//GEN-LAST:event_showpassActionPerformed

    private void enterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterActionPerformed
        // TODO add your handling code here:
        //FALTA DE USUARIO (Y/O) CONTRASEÑA (NO COINCIDE)
        String username = usuario.getText();
        String password = new String(contraseña.getPassword());
        String confirmarContraseña = new String(confirmacion.getPassword());

        //RESTRICCIONES DE FALTA DE INPUT
        if (username.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un usuario.");
            return; 
        } else if (password.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una contraseña.");
            return; 
        } else if (password.length() != 5) { 
            JOptionPane.showMessageDialog(null, "La contraseña debe de ser exactamente de 5 caracteres.");
            return;
        } else if (!password.equals(confirmarContraseña)) {
            JOptionPane.showMessageDialog(null, "Contraseñas no coinciden.");
            return;
        }

        //AGREGAR USUARIO / ERROR
        if (user.AgregarUsers (username ,password)) {
            JOptionPane.showMessageDialog(null, "Usuario creado exitosamente.");            
            MENU_PRINCIPAL menu = new MENU_PRINCIPAL(this.user);
            menu.setVisible(true);
            this.dispose();
        } else {
            //FALTA DE EXITO AL CREAR USUARIO
            JOptionPane.showMessageDialog(null, "Error al crear el usuario. El nombre de usuario ya existe.");
                MAIN_MENU menu = new MAIN_MENU(this.user);
                menu.setVisible(true);
                this.dispose();
        }
    }//GEN-LAST:event_enterActionPerformed

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
            java.util.logging.Logger.getLogger(Crear_Cuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Crear_Cuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Crear_Cuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Crear_Cuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        Manejo_usuarios user = new Manejo_usuarios();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Crear_Cuenta(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField confirmacion;
    private javax.swing.JPasswordField contraseña;
    private javax.swing.JButton enter;
    private javax.swing.JButton exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JCheckBox showpass;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
