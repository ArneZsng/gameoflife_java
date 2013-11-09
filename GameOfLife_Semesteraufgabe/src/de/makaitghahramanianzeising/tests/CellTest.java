package de.makaitghahramanianzeising.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.makaitghahramanianzeising.model.Cell;

public class CellTest {

    @Test
    public void shouldBeEqual() {
        //when
        Cell cell1 = new Cell(true);
        Cell cell2 = new Cell(true);
        //then
        assertTrue(cell1.equals(cell2));
    }

    @Test
    public void shouldNotBeEqual() {
        //when
        Cell cell1 = new Cell(true);
        Cell cell2 = new Cell(false);
        //then
        assertFalse(cell1.equals(cell2));
    }

    @Test
    public void shouldNotBeEqualWithInt() {
        //when
        Cell cell1 = new Cell(true);
        //then
        assertFalse(cell1.equals(1));
    }

    @Test
    public void shoudHaveEqualHash() {
        //when
        Cell cell1 = new Cell(true);
        Cell cell2 = new Cell(true);
        //then
        assertTrue(cell1.hashCode() == cell2.hashCode());
    }

    @Test
    public void shoudNotHaveEqualHash() {
        //when
        Cell cell1 = new Cell(true);
        Cell cell2 = new Cell(false);
        //then
        assertFalse(cell1.hashCode() == cell2.hashCode());
    }

}
