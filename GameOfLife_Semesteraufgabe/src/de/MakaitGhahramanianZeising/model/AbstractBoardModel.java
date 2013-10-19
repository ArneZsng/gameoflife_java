package de.MakaitGhahramanianZeising.model;

import java.util.Random;

public abstract class AbstractBoardModel implements BoardModel {
	
	Random randomizer = new Random();
	
	protected CellModel[][] cells;
	
	@Override
	public void populate() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				cells[i][j] = new CellModel(randomizer.nextBoolean());
			}
		}
	}
	
	@Override
	public int getWidth() {
		return cells.length;
	}
	
	@Override
	public int getHeight() {
		return cells[0].length;
	}
	
	@Override
	public CellModel[][] getCells() {
		return cells;
	}

	@Override
	public int numberOfLivingCells() {
		int numberOfLivingCells = 0;
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				if (cells[i][j].isAlive()) {
					numberOfLivingCells++;
				}
			}
		}
		return numberOfLivingCells;
	}
	
	@Override
	public int numberOfLivingNeighbors(int x, int y) {
		return countLivingNeighbors(x, y);
	}
	
	@Override
	public void updateStatus() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				cells[i][j].updateStatus();
			}
		}
	}
	
	@Override
	public boolean isAlive(int x, int y) {
		return cells[x][y].isAlive();
	}
	
	@Override
	public void cellWillBeAlive(int x, int y) {
		cells[x][y].willBeAlive();
	}
	
	@Override
	public void cellWillBeDead(int x, int y) {
		cells[x][y].willBeDead();
	}
	
	protected abstract int countLivingNeighbors(int x, int y);

}
