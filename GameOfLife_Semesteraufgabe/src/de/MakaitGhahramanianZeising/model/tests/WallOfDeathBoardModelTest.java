package de.MakaitGhahramanianZeising.model.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.MakaitGhahramanianZeising.model.BoardModel;
import de.MakaitGhahramanianZeising.model.CellModel;
import de.MakaitGhahramanianZeising.model.WallOfDeathBoardModel;

public class WallOfDeathBoardModelTest {

	private BoardModel board;
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
		board = new WallOfDeathBoardModel(cells);
		//then
		assertEquals(8, board.numberOfLivingNeighbors(1,1));
		assertEquals(5, board.numberOfLivingNeighbors(0,1));
		assertEquals(5, board.numberOfLivingNeighbors(1,2));
		assertEquals(3, board.numberOfLivingNeighbors(0,0));
	}
	
}
