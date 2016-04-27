package UserCreation;

/**
 *
 * The interface view of the RegistrationModel
 *
 * @author BeeYean Tan
 * @version  2016-04-16
 */

public abstract interface RegistrationView
{
    public abstract boolean updateRegistrationFrameState(boolean paramBoolean);

    public abstract void registerController(RegistrationController paramRegistrationController);
}
