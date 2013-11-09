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
import de.makaitghahramanianzeising.model.WallOfDeathGame;
import de.makaitghahramanianzeising.utils.FileParser;

/**
 * Tests the border behavior of a wall of death game.
 */

public class WallOfDeathGameTest {

    private static final String ENCODING = "UTF-8";
    private static final String FILENAME = "file.gol";
    private static final String LINESEPARATOR = "line.separator";
    
    private final Integer[] survives = ModeEnum.GAMEOFLIFE.getSurvives();
    private final Integer[] revives = ModeEnum.GAMEOFLIFE.getRevives();
    
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
            writer.write("111");
            writer.write(System.getProperty(LINESEPARATOR));
            writer.write("111");
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
        WallOfDeathGame game = new WallOfDeathGame(fileParser.getBoard(), survives, revives);
        //then
        assertEquals(8, game.numberOfLivingNeighbors(1, 1));
        assertEquals(5, game.numberOfLivingNeighbors(0, 1));
        assertEquals(5, game.numberOfLivingNeighbors(1, 2));
        assertEquals(3, game.numberOfLivingNeighbors(0, 0));
    }

}
