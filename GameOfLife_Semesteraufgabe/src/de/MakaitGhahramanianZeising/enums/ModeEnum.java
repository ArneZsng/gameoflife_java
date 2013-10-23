package de.MakaitGhahramanianZeising.enums;

public enum ModeEnum {

	GAMEOFLIFE("Game of Life"), HIGHLIFE("High Life"), LIFEWITHOUTDEATH("Life without Death"), THREEFOUR("Three Four");
	
	private final String name;
	
	ModeEnum (String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
