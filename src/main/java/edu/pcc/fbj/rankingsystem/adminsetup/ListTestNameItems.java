package edu.pcc.fbj.rankingsystem.adminsetup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eric Kristiansen
 *
 * version 5/31/2016.
 */
public class ListTestNameItems {

    private String testName;
    private int testNameID;
    private List<String> testNameItems;
    private List<Item> items;


    /**
     * Constructor for ListTestNameItems
     * @param passTestName
     * @param passTestNameID
     */
    public ListTestNameItems(String passTestName, int passTestNameID)
    {
        testName = passTestName;
        testNameID = passTestNameID;
        testNameItems = new ArrayList<>();
        items = new ArrayList<>();
    }

    /**
     * get the name for the test the item list pertains to
     * @return String representing the test name
     */
    public String getTestName(){return testName;}

    /**
     * get the TestName ID
     * @return TestName ID as an int
     */
    public int getTestNameID(){return testNameID;}

    /**
     * get the item list for the Test in question
     * @return List<String>
     */
    public List<String> getTestnameItems(){return testNameItems;}

    /**
     * set an Item in test name items
     * @param passTestNameItem
     */
    public void setTestNameItem(String passTestNameItem){testNameItems.add(passTestNameItem);}

    /**
     * get the list of items
     * @return items
     */
    public List<Item> getItems(){return items;}

    /**
     * set an Item
     * @param passItem
     */
    public void setItem(Item passItem){items.add(passItem);}

    /**
     * deleteTestNameItem
     * @param passTestNameItem
     */
    public void deleteTestNameItem(String passTestNameItem)
    {
        testNameItems.remove(testNameItems.indexOf(passTestNameItem));
    }
}
