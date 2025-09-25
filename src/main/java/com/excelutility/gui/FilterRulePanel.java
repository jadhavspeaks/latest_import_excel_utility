package com.excelutility.gui;

import com.excelutility.core.FilterRule;
import com.excelutility.core.expression.FilterExpression;
import com.excelutility.core.expression.RuleNode;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A panel that displays a single filter rule and provides actions for it.
 */
public class FilterRulePanel extends JPanel implements ExpressionNodeComponent {

    private final FilterRule initialRule;
    private final JTextField ruleNameField;
    private final JLabel recordCountLabel;
    private final JComboBox<com.excelutility.core.Operator> operatorComboBox;
    private final JTextField targetColumnField;
    private final JTextField sourceValueField;

    public FilterRulePanel(String name, FilterRule rule, ActionListener deleteListener, ActionListener updateListener) {
        this.initialRule = rule;
        setLayout(new MigLayout("insets 2 5 2 5, fillx", "[grow]rel[]rel[]rel[]rel[]"));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(224, 224, 224))); // Light gray separator
        setBackground(Color.WHITE);

        ruleNameField = new JTextField(name);
        ruleNameField.setBorder(null);
        add(ruleNameField, "growx, wmin 80");

        targetColumnField = new JTextField(rule.getTargetColumn(), 15);
        add(targetColumnField, "sg fields");

        operatorComboBox = new JComboBox<>(com.excelutility.core.Operator.values());
        operatorComboBox.setSelectedItem(rule.getOperator());
        add(operatorComboBox, "sg operator");

        sourceValueField = new JTextField(rule.getSourceValue(), 15);
        add(sourceValueField, "sg fields, growx");

        if (updateListener != null) {
            DocumentListener docListener = new SimpleDocumentListener(updateListener);
            targetColumnField.getDocument().addDocumentListener(docListener);
            sourceValueField.getDocument().addDocumentListener(docListener);
            operatorComboBox.addActionListener(updateListener);
        }

        recordCountLabel = new JLabel("(N/A)");
        recordCountLabel.setFont(recordCountLabel.getFont().deriveFont(Font.BOLD));
        add(recordCountLabel, "gapleft 10");

        JButton previewButton = new JButton("Preview");
        previewButton.setToolTipText("Preview matching results for this rule in a new tab");
        add(previewButton, "hidemode 3");

        JButton deleteButton = new JButton("X");
        deleteButton.setToolTipText("Delete this filter rule");
        deleteButton.setMargin(new Insets(1, 1, 1, 1));
        deleteButton.addActionListener(e -> deleteListener.actionPerformed(
                new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null)
        ));
        add(deleteButton);
    }

    public String getRuleName() {
        return ruleNameField.getText();
    }

    public FilterRule getRule() {
        return new FilterRule(
                initialRule.getSourceType(),
                sourceValueField.getText(),
                targetColumnField.getText(),
                initialRule.isTrimWhitespace(),
                (com.excelutility.core.Operator) operatorComboBox.getSelectedItem()
        );
    }

    public void setRecordCount(int count) {
        recordCountLabel.setText("(" + count + ")");
        if (count == 0) {
            recordCountLabel.setForeground(Color.RED);
        } else {
            recordCountLabel.setForeground(new Color(0, 153, 0)); // Dark Green
        }
    }

    public JButton getPreviewButton() {
        for (Component comp : getComponents()) {
            if (comp instanceof JButton && "Preview".equals(((JButton) comp).getText())) {
                return (JButton) comp;
            }
        }
        return null;
    }

    @Override
    public FilterExpression getExpression() {
        return new RuleNode(this.getRule());
    }

    @Override
    public String getName() {
        return getRuleName();
    }

    @Override
    public void setName(String name) {
        ruleNameField.setText(name);
    }

    private static class SimpleDocumentListener implements DocumentListener {
        private final ActionListener actionListener;

        SimpleDocumentListener(ActionListener actionListener) {
            this.actionListener = actionListener;
        }
        @Override public void insertUpdate(DocumentEvent e) { if(actionListener != null) actionListener.actionPerformed(null); }
        @Override public void removeUpdate(DocumentEvent e) { if(actionListener != null) actionListener.actionPerformed(null); }
        @Override public void changedUpdate(DocumentEvent e) { if(actionListener != null) actionListener.actionPerformed(null); }
    }
}