package UserCreation;

/**
 *
 * The RegistrationPanel class contains the RegistrationFrame components.
 *
 * @author BeeYean Tan
 * @version  2016-04-16
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegistrationPanel
        extends JPanel
{
    private static final Dimension DIM = new Dimension(463, 405);
    private JButton submitButton;
    private JLabel eMailLabel;
    private JLabel eMailErrorLabel;
    private JTextField eMailTextField;
    private JLabel usernameLabel;

    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JLabel passwordErrorLabel;

    private JLabel reenterPasswordLabel;
    private JButton cancelButton;
    private JTextField passwordTextField;
    private JTextField reenterPasswordTextField;

    public RegistrationPanel()
    {
        setupPanel();
        setupLayout();
    }

    public void setupLayout() {}

    public void setupPanel()
    {
        setPreferredSize(new Dimension(350, 180));
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        this.usernameLabel = new JLabel("Name:");

        GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
        gbc_usernameLabel.anchor = 13;
        gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
        gbc_usernameLabel.gridx = 0;
        gbc_usernameLabel.gridy = 0;
        add(this.usernameLabel, gbc_usernameLabel);

        this.usernameTextField = new JTextField();
        GridBagConstraints gbc_usernameTextField = new GridBagConstraints();
        gbc_usernameTextField.gridwidth = 2;
        gbc_usernameTextField.insets = new Insets(0, 0, 5, 5);
        gbc_usernameTextField.fill = 2;
        gbc_usernameTextField.gridx = 1;
        gbc_usernameTextField.gridy = 0;
        this.usernameTextField.setColumns(10);
        add(this.usernameTextField, gbc_usernameTextField);

        this.eMailLabel = new JLabel("Email:");
        GridBagConstraints gbc_eMailLabel = new GridBagConstraints();
        gbc_eMailLabel.anchor = 13;
        gbc_eMailLabel.insets = new Insets(0, 0, 5, 5);
        gbc_eMailLabel.gridx = 0;
        gbc_eMailLabel.gridy = 1;
        add(this.eMailLabel, gbc_eMailLabel);

        this.eMailTextField = new JTextField();
        GridBagConstraints gbc_eMailTextField = new GridBagConstraints();
        gbc_eMailTextField.gridwidth = 2;
        gbc_eMailTextField.insets = new Insets(0, 0, 5, 5);
        gbc_eMailTextField.fill = 2;
        gbc_eMailTextField.gridx = 1;
        gbc_eMailTextField.gridy = 1;
        this.eMailTextField.setColumns(10);

       /* this.eMailErrorLabel = new JLabel("eMail cannot be empty.");
        this.eMailErrorLabel.setFont(new Font("Tahoma", 2, 11));
        this.eMailErrorLabel.setForeground(Color.RED);
        GridBagConstraints gbc_eMailErrorLabel = new GridBagConstraints();
        gbc_eMailErrorLabel.anchor = 17;
        gbc_eMailErrorLabel.gridwidth = 3;
        gbc_eMailErrorLabel.insets = new Insets(0, 0, 5, 0);
        gbc_eMailErrorLabel.gridx = 1;
        gbc_eMailErrorLabel.gridy = 2;
        add(this.eMailErrorLabel, gbc_eMailErrorLabel);
*/
        setPreferredSize(new Dimension(400, 180));
        this.passwordLabel = new JLabel("Password:");
        GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
        gbc_passwordLabel.anchor = 13;
        gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
        gbc_passwordLabel.gridx = 0;
        gbc_passwordLabel.gridy = 2;
        add(this.passwordLabel, gbc_passwordLabel);

        /*this.passwordErrorLabel = new JLabel("Password cannot be empty.");
        this.passwordErrorLabel.setFont(new Font("Tahoma", 2, 11));
        this.passwordErrorLabel.setForeground(Color.red);
        GridBagConstraints gbc_passwordErrorLabel = new GridBagConstraints();
        gbc_passwordErrorLabel.anchor = 17;
        gbc_passwordErrorLabel.gridwidth = 3;
        gbc_passwordErrorLabel.insets = new Insets(0, 0, 5, 0);
        gbc_passwordErrorLabel.gridx = 3;
        gbc_passwordErrorLabel.gridy = 4;
        add(this.passwordErrorLabel, gbc_passwordErrorLabel);
        //add(this.submitButton, gbc_submitButton);
*/
        setPreferredSize(new Dimension(400, 180));
        this.reenterPasswordLabel = new JLabel("Reenter Password:");
        GridBagConstraints gbc_reenterPasswordLabel = new GridBagConstraints();
        gbc_reenterPasswordLabel.anchor = 13;
        gbc_reenterPasswordLabel.insets = new Insets(0, 0, 5, 5);
        gbc_reenterPasswordLabel.gridx = 0;
        gbc_reenterPasswordLabel.gridy = 3;
        add(this.reenterPasswordLabel, gbc_reenterPasswordLabel);

        this.submitButton = new JButton("Submit");
        GridBagConstraints gbc_submitButton = new GridBagConstraints();
        gbc_submitButton.insets = new Insets(0, 0, 0, 5);
        gbc_submitButton.gridx = 1;
        gbc_submitButton.gridy = 5;


        add(this.eMailTextField, gbc_eMailTextField);
        this.passwordTextField = new JTextField();
        GridBagConstraints gbc_passwordTextField = new GridBagConstraints();
        gbc_passwordTextField.gridwidth = 2;
        gbc_passwordTextField.insets = new Insets(0, 0, 5, 5);
        gbc_passwordTextField.fill = 2;
        gbc_passwordTextField.gridx = 1;
        gbc_passwordTextField.gridy = 2;
        add(this.passwordTextField, gbc_passwordTextField);
        this.passwordTextField.setColumns(10);

        this.reenterPasswordTextField = new JTextField();
        GridBagConstraints gbc_reenterPasswordTextField = new GridBagConstraints();
        gbc_reenterPasswordTextField.gridwidth = 2;
        gbc_reenterPasswordTextField.insets = new Insets(0, 0, 5, 5);
        gbc_reenterPasswordTextField.fill = 2;
        gbc_reenterPasswordTextField.gridx = 1;
        gbc_reenterPasswordTextField.gridy = 3;
        add(this.reenterPasswordTextField, gbc_reenterPasswordTextField);
        this.reenterPasswordTextField.setColumns(10);

        add(this.submitButton, gbc_submitButton);

        this.cancelButton = new JButton("Cancel");
        GridBagConstraints gbc_cancelButton = new GridBagConstraints();
        gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
        gbc_cancelButton.gridx = 2;
        gbc_cancelButton.gridy = 5;
        add(this.cancelButton, gbc_cancelButton);
    }

    public void addSubmitButtonActionListener(ActionListener al)
    {
        this.submitButton.addActionListener(al);
        this.requestFocusInWindow();
    }

    public void addCancelButtonActionListener(ActionListener al)
    {
        this.cancelButton.addActionListener(al);
    }

    public void registrationButtonClick()
    {
        this.submitButton.doClick();
    }

    public String getUsername()
    {
        return this.usernameTextField.getText();
    }

    public String getEmail()
    {
        //this.eMailTextField.requestFocus();
        return this.eMailTextField.getText();

    }

    public String getPassword()
    {
        return this.passwordTextField.getText();
    }

    public String getReenterPassword() {
        return this.reenterPasswordTextField.getText();
    }

    //public void setPasswordErrorLabelVisibility(boolean visibleState)
   // {
   //     this.passwordErrorLabel.setVisible(visibleState);
   // }

   // public void setEmailErrorLabelVisibility(boolean visibleState)
   //{
    //    this.eMailErrorLabel.setVisible(visibleState);
   // }

}
