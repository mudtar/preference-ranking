package edu.pcc.fbj.rankingsystem.usercreation;

/**
 *  The Role class is used to check whether
 *  the current user is admin or just user.
 *
 * @author BeeYean Tan
 * @version 2016-04-11
 */

public class Role
{
    private boolean userAccessRole;

   // public static enum UserAccessRole
   // {
   //     Unassigned(),  Admin("Admin"),  User;
   // }

    //public Role()
   // {
    //    setUserAccessRole(UserAccessRole.Unassigned);
   // }
  //  public Role()
  //  {
  //      setUserAccessRole(null);
   // }

    //public UserAccessRole getUserAccessRole()
    //{
   //     return this.userAccessRole;
   // }

    //public void setUserAccessRole(UserAccessRole userAccessRole)
    //{
    //    this.userAccessRole = userAccessRole;
    //}

    /**
     * Constructor of the class
     * Set the user access role to false
     */
    public Role()
    {
        setUserAccessRole(false);
    }

    /**
     * Get the user access role
     * @return the user access role
     */
    public boolean getUserAccessRole()
    {
        return this.userAccessRole;
    }

    /**
     * set the user access role
     * @param userAccessRole (Admin=true or User=false)
     */
    public void setUserAccessRole(boolean userAccessRole)
    {
        this.userAccessRole = userAccessRole;
    }
}