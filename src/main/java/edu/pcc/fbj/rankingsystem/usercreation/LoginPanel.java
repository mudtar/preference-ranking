package main.java.edu.pcc.fbj.rankingsystem.usercreation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.*;
;

/**
 * This LoginPanel class provides general-purpose
 * container for lightweight components. It basically
 * create the look and feel of the GUI of the login page.
 * It also contains the components for the LoginFrame.
 *
 * @author BeeYean Tan
 * @version 2016-04-11
 */


public class LoginPanel
        extends JPanel
{
    private JTextField userEmailTextField;
    private JTextField passwordTextField;
    //private JPasswordField passwordTextField;
    private JTextField noAccountTextField;
    private JLabel userEmailnameLabel;
    private JLabel passwordLabel;

    private JButton loginButton;
    private JButton registerButton;
    private JLabel registerLabel;

    /**
     * Constructor of the class
     *
     */
    public LoginPanel()
    {
        setupPanel();
        setupLayout();
    }

    public void setupLayout() {}

    /**
     * Setup the JPanel components
     */
    public void setupPanel()
    {
        setPreferredSize(new Dimension(380, 130));
        GridBagLayout gridBagLayoutEmail = new GridBagLayout();
        gridBagLayoutEmail.columnWidths = new int[] { 40, 40, 40, 40, 40, 40 };
        gridBagLayoutEmail.rowHeights = new int[] { 44, 40 };
        gridBagLayoutEmail.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
        gridBagLayoutEmail.rowWeights = new double[] { 0.0D, 0.0D };
        setLayout(gridBagLayoutEmail);
        this.userEmailnameLabel = new JLabel("Email:");

        setPreferredSize(new Dimension(380, 130));
        GridBagLayout gridBagLayoutPassword = new GridBagLayout();
        gridBagLayoutPassword.columnWidths = new int[] { 40, 40, 40, 40, 40, 40 };
        gridBagLayoutPassword.rowHeights = new int[] { 44, 40 };
        gridBagLayoutPassword.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
        gridBagLayoutPassword.rowWeights = new double[] { 0.0D, 0.0D };
        setLayout(gridBagLayoutPassword);
        this.passwordLabel = new JLabel("Password:");

        GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
        gbc_usernameLabel.gridwidth = 2;
        gbc_usernameLabel.anchor = 14;
        gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
        gbc_usernameLabel.gridx = 0;
        gbc_usernameLabel.gridy = 0;
        add(this.userEmailnameLabel, gbc_usernameLabel);
        this.userEmailTextField = new JTextField("");
        GridBagConstraints gbc_userEmailTextField = new GridBagConstraints();
        gbc_userEmailTextField.anchor = 15;
        gbc_userEmailTextField.fill = 2;
        gbc_userEmailTextField.insets = new Insets(0, 0, 5, 0);
        gbc_userEmailTextField.gridwidth = 4;
        gbc_userEmailTextField.gridx = 2;
        gbc_userEmailTextField.gridy = 0;
        add(this.userEmailTextField, gbc_userEmailTextField);

        GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
        gbc_passwordLabel.gridwidth = 2;
        gbc_passwordLabel.anchor = 14;
        gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
        gbc_passwordLabel.gridx = 0;
        gbc_passwordLabel.gridy = 1;
        add(this.passwordLabel, gbc_passwordLabel);
        //this.getPasswordTextField() =  char[] new JPasswordField("");
        this.passwordTextField = new JTextField("");
        GridBagConstraints gbc_passwordTextField = new GridBagConstraints();
        gbc_passwordTextField.anchor = 15;
        gbc_passwordTextField.fill = 2;
        gbc_passwordTextField.insets = new Insets(0, 0, 5, 0);
        gbc_passwordTextField.gridwidth = 4;
        gbc_passwordTextField.gridx = 2;
        gbc_passwordTextField.gridy = 1;
        add(this.passwordTextField, gbc_passwordTextField);

        this.loginButton = new JButton("Login");
        GridBagConstraints gbc_loginButton = new GridBagConstraints();
        gbc_loginButton.anchor = 17;
        gbc_loginButton.insets = new Insets(0, 0, 0, 5);
        gbc_loginButton.gridwidth = 2;
        gbc_loginButton.gridx = 2;
        gbc_loginButton.gridy = 4;
        add(this.loginButton, gbc_loginButton);

        this.registerButton = new JButton("Register");
        GridBagConstraints gbc_btnRegister = new GridBagConstraints();
        gbc_btnRegister.anchor = 13;
        gbc_btnRegister.gridwidth = 2;
        gbc_btnRegister.gridx = 4;
        gbc_btnRegister.gridy = 4;
        add(this.registerButton, gbc_btnRegister);

        this.registerLabel = new JLabel("Register");
        this.registerLabel.setForeground(Color.BLUE);
        GridBagConstraints gbc_registerLabel = new GridBagConstraints();
        gbc_registerLabel.anchor = 11;
        gbc_registerLabel.gridwidth = 3;
        gbc_registerLabel.insets = new Insets(0, 0, 0, 5);
        gbc_registerLabel.gridx = 6;
        gbc_registerLabel.gridy = 6;
    }

    /**
     * Add the action listener to the login button
     * @param al an ActionListener
     */
    public void addLoginButtonActionListener(ActionListener al)
    {
        this.loginButton.addActionListener(al);
    }

    /**
     * Add the key event listener to capture the keyboards typed
     * @param kl an KeyListener
     */
    public void addUserEmailTextFieldKeyListener(KeyListener kl)
    {
        this.userEmailTextField.addKeyListener(kl);
    }

    public void addPasswordTextFieldKeyListener(KeyListener kl)
    {
        this.passwordTextField.addKeyListener(kl);
    }

    /**
     * Add an action listener to the register button
     * @param al an ActionListener
     */
    public void addRegisterButtonActionListener(ActionListener al)
    {
        this.registerButton.addActionListener(al);
    }

    public String getUserEmailTextField()
    {
        return this.userEmailTextField.getText();
    }

    public String getPasswordTextField()
    {
       return this.passwordTextField.getText();
    }

   // public char[] getPasswordTextField()
   // {
  //      return this.passwordTextField.getPassword();
   // }

    public void loginButtonClick()
    {
        this.loginButton.doClick();
    }

    public void setUserEmailTextField(String value)
    {
        this.userEmailTextField.setText(value);
    }

    public void setPasswordTextField(String value)
    {
        this.passwordTextField.setText(value);
    }

    public JTextField getLoginEmailControl()
    {
        return userEmailTextField;
    }

    public JTextField getLoginPasswordControl()
    {
        return passwordTextField;
    }



}