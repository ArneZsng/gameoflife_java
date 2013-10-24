package de.MakaitGhahramanianZeising.model;

import java.util.HashSet;

public abstract class Game {
	
	protected Cell[][] cells;
	private int round;
	protected HashSet<Integer> survives;
	protected HashSet<Integer> revives;
	
	public int getWidth() {
		return cells.length;
	}
	
	public int getHeight() {
		return cells[0].length;
	}
	
	public Cell[][] getCells() {
		return cells;
	}
	
	public void prepareNextRound() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				if (cellAlive(i,j)) {
					cells[i][j].willBeAlive(cellSurvives(numberOfLivingNeighbors(i,j)));
				} else {
					cells[i][j].willBeAlive(cellRevives(numberOfLivingNeighbors(i,j)));
				}
			}
		}
	}
	
	public void playNextRound() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				cells[i][j].updateStatus();
				round++;
			}
		}
	}
	
	public boolean cellAlive(int x, int y) {
		return cells[x][y].isAlive();
	}
	
	private boolean cellSurvives(int livingNeighbors) {
		return survives.contains(livingNeighbors);
	}
	
	private boolean cellRevives(int livingNeighbors) {
		return revives.contains(livingNeighbors);
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
