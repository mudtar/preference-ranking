package edu.pcc.fbj.rankingsystem.usertest;

class User
{
    /**
     * The email address associated with the user who is currently
     * logged in.
     */
    private static String email;

    /**
     * The UserID associated with the user who is currently logged in.
     */
    private static int ID;

    /**
     * Set the email address associated with the user who is currently
     * logged in so that the preferences can be associated in the
     * database with the proper user.
     *
     * @param email the email address associated with the user who
     *              is currently logged in
     */
    static void setEmail(String email)
    {
        User.email = email;
    }

    static String getEmail()
    {
        return email;
    }

    static void setID(int ID)
    {
        User.ID = ID;
    }

    static int getID()
    {
        return ID;
    }
}
