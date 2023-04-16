package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MortageType {
    CONSTANT("CONSTANT"),
    DECREASING("DECREASING");

    @Getter
    private final String value;
}
