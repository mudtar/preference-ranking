package edu.pcc.fbj.rankingsystem.usercreation;

/**
 * @author BeeYean Tan
 * @version 2016-05-02
 */
public class StaticUserCredential {
    private static String email = null;
    private static String password = null;

    public static String getStaticEmail()
    {
       return email;
    }

    public static void setStaticEmail(String passEmail)
    {
        email = passEmail;
    }

    public static String getStaticPassword()
    {
        return password;
    }

    public static void setStaticPassword(String passPassword)
    {
        password = passPassword;
    }

}
