package de.MakaitGhahramanianZeising.enums;

public enum BoardEnum {
	
	WALLOFDEATH("Wall of Death"), PACMAN("Pacman Style");
	
	private final String name;
	
	BoardEnum (String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
