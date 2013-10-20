package de.MakaitGhahramanianZeising.model.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.MakaitGhahramanianZeising.model.GameModel;
import de.MakaitGhahramanianZeising.model.CellModel;
import de.MakaitGhahramanianZeising.model.GameOfLifeModeModel;
import de.MakaitGhahramanianZeising.model.ModeModel;
import de.MakaitGhahramanianZeising.model.WallOfDeathGameModel;

public class WallOfDeathGameModelTest {

	private GameModel game;
	private ModeModel mode;
	private CellModel[][] cells = new CellModel[3][3];
	
	@Test
	public void shouldCountAllLivingNeighbors() {
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
		mode = new GameOfLifeModeModel();
		game = new WallOfDeathGameModel(mode, cells);
		//then
		assertEquals(8, game.numberOfLivingNeighbors(1,1));
		assertEquals(5, game.numberOfLivingNeighbors(0,1));
		assertEquals(5, game.numberOfLivingNeighbors(1,2));
		assertEquals(3, game.numberOfLivingNeighbors(0,0));
	}
	
}
