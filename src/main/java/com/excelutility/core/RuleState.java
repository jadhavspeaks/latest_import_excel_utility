package com.excelutility.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * A serializable representation of a FilterRule for saving to a profile.
 */
public class RuleState {
    private final String name;
    private final FilterRule.SourceType sourceType;
    private final String sourceValue;
    private final String targetColumn;
    private final boolean trimWhitespace;
    private final Operator operator;

    @JsonCreator
    public RuleState(
            @JsonProperty("name") String name,
            @JsonProperty("sourceType") FilterRule.SourceType sourceType,
            @JsonProperty("sourceValue") String sourceValue,
            @JsonProperty("targetColumn") String targetColumn,
            @JsonProperty("trimWhitespace") boolean trimWhitespace,
            @JsonProperty("operator") Operator operator) {
        this.name = name;
        this.sourceType = sourceType;
        this.sourceValue = sourceValue;
        this.targetColumn = targetColumn;
        this.trimWhitespace = trimWhitespace;
        this.operator = operator != null ? operator : Operator.EQUALS;
    }

    // Getters
    public String getName() { return name; }
    public FilterRule.SourceType getSourceType() { return sourceType; }
    public String getSourceValue() { return sourceValue; }
    public String getTargetColumn() { return targetColumn; }
    public boolean isTrimWhitespace() { return trimWhitespace; }
    public Operator getOperator() { return operator; }

    public FilterRule toFilterRule() {
        return new FilterRule(sourceType, sourceValue, targetColumn, trimWhitespace, operator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleState ruleState = (RuleState) o;
        return trimWhitespace == ruleState.trimWhitespace &&
                Objects.equals(name, ruleState.name) &&
                sourceType == ruleState.sourceType &&
                operator == ruleState.operator &&
                Objects.equals(sourceValue, ruleState.sourceValue) &&
                Objects.equals(targetColumn, ruleState.targetColumn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sourceType, sourceValue, targetColumn, trimWhitespace, operator);
    }
}
