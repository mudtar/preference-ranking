package edu.pcc.fbj.rankingsystem.usercreation;

import java.util.ArrayList;

/**
 * The LoginModel class is used to represent the behavior of the user login
 * This class is independent from the user interface.
 *
 * @author BeeYean Tan
 * @version 2016-04-11
 */


public class LoginModel
{
    private User user;
    private String email;
    private String password;

    /**
     * Set the user login state to true
     * @param loginState of the user; true for logged in, false for otherwise
     */
    public void setLoginState(boolean loginState)
    {
        this.user.setLoginState(loginState);
    }

    /**
     * Get the user login state
     * @return true if the user is logged in; otherwise is false
     */
    public boolean getLoggedInState()
    {
        return this.user.getLoginState();
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    /**
     * Set the validated user information
     * @param user information containing the user's name, email, and password
     */
    public void setValidatedUser(ArrayList<String> user)
    {
        System.out.println("LoginModel::setValidatedUser()");
        this.user.setUsername((String)user.get(0));
        this.user.setPassword((String)user.get(1));
        this.user.setEmail((String)user.get(2));
    }

    /**
     * Get the user access role from the model
     * @return the role is Admin or User
     */
    public boolean getUserAccessRole()
    {
        System.out.println("LoginModel::getUserAccessRole()");
        return this.user.getUserAccessRole();
    }

    /**
     * Set the user access role
     * @param userAccessRole (Admin or User)
     */
    public void setUserAccessRole(boolean userAccessRole)
    {
        System.out.println("LoginModel::setUserAccessRole()");
        this.user.setUserAccessRole(userAccessRole);
    }

    /**
     * Get the user's name
     * @return user's name
     */
    public String getUsername()
    {
        return this.user.getUsername();
    }

    public String getUserEmail()
    {
       return this.user.getUserEmail();
    }

    public void setUserEmail(String email)
    {
      this.email = email;
    }

    public void setUserPassword(String password)
    {
      this.password = password;
    }

    public String getPassword()
    {
        return this.user.getPassword();
    }

}
