package de.makaitghahramanianzeising.view_csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import de.makaitghahramanianzeising.enums.ModeEnum;
import de.makaitghahramanianzeising.model.AbstractGame;
import de.makaitghahramanianzeising.model.Cell;
import de.makaitghahramanianzeising.model.WallOfDeathGame;
import au.com.bytecode.opencsv.CSVWriter;

public class GameWriter {
    
    private AbstractGame myGame;
    private CSVWriter writer;
    private Cell[][] board;
    private Random randomGenerator;
    private String[] gameString;
    private int gameStringCounter;
    
    public static void main(String[] args) {
        GameWriter gameWriter = new GameWriter();
        gameWriter.initWriter();
        gameWriter.simulate();
        gameWriter.close();
    }
    
    public GameWriter() {
        randomGenerator = new Random();
        String csv = "/Users/Arne/Dropbox/Harvard/STAT183/GameOfLife/Project/train_data.csv";
        try {
            writer = new CSVWriter(new FileWriter(csv), ',', CSVWriter.NO_QUOTE_CHARACTER);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void initWriter() {
        String[] header = new String[2401];
        int i = 0;
        header[i] = "id";
        i++;
        for (int j = 1; j < 7; j++) {
            for (int k = 1; k < 401; k++) {
                header[i] = "board." + j + "." + k;
                i++;
            }
        }
        writer.writeNext(header);
    }
    
    public void initGame() {
        Cell[][] board = generateBoard();
        ModeEnum mode = ModeEnum.GAMEOFLIFE;
        myGame = createGame(board, mode);
        gameString = new String[2401];
        gameStringCounter = 1;
    }
    
    public void simulate() {
        for (int i = 0; i < 50000; i++) {
            initGame();
            gameString[0] = String.valueOf(i + 1);
            System.out.println("Running Game" + (i + 1));
            runGame();
            write();
        }
    }
    
    public void runGame() {
        for (int i = 0; i < 5; i++) {
            myGame.prepareNextRound();
            myGame.playNextRound();
        }
        for (int i = 0; i < 6; i++) {
            myGame.prepareNextRound();
            myGame.playNextRound();
            addToStringArray(myGame.getBoard());
        }
        
    }
    
    public void write() {
        writer.writeNext(gameString);
    }
    
    public void test(String id) {
        String[] line = new String[2401];
        int i = 0;
        line[i] = id;
        i++;
        for (int j = 1; j < 2401; j++) {
            line[i] = String.valueOf(randomGenerator.nextInt(2));
            i++;
        }
        writer.writeNext(line);
    }
    
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private Cell[][] generateBoard() {
        board = new Cell[20][20];
        int density = randomGenerator.nextInt(98) + 1;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                board[i][j] = new Cell(cellIsAlive(density));
            }
        }
        return board;
    }
    
    private void addToStringArray(Cell[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                gameString[gameStringCounter] = toNumeralString(board[i][j].isAlive());
                gameStringCounter++;
            }
        }
    }
    
    private boolean cellIsAlive(int density) {
        int alive = randomGenerator.nextInt(101);
        if (alive < density) {
            return true;
        } else {
            return false;
        }
    }
    
    private static String toNumeralString(Boolean bool) {
        return bool ? "1" : "0";
    }

    
    private AbstractGame createGame(Cell[][] board, ModeEnum mode) {
        Integer[] survives = mode.getSurvives();
        Integer[] revives = mode.getRevives();
        return new WallOfDeathGame(board, survives, revives);
    }
}
