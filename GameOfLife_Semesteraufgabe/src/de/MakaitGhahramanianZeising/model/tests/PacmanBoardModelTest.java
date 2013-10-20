package de.MakaitGhahramanianZeising.model.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.MakaitGhahramanianZeising.model.BoardModel;
import de.MakaitGhahramanianZeising.model.CellModel;
import de.MakaitGhahramanianZeising.model.PacmanBoardModel;

public class PacmanBoardModelTest {
	
	private BoardModel board;
	
	@Test
	public void shouldCountAllLivingNeighbors() {
		//assume
		CellModel[][] cells = new CellModel[3][3];
		cells[0][0] = new CellModel(true);
		cells[0][1] = new CellModel(true);
		cells[0][2] = new CellModel(true);
		cells[1][0] = new CellModel(true);
		cells[1][1] = new CellModel(true);
		cells[1][2] = new CellModel(true);
		cells[2][0] = new CellModel(false);
		cells[2][1] = new CellModel(false);
		cells[2][2] = new CellModel(true);		
		board = new PacmanBoardModel(cells);
		//then
		assertEquals(6, board.numberOfLivingNeighbors(1,1));
		assertEquals(6, board.numberOfLivingNeighbors(0,1));
		assertEquals(6, board.numberOfLivingNeighbors(1,2));
		assertEquals(6, board.numberOfLivingNeighbors(0,0));
	}
	
	@Test
	public void shouldCountAllLivingNeighborsForSingleColumn() {
		//assume
		CellModel[][] cells = new CellModel[1][3];
		cells[0][0] = new CellModel(true);
		cells[0][1] = new CellModel(true);
		cells[0][2] = new CellModel(false);	
		board = new PacmanBoardModel(cells);
		//then
		assertEquals(5, board.numberOfLivingNeighbors(0,0));
	}
	
	@Test
	public void shouldCountAllLivingNeighborsForSingleRow() {
		//assume
		CellModel[][] cells = new CellModel[3][1];
		cells[0][0] = new CellModel(true);
		cells[1][0] = new CellModel(true);
		cells[2][0] = new CellModel(false);	
		board = new PacmanBoardModel(cells);
		//then
		assertEquals(5, board.numberOfLivingNeighbors(0,0));
	}
	
	@Test
	public void shouldCountAllLivingNeighborsForDoubleColumn() {
		//assume
		CellModel[][] cells = new CellModel[2][3];
		cells[0][0] = new CellModel(true);
		cells[0][1] = new CellModel(true);
		cells[0][2] = new CellModel(false);
		cells[1][0] = new CellModel(false);
		cells[1][1] = new CellModel(false);
		cells[1][2] = new CellModel(true);
		board = new PacmanBoardModel(cells);
		//then
		assertEquals(3, board.numberOfLivingNeighbors(0,0));
	}
	
	@Test
	public void shouldCountAllLivingNeighborsForDoubleRow() {
		//assume
		CellModel[][] cells = new CellModel[3][2];
		cells[0][0] = new CellModel(true);
		cells[0][1] = new CellModel(true);
		cells[1][0] = new CellModel(false);
		cells[1][1] = new CellModel(true);
		cells[2][0] = new CellModel(false);
		cells[2][1] = new CellModel(true);	
		board = new PacmanBoardModel(cells);
		//then
		assertEquals(6, board.numberOfLivingNeighbors(0,0));
	}

}
