package de.MakaitGhahramanianZeising.model;

public interface Mode {

	public abstract boolean cellSurvives(int livingNeighbors);

	public abstract boolean cellRevives(int livingNeighbors);

}