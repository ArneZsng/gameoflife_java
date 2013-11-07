package de.MakaitGhahramanianZeising.tests.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.MakaitGhahramanianZeising.model.Cell;

public class CellTest {
	
	@Test
	public void shouldBeEqual() {
		//assume
		Cell cell1 = new Cell(true);
		Cell cell2 = new Cell(true);
		//then
		assertTrue(cell1.equals(cell2));
	}
	
	@Test
	public void shouldNotBeEqual() {
		//assume
		Cell cell1 = new Cell(true);
		Cell cell2 = new Cell(false);
		//then
		assertFalse(cell1.equals(cell2));
	}
	
	@Test
	public void shouldNotBeEqualWithInt() {
		//assume
		Cell cell1 = new Cell(true);
		//then
		assertFalse(cell1.equals(1));
	}
	
	@Test
	public void shoudHaveEqualHash() {
		//assume
		Cell cell1 = new Cell(true);
		Cell cell2 = new Cell(true);
		//then
		assertTrue(cell1.hashCode() == cell2.hashCode());
	}
	
	@Test
	public void shoudNotHaveEqualHash() {
		//assume
		Cell cell1 = new Cell(true);
		Cell cell2 = new Cell(false);
		//then
		assertFalse(cell1.hashCode() == cell2.hashCode());
	}
	
}