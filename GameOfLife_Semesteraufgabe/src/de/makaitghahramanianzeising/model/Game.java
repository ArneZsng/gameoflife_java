package de.makaitghahramanianzeising.model;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public abstract class Game extends Observable implements Runnable {

    private static int MAXROUND = 999999999;
    
    protected Cell[][] board;
    protected Set<Integer> survives = new HashSet<Integer>();
    protected Set<Integer> revives = new HashSet<Integer>();
    private int round = 0;
    private int msSpeed = 500;

    public void run() {
        sleep();
        while (!isInterrupted()) {
            prepareNextRound();
            if (!isGameOver()) {
                playNextRound();
            } else {
                break;
            }
            sleep();
        }
    }

    public int getSpeed() {
        return msSpeed;
    }
    
    public void setSpeed(int speed) {
        this.msSpeed = speed;
    }

    public String getRoundAsString() {
        if (round <= MAXROUND) {
            return String.valueOf(round);
        } else {
            return "Unzählbar!";
        }
    }

    public int getBoardWidth() {
        return board.length;
    }

    public int getBoardHeight() {
        return board[0].length;
    }

    public Cell[][] getBoard() {
        return board;
    }
    
    public boolean cellAlive(int x, int y) {
        return board[x][y].isAlive();
    }
    
    public boolean isInterrupted() {
        return Thread.currentThread().isInterrupted();
    }

    public void prepareNextRound() {
        for (int i = 0; i < getBoardWidth(); i++) {
            for (int j = 0; j < getBoardHeight(); j++) {
                if (cellAlive(i, j)) {
                    board[i][j].willBeAlive(cellSurvives(numberOfLivingNeighbors(i, j)));
                } else {
                    board[i][j].willBeAlive(cellRevives(numberOfLivingNeighbors(i, j)));
                }
            }
        }
    }

    public void playNextRound() {
        setChanged();
        for (int i = 0; i < getBoardWidth(); i++) {
            for (int j = 0; j < getBoardHeight(); j++) {
                board[i][j].updateStatus();
            }
        }
        increaseRound();
        notifyObservers();
    }
    
    public boolean isGameOver() {
        for (int i = 0; i < getBoardWidth(); i++) {
            for (int j = 0; j < getBoardHeight(); j++) {
                if (board[i][j].willEvolve()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public abstract int numberOfLivingNeighbors(int x, int y);

    private boolean cellSurvives(int livingNeighbors) {
        return survives.contains(livingNeighbors);
    }

    private boolean cellRevives(int livingNeighbors) {
        return revives.contains(livingNeighbors);
    }

    private void increaseRound() {
        if (round <= MAXROUND) {
            round++;
        }
    }
    
    private void sleep() {
        try {
            Thread.sleep(msSpeed);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
