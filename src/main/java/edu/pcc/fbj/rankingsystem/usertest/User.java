package edu.pcc.fbj.rankingsystem.usertest;

/**
 * A class used to keep track of the user who is currently logged in.
 *
 * @author  Ian Burton
 * @version 2016.05.31.1
 */
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

    /**
     * Return the email address associated with the user who is
     * currently logged in.
     *
     * @return the email address associated with the user who is
     *         currently logged in
     */
    static String getEmail()
    {
        return email;
    }

    /**
     * Set the UserID associated with the user who is currently logged
     * in.
     *
     * @param ID the UserID associated with the user who is currently
     *           logged in
     */
    static void setID(int ID)
    {
        User.ID = ID;
    }

    /**
     * Return the UserID associated with the user who is currently
     * logged in.
     *
     * @return the UserID associated with the user who is currently
     *         logged in
     */
    static int getID()
    {
        return ID;
    }
}
