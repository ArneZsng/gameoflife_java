package de.makaitghahramanianzeising.enums;

public enum BoardTypeEnum {

    WALLOFDEATH("Wall of Death"), PACMAN("Pacman Style");

    private final String name;

    BoardTypeEnum (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
