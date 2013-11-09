package de.makaitghahramanianzeising.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import de.makaitghahramanianzeising.exceptions.GOLException;
import de.makaitghahramanianzeising.model.Cell;

public class FileParser {

    private Cell[][] board;
    private final Path filePath;

    public FileParser(String filePathString) throws GOLException {
        if (!(filePathString == null)) {
            this.filePath = Paths.get(filePathString);
        } else {
            throw new GOLException("Bitte Datei auswählen.");
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void parse() throws GOLException {
        try {
            checkFile();
            buildBoard();
        } catch (IOException ioE) {
            throw new GOLException("Die Datei konnte nicht geöffnet werden.");
        }
    }

    private void checkFile() throws GOLException, IOException {
        if (fileTypeIsNotGol()) {
            throw new GOLException("Datei muss vom Typ .gol sein.");
        }
        if (fileSizeTooBig()) {
            throw new GOLException("Dateigröße darf maximal 250kb betragen.");
        }
        if (fileIsEmpty()) {
            throw new GOLException("Datei darf nicht leer sein.");
        }
        if (boardDimensionsWrong()) {
            throw new GOLException("Das Spielbrett muss in jeder Zeile gleich viele Zellen haben.");
        }
    }

    private boolean fileTypeIsNotGol() throws IOException {
        String filePathString = filePath.toString();
        return !(".gol".equals(filePathString.substring(filePathString.length() - 4)));
    }

    private boolean fileSizeTooBig() throws IOException {
        double bytes = Files.size(filePath);
        return bytes >= 250000;
    }

    private boolean fileIsEmpty() throws IOException {
        Scanner scanner = new Scanner(filePath);
        boolean hasNext = scanner.hasNext();
        scanner.close();
        return !hasNext;
    }

    private boolean boardDimensionsWrong() throws IOException {
        Scanner scanner = new Scanner(filePath);
        int firstRowLength = scanner.nextLine().length();
        while (scanner.hasNextLine()) {
            if (!(firstRowLength == scanner.nextLine().length())) {
                scanner.close();
                return true;
            }
        }
        scanner.close();
        return false;
    }

    private void buildBoard() throws GOLException, IOException {
        initializeBoard();
        int row = 0;
        Scanner scanner = new Scanner(filePath);
        while (scanner.hasNextLine()) {
            fillBoardWithLine(scanner.nextLine(), row);
            row++;
        }
        scanner.close();
    }

    private void initializeBoard() throws IOException {
        int i = numberOfColumns();
        int j = numberOfRows();
        board = new Cell[i][j];
    }

    private int numberOfColumns() throws IOException {
        Scanner scanner = new Scanner(filePath);
        String nextLine = scanner.nextLine();
        scanner.close();
        return nextLine.length();
    }

    private int numberOfRows() throws IOException {
        Scanner scanner = new Scanner(filePath);
        int i = 0;
        while (scanner.hasNextLine()) {
            i++;
            scanner.nextLine();
        }
        scanner.close();
        return i;
    }

    private void fillBoardWithLine(String line, int row) throws GOLException {
        for (int column = 0; column < line.length(); column++) {
            if (line.charAt(column) == '0') {
                board[column][row] = new Cell(false);
            } else if (line.charAt(column) == '1') {
                board[column][row] = new Cell(true);
            } else {
                throw new GOLException("Die Datei darf nur aus 0'en und 1'sen bestehen und muss als UTF-16 encodiert sein.");
            }
        }
    }

}
