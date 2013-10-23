package de.MakaitGhahramanianZeising.tests.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.MakaitGhahramanianZeising.model.Game;
import de.MakaitGhahramanianZeising.model.Cell;
import de.MakaitGhahramanianZeising.model.GameOfLifeMode;
import de.MakaitGhahramanianZeising.model.Mode;
import de.MakaitGhahramanianZeising.model.WallOfDeathGame;

public class GameOfLifeModeTest {
	
	private Mode mode;
	private Game game;
	private Cell[][] cells = new Cell[3][3];
	
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
		mode = new GameOfLifeMode();
		game = new WallOfDeathGame(mode, cells);
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
		mode = new GameOfLifeMode();
		game = new WallOfDeathGame(mode, cells);
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
		mode = new GameOfLifeMode();
		game = new WallOfDeathGame(mode, cells);
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
		mode = new GameOfLifeMode();
		game = new WallOfDeathGame(mode, cells);
		//given
		game.prepareNextRound();
		//when
		game.playNextRound();
		//then
		assertFalse(game.cellAlive(1,1));
	}
	
	@Test
	public void shouldStayAliveWithTwoNeighbors() {
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
		mode = new GameOfLifeMode();
		game = new WallOfDeathGame(mode, cells);
		//given
		game.prepareNextRound();
		//when
		game.playNextRound();
		//then
		assertTrue(game.cellAlive(1,1));
	}
	
	@Test
	public void shouldStayAliveWithThreeNeighbors() {
		//assume
		cells[0][0] = new Cell(true);
		cells[0][1] = new Cell(true);
		cells[0][2] = new Cell(true);
		cells[1][0] = new Cell(false);
		cells[1][1] = new Cell(true);
		cells[1][2] = new Cell(false);
		cells[2][0] = new Cell(false);
		cells[2][1] = new Cell(false);
		cells[2][2] = new Cell(false);		
		mode = new GameOfLifeMode();
		game = new WallOfDeathGame(mode, cells);
		//given
		game.prepareNextRound();
		//when
		game.playNextRound();
		//then
		assertTrue(game.cellAlive(1,1));
	}

	@Test
	public void shouldReviveWithThreeNeighbors() {
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
		mode = new GameOfLifeMode();
		game = new WallOfDeathGame(mode, cells);
		//given
		game.prepareNextRound();
		//when
		game.playNextRound();
		//then
		assertTrue(game.cellAlive(1,1));
	}

}
