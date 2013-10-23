package de.MakaitGhahramanianZeising.model;

public class ThreeFourMode implements Mode {
	
	public boolean cellSurvives(int livingNeighbors) {
		return (livingNeighbors == 3 || livingNeighbors == 4);
	}

	public boolean cellRevives(int livingNeighbors) {
		return (livingNeighbors == 3 || livingNeighbors == 4);
	}
	
}
