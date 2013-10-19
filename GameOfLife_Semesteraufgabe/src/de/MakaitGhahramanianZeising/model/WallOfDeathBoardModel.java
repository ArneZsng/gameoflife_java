package de.MakaitGhahramanianZeising.model;

public class WallOfDeathBoardModel extends AbstractBoardModel {

	public WallOfDeathBoardModel() {
		cells = new CellModel[3][3];
		super.populate();
	}
	
	public WallOfDeathBoardModel(CellModel[][] cells) {
		this.cells = cells;
	}
	
	@Override
	protected int countLivingNeighbors(int x, int y) {
		int numberOfLivingNeighbors = 0;
		int minX = x - 1;
		int minY = y - 1;
		int maxX = x + 1;
		int maxY = y + 1;

		if (x == 0) {
			minX = x;
		} else if (x == (getWidth() - 1)) {
			maxX = x;
		}

		if (y == 0) {
			minY = y;
		} else if (y == (getHeight() - 1)) {
			maxY = y;
		}

		for (int i = minX; i <= maxX; i++) {
			for (int j = minY; j <= maxY; j++) {
				if (!(i == x && j == y)) {
					if (cells[i][j].isAlive()) {
						numberOfLivingNeighbors++;
					}
				}
			}
		}

		return numberOfLivingNeighbors;
	}
	
}
