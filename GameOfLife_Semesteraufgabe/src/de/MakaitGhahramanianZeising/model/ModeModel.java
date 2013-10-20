package de.MakaitGhahramanianZeising.model;

public interface ModeModel {

	public abstract boolean cellSurvives(int livingNeighbors);

	public abstract boolean cellRevives(int livingNeighbors);

}