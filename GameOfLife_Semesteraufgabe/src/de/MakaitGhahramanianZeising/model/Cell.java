package de.MakaitGhahramanianZeising.model;

public class Cell {

	private boolean isAlive;
	private boolean willBeAlive;
	
	public Cell(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void updateStatus() {
		isAlive = willBeAlive;
	}
	
	public void willBeAlive(boolean nextRoundStatus) {
		willBeAlive = nextRoundStatus;
	}
	
	public boolean willEvolve() {
		return !(isAlive == willBeAlive);
	}
	
}
