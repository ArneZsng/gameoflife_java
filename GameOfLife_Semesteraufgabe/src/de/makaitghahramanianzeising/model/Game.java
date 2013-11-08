package de.makaitghahramanianzeising.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public abstract class Game extends Observable implements Runnable {

    protected Cell[][] cells;
    private int round = 0;
    private int msSpeed = 500;
    protected Set<Integer> survives = new HashSet<Integer>();
    protected Set<Integer> revives = new HashSet<Integer>();

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

    public void setRound(int round) {
        this.round = round;
    }

    public String getRoundAsString() {
        if (round <= 999999999) {
            return String.valueOf(round);
        } else {
            return "Unz??hlbar!";
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
        increaseRound();
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
    
    private void increaseRound() {
        if (round <= 999999999) {
            round++;
        }
    }
    
    protected void setCells(Cell[][] cells) {
        if(cells == null) { 
            this.cells = new Cell[0][0]; 
        } else { 
            this.cells = Arrays.copyOf(cells, cells.length); 
        } 
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
