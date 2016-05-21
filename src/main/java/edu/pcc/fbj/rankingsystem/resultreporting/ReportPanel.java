package edu.pcc.fbj.rankingsystem.resultreporting;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * Main GUI of reporting module
 *
 * @author  David Li
 * @version 2016.04.06
 *
 * @author David Li
 * @version 2016.04.21
 * 1. Center the column titles and values of the report table;
 * 2. Resize the size of the report table;
 *
 * @author David Li
 * @version 2016.04.24
 * 1. Display message on the panel when exception happens;
 */

public class ReportPanel extends JPanel implements ActionListener
{

    private JTable basicTable;
    private JTable matrixTable;
    private JComboBox userList;
    private JLabel label;
    private Vector<String> listItem;
    private JComboBox testList;
    private Vector<String> testListItem;
    private int emailIndex;
    private Signal signal;
    private JCheckBox basicReportButton;
    private JCheckBox matrixReportButton;
    private JCheckBox statisticsButton;
    private JScrollPane matrixPane;


    /**
     * Initialize message panel
     *//*
    private void initPanel()
    {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(500, 300));
        add(new JLabel("Report is unavailable. Please check netowrk connection."));
    }*/

    /**
     * Default constructor
     */
    public ReportPanel()
    {
        emailIndex = 0;
        signal = Signal.DATABASE_SIGNAL_RETRIEVE_DATA;
        initPanel();
    }


    private void initWidgetEmailList(GridBagConstraints c)
    {
        label = new JLabel("Email List:");
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,10);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        add(label, c);

        if(listItem != null)
        {
            userList = new JComboBox(listItem);
        }
        else
        {
            userList = new JComboBox();
        }

