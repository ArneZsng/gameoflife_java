package de.MakaitGhahramanianZeising.model;

public class CellModel {

	private boolean isAlive;
	private boolean willBeAlive;
	
	public CellModel(boolean isAlive) {
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
