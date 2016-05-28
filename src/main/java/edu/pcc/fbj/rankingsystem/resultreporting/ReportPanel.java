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
    private JTable statisticsTable;
    private JComboBox userList;
    private JLabel label;
    private JComboBox testNameList;
    private JComboBox userTestIDList;
    private int emailIndex;
    private int testNameIndex;
    private int testIDIndex;
    private Signal signal;
    private JCheckBox basicReportButton;
    private JCheckBox matrixReportButton;
    private JCheckBox statisticsButton;
    private JScrollPane matrixPane;
    private JScrollPane statisticsPane;
    private boolean matrixReportFalg;
    private boolean statisticsReportFlag;
    private GridBagConstraints layout;

    /**
     * Default constructor
     */
    public ReportPanel()
    {
        emailIndex = 0;
        testNameIndex = 0;
        testIDIndex = 0;
        signal = Signal.DATABASE_SIGNAL_RETRIEVE_DATA;
        matrixReportFalg = true;
        statisticsReportFlag = true;
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
        c.ipadx = 200;
        add(label, c);


            userList = new JComboBox();


        userList.setPreferredSize(new Dimension(300, 25));

        userList.addActionListener(this);
        userList.setActionCommand("EmailList");

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,10);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 1;
        c.ipadx = 200;
        add(userList, c);
    }

    private void initWidgetTestItemList(GridBagConstraints c)
    {
        JLabel testLabel = new JLabel("Test Item List:");
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,10);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.ipadx = 200;
        add(testLabel, c);


            testNameList = new JComboBox();


        testNameList.setPreferredSize(new Dimension(300, 25));

        testNameList.addActionListener(this);
        testNameList.setActionCommand("TestNameList");

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,10);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 3;
        add(testNameList, c);
    }

    private void initWidgetUserTestList(GridBagConstraints c)
    {
        JLabel testLabel = new JLabel("User Test Id List:");
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,10);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 3;
        c.ipadx = 200;
        add(testLabel, c);


            userTestIDList = new JComboBox();


        userTestIDList.setPreferredSize(new Dimension(300, 25));

        userTestIDList.addActionListener(this);
        userTestIDList.setActionCommand("TestIDList");

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,10);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 5;
        add(userTestIDList, c);
    }

    private void initWidgetReportOption(GridBagConstraints c)
    {
        basicReportButton = new JCheckBox("Basic Report");
        basicReportButton.setMnemonic(KeyEvent.VK_C);
        basicReportButton.setSelected(true);
        basicReportButton.setEnabled(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,1,10);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 6;
        c.ipadx = 200;
        basicReportButton.addActionListener(this);
        basicReportButton.setActionCommand("BasicReport");
        add(basicReportButton, c);

        matrixReportButton = new JCheckBox("Matrix Report");
        matrixReportButton.setMnemonic(KeyEvent.VK_G);
        matrixReportButton.setSelected(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,1,10);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 7;
        matrixReportButton.addActionListener(this);
        matrixReportButton.setActionCommand("MatrixReport");
        add(matrixReportButton, c);

        statisticsButton = new JCheckBox("Statistics");
        statisticsButton.setMnemonic(KeyEvent.VK_H);
        statisticsButton.setSelected(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,1,10);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 8;
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
        scrollPane.setPreferredSize(new Dimension(400,300));

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 1000;
        c.ipadx = 500;
        c.weighty = 1.0;
        c.insets = new Insets(0,10,10,10);
        c.gridx = 3;
        c.gridwidth = 3;
        c.gridheight = 10;
        c.gridy = 1;
        add(scrollPane, c);
    }

    private void initWidgetMatrixReport(GridBagConstraints c)
    {

        if(matrixTable == null) {
            matrixTable = new JTable();
        }
        matrixPane = new JScrollPane(matrixTable);
        matrixPane.setPreferredSize(new Dimension(400,300));

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 1000;
        c.ipadx = 500;
        c.weighty = 1.0;
        c.insets = new Insets(0,10,10,10);
        c.gridx = 3;
        c.gridwidth = 3;
        c.gridy = 12;
        c.gridheight = 10;
        add(matrixPane, c);
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if(frame != null) {
            frame.setSize(new Dimension(800, 600));
            frame.revalidate();
            frame.setLocationRelativeTo(null);
        }
    }

    private void initWidgetStatisticsReport(GridBagConstraints c)
    {

        if(statisticsTable == null) {
            statisticsTable = new JTable();
        }
        statisticsPane = new JScrollPane(statisticsTable);
        statisticsPane.setPreferredSize(new Dimension(400,300));

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 1000;
        c.ipadx = 500;
        c.weighty = 1.0;

        c.insets = new Insets(0,10,10,10);
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 12;
        c.gridheight = 10;
        add(statisticsPane, c);
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if(frame != null) {
            frame.setSize(new Dimension(800, 600));
            frame.revalidate();
            frame.setLocationRelativeTo(null);
        }
    }


    /**
     * Initialize Report Panel
     */
    private void initPanel()
    {

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(800, 300));
        layout = new GridBagConstraints();

        initWidgetEmailList(layout);
        initWidgetTestItemList(layout);
        initWidgetUserTestList(layout);
        initWidgetReportOption(layout);
        initWidgetBasicReport(layout);
        initWidgetMatrixReport(layout);
        initWidgetStatisticsReport(layout);
    }

    public void setLabelText(String text)
    {
        label.setText(text);
    }

    /**
     * Set items for ComboBoxList
     * @param listItem Vector<String>
     */
    public void setEmailListItem(Vector<String> listItem)
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
     * Set items for user test id list ComboBoxList
     * @param listItem Vector<String>
     */
    public void setUserTestIDListItem(Vector<String> listItem)
    {
        if(listItem == null)
            return;



        this.userTestIDList.removeAllItems();
        for(String item: listItem)
        {
            this.userTestIDList.addItem(item);
        }
        userTestIDList.setSelectedIndex(0);

    }

    /**
     * Set items for user test name list
     * @param listItem Vector<String>
     */
    public void setUserTestNameListItem(Vector<String> listItem)
    {
        if(listItem == null)
            return;



        this.testNameList.removeAllItems();
        for(String item: listItem)
        {
            this.testNameList.addItem(item);
        }
        testNameList.setSelectedIndex(0);
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
            for(int j=0; j<data[0].length; j++) {
                model.setValueAt(data[i][j], i, j);
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

            model.setValueAt((-1)* (int) data[i][2], row-1, column);
            model.setValueAt(data[i][2], column-1, row);
        }

    }

    /**
     * Fill statistics report table
     * @param data
     */
    public void setStatisticsReportData(Object[][] data)
    {
        if(data == null)
            return;

        DefaultTableModel model = (DefaultTableModel) statisticsTable.getModel();
        int rowCount = model.getRowCount();

        for(int i = rowCount-1; i>=0; i--)
        {
            model.removeRow(i);
        }

        model.setRowCount(data.length);
        for(int i = 0; i<data.length; i++)
        {
            for(int j=0; j<data[0].length; j++) {
                model.setValueAt(data[i][j], i, j);
            }
        }

    }

    /**
     * set statistics report table columns
     * @param data
     */
    public void setStatisticsTableColumns(List<String> data)
    {
        if(data == null)
            return;

        DefaultTableModel model = (DefaultTableModel) statisticsTable.getModel();
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
        ((DefaultTableCellRenderer)statisticsTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
        for(int i = 1;i < statisticsTable.getModel().getColumnCount(); i++) {
            statisticsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    /**
     * Action listener userd for respond to event of Changing item in ComboBoxList.
     * @param e  - action event
     */
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if (command.equals("EmailList")) {
            JComboBox user = (JComboBox) e.getSource();
            setEmailIndex(user.getSelectedIndex());
            setSignal(Signal.DATABASE_SIGNAL_UPDATE_EAMIL_LIST);
        }
        else if (command.equals("TestNameList"))
        {
            JComboBox testName = (JComboBox) e.getSource();
            setTestNameIndex(testName.getSelectedIndex());
            setSignal(Signal.DATABASE_SIGNAL_UPDATE_TESTNAME_LIST);
        }
        else if (command.equals("TestIDList"))
        {
            JComboBox testID = (JComboBox) e.getSource();
            setTestIDIndex(testID.getSelectedIndex());
            setSignal(Signal.DATABASE_SIGNAL_RETRIEVE_DATA);
        }
        else if (command.equals("BasicReport"))
        {
            System.out.println(command);
        }
        else if (command.equals("MatrixReport"))
        {
            if(matrixReportButton.isSelected()) {
                GridBagConstraints c = new GridBagConstraints();
                initWidgetMatrixReport(c);
                enableMatrixReport();
                setSignal(Signal.DATABASE_SIGNAL_RETRIEVE_DATA);
            }
            else
            {
                remove(matrixPane);
                disableMatrixReport();
                setSignal(Signal.DATABASE_SIGNAL_RETRIEVE_DATA);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                if(frame != null) {
                    if(!isStatisticsReportEnabled()) {
                        frame.setSize(new Dimension(800, 300));
                    }
                    frame.revalidate();
                    frame.getContentPane().validate();
                    frame.getContentPane().repaint();
                    frame.setLocationRelativeTo(null);
                }
            }
        }
        else if (command.equals("StatisticsReport"))
        {
            if(statisticsButton.isSelected()) {
                System.out.println(command);
                GridBagConstraints c = new GridBagConstraints();
                initWidgetStatisticsReport(c);
                enableStatisticsReport();
                setSignal(Signal.DATABASE_SIGNAL_RETRIEVE_DATA);
            }
            else
            {
                remove(statisticsPane);
                disableStatisticsReport();
                setSignal(Signal.DATABASE_SIGNAL_RETRIEVE_DATA);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                if(frame != null) {
                    if(!isMatrixReportEnabled()) {
                        frame.setSize(new Dimension(800, 300));
                    }
                    frame.revalidate();
                    frame.getContentPane().validate();
                    frame.getContentPane().repaint();
                    frame.setLocationRelativeTo(null);
                }
            }
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
     * Get Test Name List Index
     * @return
     */
    public int getTestNameIndex()
    {
        return testNameIndex;
    }

    /**
     * Get Test ID List Index
     * @return
     */
    public int getTestIDIndex()
    {
        return testIDIndex;
    }

    /**
     * Set email index
     */
    private void setEmailIndex(int index)
    {
        emailIndex = index;
    }

    /**
     * Set Test Name List Index
     * @param index
     */
    private void setTestNameIndex(int index)
    {
        testNameIndex = index;
    }

    /**
     * Set Test Name List Index
     * @param index
     */
    private void setTestIDIndex(int index)
    {
        testIDIndex = index;
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

    private void enableMatrixReport()
    {
        matrixReportFalg = true;
    }

    private void disableMatrixReport()
    {
        matrixReportFalg = false;
    }

    public boolean isMatrixReportEnabled()
    {
        return matrixReportFalg;
    }

    private void enableStatisticsReport()
    {
        statisticsReportFlag = true;
    }

    private void disableStatisticsReport()
    {
        statisticsReportFlag = false;
    }

    public boolean isStatisticsReportEnabled()
    {
        return statisticsReportFlag;
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
