package de.MakaitGhahramanianZeising.model;

import java.util.ArrayList;

public class PacmanBoardModel extends AbstractBoardModel {
	
	public PacmanBoardModel() {
		cells = new CellModel[3][3];
		super.populate();
	}
	
	public PacmanBoardModel(CellModel[][] cells) {
		this.cells = cells;
	}

	@Override
	protected int countLivingNeighbors(int x, int y) {
		int numberOfLivingNeighbors = 0;
		x = ensurePositiveCoordinate(x, getWidth());
		y = ensurePositiveCoordinate(y, getHeight());

		//Test
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (!(i == 0 && j == 0)) {
					int xCoordinate = ((x+i) % getWidth()); 
					int yCoordinate = ((y+j) % getHeight());
					if ( isAlive(xCoordinate,yCoordinate) ) {
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
