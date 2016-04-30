package edu.pcc.fbj.rankingsystem.usercreation;

import java.util.BitSet;

/**
 * The User class handles the user's information, user access role and user login state.
 * @author BeeYean Tan
 * @version 2016-04-11
 */

public class User
{
    private String username;
    private String email;
    private String password;
    private Role role;
    private User user;
    private LoginState loginState;

    public User()
    {
        this.role = new Role();
        this.loginState = new LoginState();
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getUserEmail()
    {
        return this.email;
    }

    public String getPassword()
    {
        return this.password ;
    }

   // public int getIsAdmin() {
//
      //  return this.isAdmin;
  //  }

    public boolean getUserAccessRole()
    {
        return this.role.getUserAccessRole();
    }

    public boolean getLoginState()
    {
        return this.loginState.getLoginState();
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setUserAccessRole(boolean role)
    {
        this.role.setUserAccessRole(role);
    }

    public void setLoginState(boolean loginState)
    {
        this.loginState.setLoginState(loginState);
    }

}
