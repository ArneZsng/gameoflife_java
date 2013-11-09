package de.makaitghahramanianzeising.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import de.makaitghahramanianzeising.enums.ModeEnum;
import de.makaitghahramanianzeising.exceptions.GOLException;
import de.makaitghahramanianzeising.model.PacmanGame;
import de.makaitghahramanianzeising.utils.FileParser;

/**
 * Tests the border behavior of a pacman game. 
 */

public class PacmanGameTest {

    private static final String ENCODING = "UTF-8";
    private static final String FILENAME = "file.gol";
    private static final String LINESEPARATOR = "line.separator";
    
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    @Test
    public void shouldCountAllLivingNeighbors() throws IOException, GOLException {
        //assume
        File tmpFile = folder.newFile(FILENAME);
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile, true);
        CharsetEncoder charsetEncoder = Charset.forName(ENCODING).newEncoder();
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, charsetEncoder);
        try {
            writer.getEncoding();
            writer.write("000");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("111");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("100");
            writer.flush();
        } finally {
            writer.close();
        }
        String filePathString = tmpFile.getAbsolutePath();
        FileParser fileParser = new FileParser(filePathString);
        fileParser.parse();
        //when
        Integer[] survives = ModeEnum.GAMEOFLIFE.getSurvives();
        Integer[] revives = ModeEnum.GAMEOFLIFE.getRevives();
        PacmanGame game = new PacmanGame(fileParser.getBoard(), survives, revives);
        //then
        assertEquals(6, game.numberOfLivingNeighbors(1, 1));
        assertEquals(6, game.numberOfLivingNeighbors(0, 1));
        assertEquals(6, game.numberOfLivingNeighbors(1, 2));
        assertEquals(6, game.numberOfLivingNeighbors(0, 0));
    }

    @Test
    public void shouldCountAllLivingNeighborsForSingleColumn() throws IOException, GOLException {
        //assume
        File tmpFile = folder.newFile(FILENAME);
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile, true);
        CharsetEncoder charsetEncoder = Charset.forName(ENCODING).newEncoder();
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, charsetEncoder);
        try {
            writer.getEncoding();
            writer.write("1");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("1");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("0");
            writer.flush();
        } finally {
            writer.close();
        }
        String filePathString = tmpFile.getAbsolutePath();
        FileParser fileParser = new FileParser(filePathString);
        fileParser.parse();
        //when
        Integer[] survives = ModeEnum.GAMEOFLIFE.getSurvives();
        Integer[] revives = ModeEnum.GAMEOFLIFE.getRevives();
        PacmanGame game = new PacmanGame(fileParser.getBoard(), survives, revives);
        //then
        assertEquals(5, game.numberOfLivingNeighbors(0, 0));
    }

    @Test
    public void shouldCountAllLivingNeighborsForSingleRow() throws IOException, GOLException {
        //assume
        File tmpFile = folder.newFile(FILENAME);
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile, true);
        CharsetEncoder charsetEncoder = Charset.forName(ENCODING).newEncoder();
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, charsetEncoder);
        try {
            writer.getEncoding();
            writer.write("110");
            writer.flush();
        } finally {
            writer.close();
        }
        String filePathString = tmpFile.getAbsolutePath();
        FileParser fileParser = new FileParser(filePathString);
        fileParser.parse();
        //when
        Integer[] survives = ModeEnum.GAMEOFLIFE.getSurvives();
        Integer[] revives = ModeEnum.GAMEOFLIFE.getRevives();
        PacmanGame game = new PacmanGame(fileParser.getBoard(), survives, revives);
        //then
        assertEquals(5, game.numberOfLivingNeighbors(0, 0));
    }

    @Test
    public void shouldCountAllLivingNeighborsForDoubleColumn() throws IOException, GOLException {
        //assume
        File tmpFile = folder.newFile(FILENAME);
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile, true);
        CharsetEncoder charsetEncoder = Charset.forName(ENCODING).newEncoder();
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, charsetEncoder);
        try {
            writer.getEncoding();
            writer.write("10");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("10");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("01");
            writer.flush();
        } finally {
            writer.close();
        }
        String filePathString = tmpFile.getAbsolutePath();
        FileParser fileParser = new FileParser(filePathString);
        fileParser.parse();
        //when
        Integer[] survives = ModeEnum.GAMEOFLIFE.getSurvives();
        Integer[] revives = ModeEnum.GAMEOFLIFE.getRevives();
        PacmanGame game = new PacmanGame(fileParser.getBoard(), survives, revives);
        //then
        assertEquals(3, game.numberOfLivingNeighbors(0, 0));
    }

    @Test
    public void shouldCountAllLivingNeighborsForDoubleRow() throws IOException, GOLException {
        //assume
        File tmpFile = folder.newFile(FILENAME);
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile, true);
        CharsetEncoder charsetEncoder = Charset.forName(ENCODING).newEncoder();
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, charsetEncoder);
        try {
            writer.getEncoding();
            writer.write("100");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("111");
            writer.flush();
        } finally {
            writer.close();
        }
        String filePathString = tmpFile.getAbsolutePath();
        FileParser fileParser = new FileParser(filePathString);
        fileParser.parse();
        //when
        Integer[] survives = ModeEnum.GAMEOFLIFE.getSurvives();
        Integer[] revives = ModeEnum.GAMEOFLIFE.getRevives();
        PacmanGame game = new PacmanGame(fileParser.getBoard(), survives, revives);
        //then
        assertEquals(6, game.numberOfLivingNeighbors(0, 0));
    }

}
