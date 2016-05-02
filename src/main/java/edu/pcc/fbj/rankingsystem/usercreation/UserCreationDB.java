package edu.pcc.fbj.rankingsystem.usercreation;

import java.sql.*;
import java.util.ArrayList;

/**
 *  The UserCreation class is the model for all connection to 234a_FueledByJava SQL database.
 *
 *  @author BeeYean
 *  @version: 2016.04.19
 *
 */


public enum UserCreationDB
{

   INSTANCE;

    private Connection connect = null;
    private static final String FUELDBYJAVA_URL = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/234a_FueledByJava";
    private static final String USERNAME = "234a_FueledByJava";
    private static final String PASSWORD = "avaJyBdeleuF_a432#";
    private Role role;

    /**
     * Creates a connection to the database
     */
    public void connection()
    {
        System.out.println("UserCreationDB::connection()");
        try
        {
            this.connect = DriverManager.getConnection(FUELDBYJAVA_URL, USERNAME, PASSWORD);
        }
        catch (Exception e)
        {
            System.err.println("Got an exception in USerCreationDB::connection()! ");
            System.err.println(e.getMessage());
        }
    }

    /**
     * Validate the user's email against the User Table from the database
     * @param email
     * @return true if the user's email address if found, otherwise false
     */

    public boolean validateUser(String email)
    {
        String emailAddress = " ";
        try {
            connection();
            System.out.println("UserCreationDB::validateUser()");
            PreparedStatement preparedStmt = this.connect.prepareStatement("SELECT Email FROM FBJ_USER " +
                    "WHERE Email = ?;");
            preparedStmt.setString(1, email);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next())
            {
                emailAddress = rs.getString("Email");
                System.out.println("Email : " + emailAddress );
            }
            if (email.equalsIgnoreCase(emailAddress))
            {
               return true;
            }
           else {
                System.out.println("Invalid Email address" + emailAddress );
                return false;
            }

        } catch (SQLException e)
        {
            System.err.println("There is an exception in USerCreationDB::validateUser()! ");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean validateUser1(String email, String password)
    {
        String emailAddress = " ";
        String userpassword = " ";
        try {
            connection();
            System.out.println("UserCreationDB::validateUser1()");
            PreparedStatement preparedStmt = this.connect.prepareStatement("SELECT Email, Password FROM FBJ_USER " +
                    "WHERE Email = ? AND Password = ?;");
            preparedStmt.setString(1, email);
            preparedStmt.setString(2, password);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next())
            {
                emailAddress = rs.getString("Email");
                System.out.println("Email : " + emailAddress );
                userpassword = rs.getString("Password");
                System.out.println("Password : " + password );
            }
            if (email.equalsIgnoreCase(emailAddress) && password.equalsIgnoreCase(userpassword))
            {
                return true;
            }
            else
            {
                System.out.println("Invalid Email address or Invalid Password");
                return false;
            }

        }
        catch (SQLException e)
        {
            System.err.println("There is an exception in USerCreationDB::validateUser1()! ");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public ArrayList<String> getUser(String email)
    {
        ArrayList<String> user = new ArrayList();
        try {
            connection();
            //Connection connection = UserCreationDB.getConnection();
            System.out.println("UserCreationDB::getUser()");
            PreparedStatement preparedStmt = this.connect.prepareStatement("SELECT Name, Password " +
                    "FROM FBJ_USER WHERE Email = ?;");

            preparedStmt.setString(1, email);
            ResultSet rs = preparedStmt.executeQuery();

             while (rs.next())
             {
                user.add(rs.getString("Name"));
                user.add(rs.getString("Password"));
             }
           user.add(email);
           return user;
        } catch (SQLException e)
        {
            System.err.println("There is an exception in USerCreationDB::getUser()! ");
            System.err.println(e.getMessage());
        }
       return null;

    }

    /**
     * Verify the user's email against the User table from the database
     * to see if the user is Admin or User
     * @param email of the current user
     * @return true if the user is Admin, false if User
     */
    public boolean getUserAccessRole(String email)
    {
        try
        {
            connection();
            PreparedStatement preparedStmt = this.connect.prepareStatement("SELECT IsAdmin FROM FBJ_USER " +
                            "WHERE Email = ?;");
            preparedStmt.setString(1, email);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next())
            {
                int accessRole = rs.getInt("IsAdmin");
                if (accessRole == 1)
                {
                    return true;
                    //role.setUserAccessRole(true);
                }
                if (accessRole == 0)
                {
                    return false;
                    //role.setUserAccessRole(false);
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Got an exception! ");
            System.err.println("There is an exception in UserCreationDB::getUserAccessRole()! ");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public void saveUser(ArrayList<String> user)
    {
        try
        {
            connection();
            System.out.println("UserCreationDB::saveUser()");
            PreparedStatement preparedStmt = this.connect.prepareStatement("INSERT INTO FBJ_USER " +
                    "(Name, Email, Password, IsAdmin) VALUES (?, ?, ?, ?);");
            preparedStmt.setString(1, user.get(0));
            preparedStmt.setString(2, user.get(1));
            preparedStmt.setString(3, user.get(2));
            preparedStmt.setBoolean(4, false);

            preparedStmt.execute();
        }
        catch (SQLException e1)
        {
            //System.err.println("Got an exception! ");
            System.err.println("There is an exception in USerCreationDB::getUserAccessRole()! ");
            System.err.println(e1.getMessage());
            e1.printStackTrace();
        }
    }

    public int getUserID(String emailAddress)
    {
        int userID = 0;
        try
        {
            connection();
            System.out.println("UserCreationDB::getUserID()");
            PreparedStatement preparedStmt = this.connect.prepareStatement("SELECT PK_UserID " +
                    "FROM FBJ_USER WHERE Email = ?;");
            preparedStmt.setString(1, emailAddress);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next())
            {
                return rs.getInt("PK_UserID");
            }
        }
        catch (SQLException e)
        {
            System.err.println("There is an exception in USerCreationDB::getUserID()! ");
            System.err.println(e.getMessage());
        }
        return userID;
    }

    public void saveUserAccess(int userID)
    {
        try
        {
            connection();
            System.err.println("Starting saveUserAccess() function! ");
            PreparedStatement preparedStmt = this.connect.prepareStatement("INSERT INTO [FBJ_USER](PK_UserID, IsAdmin)"
                    + " VALUES (?, ?);");
            preparedStmt.setInt(1,userID);
            preparedStmt.setBoolean(2, false);
            preparedStmt.execute();
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
            System.err.println("There is an exception in USerCreationDB::saveUserAccess())! ");
        }
    }
    public ResultSet sqlFueledByJavaQuery(String query)
    {
        connection();
        ResultSet rs = null;
        try
        {
            Statement stmt = this.connect.createStatement(1004, 1007);
            rs = stmt.executeQuery(query);
        }
        catch (SQLException e)
        {
            System.err.println("You have got an exception squlFueledByJavaQuery! ");
            System.err.println(e.getMessage());
        }
        return rs;
    }

    public void closeConnection()
    {
        if (this.connect != null)
        {
            try
            {
                this.connect.close();
            }
            catch (Exception e)
            {
                System.err.println("You got an exception in closeConnection()! ");
                System.err.println(e.getMessage());
            }
        }
    }
}
