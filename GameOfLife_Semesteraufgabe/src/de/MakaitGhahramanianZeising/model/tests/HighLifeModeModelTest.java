package de.MakaitGhahramanianZeising.model.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.MakaitGhahramanianZeising.model.GameModel;
import de.MakaitGhahramanianZeising.model.CellModel;
import de.MakaitGhahramanianZeising.model.HighLifeModeModel;
import de.MakaitGhahramanianZeising.model.ModeModel;
import de.MakaitGhahramanianZeising.model.WallOfDeathGameModel;

public class HighLifeModeModelTest {
	
	private ModeModel mode;
	private GameModel game;
	private CellModel[][] cells = new CellModel[3][3];
	
	@Test
	public void shouldDieDueToOverpopulation() {
		//assume
		cells[0][0] = new CellModel(true);
		cells[0][1] = new CellModel(true);
		cells[0][2] = new CellModel(true);
		cells[1][0] = new CellModel(true);
		cells[1][1] = new CellModel(true);
		cells[1][2] = new CellModel(false);
		cells[2][0] = new CellModel(false);
		cells[2][1] = new CellModel(false);
		cells[2][2] = new CellModel(false);		
		mode = new HighLifeModeModel();
		game = new WallOfDeathGameModel(mode, cells);
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
		cells[0][0] = new CellModel(true);
		cells[0][1] = new CellModel(false);
		cells[0][2] = new CellModel(false);
		cells[1][0] = new CellModel(false);
		cells[1][1] = new CellModel(true);
		cells[1][2] = new CellModel(false);
		cells[2][0] = new CellModel(false);
		cells[2][1] = new CellModel(false);
		cells[2][2] = new CellModel(false);		
		mode = new HighLifeModeModel();
		game = new WallOfDeathGameModel(mode, cells);
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
		cells[0][0] = new CellModel(true);
		cells[0][1] = new CellModel(true);
		cells[0][2] = new CellModel(false);
		cells[1][0] = new CellModel(false);
		cells[1][1] = new CellModel(false);
		cells[1][2] = new CellModel(false);
		cells[2][0] = new CellModel(false);
		cells[2][1] = new CellModel(false);
		cells[2][2] = new CellModel(false);		
		mode = new HighLifeModeModel();
		game = new WallOfDeathGameModel(mode, cells);
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
		cells[0][0] = new CellModel(true);
		cells[0][1] = new CellModel(true);
		cells[0][2] = new CellModel(true);
		cells[1][0] = new CellModel(true);
		cells[1][1] = new CellModel(false);
		cells[1][2] = new CellModel(false);
		cells[2][0] = new CellModel(false);
		cells[2][1] = new CellModel(false);
		cells[2][2] = new CellModel(false);		
		mode = new HighLifeModeModel();
		game = new WallOfDeathGameModel(mode, cells);
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
		cells[0][0] = new CellModel(true);
		cells[0][1] = new CellModel(true);
		cells[0][2] = new CellModel(false);
		cells[1][0] = new CellModel(false);
		cells[1][1] = new CellModel(true);
		cells[1][2] = new CellModel(false);
		cells[2][0] = new CellModel(false);
		cells[2][1] = new CellModel(false);
		cells[2][2] = new CellModel(false);		
		mode = new HighLifeModeModel();
		game = new WallOfDeathGameModel(mode, cells);
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
		cells[0][0] = new CellModel(true);
		cells[0][1] = new CellModel(true);
		cells[0][2] = new CellModel(true);
		cells[1][0] = new CellModel(false);
		cells[1][1] = new CellModel(true);
		cells[1][2] = new CellModel(false);
		cells[2][0] = new CellModel(false);
		cells[2][1] = new CellModel(false);
		cells[2][2] = new CellModel(false);		
		mode = new HighLifeModeModel();
		game = new WallOfDeathGameModel(mode, cells);
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
		cells[0][0] = new CellModel(true);
		cells[0][1] = new CellModel(true);
		cells[0][2] = new CellModel(true);
		cells[1][0] = new CellModel(false);
		cells[1][1] = new CellModel(false);
		cells[1][2] = new CellModel(false);
		cells[2][0] = new CellModel(false);
		cells[2][1] = new CellModel(false);
		cells[2][2] = new CellModel(false);		
		mode = new HighLifeModeModel();
		game = new WallOfDeathGameModel(mode, cells);
		//given
		game.prepareNextRound();
		//when
		game.playNextRound();
		//then
		assertTrue(game.cellAlive(1,1));	
	}
	
	@Test
	public void shouldReviveWithSixNeighbors() {
		//assume
		cells[0][0] = new CellModel(true);
		cells[0][1] = new CellModel(true);
		cells[0][2] = new CellModel(true);
		cells[1][0] = new CellModel(true);
		cells[1][1] = new CellModel(false);
		cells[1][2] = new CellModel(true);
		cells[2][0] = new CellModel(true);
		cells[2][1] = new CellModel(false);
		cells[2][2] = new CellModel(false);		
		mode = new HighLifeModeModel();
		game = new WallOfDeathGameModel(mode, cells);
		//given
		game.prepareNextRound();
		//when
		game.playNextRound();
		//then
		assertTrue(game.cellAlive(1,1));
	}

}
