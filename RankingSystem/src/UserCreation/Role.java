package edu.pcc.fueledbyjava.rankingsystem.usercreation;

/**
 * Role class is use to check whether the user
 * is admin or just normal user.
 *
 * @author BeeYean Tan
 * @version 2016-04-11
 */

public class Role
{
    private UserAccessRole userAccessRole;

    public static enum UserAccessRole
    {
        Unassigned,  Admin,  User;
    }

    public Role()
    {
        setUserAccessRole(UserAccessRole.Unassigned);
    }

    public UserAccessRole getUserAccessRole()
    {
        return this.userAccessRole;
    }

    public void setUserAccessRole(UserAccessRole userAccessRole)
    {
        this.userAccessRole = userAccessRole;
    }
}
