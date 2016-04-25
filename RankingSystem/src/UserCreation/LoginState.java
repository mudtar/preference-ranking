package UserCreation;

/**
 * This LoginState class is used to check the user's login state
 * independent from the user interface i.e any user being logged in.
 *
 * @author BeeYean Tan
 * @version 2016-04-11
 */
public class LoginState
{
    private boolean loginState;

    /**
     * Constructor of the class
     */
    public LoginState()
    {
        setLoginState(false); // initialize to false (logged out)
    }

    /**
     * Get the user's login state
     * @return true if it is logged in, otherwise logged out.
     */
    public boolean getLoginState()
    {
        return this.loginState;
    }

    /**
     * Set the user login state
     * @param loginState true for logged in, otherwise false for logged out.
     */
    public void setLoginState(boolean loginState)
    {
        this.loginState = loginState;
    }
}

