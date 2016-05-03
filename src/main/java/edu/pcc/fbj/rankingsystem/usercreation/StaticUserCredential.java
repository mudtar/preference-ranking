package edu.pcc.fbj.rankingsystem.usercreation;

/**
 * Created by BeeYean on 5/2/2016.
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