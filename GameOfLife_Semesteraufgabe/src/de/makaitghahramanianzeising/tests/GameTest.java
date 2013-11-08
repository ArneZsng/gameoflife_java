package de.makaitghahramanianzeising.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.makaitghahramanianzeising.enums.BoardTypeEnum;
import de.makaitghahramanianzeising.enums.ModeEnum;
import de.makaitghahramanianzeising.model.Cell;
import de.makaitghahramanianzeising.model.Game;
import de.makaitghahramanianzeising.model.WallOfDeathGame;

public class GameTest {

    private Game game;
    private Cell[][] cells = new Cell[3][3];

    @Test
    public void shouldBeGameOver() {
        //assume
        cells[0][0] = new Cell(false);
        cells[0][1] = new Cell(false);
        cells[0][2] = new Cell(false);
        cells[1][0] = new Cell(true);
        cells[1][1] = new Cell(true);
        cells[1][2] = new Cell(true);
        cells[2][0] = new Cell(true);
        cells[2][1] = new Cell(false);
        cells[2][2] = new Cell(false);
        game = new WallOfDeathGame(cells, new Integer[] {4}, new Integer[] {4});
        //given
        game.run();
        //then
        assertTrue(game.isGameOver());
    }

    @Test
    public void shouldNotBeGameOver() {
        //assume
        cells[0][0] = new Cell(false);
        cells[0][1] = new Cell(false);
        cells[0][2] = new Cell(false);
        cells[1][0] = new Cell(false);
        cells[1][1] = new Cell(true);
        cells[1][2] = new Cell(false);
        cells[2][0] = new Cell(false);
        cells[2][1] = new Cell(false);
        cells[2][2] = new Cell(false);
        game = new WallOfDeathGame(cells, new Integer[] {1}, new Integer[] {1});
        //given
        game.prepareNextRound();
        //then
        assertFalse(game.isGameOver());
    }

    @Test
    public void shouldDieDueToOverpopulation() {
        //assume
        cells[0][0] = new Cell(true);
        cells[0][1] = new Cell(true);
        cells[0][2] = new Cell(true);
        cells[1][0] = new Cell(true);
        cells[1][1] = new Cell(true);
        cells[1][2] = new Cell(false);
        cells[2][0] = new Cell(false);
        cells[2][1] = new Cell(false);
        cells[2][2] = new Cell(false);
        game = new WallOfDeathGame(cells, new Integer[] {2}, new Integer[] {3});
        //given
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertFalse(game.cellAlive(1,1));
    }

    @Test
    public void shouldDieDueToUnderpopulation() {
        //assume
        cells[0][0] = new Cell(true);
        cells[0][1] = new Cell(false);
        cells[0][2] = new Cell(false);
        cells[1][0] = new Cell(false);
        cells[1][1] = new Cell(true);
        cells[1][2] = new Cell(false);
        cells[2][0] = new Cell(false);
        cells[2][1] = new Cell(false);
        cells[2][2] = new Cell(false);		
        game = new WallOfDeathGame(cells, new Integer[] {2}, new Integer[] {3});
        //given
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertFalse(game.cellAlive(1,1));
    }

    @Test
    public void shouldStayDeadDueToUnderpopulation() {
        //assume
        cells[0][0] = new Cell(true);
        cells[0][1] = new Cell(true);
        cells[0][2] = new Cell(false);
        cells[1][0] = new Cell(false);
        cells[1][1] = new Cell(false);
        cells[1][2] = new Cell(false);
        cells[2][0] = new Cell(false);
        cells[2][1] = new Cell(false);
        cells[2][2] = new Cell(false);		
        game = new WallOfDeathGame(cells, new Integer[] {2}, new Integer[] {3});
        //given
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertFalse(game.cellAlive(1,1));
    }

    @Test
    public void shouldStayDeadDueToOverpopulation() {
        //assume
        cells[0][0] = new Cell(true);
        cells[0][1] = new Cell(true);
        cells[0][2] = new Cell(true);
        cells[1][0] = new Cell(true);
        cells[1][1] = new Cell(false);
        cells[1][2] = new Cell(false);
        cells[2][0] = new Cell(false);
        cells[2][1] = new Cell(false);
        cells[2][2] = new Cell(false);		
        game = new WallOfDeathGame(cells, new Integer[] {2}, new Integer[] {3});
        //given
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertFalse(game.cellAlive(1,1));
    }

    @Test
    public void shouldStayAlive() {
        //assume
        cells[0][0] = new Cell(true);
        cells[0][1] = new Cell(true);
        cells[0][2] = new Cell(false);
        cells[1][0] = new Cell(false);
        cells[1][1] = new Cell(true);
        cells[1][2] = new Cell(false);
        cells[2][0] = new Cell(false);
        cells[2][1] = new Cell(false);
        cells[2][2] = new Cell(false);		
        game = new WallOfDeathGame(cells, new Integer[] {2}, new Integer[] {3});
        //given
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertTrue(game.cellAlive(1,1));
    }

    @Test
    public void shouldRevive() {
        //assume
        cells[0][0] = new Cell(true);
        cells[0][1] = new Cell(true);
        cells[0][2] = new Cell(true);
        cells[1][0] = new Cell(false);
        cells[1][1] = new Cell(false);
        cells[1][2] = new Cell(false);
        cells[2][0] = new Cell(false);
        cells[2][1] = new Cell(false);
        cells[2][2] = new Cell(false);
        game = new WallOfDeathGame(cells, new Integer[] {2}, new Integer[] {3});
        //given
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertTrue(game.cellAlive(1,1));
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
        //then
        String name = "Wall of Death";
        assertTrue(name.equals(boardType.getName()));
    }

    @Test
    public void shouldSetAndReturnSpeed() {
        //given
        game = new WallOfDeathGame(cells, new Integer[] {}, new Integer[] {});
        //when
        game.setSpeed(500);
        //then
        assertEquals(game.getSpeed(), 500);
    }

    @Test
    public void shouldReturnRound() {
        //assume
        cells[0][0] = new Cell(true);
        cells[0][1] = new Cell(false);
        cells[0][2] = new Cell(false);
        cells[1][0] = new Cell(false);
        cells[1][1] = new Cell(false);
        cells[1][2] = new Cell(false);
        cells[2][0] = new Cell(false);
        cells[2][1] = new Cell(false);
        cells[2][2] = new Cell(false);
        game = new WallOfDeathGame(cells, new Integer[] {1}, new Integer[] {1});
        //given
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertEquals("1", game.getRoundAsString());
    }

    @Test
    public void shouldReturnUncountable() {
        //given		
        game = new WallOfDeathGame(cells, new Integer[] {}, new Integer[] {});
        //when
        game.setRound(1000000000);
        //then
        assertEquals(game.getRoundAsString(), "Unz??hlbar!");
    }

}
