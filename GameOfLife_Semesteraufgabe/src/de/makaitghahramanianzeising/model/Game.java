package de.makaitghahramanianzeising.model;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public abstract class Game extends Observable implements Runnable {

    protected Cell[][] board;
    private int round = 0;
    private int msSpeed = 500;
    protected Set<Integer> survives = new HashSet<Integer>();
    protected Set<Integer> revives = new HashSet<Integer>();

    public void run() {
        try {
            Thread.sleep(msSpeed);
        } catch (InterruptedException ex) {
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
            } catch (InterruptedException ex) {
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
            return "Unzählbar!";
        }
    }

    public int getWidth() {
        return board.length;
    }

    public int getHeight() {
        return board[0].length;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void prepareNextRound() {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
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
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                board[i][j].updateStatus();
            }
        }
        increaseRound();
        notifyObservers();
    }

    public boolean cellAlive(int x, int y) {
        return board[x][y].isAlive();
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

    public boolean isGameOver() {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (board[i][j].willEvolve()) {
                    return false;
                }
            }
        }
        return true;
    }

    public abstract int numberOfLivingNeighbors(int x, int y);

}
