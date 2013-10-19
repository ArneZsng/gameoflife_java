package de.MakaitGhahramanianZeising.model;

public class CellModel {

	private boolean isAlive;
	private boolean willBeAlive;
	
	public CellModel(boolean isAlive) {
		super();
		this.isAlive = isAlive;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void updateStatus() {
		isAlive = willBeAlive;
	}
	
	public void willBeDead() {
		willBeAlive = false;
	}
	
	public void willBeAlive() {
		willBeAlive = true;
	}
	
}
