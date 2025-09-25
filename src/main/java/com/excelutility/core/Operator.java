package com.excelutility.core;

/**
 * Represents the operators that can be used in a filter condition.
 */
public enum Operator {
    EQUALS("=", ValueType.VALUE_FIXED),
    NOT_EQUALS("!=", ValueType.VALUE_FIXED),
    CONTAINS("contains", ValueType.VALUE_EDITABLE),
    NOT_CONTAINS("does not contain", ValueType.VALUE_EDITABLE),
    STARTS_WITH("starts with", ValueType.VALUE_EDITABLE),
    ENDS_WITH("ends with", ValueType.VALUE_EDITABLE),
    REGEX_MATCH("regex match", ValueType.VALUE_EDITABLE),
    IN_LIST("in list", ValueType.VALUE_EDITABLE),
    NOT_IN_LIST("not in list", ValueType.VALUE_EDITABLE),
    IS_NULL("is null", ValueType.NO_VALUE),
    IS_NOT_NULL("is not null", ValueType.NO_VALUE),
    GREATER_THAN(">", ValueType.VALUE_FIXED),
    LESS_THAN("<", ValueType.VALUE_FIXED),
    GREATER_OR_EQUAL(">=", ValueType.VALUE_FIXED),
    LESS_OR_EQUAL("<=", ValueType.VALUE_FIXED),
    BETWEEN("between", ValueType.VALUE_EDITABLE);

    private final String displayName;
    private final ValueType valueType;

    Operator(String displayName, ValueType valueType) {
        this.displayName = displayName;
        this.valueType = valueType;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public enum ValueType {
        VALUE_FIXED,
        VALUE_EDITABLE,
        NO_VALUE
    }
}