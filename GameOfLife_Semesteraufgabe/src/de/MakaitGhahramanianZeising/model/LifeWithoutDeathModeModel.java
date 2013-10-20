package de.MakaitGhahramanianZeising.model;

public class LifeWithoutDeathModeModel implements ModeModel {
	
	public boolean cellSurvives(int livingNeighbors) {
		return true;
	}

	public boolean cellRevives(int livingNeighbors) {
		return (livingNeighbors == 3);
	}

}
