package de.makaitghahramanianzeising.enums;

/**
 * Board types that the user can select from.
 */

public enum BoardTypeEnum {

    WALLOFDEATH("Wall of Death"), PACMAN("Pacman Style");

    private final String label;

    BoardTypeEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
