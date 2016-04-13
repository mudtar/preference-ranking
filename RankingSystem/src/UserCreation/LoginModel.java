package edu.pcc.fueledbyjava.rankingsystem.usercreation;

import java.util.ArrayList;

/**
 * @author BeeYean Tan
 * @version 2016-04-11
 */
public class LoginModel
{
    private User user;

    public void setLoginState(boolean loginState)
    {
        this.user.setLoginState(loginState);
    }

    public boolean getLoggedInState()
    {
        return this.user.getLoginState();
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public void setValidatedUser(ArrayList<String> user)
    {
        this.user.setUsername((String)user.get(0));
        this.user.setEmail((String)user.get(1));
        this.user.setPassword((String)user.get(2));
    }

    public Role.UserAccessRole getUserAccessRole()
    {
        return this.user.getUserAccessRole();
    }

    public void setUserAccessRole(Role.UserAccessRole userAccessRole)
    {
        this.user.setUserAccessRole(userAccessRole);
    }

    public String getUsername()
    {
        return this.user.getUsername();
    }

    public String getUserEmail()
    {
        return this.user.getUserEmail();
    }

    public String getPassword() { return this.user.getPassword();}

}
