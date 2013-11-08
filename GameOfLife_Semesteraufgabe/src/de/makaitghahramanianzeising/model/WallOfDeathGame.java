package de.makaitghahramanianzeising.model;

import java.util.Arrays;
import java.util.HashSet;

public class WallOfDeathGame extends Game {

    public WallOfDeathGame(Cell[][] cells, Integer[] survives, Integer[] revives) {
        this.cells = cells.clone();
        this.survives = new HashSet<Integer>(Arrays.asList(survives));
        this.revives = new HashSet<Integer>(Arrays.asList(revives));
    }

    @Override
    public int numberOfLivingNeighbors(int x, int y) {
        int numberOfLivingNeighbors = 0;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!(i == 0 && j == 0)) {
                    int xCoordinate = x+i; 
                    int yCoordinate = y+j;
                    if (cellExists(xCoordinate, yCoordinate) && cells[xCoordinate][yCoordinate].isAlive()) {
                        numberOfLivingNeighbors++;
                    }
                }
            }
        }

        return numberOfLivingNeighbors;
    }
    
    private boolean cellExists(int xCoordinate, int yCoordinate) {
        return xCoordinate >= 0 
                && xCoordinate < getWidth() 
                && yCoordinate >= 0 
                && yCoordinate < getHeight();
    }

}
