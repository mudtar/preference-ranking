package UserCreation;

/**
 * The RegistrationModel class takes care of the behavior of the user registration logic.
 *
 * @author BeeYean Tan
 * @version  2016-04-16
 */
public class RegistrationModel
{
    private boolean registrationState;

    public void setRegistrationState(boolean registrationState)
    {
        this.registrationState = registrationState;
    }

    public boolean getRegistrationState()
    {
        return this.registrationState;
    }
}

