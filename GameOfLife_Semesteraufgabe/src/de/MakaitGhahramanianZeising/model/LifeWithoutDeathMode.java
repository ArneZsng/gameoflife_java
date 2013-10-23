package de.MakaitGhahramanianZeising.model;

public class LifeWithoutDeathMode implements Mode {
	
	public boolean cellSurvives(int livingNeighbors) {
		return true;
	}

	public boolean cellRevives(int livingNeighbors) {
		return (livingNeighbors == 3);
	}

}
