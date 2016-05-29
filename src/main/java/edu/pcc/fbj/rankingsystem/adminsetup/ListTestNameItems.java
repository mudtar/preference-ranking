package edu.pcc.fbj.rankingsystem.adminsetup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 5/28/2016.
 */
public class ListTestNameItems {

    private String testName;
    private int testNameID;
    private List<String> testNameItems;
    private List<Item> items;

    public ListTestNameItems(String passTestName, int passTestNameID)
    {
        testName = passTestName;
        testNameID = passTestNameID;
        testNameItems = new ArrayList<>();
        items = new ArrayList<>();
    }

    public String getTestName(){return testName;}

    public int getTestNameID(){return testNameID;}

    public List<String> getTestnameItems(){return testNameItems;}
    public void setTestNameItem(String passTestNameItem){testNameItems.add(passTestNameItem);}

    public List<Item> getItems(){return items;}
    public void setItem(Item passItem){items.add(passItem);}

    public void deleteTestNameItem(String passTestNameItem)
    {
        testNameItems.remove(testNameItems.indexOf(passTestNameItem));
    }
}
