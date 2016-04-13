package edu.pcc.fueledbyjava.rankingsystem.usercreation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * This LoginPanel class provides general-purpose
 * container for lightweight components. It basically
 * create the look and feel of the GUI of the login page.
 *
 * @author BeeYean Tan
 * @version 2016-04-11
 */
public class LoginPanel
        extends JPanel
{
    private JTextField userEmailTextField;
    private JTextField passwordTextField;
    private JTextField noAccountTextField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    private JButton loginButton;
    private JButton registerButton;
    private JLabel registerLabel;
    private JLabel noAccountLabel;

    public LoginPanel()
    {
        setupPanel();
    }

    public void setupPanel()
    {
        setPreferredSize(new Dimension(380, 130));
        GridBagLayout gridBagLayoutEmail = new GridBagLayout();
        gridBagLayoutEmail.columnWidths = new int[] { 40, 40, 40, 40, 40, 40 };
        gridBagLayoutEmail.rowHeights = new int[] { 44, 40 };
        gridBagLayoutEmail.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
        gridBagLayoutEmail.rowWeights = new double[] { 0.0D, 0.0D };
        setLayout(gridBagLayoutEmail);
        this.usernameLabel = new JLabel("Email:");

        setPreferredSize(new Dimension(380, 130));
        GridBagLayout gridBagLayoutPassword = new GridBagLayout();
        gridBagLayoutPassword.columnWidths = new int[] { 40, 40, 40, 40, 40, 40 };
        gridBagLayoutPassword.rowHeights = new int[] { 44, 40 };
        gridBagLayoutPassword.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
        gridBagLayoutPassword.rowWeights = new double[] { 0.0D, 0.0D };
        setLayout(gridBagLayoutPassword);
        this.passwordLabel = new JLabel("Password:");

        setPreferredSize(new Dimension(380, 130));
        GridBagLayout gridBagLayoutNoAccount = new GridBagLayout();
        setLayout(gridBagLayoutNoAccount);
        this.noAccountLabel = new JLabel("Don't have any account?");

        GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
        gbc_usernameLabel.gridwidth = 2;
        gbc_usernameLabel.anchor = 14;
        gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
        gbc_usernameLabel.gridx = 0;
        gbc_usernameLabel.gridy = 0;
        add(this.usernameLabel, gbc_usernameLabel);
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

        GridBagConstraints gbc_noAccountLabel = new GridBagConstraints();
        gbc_noAccountLabel.gridwidth = 2;
        gbc_noAccountLabel.anchor = 14;
        gbc_noAccountLabel.insets = new Insets(0, 0, 5, 5);
        gbc_noAccountLabel.gridx = 3;
        gbc_noAccountLabel.gridy = 3;
        add(this.noAccountLabel, gbc_noAccountLabel);

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

    public void addLoginButtonActionListener(ActionListener al)
    {
        this.loginButton.addActionListener(al);
    }

    public void addUserEmailTextFieldKeyListener(KeyListener kl)
    {
        this.userEmailTextField.addKeyListener(kl);
    }

    public void addPasswordTextFieldKeyListener(KeyListener kl)
    {
        this.userEmailTextField.addKeyListener(kl);
    }

    //public void addRegisterButtonActionListener(ActionListener al)
    //{
    //  this.registerButton.addActionListener(al);
    //}

    public String getUserEmailTextField()
    {
        return this.userEmailTextField.getText();
    }

    public String getPasswordTextField()
    {
        return this.passwordTextField.getText();
    }

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
        this.userEmailTextField.setText(value);
    }
}
