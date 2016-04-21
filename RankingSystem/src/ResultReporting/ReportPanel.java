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
 */

public class ReportPanel extends JPanel implements ActionListener{

    private boolean DEBUG = false;
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    private JLabel userLabel;
    private String labelText;
    private JTable table;
    private HashMap<String, Object[][]> listToTableData;
    private JComboBox userList;
    private Vector<String> listItem;


    /**
     * Initialzie Report Panel only used for test with sample data.
     */
    private void initPanel() {
        if (RIGHT_TO_LEFT) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        userLabel = new JLabel("Please select a user to view the result:");
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,10,10);  //top padding
        c.gridx = 0;
        c.gridy = 0;
        add(userLabel, c);


        String[] emailList = { "david@gmail.com", "ian@gmail.com", "eric@gmail.com", "beeyean@gmail.com" };

        userList = new JComboBox(emailList);
        userList.setPreferredSize(new Dimension(500, 20));
        userList.setSelectedIndex(3);
        userList.addActionListener(this);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0,10,10,10);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        add(userList, c);


        String[] columnNames = {"Item",
                "Wins",
                "Losses",
                "Ties"
        };

        Object[][] data = {
                {"Dog", "4", "1", "0"},
                {"Cat", "2", "1", "0"},
                {"horse", "3", "1", "0"},
                {"Lion", "1", "1", "0"},
                {"Tiger", "3", "1", "0"}};


        table = new JTable(new DefaultTableModel(data, columnNames));
        table.setPreferredScrollableViewportSize(new Dimension(500, 260));
        table.setFillsViewportHeight(true);

        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }

        JScrollPane scrollPane = new JScrollPane(table);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 100;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(0,10,10,10);
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 2;
        add(scrollPane, c);
    }

    /**
     * Initialize Report Panel.
     * @param columnNames   - define column names of report table.
     */
    private void initPanel(String[] columnNames) {
        if (RIGHT_TO_LEFT) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(500, 300));
        GridBagConstraints c = new GridBagConstraints();

        userLabel = new JLabel("Please select a user to view the result:");
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,10,10);  //top padding
        c.gridx = 0;
        c.gridy = 0;
        add(userLabel, c);

        userList = new JComboBox(listItem);
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

        table = new JTable(new DefaultTableModel(listToTableData.get(listItem.get(0)), columnNames));

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
     * Constructor used for test.
     */
    public ReportPanel(){
        initPanel();
    }

    /**
     * Constructor used for defining a ReportPanel object.
     * @param userEmailList - data for ComboBoxList
     * @param columnNames - data for column names of report table.
     * @param userToResults - data for test result.
     */
    public ReportPanel(Vector<String> userEmailList, String[] columnNames, HashMap<String, Object[][]> userToResults){
        this.listItem = userEmailList;
        this.listToTableData = userToResults;
        initPanel(columnNames);
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
