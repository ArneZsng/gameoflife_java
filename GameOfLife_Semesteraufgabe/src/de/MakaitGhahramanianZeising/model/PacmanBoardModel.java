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
		ArrayList<Integer> adjacentColumns = findAdjacentColumns(x);
		ArrayList<Integer> adjacentRows = findAdjacentRows(y);

		for (int i : adjacentColumns) {
			for (int j : adjacentRows) {
				if (!(i == x && j == y)) {
					if (isAlive(i,j)) {
						numberOfLivingNeighbors++;
					}
				}
			}
		}

		return numberOfLivingNeighbors;
	}
	
	private ArrayList<Integer> findAdjacentRows(int y) {
		ArrayList<Integer> adjacentRows = new ArrayList<Integer>();
		
		if (getHeight() == 1) {
			adjacentRows.add(y);
		} else {
			if (y == 0 && getHeight() > 1) {
				adjacentRows.add(getHeight()-1);
			} else {
				adjacentRows.add(y-1);
			}
			
			adjacentRows.add(y);
			
			if ((y+1) == getHeight() && getHeight() > 1) {
				adjacentRows.add(0);
			} else {
				adjacentRows.add(y+1);
			}
		}
		
		return adjacentRows;
	}

	private ArrayList<Integer> findAdjacentColumns(int x) {
		ArrayList<Integer> adjacentColumns = new ArrayList<Integer>();
		
		if (getWidth() == 1) {
			adjacentColumns.add(x);
		} else {
			if (x == 0 && getWidth() > 1) {
				adjacentColumns.add(getWidth()-1);
			} else {
				adjacentColumns.add(x-1);
			}
			
			adjacentColumns.add(x);
			
			if ((x+1) == getWidth() && getWidth() > 1) {
				adjacentColumns.add(0);
			} else {
				adjacentColumns.add(x+1);
			}
		}
		
		return adjacentColumns;
	}

}
