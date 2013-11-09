package de.makaitghahramanianzeising.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import de.makaitghahramanianzeising.enums.BoardTypeEnum;
import de.makaitghahramanianzeising.enums.ModeEnum;
import de.makaitghahramanianzeising.exceptions.GOLException;
import de.makaitghahramanianzeising.model.Cell;
import de.makaitghahramanianzeising.model.AbstractGame;
import de.makaitghahramanianzeising.model.WallOfDeathGame;
import de.makaitghahramanianzeising.utils.FileParser;

/**
 * Tests the game in an integration setting.
 */

public class GameTest {

    private AbstractGame game;
    private static final String ENCODING = "UTF-8";
    private static final String FILENAME = "file.gol";
    private static final String LINESEPARATOR = "line.separator";

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void shouldBeGameOver() throws IOException, GOLException {
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
        //given
        game = new WallOfDeathGame(fileParser.getBoard(), new Integer[] {4}, new Integer[] {4});
        //when
        game.run();
        //then
        assertTrue(game.isGameOver());
    }

    @Test
    public void shouldNotBeGameOver() throws IOException, GOLException {
        //assume
        File tmpFile = folder.newFile(FILENAME);
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile, true);
        CharsetEncoder charsetEncoder = Charset.forName(ENCODING).newEncoder();
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, charsetEncoder);
        try {
            writer.getEncoding();
            writer.write("000");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("010");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("000");
            writer.flush();
        } finally {
            writer.close();
        }
        String filePathString = tmpFile.getAbsolutePath();
        FileParser fileParser = new FileParser(filePathString);
        fileParser.parse();
        //given
        game = new WallOfDeathGame(fileParser.getBoard(), new Integer[] {1}, new Integer[] {1});
        //when
        game.prepareNextRound();
        //then
        assertFalse(game.isGameOver());
    }

    @Test
    public void shouldDieDueToOverpopulation() throws IOException, GOLException {
        //assume
        File tmpFile = folder.newFile(FILENAME);
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile, true);
        CharsetEncoder charsetEncoder = Charset.forName(ENCODING).newEncoder();
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, charsetEncoder);
        try {
            writer.getEncoding();
            writer.write("111");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("110");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("000");
            writer.flush();
        } finally {
            writer.close();
        }
        String filePathString = tmpFile.getAbsolutePath();
        FileParser fileParser = new FileParser(filePathString);
        fileParser.parse();
        //given
        game = new WallOfDeathGame(fileParser.getBoard(), new Integer[] {2}, new Integer[] {3});
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertFalse(game.cellAlive(1, 1));
    }

    @Test
    public void shouldDieDueToUnderpopulation() throws IOException, GOLException {
        //assume
        File tmpFile = folder.newFile(FILENAME);
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile, true);
        CharsetEncoder charsetEncoder = Charset.forName(ENCODING).newEncoder();
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, charsetEncoder);
        try {
            writer.getEncoding();
            writer.write("100");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("010");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("000");
            writer.flush();
        } finally {
            writer.close();
        }
        String filePathString = tmpFile.getAbsolutePath();
        FileParser fileParser = new FileParser(filePathString);
        fileParser.parse();
        //given
        game = new WallOfDeathGame(fileParser.getBoard(), new Integer[] {2}, new Integer[] {3});
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertFalse(game.cellAlive(1, 1));
    }

    @Test
    public void shouldStayDeadDueToUnderpopulation() throws IOException, GOLException {
        //assume
        File tmpFile = folder.newFile(FILENAME);
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile, true);
        CharsetEncoder charsetEncoder = Charset.forName(ENCODING).newEncoder();
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, charsetEncoder);
        try {
            writer.getEncoding();
            writer.write("110");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("000");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("000");
            writer.flush();
        } finally {
            writer.close();
        }
        String filePathString = tmpFile.getAbsolutePath();
        FileParser fileParser = new FileParser(filePathString);
        fileParser.parse();
        //given
        game = new WallOfDeathGame(fileParser.getBoard(), new Integer[] {2}, new Integer[] {3});
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertFalse(game.cellAlive(1, 1));
    }

    @Test
    public void shouldStayDeadDueToOverpopulation() throws IOException, GOLException {
        //assume
        File tmpFile = folder.newFile(FILENAME);
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile, true);
        CharsetEncoder charsetEncoder = Charset.forName(ENCODING).newEncoder();
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, charsetEncoder);
        try {
            writer.getEncoding();
            writer.write("111");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("100");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("000");
            writer.flush();
        } finally {
            writer.close();
        }
        String filePathString = tmpFile.getAbsolutePath();
        FileParser fileParser = new FileParser(filePathString);
        fileParser.parse();
        //given
        game = new WallOfDeathGame(fileParser.getBoard(), new Integer[] {2}, new Integer[] {3});
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertFalse(game.cellAlive(1, 1));
    }

    @Test
    public void shouldStayAlive() throws IOException, GOLException {
        //assume
        File tmpFile = folder.newFile(FILENAME);
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile, true);
        CharsetEncoder charsetEncoder = Charset.forName(ENCODING).newEncoder();
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, charsetEncoder);
        try {
            writer.getEncoding();
            writer.write("110");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("010");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("000");
            writer.flush();
        } finally {
            writer.close();
        }
        String filePathString = tmpFile.getAbsolutePath();
        FileParser fileParser = new FileParser(filePathString);
        fileParser.parse();
        //given
        game = new WallOfDeathGame(fileParser.getBoard(), new Integer[] {2}, new Integer[] {3});
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertTrue(game.cellAlive(1, 1));
    }

    @Test
    public void shouldRevive() throws IOException, GOLException {
        //assume
        File tmpFile = folder.newFile(FILENAME);
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile, true);
        CharsetEncoder charsetEncoder = Charset.forName(ENCODING).newEncoder();
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, charsetEncoder);
        try {
            writer.getEncoding();
            writer.write("111");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("000");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("000");
            writer.flush();
        } finally {
            writer.close();
        }
        String filePathString = tmpFile.getAbsolutePath();
        FileParser fileParser = new FileParser(filePathString);
        fileParser.parse();
        //given
        game = new WallOfDeathGame(fileParser.getBoard(), new Integer[] {2}, new Integer[] {3});
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertTrue(game.cellAlive(1, 1));
    }

    @Test
    public void shouldReturnModeName() {
        //when
        ModeEnum mode = ModeEnum.GAMEOFLIFE;
        //then
        String name = "Game of Life";
        assertTrue(name.equals(mode.getLabel()));
    }

    @Test
    public void shouldReturnBoardTypeName() {
        //when
        BoardTypeEnum boardType = BoardTypeEnum.WALLOFDEATH;
        String name = "Wall of Death";
        //then
        assertTrue(name.equals(boardType.getLabel()));
    }

    @Test
    public void shouldSetAndReturnSpeed() {
        //given
        Cell[][] board = new Cell[1][1];
        game = new WallOfDeathGame(board, new Integer[] {}, new Integer[] {});
        //when
        game.setSpeed(500);
        //then
        assertEquals(game.getSpeed(), 500);
    }

    @Test
    public void shouldReturnRound() throws IOException, GOLException {
        //assume
        File tmpFile = folder.newFile(FILENAME);
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile, true);
        CharsetEncoder charsetEncoder = Charset.forName(ENCODING).newEncoder();
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, charsetEncoder);
        try {
            writer.getEncoding();
            writer.write("100");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("000");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("000");
            writer.flush();
        } finally {
            writer.close();
        }
        String filePathString = tmpFile.getAbsolutePath();
        FileParser fileParser = new FileParser(filePathString);
        fileParser.parse();
        //given
        game = new WallOfDeathGame(fileParser.getBoard(), new Integer[] {1}, new Integer[] {1});
        game.prepareNextRound();
        //when
        game.playNextRound();
        //then
        assertEquals("1", game.getRoundAsString());
    }

}
