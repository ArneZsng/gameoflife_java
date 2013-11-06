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
	
    public boolean equals(Object obj) {
        if (obj instanceof Cell)
            return isAlive == ((Cell)obj).isAlive(); 
        else
            return false;
    }
    
    public int hashCode() {
    	return isAlive ? 1231 : 1237;
    }
}
