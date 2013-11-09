package de.makaitghahramanianzeising.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.makaitghahramanianzeising.enums.ModeEnum;
import de.makaitghahramanianzeising.model.Cell;
import de.makaitghahramanianzeising.model.WallOfDeathGame;

public class WallOfDeathGameTest {

    @Test
    public void shouldCountAllLivingNeighbors() {
        //assume
        Cell[][] board = new Cell[3][3];
        board[0][0] = new Cell(true);
        board[0][1] = new Cell(true);
        board[0][2] = new Cell(true);
        board[1][0] = new Cell(true);
        board[1][1] = new Cell(true);
        board[1][2] = new Cell(true);
        board[2][0] = new Cell(true);
        board[2][1] = new Cell(true);
        board[2][2] = new Cell(true);	
        WallOfDeathGame game = new WallOfDeathGame(board, ModeEnum.GAMEOFLIFE.getSurvives(), ModeEnum.GAMEOFLIFE.getRevives());
        //then
        assertEquals(8, game.numberOfLivingNeighbors(1,1));
        assertEquals(5, game.numberOfLivingNeighbors(0,1));
        assertEquals(5, game.numberOfLivingNeighbors(1,2));
        assertEquals(3, game.numberOfLivingNeighbors(0,0));
    }

}