        userList.setPreferredSize(new Dimension(250, 25));
        userList.setMaximumSize(new Dimension(250, 25));
        if(listItem != null)
        {
            userList.setSelectedIndex(0);
        }
        userList.addActionListener(this);
        userList.setActionCommand("EmailList");

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0,10,0,10);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 1;
        add(userList, c);
    }

    private void initWidgetTestList(GridBagConstraints c)
    {
        JLabel testLabel = new JLabel("Test List:");
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,10);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        add(testLabel, c);

        if(testListItem != null)
        {
            testList = new JComboBox(listItem);
        }
        else
        {
            testList = new JComboBox();
        }

        testList.setPreferredSize(new Dimension(250, 25));
        if(testListItem != null)
        {
            testList.setSelectedIndex(0);
        }
        testList.addActionListener(this);
        testList.setActionCommand("TestList");

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0,10,0,10);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 3;
        add(testList, c);
    }

    private void initWidgetReportOption(GridBagConstraints c)
    {
        basicReportButton = new JCheckBox("Basic Report");
        basicReportButton.setMnemonic(KeyEvent.VK_C);
        basicReportButton.setSelected(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0,10,1,10);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 4;
        basicReportButton.addActionListener(this);
        basicReportButton.setActionCommand("BasicReport");
        add(basicReportButton, c);

        matrixReportButton = new JCheckBox("Matrix Report");
        matrixReportButton.setMnemonic(KeyEvent.VK_G);
        matrixReportButton.setSelected(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0,10,1,10);
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 4;
        matrixReportButton.addActionListener(this);
        matrixReportButton.setActionCommand("MatrixReport");
        add(matrixReportButton, c);

        statisticsButton = new JCheckBox("Statistics");
        statisticsButton.setMnemonic(KeyEvent.VK_H);
        statisticsButton.setSelected(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0,10,1,10);
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridwidth = 1;
        c.gridy = 4;
        statisticsButton.addActionListener(this);
        statisticsButton.setActionCommand("StatisticsReport");
        add(statisticsButton, c);
    }

    private void initWidgetBasicReport(GridBagConstraints c)
    {

        if(basicTable == null) {
            basicTable = new JTable();
        }
        JScrollPane scrollPane = new JScrollPane(basicTable);
        scrollPane.setPreferredSize(new Dimension(500,300));

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 1000;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(0,10,10,10);
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 5;
        add(scrollPane, c);
    }

    private void initWidgetMatrixReport(GridBagConstraints c)
    {

        if(matrixTable == null) {
            matrixTable = new JTable();
        }
        matrixPane = new JScrollPane(matrixTable);
        matrixPane.setPreferredSize(new Dimension(500,300));

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 1000;
        c.ipadx = 500;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(0,10,10,10);
        c.gridx = 3;
        c.gridwidth = 3;
        c.gridy = 5;
        add(matrixPane, c);
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if(frame != null) {
            frame.setSize(new Dimension(1000, 300));
            frame.revalidate();
        }
    }


    /**
     * Initialize Report Panel
     */
    private void initPanel()
    {

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(500, 300));
        GridBagConstraints c = new GridBagConstraints();

        initWidgetEmailList(c);
        initWidgetTestList(c);
        initWidgetReportOption(c);
        initWidgetBasicReport(c);
    }

    public void setLabelText(String text)
    {
        label.setText(text);
    }

    /**
     * Set items for ComboBoxList
     * @param listItem Vector<String>
     */
    public void setListItem(Vector<String> listItem)
    {
        if(listItem == null)
            return;

        for(String item: listItem)
        {
            this.userList.addItem(item);
        }
        userList.setSelectedIndex(0);
    }

    /**
     * Fill basic report table
     * @param data
     */
    public void setBasicReportData(Object[][] data)
    {
        if(data == null)
            return;

        DefaultTableModel model = (DefaultTableModel) basicTable.getModel();
        int rowCount = model.getRowCount();

        for(int i = rowCount-1; i>=0; i--)
        {
            model.removeRow(i);
        }

        model.setRowCount(data.length);
        for(int i = 0; i<data.length; i++)
        {
            for(int j=0; j<data[0].length; j++)
            {
                try {
                    //System.out.println("===============b!");
                    model.setValueAt(data[i][j], i, j);
                    //System.out.println("===============e!");
                }
                catch (NoSuchElementException e)
                {
                    System.out.println("Warning: No Such Element!");
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("Warning: Something wrong with your array");
                }
            }
        }
    }

    /**
     * set basic report table columns
     * @param data
     */
    public void setBasicTableColumns(List<String> data)
    {
        DefaultTableModel model = (DefaultTableModel) basicTable.getModel();
        int rowCount = model.getRowCount();

        for(int i = rowCount-1; i>=0; i--)
        {
            model.removeRow(i);
        }

        model.setColumnCount(0);

        for(String name : data)
        {
            model.addColumn(name);
        }

        //Center table title and cell values
        ((DefaultTableCellRenderer)basicTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
        for(int i = 1;i < basicTable.getModel().getColumnCount(); i++) {
            basicTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

    }

    /**
     * Set matrix table columns
     * @param data
     */
    public void setMatrixTableColumns(List<String> data) throws NoSuchElementException, ArrayIndexOutOfBoundsException
    {
        DefaultTableModel model = (DefaultTableModel) matrixTable.getModel();
        int rowCount = model.getRowCount();

        for(int i = rowCount-1; i>=0; i--)
        {
            model.removeRow(i);
        }

        model.setColumnCount(0);

        model.addColumn("");
        for(String name : data)
        {
            model.addColumn(name);
        }

        //Center table title and cell values
        ((DefaultTableCellRenderer)matrixTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
        for(int i = 1;i < matrixTable.getModel().getColumnCount(); i++)
        {
            matrixTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }


    }

    /**
     * Fill matrix report table
     * @param data
     */
    public void setMatrixReportData(Object[][] data)
    {

        if(data == null)
        {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) matrixTable.getModel();
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();

        for(int i = rowCount-1; i>=0; i--)
        {
            model.removeRow(i);
        }

        model.setRowCount(columnCount-1);
        rowCount = model.getRowCount();
        for(int i = 0; i<rowCount; i++)
        {
            model.setValueAt(model.getColumnName(i+1), i ,0);
            model.setValueAt("x", i ,i+1);
        }

        System.out.println("data length : "+data.length);

        for(int i=0; i<data.length; i++)
        {
            int row = 0, column = 0;
            for(int j=1; j<columnCount; j++)
            {
                if(data[i][0].equals(model.getColumnName(j)))
                {
                    row = j;
                }
            }
                
            for(int k=1; k<columnCount; k++)
            {
                if(data[i][1].equals(model.getColumnName(k)))
                {
                    column = k;
                }
            }
            //System.out.println("row:"+row+"-"+"colunm:"+column+"-"+"value:"+data[i][2]);
            model.setValueAt(data[i][2], row-1, column);
            model.setValueAt((-1)*(int)data[i][2], column-1, row);
        }

    }

    /**
     * Action listener userd for respond to event of Changing iteln in ComboBoxList.
     * @param e  - action event
     */
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if (command.equals("EmailList")) {
            JComboBox user = (JComboBox) e.getSource();
            setEmailIndex(user.getSelectedIndex());
            setSignal(Signal.DATABASE_SIGNAL_RETRIEVE_DATA);
        }
        else if (command.equals("TestList"))
        {
            System.out.println(command);
        }
        else if (command.equals("BasicReport"))
        {
            System.out.println(command);
        }
        else if (command.equals("MatrixReport"))
        {
            if(matrixReportButton.isSelected()) {
                System.out.println(command);
                GridBagConstraints c = new GridBagConstraints();
                initWidgetMatrixReport(c);
                setSignal(Signal.DATABASE_SIGNAL_RETRIEVE_MATRIX_DATA);
            }
            else
            {
                remove(matrixPane);
                setSignal(Signal.DATABASE_SIGNAL_RETRIEVE_DATA);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                if(frame != null) {
                    frame.setSize(new Dimension(500, 300));
                    frame.revalidate();
                }
            }
        }
        else if (command.equals("StatisticsReport"))
        {
            System.out.println(command);
        }

    }


    /**
     * Get email index
     * @return
     */
    public int getEmailIndex()
    {
        return emailIndex;
    }

    /**
     * Set email index
     */
    public void setEmailIndex(int index)
    {
        emailIndex = index;
    }

    /**
     * Get signal
     * @return
     */
    public Signal getSignal()
    {
        return signal;
    }

    /**
     * Set signal
     */
    public void setSignal(Signal signal)
    {
        this.signal = signal;
    }

    /**
     * Display debug information Used for debugging.
     * @param  table JTable
     */
    private void printDebugData(JTable table)
    {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++)
        {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++)
            {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
    }

}
