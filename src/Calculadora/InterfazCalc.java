package Calculadora;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author Andres
 */
public class InterfazCalc extends javax.swing.JFrame {
    private Calculadora cal;
    
    public InterfazCalc() {
        initComponents();
        cal = new Calculadora();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtExpresion = new javax.swing.JTextField();
        btnLexico = new javax.swing.JButton();
        btnSintactico = new javax.swing.JButton();
        btnPrefijo = new javax.swing.JButton();
        btnPosfijo = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblResultado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(36, 47, 65));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtExpresion.setBackground(new java.awt.Color(35, 48, 62));
        txtExpresion.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtExpresion.setForeground(new java.awt.Color(255, 255, 255));
        txtExpresion.setText("Ingresa un valor");
        txtExpresion.setBorder(null);
        txtExpresion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExpresionActionPerformed(evt);
            }
        });
        jPanel1.add(txtExpresion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 240, 20));

        btnLexico.setBackground(new java.awt.Color(29, 40, 52));
        btnLexico.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnLexico.setForeground(new java.awt.Color(255, 255, 255));
        btnLexico.setText("Lexico");
        btnLexico.setBorderPainted(false);
        btnLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLexicoActionPerformed(evt);
            }
        });
        jPanel1.add(btnLexico, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 90, -1));
        btnLexico.getAccessibleContext().setAccessibleName("btnLexico");

        btnSintactico.setBackground(new java.awt.Color(29, 40, 52));
        btnSintactico.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSintactico.setForeground(new java.awt.Color(255, 255, 255));
        btnSintactico.setText("Sintactico");
        btnSintactico.setBorderPainted(false);
        btnSintactico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSintacticoActionPerformed(evt);
            }
        });
        jPanel1.add(btnSintactico, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 90, -1));

        btnPrefijo.setBackground(new java.awt.Color(29, 40, 52));
        btnPrefijo.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnPrefijo.setForeground(new java.awt.Color(255, 255, 255));
        btnPrefijo.setText("Prefijo");
        btnPrefijo.setBorderPainted(false);
        btnPrefijo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrefijoActionPerformed(evt);
            }
        });
        jPanel1.add(btnPrefijo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 79, -1));

        btnPosfijo.setBackground(new java.awt.Color(29, 40, 52));
        btnPosfijo.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnPosfijo.setForeground(new java.awt.Color(255, 255, 255));
        btnPosfijo.setText("Posfijo");
        btnPosfijo.setBorderPainted(false);
        btnPosfijo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPosfijoActionPerformed(evt);
            }
        });
        jPanel1.add(btnPosfijo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 79, -1));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 244, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 160));

        jPanel2.setBackground(new java.awt.Color(81, 186, 171));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Resultado ");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        lblResultado.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lblResultado.setForeground(new java.awt.Color(255, 255, 255));
        lblResultado.setText(" _____________________");
        jPanel2.add(lblResultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 200, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 260, 160));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public String getExpresion(){
        String expresion = txtExpresion.getText();
        expresion = expresion.toUpperCase();
        expresion = expresion.replace("E", "e");
        expresion = expresion.replace(" ", "");
        return expresion;
    }
    
    private void btnSintacticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSintacticoActionPerformed
        String expresion = getExpresion();
        boolean paso = cal.evaluarSintactico(expresion);
        if(paso){
            System.out.println("El resultado fue: "+cal.getResultado().getValor());
            lblResultado.setText(cal.getResultado().toString());
        }else{
            JOptionPane.showMessageDialog(null,"Error Sintáctico");
        }
    }//GEN-LAST:event_btnSintacticoActionPerformed

    private void btnPosfijoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPosfijoActionPerformed
        String expresion = getExpresion();
        boolean paso = cal.evaluarSintactico(expresion,2);
        if(paso){
            System.out.println("El resultado fue: "+cal.getResultado().getValor());
            lblResultado.setText(cal.getExpresion().toString());
        }else{
            JOptionPane.showMessageDialog(null,"Error Sintáctico");
        }
    }//GEN-LAST:event_btnPosfijoActionPerformed

    private void btnLexicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLexicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLexicoActionPerformed

    private void btnPrefijoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrefijoActionPerformed
        String expresion = getExpresion();
        boolean paso = cal.evaluarSintactico(expresion,1);
        if(paso){
            System.out.println("El resultado fue: "+cal.getResultado().getValor());
            lblResultado.setText(cal.getExpresion().toString());
        }else{
            JOptionPane.showMessageDialog(null,"Error Sintáctico");
        }
    }//GEN-LAST:event_btnPrefijoActionPerformed

    private void txtExpresionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExpresionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtExpresionActionPerformed

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
            java.util.logging.Logger.getLogger(InterfazCalc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazCalc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazCalc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazCalc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazCalc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLexico;
    private javax.swing.JButton btnPosfijo;
    private javax.swing.JButton btnPrefijo;
    private javax.swing.JButton btnSintactico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblResultado;
    private javax.swing.JTextField txtExpresion;
    // End of variables declaration//GEN-END:variables
}