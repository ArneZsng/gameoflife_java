package de.MakaitGhahramanianZeising.model;

public class WallOfDeathGame extends Game {
	
	public WallOfDeathGame(Mode mode, Cell[][] cells) {
		this.cells = cells;
		this.mode = mode;
	}
	
	@Override
	public int numberOfLivingNeighbors(int x, int y) {
		int numberOfLivingNeighbors = 0;

		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (!(i == 0 && j == 0)) {
					int xCoordinate = x+i; 
					int yCoordinate = y+j;
					if (xCoordinate >= 0 && xCoordinate < getWidth() && yCoordinate >= 0 && yCoordinate < getHeight()) {
						if (cells[xCoordinate][yCoordinate].isAlive()) {
							numberOfLivingNeighbors++;
						}
					}
				}
			}
		}

		return numberOfLivingNeighbors;
	}
	
}
