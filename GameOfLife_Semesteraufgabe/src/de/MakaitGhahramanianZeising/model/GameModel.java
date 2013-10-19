package de.MakaitGhahramanianZeising.model;

public interface GameModel {
	
	public abstract boolean getStatus(int x, int y);
	
	public abstract void prepareNextRound();

	public abstract void playNextRound();
	
}