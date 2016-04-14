package UserCreation;

/**
 * @author BeeYean Tan
 * @version 2016-04-11
 */
public abstract interface LoginView
{
    public abstract boolean updateLoginFrameState(boolean paramBoolean);

    public abstract void registerController(LoginController paramLoginController);
}