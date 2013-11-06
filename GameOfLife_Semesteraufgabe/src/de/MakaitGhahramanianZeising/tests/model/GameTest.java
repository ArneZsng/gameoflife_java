package de.MakaitGhahramanianZeising.tests.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.MakaitGhahramanianZeising.enums.ModeEnum;
import de.MakaitGhahramanianZeising.model.Cell;
import de.MakaitGhahramanianZeising.model.Game;
import de.MakaitGhahramanianZeising.model.WallOfDeathGame;

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
		cells[2][0] = new Cell(false);
		cells[2][1] = new Cell(false);
		cells[2][2] = new Cell(false);
		game = new WallOfDeathGame(cells, new Integer[] {1,2}, new Integer[] {0});
		//given
		game.prepareNextRound();
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

}
