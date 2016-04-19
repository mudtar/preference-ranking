package UserCreation;

/**
 * @author BeeYean Tan
 * @version 2016-04-11
 */

public class User
{
    private String username;
    private String eMail;
    private String password;
    private Role role;
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
        return this.eMail;
    }

    public String getPassword() {return this.password ;}

    public Role.UserAccessRole getUserAccessRole()
    {
        return this.role.getUserAccessRole();
    }

    public boolean getLoginState()
    {
        return this.loginState.getLoginState();
    }

    public void setEmail(String eMail)
    {
        this.eMail = eMail;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password) { this.password = password; }

    public void setUserAccessRole(Role.UserAccessRole role)
    {
        this.role.setUserAccessRole(role);
    }

    public void setLoginState(boolean loginState)
    {
        this.loginState.setLoginState(loginState);
    }
}
