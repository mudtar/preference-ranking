package UserCreation;

/**
 * The interface for the Login Model view
 *
 * @author BeeYean Tan
 * @version 2016-04-11
 */
public abstract interface LoginView
{
    /**
     *
     * @param paramBoolean
     * @return true if the user is done with the view; otherwise return false
     */
    public abstract boolean updateLoginFrameState(boolean paramBoolean);

    /**
     *
     * @param paramLoginController to register with this view
     */
    public abstract void registerController(LoginController paramLoginController);
}