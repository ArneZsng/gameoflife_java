package de.makaitghahramanianzeising.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.*;
import org.junit.rules.*;

import de.makaitghahramanianzeising.exceptions.GOLException;
import de.makaitghahramanianzeising.model.Cell;
import de.makaitghahramanianzeising.utils.FileParser;

public class FileParserTest {	
	
	private Cell[][] cells = new Cell[3][3];
	private FileParser fileParser;
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	private File tmp_file;

	@Test
	public void shouldThrowErrorWhenFileTypeIsNotGol() throws Exception {
		//assume
		tmp_file = folder.newFile("file.txt");
		//given
		String filePathString = tmp_file.getAbsolutePath();
		//when
        try {
            fileParser = new FileParser(filePathString);
            fileParser.parse();
		} catch (GOLException e) {
			//then
			String msg = "Datei muss vom Typ .gol sein.";
			assertEquals(msg,e.getMessage());
		}
	}

	@Test
	public void shouldThrowErrorWhenFileSizeTooBig() throws Exception {
		//assume
		tmp_file = folder.newFile("file.gol");
		//given
		String filePathString = tmp_file.getAbsolutePath();
		Path filePath = Paths.get(filePathString);
		byte[] bytes = new byte[260000];
		Files.write(filePath, bytes, StandardOpenOption.APPEND);
		//when
        try {
            fileParser = new FileParser(filePathString);
            fileParser.parse();
		} catch (GOLException e) {
			//then
			String msg="Dateigröße darf maximal 250kb betragen.";
			assertEquals(msg,e.getMessage());
		}
	}

	@Test
	public void shouldThrowErrorWhenFileIsEmpty() throws Exception {
		//assume
		tmp_file = folder.newFile("file.gol");
		//given
		String filePathString = tmp_file.getAbsolutePath();
		//when
        try {
            fileParser = new FileParser(filePathString);
            fileParser.parse();
		} catch (GOLException e) { 
			//then
			String msg="Datei darf nicht leer sein.";
			assertEquals(msg, e.getMessage());
		}
	}

	@Test
	public void shouldThrowErrorWhenBoardDimensionsWrong() throws Exception {
		//assume
		tmp_file = folder.newFile("file.gol");
		//given
		FileWriter writer = new FileWriter(tmp_file, true);
		writer.write("01");
		writer.write(System.getProperty("line.separator"));
		writer.write("010");
		writer.flush();
		writer.close();
		String filePathString = tmp_file.getAbsolutePath();
		//when
        try {
            fileParser = new FileParser(filePathString);
            fileParser.parse();
		} catch (GOLException e) {  
			//then
			String msg="Das Spielbrett muss in jeder Zeile gleich viele Zellen haben.";
			assertEquals(msg, e.getMessage());
		}
	}
		

	@Test
	public void shouldThrowErrorWhenCharactersAreInvalid() throws Exception {
		//assume
		tmp_file = folder.newFile("file.gol");
		FileWriter writer = new FileWriter(tmp_file, true);
		//when
		writer.write("45");
		writer.write(System.getProperty("line.separator"));
		writer.write("44");
		writer.flush();
		writer.close();
		String filePathString = tmp_file.getAbsolutePath();
		//then
        try {
            fileParser = new FileParser(filePathString);
            fileParser.parse();
		} catch (GOLException e) {  
			String msg="Die Datei darf nur aus 0'en und 1'sen bestehen und muss als UTF-16 encodiert sein.";
			assertEquals(msg, e.getMessage());
		}
	}
	
	@Test 
	public void shouldThrowErrorWhenFileCannotBeOpened() throws Exception {
		//assume 
		tmp_file= folder.newFile("file.gol");
		//given
		String filePathString=tmp_file.getAbsolutePath(); 
		tmp_file.delete();
		//when 
        try {
            fileParser = new FileParser(filePathString);
            fileParser.parse();
		} catch (GOLException e) {  
			//then
			String msg="Die Datei konnte nicht geöffnet werden.";
			assertEquals(msg, e.getMessage());
		}
	}
	
	@Test 
	public void shouldThrowErrorWhenFileIsNotSelected() throws Exception {
		//assume 
		String filePathString = null;
		//when 
        try {
            fileParser = new FileParser(filePathString);
            fileParser.parse();
		} catch (GOLException e) {  
			//then
			String msg = "Bitte Datei auswählen.";
			assertEquals(msg, e.getMessage());
		}
	}
	
	@Test
	public void shouldBuildCorrectBoard() throws Exception {
		//assume
		tmp_file = folder.newFile("file.gol");
		FileWriter writer = new FileWriter(tmp_file, true);
		//given
		writer.write("000");
		writer.write(System.getProperty("line.separator"));
		writer.write("110");
		writer.write(System.getProperty("line.separator"));
		writer.write("001");
		writer.flush();
		writer.close();
		String filePathString = tmp_file.getAbsolutePath();
		cells[0][0] = new Cell(false);
		cells[1][0] = new Cell(false);
		cells[2][0] = new Cell(false);
		cells[0][1] = new Cell(true);
		cells[1][1] = new Cell(true);
		cells[2][1] = new Cell(false);
		cells[0][2] = new Cell(false);
		cells[1][2] = new Cell(false);
		cells[2][2] = new Cell(true);
		//when
		try {
			fileParser = new FileParser(filePathString);
			fileParser.parse();
		} catch (Exception e) { 
		}
		//then
		assertArrayEquals(fileParser.getBoard(), cells);
	}
		
	
}

