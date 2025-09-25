package com.excelutility.core;

/**
 * Represents the operators that can be used in a filter condition.
 */
public enum Operator {
    EQUALS("="),
    NOT_EQUALS("!="),
    CONTAINS("contains"),
    NOT_CONTAINS("does not contain"),
    STARTS_WITH("starts with"),
    ENDS_WITH("ends with"),
    REGEX_MATCH("regex match"),
    IN_LIST("in list"),
    NOT_IN_LIST("not in list"),
    IS_NULL("is null"),
    IS_NOT_NULL("is not null"),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_OR_EQUAL(">="),
    LESS_OR_EQUAL("<="),
    BETWEEN("between");

    private final String displayName;

    Operator(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}