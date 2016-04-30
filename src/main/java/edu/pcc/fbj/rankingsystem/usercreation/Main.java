package main.java.edu.pcc.fbj.rankingsystem.usercreation;

import javax.swing.*;

/**
 * Start the program here and launch the login page.
 *
 * @author BeeYean Tan
 * @version 2016-04-11
 */

public class Main
{
    private static RankingSystemController controller;

    public static void main(String[] args)
    {
        controller = RankingSystemController.INSTANCE;
        System.out.println(RankingSystemController.INSTANCE);

        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    Main.controller.launchLogin();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
