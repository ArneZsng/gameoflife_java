package de.MakaitGhahramanianZeising.model;

public abstract class GameModel {
	
	protected CellModel[][] cells;
	protected ModeModel mode;
	private int generation;
	
	public int getWidth() {
		return cells.length;
	}
	
	public int getHeight() {
		return cells[0].length;
	}
	
	public CellModel[][] getCells() {
		return cells;
	}
	
	public void prepareNextRound() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				if (cellAlive(i,j)) {
					cells[i][j].willBeAlive(mode.cellSurvives(numberOfLivingNeighbors(i,j)));
				} else {
					cells[i][j].willBeAlive(mode.cellRevives(numberOfLivingNeighbors(i,j)));
				}
			}
		}
	}
	
	public void playNextRound() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				cells[i][j].updateStatus();
				generation++;
			}
		}
	}
	
	public boolean cellAlive(int x, int y) {
		return cells[x][y].isAlive();
	}
	
	public boolean isGameOver() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				if (cells[i][j].willEvolve()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public abstract int numberOfLivingNeighbors(int x, int y);

}
