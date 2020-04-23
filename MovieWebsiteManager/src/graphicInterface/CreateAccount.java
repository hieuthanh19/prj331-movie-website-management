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
import movieWebsiteManager.AccountException;
import movieWebsiteManager.AccountModel;
import movieWebsiteManager.ClientException;
import movieWebsiteManager.ClientModel;

/**
 *
 * @author ThanhKH
 */
public class CreateAccount extends javax.swing.JFrame {

    private static Color backgroundColor = new Color(234, 244, 237, 200);  //Color for background
    private static Color activeColor = new Color(30, 215, 96); //Color for active text fields
    LogIn li = null;
    Image appLogo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/group10_logo.png"));

    /**
     * Creates new form CreateAccount
     *
     * @param li
     */
    public CreateAccount(LogIn li) {
        initComponents();
        this.li = li;
        this.setIconImage(appLogo);
        //set vector 
        confirmPassFld.setFocusTraversalKeysEnabled(false);
        txtFldFirstName.setFocusTraversalKeysEnabled(false);
    }

    /**
     * check email name password name gander print error and input database
     *
     * @throws movieWebsiteManager.ClientException
     */
    public void doCreateAccount() throws ClientException {
        String confirmPassError = "", emailError = "", usernameError = "", passwordError2 = "", genderError = "", fullnameError = "";
        String username = txtFldUsername.getText();
        String password = passFld.getText();

        if (username.length() > 30) {
            usernameError += "Username can't be greater than 30 characters";
        }
        if (!password.matches("\\w{6,30}")) {
            passwordError2 = "Password length must be greater than 6 and not greater than 30";
        }
        String re_password = confirmPassFld.getText();
        // check Confirm Password
        if (!password.equalsIgnoreCase(re_password) || password.isEmpty()) {
            confirmPassError = "Confirm Password must match the password";
        }
        //check full name
        String fullname = txtFldFirstName.getText() + " " + txtFldLastName.getText();
        if (fullname.length() > 30) {
            fullnameError += "Name length can't be greater than 30 characters";
        }
        //check gender        
        int Gender = 1;

        if (rbtMale.isSelected()) {
            Gender = 1;
        } else if (rbtFemale.isSelected()) {
            Gender = 0;
        }
        String re_email = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

      
        String email = txtFldEmail.getText();
        // check email
      
        if (!email.matches(re_email) || email.isEmpty()) {
            emailError += "Email format incorrect\n";
        } else if (email.length() > 30) {
            email += "Email length can't greater than 30 charecters";
        }

        AccountModel am = new AccountModel();
        int acc_id = 0;
        try {
            //check if Username already exists
            if (am.isUsernameExist(username)) {
                usernameError += "Username already exists";
            }

        } catch (SQLException ex) {
            Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
        }

        //show error
        if (!usernameError.isEmpty() || !confirmPassError.isEmpty() || !emailError.isEmpty() || !fullnameError.isEmpty()) {
            String errorStr = "";
            //create error string
            if (!usernameError.isEmpty()) {
                errorStr += usernameError + "\n";
            }
            if (!confirmPassError.isEmpty()) {
                errorStr += confirmPassError + "\n";
            }
            if (!passwordError2.isEmpty()) {
                errorStr += passwordError2 + "\n";
            }
            if (!fullnameError.isEmpty()) {
                errorStr += fullnameError + "\n";
            }

            if (!genderError.isEmpty()) {
                errorStr += genderError + "\n";
            }
            if (!emailError.isEmpty()) {
                errorStr += emailError + "\n";
            }

            JOptionPane.showMessageDialog(this, errorStr);
        } else {
            try {
                //add account do DB
                acc_id = am.insert(username, re_password, 0, 1);
                if (acc_id == -1) {
                    JOptionPane.showMessageDialog(this, "An Unexpected Error has happended! Can't create account", "Create Account Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    //add client do DB
                    int c_id = -1;
                    ClientModel cm = new ClientModel();
                    //add new client to DB
                    c_id = cm.insert(fullname, email, Gender, acc_id);

                    if (c_id == -1) {
                        JOptionPane.showMessageDialog(this, "An Unexpected Error has happended! Can't create Client", "Create Client Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        //output message
                        JOptionPane.showMessageDialog(this, "Account created successfully");
                        //open main menu frame
                        MainMenu mn = new MainMenu(am.getAccount(username, password), "darkTheme");
                        mn.setVisible(true);
                        mn.setLocationRelativeTo(null);
                        this.dispose();
                    }

                }

            } catch (SQLException | AccountException ex) {
                Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        lblBack = new javax.swing.JLabel();
        lblUsernameTitle = new javax.swing.JLabel();
        txtFldUsername = new javax.swing.JTextField();
        lblPasswordTitle = new javax.swing.JLabel();
        passFld = new javax.swing.JPasswordField();
        lblConfirmPasswordTitle = new javax.swing.JLabel();
        confirmPassFld = new javax.swing.JPasswordField();
        lblNameTitle = new javax.swing.JLabel();
        txtFldFirstName = new javax.swing.JTextField();
        txtFldLastName = new javax.swing.JTextField();
        lblGenderTitle = new javax.swing.JLabel();
        rbtMale = new javax.swing.JRadioButton();
        rbtFemale = new javax.swing.JRadioButton();
        lblEmailTitle = new javax.swing.JLabel();
        txtFldEmail = new javax.swing.JTextField();
        lblCreate = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create Account");
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(875, 765));
        setResizable(false);
        getContentPane().setLayout(null);

        lblBack.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblBack.setForeground(new java.awt.Color(255, 255, 255));
        lblBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/backArrowActive.png"))); // NOI18N
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblBackMouseExited(evt);
            }
        });
        getContentPane().add(lblBack);
        lblBack.setBounds(50, 30, 30, 46);

        lblUsernameTitle.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        lblUsernameTitle.setText("Username");
        getContentPane().add(lblUsernameTitle);
        lblUsernameTitle.setBounds(250, 120, 99, 27);

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
        txtFldUsername.setBounds(250, 150, 300, 30);

        lblPasswordTitle.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        lblPasswordTitle.setText("Password");
        getContentPane().add(lblPasswordTitle);
        lblPasswordTitle.setBounds(250, 200, 97, 27);

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
        passFld.setBounds(250, 230, 300, 30);

        lblConfirmPasswordTitle.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        lblConfirmPasswordTitle.setText("Confirm Password");
        getContentPane().add(lblConfirmPasswordTitle);
        lblConfirmPasswordTitle.setBounds(250, 280, 180, 27);

        confirmPassFld.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        confirmPassFld.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                confirmPassFldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                confirmPassFldFocusLost(evt);
            }
        });
        confirmPassFld.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                confirmPassFldKeyPressed(evt);
            }
        });
        getContentPane().add(confirmPassFld);
        confirmPassFld.setBounds(250, 310, 300, 30);

        lblNameTitle.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        lblNameTitle.setText("Name");
        getContentPane().add(lblNameTitle);
        lblNameTitle.setBounds(250, 350, 59, 27);

        txtFldFirstName.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtFldFirstName.setForeground(new java.awt.Color(204, 204, 204));
        txtFldFirstName.setText("First");
        txtFldFirstName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFldFirstNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFldFirstNameFocusLost(evt);
            }
        });
        txtFldFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFldFirstNameKeyPressed(evt);
            }
        });
        getContentPane().add(txtFldFirstName);
        txtFldFirstName.setBounds(250, 380, 220, 30);

        txtFldLastName.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtFldLastName.setForeground(new java.awt.Color(204, 204, 204));
        txtFldLastName.setText("Last");
        txtFldLastName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFldLastNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFldLastNameFocusLost(evt);
            }
        });
        txtFldLastName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFldLastNameKeyPressed(evt);
            }
        });
        getContentPane().add(txtFldLastName);
        txtFldLastName.setBounds(480, 380, 150, 30);

        lblGenderTitle.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        lblGenderTitle.setText("Gender");
        getContentPane().add(lblGenderTitle);
        lblGenderTitle.setBounds(250, 420, 70, 27);

        buttonGroup1.add(rbtMale);
        rbtMale.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        rbtMale.setText("Male");
        rbtMale.setOpaque(false);
        rbtMale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rbtMaleMouseEntered(evt);
            }
        });
        getContentPane().add(rbtMale);
        rbtMale.setBounds(270, 450, 70, 31);

        buttonGroup1.add(rbtFemale);
        rbtFemale.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        rbtFemale.setText("Female");
        rbtFemale.setOpaque(false);
        rbtFemale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rbtFemaleMouseEntered(evt);
            }
        });
        getContentPane().add(rbtFemale);
        rbtFemale.setBounds(270, 480, 90, 31);

        lblEmailTitle.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        lblEmailTitle.setText("Email");
        getContentPane().add(lblEmailTitle);
        lblEmailTitle.setBounds(250, 520, 54, 27);

        txtFldEmail.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtFldEmail.setForeground(new java.awt.Color(204, 204, 204));
        txtFldEmail.setText("abc@def.gh");
        txtFldEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFldEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFldEmailFocusLost(evt);
            }
        });
        txtFldEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFldEmailKeyPressed(evt);
            }
        });
        getContentPane().add(txtFldEmail);
        txtFldEmail.setBounds(250, 550, 350, 30);

        lblCreate.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        lblCreate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/createAccount.png"))); // NOI18N
        lblCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCreateMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCreateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCreateMouseExited(evt);
            }
        });
        getContentPane().add(lblCreate);
        lblCreate.setBounds(370, 600, 128, 50);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/createAccountBackground.png"))); // NOI18N
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 880, 820);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * set knot black
     *
     * @param evt
     */
    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked
        li.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_lblBackMouseClicked

    /**
     * set knot back when hovering over it
     *
     * @param evt
     */
    private void lblBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseEntered
        Font font;
        font = lblBack.getFont();
        Map attribute = font.getAttributes();
        attribute.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);  //set text to underline on mouse hover
        lblBack.setFont(font.deriveFont(attribute));
        lblBack.setForeground(activeColor);
        lblBack.setBackground(backgroundColor);
    }//GEN-LAST:event_lblBackMouseEntered
    /**
     * set knot back when taking the mouse out of it
     *
     * @param evt
     */
    private void lblBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseExited
        Font font;
        font = lblBack.getFont();
        Map attribute = font.getAttributes();
        attribute.put(TextAttribute.UNDERLINE, -1);  //set text to normal
        lblBack.setFont(font.deriveFont(attribute));
        lblBack.setForeground(Color.BLACK);
        lblBack.setBackground(backgroundColor);
    }//GEN-LAST:event_lblBackMouseExited
    /**
     * 18/7 event = key enter then run do create account
     *
     * @param evt
     */
    private void txtFldUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFldUsernameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                doCreateAccount();
            } catch (ClientException ex) {
                Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtFldUsernameKeyPressed
    /**
     * 18/7 event = key enter then run do create account
     *
     * @param evt
     */
    private void passFldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passFldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                doCreateAccount();
            } catch (ClientException ex) {
                Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_passFldKeyPressed
    /**
     * 18/7 even = key enter then run do create account and even = key tab then
     * set text ="" , set color = black
     *
     * @param evt
     */
    private void confirmPassFldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_confirmPassFldKeyPressed
        confirmPassFld.setFocusTraversalKeysEnabled(true);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                doCreateAccount();
            } catch (ClientException ex) {
                Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtFldFirstName.setText("");
            txtFldFirstName.setForeground(Color.BLACK);
            confirmPassFld.nextFocus();
        }
    }//GEN-LAST:event_confirmPassFldKeyPressed
    /**
     * 18/7 even = key enter then run do create account and even = key tab then
     * set text ="" , set color = black
     *
     * @param evt
     */
    private void txtFldFirstNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFldFirstNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                doCreateAccount();
            } catch (ClientException ex) {
                Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (evt.getKeyCode() == evt.VK_TAB) {
            txtFldLastName.setText("");
            txtFldLastName.setForeground(Color.BLACK);
            txtFldFirstName.setFocusTraversalKeysEnabled(true);
            txtFldFirstName.nextFocus();
        }
    }//GEN-LAST:event_txtFldFirstNameKeyPressed
    /**
     * 18/7 even = key enter then run do create account
     *
     * @param evt
     */
    private void txtFldLastNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFldLastNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                doCreateAccount();
            } catch (ClientException ex) {
                Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtFldLastNameKeyPressed
    /**
     * 18/7 even = key enter then run do create account
     *
     * @param evt
     */
    private void txtFldEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFldEmailKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                doCreateAccount();
            } catch (ClientException ex) {
                Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtFldEmailKeyPressed
    /**
     * set knot click
     *
     * @param evt
     */
    private void lblCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCreateMouseClicked
        try {
            doCreateAccount();
        } catch (ClientException ex) {
            Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblCreateMouseClicked
    /**
     * change mouse into hand
     *
     * @param evt
     */
    private void rbtMaleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtMaleMouseEntered
        rbtMale.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_rbtMaleMouseEntered
    /**
     * change mouse into hand
     *
     * @param evt
     */
    private void rbtFemaleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtFemaleMouseEntered
        rbtFemale.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_rbtFemaleMouseEntered

    private void lblCreateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCreateMouseEntered
        ImageIcon lblIcon = new ImageIcon("src/img/createAccountHover.png");
        lblCreate.setIcon(lblIcon);
        lblCreate.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblCreateMouseEntered

    private void lblCreateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCreateMouseExited
        ImageIcon lblIcon = new ImageIcon("src/img/createAccount.png");
        lblCreate.setIcon(lblIcon);
    }//GEN-LAST:event_lblCreateMouseExited

    private void txtFldUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFldUsernameFocusGained
        txtFldUsername.setBorder(new LineBorder(activeColor, 2));
    }//GEN-LAST:event_txtFldUsernameFocusGained

    private void txtFldUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFldUsernameFocusLost
        txtFldUsername.setBorder(null);
    }//GEN-LAST:event_txtFldUsernameFocusLost

    private void passFldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passFldFocusGained
        passFld.setBorder(new LineBorder(activeColor, 2));
    }//GEN-LAST:event_passFldFocusGained

    private void passFldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passFldFocusLost
        passFld.setBorder(null);
    }//GEN-LAST:event_passFldFocusLost

    private void confirmPassFldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmPassFldFocusGained
        confirmPassFld.setBorder(new LineBorder(activeColor, 2));
    }//GEN-LAST:event_confirmPassFldFocusGained

    private void confirmPassFldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmPassFldFocusLost
        confirmPassFld.setBorder(null);
    }//GEN-LAST:event_confirmPassFldFocusLost

    private void txtFldFirstNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFldFirstNameFocusGained
        //if text color in text field not black -> remove text in text field
        if (!txtFldFirstName.getForeground().equals(Color.black)) {
            txtFldFirstName.setBorder(new LineBorder(activeColor, 2));
            txtFldFirstName.setText("");
            txtFldFirstName.setForeground(Color.BLACK);
        }

    }//GEN-LAST:event_txtFldFirstNameFocusGained

    private void txtFldFirstNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFldFirstNameFocusLost
        txtFldFirstName.setBorder(null);
        if (txtFldFirstName.getText().isEmpty()) {
            txtFldFirstName.setText("First");
            txtFldFirstName.setForeground(new Color(204, 204, 204));
        }

    }//GEN-LAST:event_txtFldFirstNameFocusLost

    private void txtFldLastNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFldLastNameFocusGained
        //if text color in text field not black -> remove text in text field
        if (!txtFldLastName.getForeground().equals(Color.black)) {
            txtFldLastName.setBorder(new LineBorder(activeColor, 2));
            txtFldLastName.setText("");
            txtFldLastName.setForeground(Color.BLACK);
        }


    }//GEN-LAST:event_txtFldLastNameFocusGained

    private void txtFldLastNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFldLastNameFocusLost
        txtFldLastName.setBorder(null);
        if (txtFldLastName.getText().isEmpty()) {
            txtFldLastName.setText("Last");
            txtFldLastName.setForeground(new Color(204, 204, 204));
        }

    }//GEN-LAST:event_txtFldLastNameFocusLost

    private void txtFldEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFldEmailFocusGained
        //if text color in text field not black -> remove text in text field
        if (!txtFldEmail.getForeground().equals(Color.black)) {
            txtFldEmail.setBorder(new LineBorder(activeColor, 2));
            txtFldEmail.setText("");
            txtFldEmail.setForeground(Color.BLACK);
        }

    }//GEN-LAST:event_txtFldEmailFocusGained

    private void txtFldEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFldEmailFocusLost
        txtFldEmail.setBorder(null);
        if (txtFldEmail.getText().isEmpty()) {
            txtFldEmail.setText("abc@def.gh");
            txtFldEmail.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_txtFldEmailFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPasswordField confirmPassFld;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblConfirmPasswordTitle;
    private javax.swing.JLabel lblCreate;
    private javax.swing.JLabel lblEmailTitle;
    private javax.swing.JLabel lblGenderTitle;
    private javax.swing.JLabel lblNameTitle;
    private javax.swing.JLabel lblPasswordTitle;
    private javax.swing.JLabel lblUsernameTitle;
    private javax.swing.JPasswordField passFld;
    private javax.swing.JRadioButton rbtFemale;
    private javax.swing.JRadioButton rbtMale;
    private javax.swing.JTextField txtFldEmail;
    private javax.swing.JTextField txtFldFirstName;
    private javax.swing.JTextField txtFldLastName;
    private javax.swing.JTextField txtFldUsername;
    // End of variables declaration//GEN-END:variables

}
