package de.MakaitGhahramanianZeising.model;

public interface BoardModel {

	public abstract void populate();
	
	public abstract int getWidth();
	
	public abstract int getHeight();

	public abstract CellModel[][] getCells();

	public abstract int numberOfLivingCells();

	public abstract int numberOfLivingNeighbors(int x, int y);
	
	public abstract void updateStatus();
	
	public abstract boolean isAlive(int x, int y);
	
	public abstract void cellWillBeAlive(int x, int y);
	
	public abstract void cellWillBeDead(int x, int y);

}