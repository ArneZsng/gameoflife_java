package de.MakaitGhahramanianZeising.model;

public class PacmanGameModel extends GameModel {
	
	public PacmanGameModel(ModeModel mode, CellModel[][] cells) {
		this.cells = cells;
		this.mode = mode;
	}
	
	@Override
	public int numberOfLivingNeighbors(int x, int y) {
		int numberOfLivingNeighbors = 0;
		x = ensurePositiveCoordinate(x, getWidth());
		y = ensurePositiveCoordinate(y, getHeight());

		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (!(i == 0 && j == 0)) {
					int xCoordinate = ((x+i) % getWidth()); 
					int yCoordinate = ((y+j) % getHeight());
					if (cells[xCoordinate][yCoordinate].isAlive()) {
						numberOfLivingNeighbors++;
					}
				}
			}
		}

		return numberOfLivingNeighbors;
	}

	private int ensurePositiveCoordinate(int i, int dimension) {
		if (i == 0) {
			i += dimension;
		}
		return i;
	}

}
