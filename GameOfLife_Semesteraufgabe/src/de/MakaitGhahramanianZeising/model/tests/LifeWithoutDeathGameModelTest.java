package de.MakaitGhahramanianZeising.model.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.MakaitGhahramanianZeising.model.BoardModel;
import de.MakaitGhahramanianZeising.model.CellModel;
import de.MakaitGhahramanianZeising.model.GameModel;
import de.MakaitGhahramanianZeising.model.LifeWithoutDeathGameModel;
import de.MakaitGhahramanianZeising.model.WallOfDeathBoardModel;

public class LifeWithoutDeathGameModelTest {

	private GameModel game;
	private BoardModel board;
	private CellModel[][] cells = new CellModel[3][3];
	
	@Test
	public void shouldStayDeadDueToUnderpopulation() {
		//assume
		cells[0][0] = new CellModel(true);
		cells[0][1] = new CellModel(true);
		cells[0][2] = new CellModel(false);
		cells[1][0] = new CellModel(false);
		cells[1][1] = new CellModel(false);
		cells[1][2] = new CellModel(false);
		cells[2][0] = new CellModel(false);
		cells[2][1] = new CellModel(false);
		cells[2][2] = new CellModel(false);		
		board = new WallOfDeathBoardModel(cells);
		game = new LifeWithoutDeathGameModel(board);
		//given
		game.prepareNextRound();
		//when
		game.playNextRound();
		//then
		assertFalse(game.getStatus(1,1));
	}

	@Test
	public void shouldStayDeadDueToOverpopulation() {
		//assume
		cells[0][0] = new CellModel(true);
		cells[0][1] = new CellModel(true);
		cells[0][2] = new CellModel(true);
		cells[1][0] = new CellModel(true);
		cells[1][1] = new CellModel(false);
		cells[1][2] = new CellModel(false);
		cells[2][0] = new CellModel(false);
		cells[2][1] = new CellModel(false);
		cells[2][2] = new CellModel(false);		
		board = new WallOfDeathBoardModel(cells);
		game = new LifeWithoutDeathGameModel(board);
		//given
		game.prepareNextRound();
		//when
		game.playNextRound();
		//then
		assertFalse(game.getStatus(1,1));
	}
	
	@Test
	public void shouldStayAliveWithZeroNeighbors() {
		//assume
		cells[0][0] = new CellModel(false);
		cells[0][1] = new CellModel(false);
		cells[0][2] = new CellModel(false);
		cells[1][0] = new CellModel(false);
		cells[1][1] = new CellModel(true);
		cells[1][2] = new CellModel(false);
		cells[2][0] = new CellModel(false);
		cells[2][1] = new CellModel(false);
		cells[2][2] = new CellModel(false);		
		board = new WallOfDeathBoardModel(cells);
		game = new LifeWithoutDeathGameModel(board);
		//given
		game.prepareNextRound();
		//when
		game.playNextRound();
		//then
		assertTrue(game.getStatus(1,1));
	}
	
	@Test
	public void shouldStayAliveWithEightNeighbors() {
		//assume
		cells[0][0] = new CellModel(true);
		cells[0][1] = new CellModel(true);
		cells[0][2] = new CellModel(true);
		cells[1][0] = new CellModel(true);
		cells[1][1] = new CellModel(true);
		cells[1][2] = new CellModel(true);
		cells[2][0] = new CellModel(true);
		cells[2][1] = new CellModel(true);
		cells[2][2] = new CellModel(true);		
		board = new WallOfDeathBoardModel(cells);
		game = new LifeWithoutDeathGameModel(board);
		//given
		game.prepareNextRound();
		//when
		game.playNextRound();
		//then
		assertTrue(game.getStatus(1,1));
	}

	@Test
	public void shouldReviveWithThreeNeighbors() {
		//assume
		cells[0][0] = new CellModel(true);
		cells[0][1] = new CellModel(true);
		cells[0][2] = new CellModel(true);
		cells[1][0] = new CellModel(false);
		cells[1][1] = new CellModel(false);
		cells[1][2] = new CellModel(false);
		cells[2][0] = new CellModel(false);
		cells[2][1] = new CellModel(false);
		cells[2][2] = new CellModel(false);		
		board = new WallOfDeathBoardModel(cells);
		game = new LifeWithoutDeathGameModel(board);
		//given
		game.prepareNextRound();
		//when
		game.playNextRound();
		//then
		assertTrue(game.getStatus(1,1));
	}
	
}
