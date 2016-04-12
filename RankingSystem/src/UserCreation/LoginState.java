package UserCreation;

/**
 * This LoginState class is used
 * to check the login state, i.e any user being logged in.
 *
 * @author BeeYean Tan
 * @version 2016-04-11
 */
public class LoginState
{
    private boolean loginState;

    public LoginState()
    {
        setLoginState(false);
    }

    public boolean getLoginState()
    {
        return this.loginState;
    }

    public void setLoginState(boolean loginState)
    {
        this.loginState = loginState;
    }
}

