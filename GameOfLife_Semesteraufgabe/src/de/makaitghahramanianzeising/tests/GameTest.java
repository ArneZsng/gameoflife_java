package de.makaitghahramanianzeising.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.makaitghahramanianzeising.enums.BoardTypeEnum;
import de.makaitghahramanianzeising.enums.ModeEnum;
import de.makaitghahramanianzeising.model.Cell;
import de.makaitghahramanianzeising.model.AbstractGame;
import de.makaitghahramanianzeising.model.WallOfDeathGame;

/**
 * Tests the game in an integration setting.
 */

public class GameTest {

    private AbstractGame game;

    @Test
    public void shouldBeGameOver() {
        //assume
        Cell[][] board = new Cell[3][3];
        board[0][0] = new Cell(false);
        board[0][1] = new Cell(false);
        board[0][2] = new Cell(false);
        board[1][0] = new Cell(true);
        board[1][1] = new Cell(true);
        board[1][2] = new Cell(true);
        board[2][0] = new Cell(true);
        board[2][1] = new Cell(false);
        board[2][2] = new Cell(false);
        game = new WallOfDeathGame(board, new Integer[] {4}, new Integer[] {4});
        //when
        game.run();
        //then
        assertTrue(game.isGameOver());
    }

    @Test
    public void shouldNotBeGameOver() {
        //assume
        Cell[][] board = new Cell[3][3];
        board[0][0] = new Cell(false);
        board[0][1] = new Cell(false);
        board[0][2] = new Cell(false);
        board[1][0] = new Cell(false);
        board[1][1] = new Cell(true);
        board[1][2] = new Cell(false);
        board[2][0] = new Cell(false);
        board[2][1] = new Cell(false);
        board[2][2] = new Cell(false);
        game = new WallOfDeathGame(board, new Integer[] {1}, new Integer[] {1});
        //when
        game.prepareNextRound();
        //then
        assertFalse(game.isGameOver());
    }

    @Test
    public void shouldDieDueToOverpopulation() {
        //assume
        Cell[][] board = new Cell[3][3];
        board[0][0] = new Cell(true);
        board[0][1] = new Cell(true);
        board[0][2] = new Cell(true);
        board[1][0] = new Cell(true);
        board[1][1] = new Cell(true);
        board[1][2] = new Cell(false);
        board[2][0] = new Cell(false);
        board[2][1] = new Cell(false);
        board[2][2] = new Cell(false);
        game = new WallOfDeathGame(board, new Integer[] {2}, new Integer[] {3});
        //given
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertFalse(game.cellAlive(1, 1));
    }

    @Test
    public void shouldDieDueToUnderpopulation() {
        //assume
        Cell[][] board = new Cell[3][3];
        board[0][0] = new Cell(true);
        board[0][1] = new Cell(false);
        board[0][2] = new Cell(false);
        board[1][0] = new Cell(false);
        board[1][1] = new Cell(true);
        board[1][2] = new Cell(false);
        board[2][0] = new Cell(false);
        board[2][1] = new Cell(false);
        board[2][2] = new Cell(false);
        game = new WallOfDeathGame(board, new Integer[] {2}, new Integer[] {3});
        //given
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertFalse(game.cellAlive(1, 1));
    }

    @Test
    public void shouldStayDeadDueToUnderpopulation() {
        //assume
        Cell[][] board = new Cell[3][3];
        board[0][0] = new Cell(true);
        board[0][1] = new Cell(true);
        board[0][2] = new Cell(false);
        board[1][0] = new Cell(false);
        board[1][1] = new Cell(false);
        board[1][2] = new Cell(false);
        board[2][0] = new Cell(false);
        board[2][1] = new Cell(false);
        board[2][2] = new Cell(false);
        game = new WallOfDeathGame(board, new Integer[] {2}, new Integer[] {3});
        //given
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertFalse(game.cellAlive(1, 1));
    }

    @Test
    public void shouldStayDeadDueToOverpopulation() {
        //assume
        Cell[][] board = new Cell[3][3];
        board[0][0] = new Cell(true);
        board[0][1] = new Cell(true);
        board[0][2] = new Cell(true);
        board[1][0] = new Cell(true);
        board[1][1] = new Cell(false);
        board[1][2] = new Cell(false);
        board[2][0] = new Cell(false);
        board[2][1] = new Cell(false);
        board[2][2] = new Cell(false);
        game = new WallOfDeathGame(board, new Integer[] {2}, new Integer[] {3});
        //given
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertFalse(game.cellAlive(1, 1));
    }

    @Test
    public void shouldStayAlive() {
        //assume
        Cell[][] board = new Cell[3][3];
        board[0][0] = new Cell(true);
        board[0][1] = new Cell(true);
        board[0][2] = new Cell(false);
        board[1][0] = new Cell(false);
        board[1][1] = new Cell(true);
        board[1][2] = new Cell(false);
        board[2][0] = new Cell(false);
        board[2][1] = new Cell(false);
        board[2][2] = new Cell(false);
        game = new WallOfDeathGame(board, new Integer[] {2}, new Integer[] {3});
        //given
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertTrue(game.cellAlive(1, 1));
    }

    @Test
    public void shouldRevive() {
        //assume
        Cell[][] board = new Cell[3][3];
        board[0][0] = new Cell(true);
        board[0][1] = new Cell(true);
        board[0][2] = new Cell(true);
        board[1][0] = new Cell(false);
        board[1][1] = new Cell(false);
        board[1][2] = new Cell(false);
        board[2][0] = new Cell(false);
        board[2][1] = new Cell(false);
        board[2][2] = new Cell(false);
        game = new WallOfDeathGame(board, new Integer[] {2}, new Integer[] {3});
        //given
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertTrue(game.cellAlive(1, 1));
    }

    @Test
    public void shouldReturnModeName() {
        //when
        ModeEnum mode = ModeEnum.GAMEOFLIFE;
        //then
        String name = "Game of Life";
        assertTrue(name.equals(mode.getName()));
    }

    @Test
    public void shouldReturnBoardTypeName() {
        //when
        BoardTypeEnum boardType = BoardTypeEnum.WALLOFDEATH;
        String name = "Wall of Death";
        //then
        assertTrue(name.equals(boardType.getName()));
    }

    @Test
    public void shouldSetAndReturnSpeed() {
        //given
        Cell[][] board = new Cell[1][1];
        game = new WallOfDeathGame(board, new Integer[] {}, new Integer[] {});
        //when
        game.setSpeed(500);
        //then
        assertEquals(game.getSpeed(), 500);
    }

    @Test
    public void shouldReturnRound() {
        //assume
        Cell[][] board = new Cell[3][3];
        board[0][0] = new Cell(true);
        board[0][1] = new Cell(false);
        board[0][2] = new Cell(false);
        board[1][0] = new Cell(false);
        board[1][1] = new Cell(false);
        board[1][2] = new Cell(false);
        board[2][0] = new Cell(false);
        board[2][1] = new Cell(false);
        board[2][2] = new Cell(false);
        game = new WallOfDeathGame(board, new Integer[] {1}, new Integer[] {1});
        //given
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertEquals("1", game.getRoundAsString());
    }

}
