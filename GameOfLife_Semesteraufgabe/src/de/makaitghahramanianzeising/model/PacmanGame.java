package de.makaitghahramanianzeising.model;

import java.util.Arrays;
import java.util.HashSet;

public class PacmanGame extends Game {

    public PacmanGame(Cell[][] cells, Integer[] survives, Integer[] revives) {
        this.cells = cells.clone();
        this.survives = new HashSet<Integer>(Arrays.asList(survives));
        this.revives = new HashSet<Integer>(Arrays.asList(revives));
    }

    @Override
    public int numberOfLivingNeighbors(int x, int y) {
        int numberOfLivingNeighbors = 0;
        x = ensurePositiveCoordinate(x, getWidth());
        y = ensurePositiveCoordinate(y, getHeight());

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!(i == 0 && j == 0)) {
                    int xCoordinate = (x+i) % getWidth(); 
                    int yCoordinate = (y+j) % getHeight();
                    if (cells[xCoordinate][yCoordinate].isAlive()) {
                        numberOfLivingNeighbors++;
                    }
                }
            }
        }

        return numberOfLivingNeighbors;
    }

    private int ensurePositiveCoordinate(int i, int dimension) {
        if (i == 0) {
            i += dimension;
        }
        return i;
    }

}
