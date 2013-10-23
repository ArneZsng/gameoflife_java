package de.MakaitGhahramanianZeising.model;

public class HighLifeMode implements Mode {
	
	public boolean cellSurvives(int livingNeighbors) {
		return (livingNeighbors == 2 || livingNeighbors == 3);
	}

	public boolean cellRevives(int livingNeighbors) {
		return (livingNeighbors == 3 || livingNeighbors == 6);
	}

}
