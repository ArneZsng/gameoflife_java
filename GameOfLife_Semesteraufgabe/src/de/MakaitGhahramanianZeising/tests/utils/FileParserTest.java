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

private FileParser fileparser;


	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	private File tmp_file;

	@Test
	public void test_fileTypeIsNotGol() throws Exception {
		
		//assume
		tmp_file = folder.newFile("file.txt");
		String filePathString = tmp_file.getAbsolutePath();
		//then
		try {
		fileparser = new FileParser(filePathString);
		}catch (GOLException e) {
			String msg="Datei muss vom Typ .gol sein.";
			assertEquals(msg,e.getMessage());
		}
	}

	@Test
	public void test_fileSizeTooBig() throws Exception {
		
		//assume
		tmp_file = folder.newFile("file.gol");
		String filePathString = tmp_file.getAbsolutePath();
		Path filePath = Paths.get(filePathString);
		//when
		byte[] bytes = new byte[260000];
		Files.write(filePath, bytes, StandardOpenOption.APPEND);
		//then
		try {
			fileparser = new FileParser(filePathString);
		} catch (GOLException e) {
			String msg="Dateigröße muss kleiner als 250kb sein.";
			assertEquals(msg,e.getMessage());
		}
	}

	@Test
	public void test_fileIsEmpty() throws Exception {
		
		//assume
		tmp_file = folder.newFile("file.gol");
		String filePathString = tmp_file.getAbsolutePath();
		//then
		try {
			fileparser = new FileParser(filePathString);
		} catch (GOLException e) {  
			String msg="Datei darf nicht leer sein.";
			assertEquals(msg, e.getMessage());
		}
	}

	@Test
	public void test_boardDimensionsWrong() throws Exception {
		
		//assume
		tmp_file = folder.newFile("file.gol");
		//when
		FileWriter writer = new FileWriter(tmp_file, true);
		writer.write("01");
		writer.write(System.getProperty("line.separator"));
		writer.write("010");
		writer.flush();
		writer.close();
		String filePathString = tmp_file.getAbsolutePath();
		//then
		try {
			fileparser = new FileParser(filePathString);
		} catch (GOLException e) {  
			String msg="Das Spielbrett muss in jeder Zeile gleich viele Zellen haben.";
			assertEquals(msg, e.getMessage());
		}
	}
		

	@Test
	public void test_fillBoardWithLine() throws Exception {
		
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
			fileparser = new FileParser(filePathString);
		} catch (GOLException e) {  
			String msg="Die Datei darf nur aus 0'en und 1'sen bestehen und muss als UTF-16 encodiert sein.";
			assertEquals(msg, e.getMessage());
		}
	}
	
	@Test 
	public void test_buildBoard() throws Exception {
		
		//assume 
		tmp_file= folder.newFile("file.gol");
		String filePathString=tmp_file.getAbsolutePath();
		//when 
		tmp_file.delete();
		//then 
		try {
			fileparser = new FileParser(filePathString);
		} catch (GOLException e) {  
			String msg="Die Datei konnte nicht geöffnet werden.";
			assertEquals(msg, e.getMessage());
		}
	}
		
	
}

