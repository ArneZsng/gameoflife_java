package de.makaitghahramanianzeising.model;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Returns a pacman game where the cells neighbors are
 * direct neighbors as well as those on the other side
 * of the universe. For example: For a cell in the upper
 * left corner, neighbors are direct neighbors as well as
 * the cells in the bottom left, the upper right and the one
 * in the bottom right corner.
 */

public class PacmanGame extends AbstractGame {

    public PacmanGame(Cell[][] board, Integer[] survives, Integer[] revives) {
        this.board = board.clone();
        this.survives = new HashSet<Integer>(Arrays.asList(survives));
        this.revives = new HashSet<Integer>(Arrays.asList(revives));
    }

    @Override
    public int numberOfLivingNeighbors(int x, int y) {
        int numberOfLivingNeighbors = 0;
        int xPositive = ensurePositiveCoordinate(x, getBoardWidth());
        int yPositive = ensurePositiveCoordinate(y, getBoardHeight());

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!(i == 0 && j == 0)) {
                    int xCoordinate = (xPositive + i) % getBoardWidth();
                    int yCoordinate = (yPositive + j) % getBoardHeight();
                    if (board[xCoordinate][yCoordinate].isAlive()) {
                        numberOfLivingNeighbors++;
                    }
                }
            }
        }

        return numberOfLivingNeighbors;
    }

    private int ensurePositiveCoordinate(int i, int dimension) {
        int iPositive = i;
        if (iPositive == 0) {
            iPositive += dimension;
        }
        return iPositive;
    }

}
