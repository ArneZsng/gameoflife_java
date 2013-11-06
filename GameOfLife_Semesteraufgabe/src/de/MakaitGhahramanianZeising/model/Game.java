package de.MakaitGhahramanianZeising.model;

import java.util.HashSet;
import java.util.Observable;

public abstract class Game extends Observable implements Runnable {
	
	protected Cell[][] cells;
	private int round = 1;
	private int msSpeed = 500;
	protected HashSet<Integer> survives;
	protected HashSet<Integer> revives;
	
	public void run() {
		try {
		    Thread.sleep(msSpeed);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		while (!isInterrupted()) {
			prepareNextRound();
			if (!isGameOver()) {
				playNextRound();
			} else {
				break;
			}
			try {
			    Thread.sleep(msSpeed);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public boolean isInterrupted() {
		return Thread.currentThread().isInterrupted();
	}
	
	public void setSpeed(int speed) {
		this.msSpeed = speed;
	}
	
	public int getSpeed() {
		return msSpeed;
	}
	
	public int getRound() {
		return round;
	}
	
	public String getRoundAsString() {
		if (round <= 999999999) {
			return String.valueOf(round);
		} else {
			return "Unzählbar!";
		}
	}
	
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
		setChanged();
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				cells[i][j].updateStatus();
			}
		}
		if (round <= 999999999) {
			round++;
		}
		notifyObservers();
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
