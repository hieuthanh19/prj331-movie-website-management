/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicInterface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import movieWebsiteManager.Account;
import movieWebsiteManager.AccountException;
import movieWebsiteManager.AccountModel;
import movieWebsiteManager.ClientException;

/**
 *
 * @author ThanhKH
 */
public class LogIn extends javax.swing.JFrame {

    private static Color backgroundColor = new Color(234, 244, 237, 200);  //Color for background
    private static Color activeColor = new Color(30, 215, 96); //Color for active text fields
    private static Account account;
    Image appLogo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/group10_logo.png"));

    /**
     * Creates new form LogIn
     */
    public LogIn() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(appLogo);
        cbShowPass.setBackground(backgroundColor);
    }

    /**
     * Log in into account
     *
     * @throws ClientException
     */
    public void doLogIn() throws ClientException {
        String user = txtFldUsername.getText();
        String password = passFld.getText();
        AccountModel am = new AccountModel();
        try {
            account = am.getAccount(user, password);
            //check account is exist(not null) and not banned (status ==1)
            if (account != null && account.getAcc_status() == 1) {
                MainMenu mn = new MainMenu(account, "darkTheme");
                JOptionPane.showMessageDialog(this, "Login successful ");
                // open main menu frame
                mn.setVisible(true);
                mn.setLocationRelativeTo(null);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this,"Username or password is incorrect", "Login Error!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println("Error 1");
            Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccountException ex) {
            System.out.println("Error 2");
            Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
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

        lblBNT = new javax.swing.JLabel();
        lvlVersion = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblLogInTitle = new javax.swing.JLabel();
        lblUsernameTitle = new javax.swing.JLabel();
        txtFldUsername = new javax.swing.JTextField();
        lblPasswordTitle = new javax.swing.JLabel();
        passFld = new javax.swing.JPasswordField();
        lblLogIn = new javax.swing.JLabel();
        lblNoAcc = new javax.swing.JLabel();
        cbShowPass = new javax.swing.JCheckBox();
        lblCreateAcc = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Movie Website Manager");
        setPreferredSize(new java.awt.Dimension(995, 735));
        setResizable(false);
        getContentPane().setLayout(null);

        lblBNT.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblBNT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/group10_logo.png"))); // NOI18N
        lblBNT.setText("BNT Group");
        getContentPane().add(lblBNT);
        lblBNT.setBounds(630, 20, 105, 32);

        lvlVersion.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lvlVersion.setForeground(new java.awt.Color(255, 255, 255));
        lvlVersion.setText("v1.1.6");
        getContentPane().add(lvlVersion);
        lvlVersion.setBounds(20, 670, 80, 22);

        lblTitle.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 75)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("<html>\nMovie <br>Website <br>Manager\n</html>");
        getContentPane().add(lblTitle);
        lblTitle.setBounds(20, 30, 330, 290);

        lblLogInTitle.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 48)); // NOI18N
        lblLogInTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogInTitle.setText("Sign In");
        getContentPane().add(lblLogInTitle);
        lblLogInTitle.setBounds(714, 70, 170, 57);

        lblUsernameTitle.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        lblUsernameTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsernameTitle.setText("Username");
        lblUsernameTitle.setPreferredSize(new java.awt.Dimension(100, 24));
        getContentPane().add(lblUsernameTitle);
        lblUsernameTitle.setBounds(640, 150, 100, 24);

        txtFldUsername.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtFldUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30, 215, 96), 2));
        txtFldUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFldUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFldUsernameFocusLost(evt);
            }
        });
        txtFldUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFldUsernameKeyPressed(evt);
            }
        });
        getContentPane().add(txtFldUsername);
        txtFldUsername.setBounds(640, 190, 310, 30);

        lblPasswordTitle.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        lblPasswordTitle.setText("Password");
        getContentPane().add(lblPasswordTitle);
        lblPasswordTitle.setBounds(640, 240, 100, 27);

        passFld.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        passFld.setBorder(null);
        passFld.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passFldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passFldFocusLost(evt);
            }
        });
        passFld.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passFldKeyPressed(evt);
            }
        });
        getContentPane().add(passFld);
        passFld.setBounds(640, 280, 310, 30);

        lblLogIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logIn.png"))); // NOI18N
        lblLogIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogInMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLogInMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLogInMouseExited(evt);
            }
        });
        getContentPane().add(lblLogIn);
        lblLogIn.setBounds(730, 360, 128, 53);

        lblNoAcc.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblNoAcc.setText("No account?");
        getContentPane().add(lblNoAcc);
        lblNoAcc.setBounds(640, 430, 100, 22);

        cbShowPass.setBackground(new java.awt.Color(243, 247, 234));
        cbShowPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbShowPass.setText("Show Password");
        cbShowPass.setOpaque(false);
        cbShowPass.setPreferredSize(new java.awt.Dimension(130, 25));
        cbShowPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cbShowPassMouseEntered(evt);
            }
        });
        cbShowPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbShowPassActionPerformed(evt);
            }
        });
        getContentPane().add(cbShowPass);
        cbShowPass.setBounds(640, 320, 130, 25);

        lblCreateAcc.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblCreateAcc.setText("Create one?");
        lblCreateAcc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCreateAccMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCreateAccMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCreateAccMouseExited(evt);
            }
        });
        getContentPane().add(lblCreateAcc);
        lblCreateAcc.setBounds(750, 430, 97, 22);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logInBackground.png"))); // NOI18N
        lblBackground.setPreferredSize(new java.awt.Dimension(990, 700));
        getContentPane().add(lblBackground);
        lblBackground.setBounds(-10, 0, 1010, 700);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblCreateAccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCreateAccMouseClicked

        CreateAccount ca = new CreateAccount(this);
        ca.setVisible(true);
        ca.setLocationRelativeTo(null);
        this.setEnabled(false);
    }//GEN-LAST:event_lblCreateAccMouseClicked

    /**
     * show password and hidden password
     *
     * @param evt
     */
    private void cbShowPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbShowPassActionPerformed
        if (cbShowPass.isSelected()) {
            passFld.setEchoChar((char) 0);
        } else {
            passFld.setEchoChar('*');
        }
    }//GEN-LAST:event_cbShowPassActionPerformed
    /**
     * set knot back when hovering over it change mouse into hand
     *
     * @param evt
     */
    private void lblCreateAccMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCreateAccMouseEntered
        Font font;
        font = lblCreateAcc.getFont();
        Map attribute = font.getAttributes();
        attribute.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);  //set text to underline on mouse hover
        lblCreateAcc.setFont(font.deriveFont(attribute));
        lblCreateAcc.setForeground(activeColor);
        lblCreateAcc.setBackground(backgroundColor);
        lblCreateAcc.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblCreateAccMouseEntered
    /**
     *
     * set knot back when hovering over it
     *
     * @param evt
     */
    private void lblCreateAccMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCreateAccMouseExited
        Font font;
        font = lblCreateAcc.getFont();
        Map attribute = font.getAttributes();
        attribute.put(TextAttribute.UNDERLINE, -1);  //set text to normal
        lblCreateAcc.setFont(font.deriveFont(attribute));
        lblCreateAcc.setForeground(Color.BLACK);
        lblCreateAcc.setBackground(backgroundColor); 
    }//GEN-LAST:event_lblCreateAccMouseExited

    /**
     * click enter
     *
     * @param evt
     */
    private void txtFldUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFldUsernameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                doLogIn();
            } catch (ClientException ex) {
                Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtFldUsernameKeyPressed
    /**
     * click enter
     *
     * @param evt
     */
    private void passFldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passFldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                doLogIn();
            } catch (ClientException ex) {
                Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_passFldKeyPressed


    private void cbShowPassMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbShowPassMouseEntered
        cbShowPass.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_cbShowPassMouseEntered
    /**
     * click login
     *
     * @param evt
     */
    private void lblLogInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogInMouseClicked
        try {
            doLogIn();
        } catch (ClientException ex) {
            Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblLogInMouseClicked
    /**
     * change mouse into hand
     *
     * @param evt
     */
    private void lblLogInMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogInMouseEntered
        lblLogIn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ImageIcon lblIcon = new ImageIcon("src/img/logInHover.png");
        lblLogIn.setIcon(lblIcon);
    }//GEN-LAST:event_lblLogInMouseEntered
    /**
     * Effect on log in
     *
     * @param evt
     */
    private void lblLogInMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogInMouseExited
        ImageIcon lblIcon = new ImageIcon("src/img/logIn.png");
        lblLogIn.setIcon(lblIcon);
    }//GEN-LAST:event_lblLogInMouseExited
    /**
     * Effect on username
     *
     * @param evt
     */
    private void txtFldUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFldUsernameFocusGained
        txtFldUsername.setBorder(new LineBorder(activeColor, 2));
    }//GEN-LAST:event_txtFldUsernameFocusGained
    /**
     * Effect on username
     *
     * @param evt
     */
    private void txtFldUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFldUsernameFocusLost
        txtFldUsername.setBorder(null);
    }//GEN-LAST:event_txtFldUsernameFocusLost
    /**
     * Effect on password
     *
     * @param evt
     */
    private void passFldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passFldFocusGained
        passFld.setBorder(new LineBorder(activeColor, 2));
    }//GEN-LAST:event_passFldFocusGained
    /**
     * Effect on password
     *
     * @param evt
     */
    private void passFldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passFldFocusLost
        passFld.setBorder(null);
    }//GEN-LAST:event_passFldFocusLost

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbShowPass;
    private javax.swing.JLabel lblBNT;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblCreateAcc;
    private javax.swing.JLabel lblLogIn;
    private javax.swing.JLabel lblLogInTitle;
    private javax.swing.JLabel lblNoAcc;
    private javax.swing.JLabel lblPasswordTitle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsernameTitle;
    private javax.swing.JLabel lvlVersion;
    private javax.swing.JPasswordField passFld;
    private javax.swing.JTextField txtFldUsername;
    // End of variables declaration//GEN-END:variables
}
/**
 * login input username and password check username and password in database
 * print login error if user name and password no match else run main menu
 *
 * @param evt
 */