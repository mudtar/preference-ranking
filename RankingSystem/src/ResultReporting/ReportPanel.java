package ResultReporting;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * Main GUI of reporting module
 *
 * @Author:  David Li
 * @Version: 2016.04.06
 *
 * @Author David Li
 * @Version 2016.04.21
 * 1. Center the column titles and values of the report table;
 * 2. Resize the size of the report table;
 *
 * @Authot David Li
 * @Version 2016.04.24
 * 1. Display message on the panel when exception happens;
 */

public class ReportPanel extends JPanel implements ActionListener{

    private boolean DEBUG = false;
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    //final static boolean RIGHT_TO_LEFT = false;
    private JLabel userLabel;
    private String labelText;
    private JTable table;
    private HashMap<String, Object[][]> listToTableData;
    private JComboBox userList;
    private Vector<String> listItem;


    /**
     * Initialize message panel
     */
    private void initPanel()
    {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(500, 300));
        GridBagConstraints c = new GridBagConstraints();
        userLabel = new JLabel("Report is not available. Please check netowrk connection.");
        add(userLabel);
    }

    /**
     * Constructor used for display message.
     */
    public ReportPanel(){
        initPanel();
    }

    /**
     * Initialize Report Panel.
     * @param columnNames   - define column names of report table.
     */
    private void initPanel(String[] columnNames) {

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(500, 300));
        GridBagConstraints c = new GridBagConstraints();

        userLabel = new JLabel("Please select a user to view the result:");
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,10,10);
        c.gridx = 0;
        c.gridy = 0;
        add(userLabel, c);

        if(listItem != null) {
            userList = new JComboBox(listItem);
        } else {
            userList = new JComboBox();
        }

        userList.setPreferredSize(new Dimension(500, 25));
        userList.setSelectedIndex(0);
        userList.addActionListener(this);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0,10,10,10);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        add(userList, c);

        if(listToTableData != null) {
            table = new JTable(new DefaultTableModel(listToTableData.get(listItem.get(0)), columnNames));
        }else {
            table = new JTable();
        }

        //Center table title and cell values
        ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
        for(int i = 1;i < table.getModel().getColumnCount(); i++){
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500,300));

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 500;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(0,10,10,10);
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 2;
        add(scrollPane, c);

    }

    /**
     * Constructor used for defining a ReportPanel object.
     * @param userEmailList - data for ComboBoxList
     * @param columnNames - data for column names of report table.
     * @param userToResults - data for test result.
     */
    public ReportPanel(Vector<String> userEmailList, String[] columnNames, HashMap<String, Object[][]> userToResults){
        if(userEmailList != null && userToResults != null) {
            this.listItem = userEmailList;
            this.listToTableData = userToResults;
            initPanel(columnNames);
        } else {
            initPanel();
        }
    }

    /**
     * Action listener userd for respond to event of Changing iteln in ComboBoxList.
     * @param e  - action event
     */
    public void actionPerformed(ActionEvent e){
        JComboBox user = (JComboBox)e.getSource();
        updateData((String)user.getSelectedItem());
    }

    /**
     *Used for updating data in the table.
     * @param userEmail - user email related to data in table.
     */
    private void updateData(String userEmail) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object [][] data = listToTableData.get(userEmail);
        int rowCount = model.getRowCount();

        for(int i = rowCount-1; i>=0; i--){
            model.removeRow(i);

        }

        model.setRowCount(data.length);
        for(int i = 0; i<data.length; i++){
            for(int j=0; j<data[0].length; j++) {
                model.setValueAt(data[i][j], i ,j);
            }
        }

    }

    /**
     * Display debug information Used for debugging.
     * @param table
     */
    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
    }

}