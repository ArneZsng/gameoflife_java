package de.makaitghahramanianzeising.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
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

    private static final String ENCODING = "UTF-8";
    private static final String FILENAME = "file.gol";
    private static final String LINESEPARATOR = "line.separator";


    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void shouldThrowErrorWhenFileTypeIsNotGol() throws IOException {
        //assume
        File tmpFile = folder.newFile("file.txt");
        //given
        String filePathString = tmpFile.getAbsolutePath();
        //when
        try {
            FileParser fileParser = new FileParser(filePathString);
            fileParser.parse();
        } catch (GOLException e) {
            //then
            String msg = "Datei muss vom Typ .gol sein.";
            assertEquals(msg,e.getMessage());
        }
    }

    @Test
    public void shouldThrowErrorWhenFileSizeTooBig() throws IOException {
        //assume
        File tmpFile = folder.newFile(FILENAME);
        //given
        String filePathString = tmpFile.getAbsolutePath();
        Path filePath = Paths.get(filePathString);
        byte[] bytes = new byte[260000];
        Files.write(filePath, bytes, StandardOpenOption.APPEND);
        //when
        try {
            FileParser fileParser = new FileParser(filePathString);
            fileParser.parse();
        } catch (GOLException e) {
            //then
            String msg="Dateigröße darf maximal 250kb betragen.";
            assertEquals(msg,e.getMessage());
        }
    }

    @Test
    public void shouldThrowErrorWhenFileIsEmpty() throws IOException {
        //assume
        File tmpFile = folder.newFile(FILENAME);
        //given
        String filePathString = tmpFile.getAbsolutePath();
        //when
        try {
            FileParser fileParser = new FileParser(filePathString);
            fileParser.parse();
        } catch (GOLException e) { 
            //then
            String msg="Datei darf nicht leer sein.";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void shouldThrowErrorWhenBoardDimensionsWrong() throws IOException {
        //assume
        File tmpFile = folder.newFile(FILENAME);
        OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(tmpFile, true),Charset.forName(ENCODING).newEncoder());
        //given
        try{
            writer.getEncoding(); 
            writer.write("01");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("010");
            writer.flush();
        } finally {
            writer.close();
        }
        String filePathString = tmpFile.getAbsolutePath();
        //when
        try {
            FileParser fileParser = new FileParser(filePathString);
            fileParser.parse();
        } catch (GOLException e) {  
            //then
            String msg="Das Spielbrett muss in jeder Zeile gleich viele Zellen haben.";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void shouldThrowErrorWhenCharactersAreInvalid() throws IOException {
        //assume
        File tmpFile = folder.newFile(FILENAME);
        OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(tmpFile, true),Charset.forName(ENCODING).newEncoder());
        //when
        try {
            writer.getEncoding();
            writer.write("45");
            writer.flush();
        } finally {
            writer.close();
        }
        String filePathString = tmpFile.getAbsolutePath();
        //then
        try {
            FileParser fileParser = new FileParser(filePathString);
            fileParser.parse();
        } catch (GOLException e) {  
            String msg="Die Datei darf nur aus 0'en und 1'sen bestehen und muss als UTF-16 encodiert sein.";
            assertEquals(msg, e.getMessage());
        }
    }


    @Test 
    public void shouldThrowErrorWhenFileCannotBeOpened() throws IOException {
        //assume 
        File tmpFile= folder.newFile(FILENAME);
        //given
        String filePathString=tmpFile.getAbsolutePath(); 
        tmpFile.delete();
        //when 
        try {
            FileParser fileParser = new FileParser(filePathString);
            fileParser.parse();
        } catch (GOLException e) {  
            //then
            String msg="Die Datei konnte nicht geöffnet werden.";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test 
    public void shouldThrowErrorWhenFileIsNotSelected() throws IOException {
        //when 
        try {
            FileParser fileParser = new FileParser(null);
            fileParser.parse();
        } catch (GOLException e) {  
            //then
            String msg = "Bitte Datei auswählen.";
            assertEquals(msg, e.getMessage());
        }}


    @Test
    public void shouldBuildCorrectBoard() throws Exception {
        //assume
        File tmpFile = folder.newFile(FILENAME);
        FileOutputStream fileOutputStream  = new FileOutputStream(tmpFile, true);
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, Charset.forName(ENCODING).newEncoder());
        //given
        try {
            writer.getEncoding(); 
            writer.write("000");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("110");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("001");
            writer.flush();
        } finally{
            writer.close();
        }
        String filePathString = tmpFile.getAbsolutePath();
        //when
        FileParser fileParser = new FileParser(filePathString);
        fileParser.parse();
        //then
        Cell[][] board = new Cell[3][3];
        board[0][0] = new Cell(false);
        board[1][0] = new Cell(false);
        board[2][0] = new Cell(false);
        board[0][1] = new Cell(true);
        board[1][1] = new Cell(true);
        board[2][1] = new Cell(false);
        board[0][2] = new Cell(false);
        board[1][2] = new Cell(false);
        board[2][2] = new Cell(true);
        assertArrayEquals(fileParser.getBoard(), board);
    }
}



