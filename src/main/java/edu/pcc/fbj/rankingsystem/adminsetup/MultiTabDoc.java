package edu.pcc.fbj.rankingsystem.adminsetup;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Electech on 5/3/2016.
 */
public class MultiTabDoc {
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JList itemListItemControl;
    private JTextField itemTextFieldItemControl;
    private JButton removeItemButtonItemControl;
    private JButton cancelButtonItemControl;
    private JButton finishedButton;
    private JList testListTestControl;
    private JTextField testTextFieldTestControl;
    private JButton removeTestButtonTestControl;
    private JList itemListTestControl;
    private JList assignedItemListTestControl;
    private JButton assignItemButtonTestControl;
    private JButton removeItemButtonTestControl;
    private JLabel errorLabelTestControl;
    private JLabel imageLabelItemControl;
    private JLabel imageLabelTestControl;
    private JFileChooser fileChooserItemControl;
    private JLabel errorLabelItemControl;


    public MultiTabDoc() {


        itemListItemControl.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                //selected item
            }
        });

        removeItemButtonItemControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //remove selected item
            }
        });

        itemTextFieldItemControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //append item list
            }
        });

        finishedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //finished
            }
        });

        cancelButtonItemControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //cancel
            }
        });

        fileChooserItemControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        testListTestControl.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
        itemListTestControl.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
        assignedItemListTestControl.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
        removeTestButtonTestControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        testTextFieldTestControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        assignItemButtonTestControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        removeItemButtonTestControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getRootPanel()
    {
        return rootPanel;
    }


}
