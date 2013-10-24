package de.MakaitGhahramanianZeising.tests.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.MakaitGhahramanianZeising.enums.ModeEnum;
import de.MakaitGhahramanianZeising.model.Game;
import de.MakaitGhahramanianZeising.model.Cell;
import de.MakaitGhahramanianZeising.model.WallOfDeathGame;

public class WallOfDeathGameTest {

	private Game game;
	private Cell[][] cells = new Cell[3][3];
	
	@Test
	public void shouldCountAllLivingNeighbors() {
		//assume
		cells[0][0] = new Cell(true);
		cells[0][1] = new Cell(true);
		cells[0][2] = new Cell(true);
		cells[1][0] = new Cell(true);
		cells[1][1] = new Cell(true);
		cells[1][2] = new Cell(true);
		cells[2][0] = new Cell(true);
		cells[2][1] = new Cell(true);
		cells[2][2] = new Cell(true);	
		game = new WallOfDeathGame(cells, ModeEnum.GAMEOFLIFE.getSurvives(), ModeEnum.GAMEOFLIFE.getRevives());
		//then
		assertEquals(8, game.numberOfLivingNeighbors(1,1));
		assertEquals(5, game.numberOfLivingNeighbors(0,1));
		assertEquals(5, game.numberOfLivingNeighbors(1,2));
		assertEquals(3, game.numberOfLivingNeighbors(0,0));
	}
	
}
