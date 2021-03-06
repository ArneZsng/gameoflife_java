package de.makaitghahramanianzeising.enums;

/**
 * Modes that the user can select from.
 */

public enum ModeEnum {

    GAMEOFLIFE("Game of Life", new Integer[] {2, 3}, new Integer[] {3}),
    HIGHLIFE("High Life", new Integer[] {2, 3}, new Integer[] {3, 6}),
    LIFEWITHOUTDEATH("Life without Death", new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8}, new Integer[] {3}),
    THREEFOUR("Three Four", new Integer[] {3, 4}, new Integer[] {3, 4});

    private final String label;
    private final Integer[] survives;
    private final Integer[] revives;

    ModeEnum(String label, Integer[] survives, Integer[] revives) {
        this.label = label;
        this.survives = survives.clone();
        this.revives = revives.clone();
    }

    public String getLabel() {
        return label;
    }

    public Integer[] getSurvives() {
        return survives;
    }

    public Integer[] getRevives() {
        return revives;
    }
}
