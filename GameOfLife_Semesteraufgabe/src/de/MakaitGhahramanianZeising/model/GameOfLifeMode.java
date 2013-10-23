package de.MakaitGhahramanianZeising.model;

public class GameOfLifeMode implements Mode {
	
	public boolean cellSurvives(int livingNeighbors) {
		return (livingNeighbors == 2 || livingNeighbors == 3);
	}

	public boolean cellRevives(int livingNeighbors) {
		return (livingNeighbors == 3);
	}
	
}
