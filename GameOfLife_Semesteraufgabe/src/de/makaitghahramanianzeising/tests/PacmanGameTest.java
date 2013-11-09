package de.makaitghahramanianzeising.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.makaitghahramanianzeising.enums.ModeEnum;
import de.makaitghahramanianzeising.model.Cell;
import de.makaitghahramanianzeising.model.PacmanGame;

public class PacmanGameTest {

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
        board[2][0] = new Cell(false);
        board[2][1] = new Cell(false);
        board[2][2] = new Cell(true);
        PacmanGame game = new PacmanGame(board, ModeEnum.GAMEOFLIFE.getSurvives(), ModeEnum.GAMEOFLIFE.getRevives());
        //then
        assertEquals(6, game.numberOfLivingNeighbors(1, 1));
        assertEquals(6, game.numberOfLivingNeighbors(0, 1));
        assertEquals(6, game.numberOfLivingNeighbors(1, 2));
        assertEquals(6, game.numberOfLivingNeighbors(0, 0));
    }

    @Test
    public void shouldCountAllLivingNeighborsForSingleColumn() {
        //assume
        Cell[][] board = new Cell[1][3];
        board[0][0] = new Cell(true);
        board[0][1] = new Cell(true);
        board[0][2] = new Cell(false);
        PacmanGame game = new PacmanGame(board, ModeEnum.GAMEOFLIFE.getSurvives(), ModeEnum.GAMEOFLIFE.getRevives());
        //then
        assertEquals(5, game.numberOfLivingNeighbors(0, 0));
    }

    @Test
    public void shouldCountAllLivingNeighborsForSingleRow() {
        //assume
        Cell[][] board = new Cell[3][1];
        board[0][0] = new Cell(true);
        board[1][0] = new Cell(true);
        board[2][0] = new Cell(false);
        PacmanGame game = new PacmanGame(board, ModeEnum.GAMEOFLIFE.getSurvives(), ModeEnum.GAMEOFLIFE.getRevives());
        //then
        assertEquals(5, game.numberOfLivingNeighbors(0, 0));
    }

    @Test
    public void shouldCountAllLivingNeighborsForDoubleColumn() {
        //assume
        Cell[][] board = new Cell[2][3];
        board[0][0] = new Cell(true);
        board[0][1] = new Cell(true);
        board[0][2] = new Cell(false);
        board[1][0] = new Cell(false);
        board[1][1] = new Cell(false);
        board[1][2] = new Cell(true);
        PacmanGame game = new PacmanGame(board, ModeEnum.GAMEOFLIFE.getSurvives(), ModeEnum.GAMEOFLIFE.getRevives());
        //then
        assertEquals(3, game.numberOfLivingNeighbors(0, 0));
    }

    @Test
    public void shouldCountAllLivingNeighborsForDoubleRow() {
        //assume
        Cell[][] board = new Cell[3][2];
        board[0][0] = new Cell(true);
        board[0][1] = new Cell(true);
        board[1][0] = new Cell(false);
        board[1][1] = new Cell(true);
        board[2][0] = new Cell(false);
        board[2][1] = new Cell(true);
        PacmanGame game = new PacmanGame(board, ModeEnum.GAMEOFLIFE.getSurvives(), ModeEnum.GAMEOFLIFE.getRevives());
        //then
        assertEquals(6, game.numberOfLivingNeighbors(0, 0));
    }

}
