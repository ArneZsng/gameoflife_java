package de.MakaitGhahramanianZeising.tests.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.*;
import org.junit.rules.*;

import de.MakaitGhahramanianZeising.exceptions.GOLException;
import de.MakaitGhahramanianZeising.utils.FileParser;

public class FileParserTest {	

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
			new FileParser(filePathString);
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
			new FileParser(filePathString);
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
			new FileParser(filePathString);
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
			new FileParser(filePathString);
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
			new FileParser(filePathString);
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
			new FileParser(filePathString);
		} catch (GOLException e) {  
			//then
			String msg="Die Datei konnte nicht geöffnet werden.";
			assertEquals(msg, e.getMessage());
		}
	}
		
	
}

