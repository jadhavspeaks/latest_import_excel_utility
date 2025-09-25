package com.excelutility.gui;

import com.excelutility.core.Operator;

import javax.swing.*;
import java.awt.*;

public class OperatorSelectionDialog extends JDialog {
    private JComboBox<Operator> operatorComboBox;
    private Operator selectedOperator;

    public OperatorSelectionDialog(Frame owner) {
        super(owner, "Select Operator", true);
        setLayout(new BorderLayout());

        operatorComboBox = new JComboBox<>(Operator.values());
        add(operatorComboBox, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            selectedOperator = (Operator) operatorComboBox.getSelectedItem();
            setVisible(false);
        });
        add(okButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(owner);
    }

    public Operator getSelectedOperator() {
        return selectedOperator;
    }
}