package de.MakaitGhahramanianZeising.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import de.MakaitGhahramanianZeising.exceptions.GOLException;
import de.MakaitGhahramanianZeising.model.Cell;

public class FileParser {

	private Cell[][] board;

	public FileParser(String filePathString) throws Exception {
		if (filePathString == null) {
			throw new GOLException("Bitte Datei auswählen.");
		}
		Path filePath = Paths.get(filePathString);
		parse(filePath);
	}
	
	public Cell[][] getBoard() {
		return board;
	}
	
	public void parse(Path filePath) throws Exception {
		try {
			if (fileTypeIsNotGol(filePath)) {
				throw new GOLException("Datei muss vom Typ .gol sein.");
			}
			if (fileSizeTooBig(filePath)) {
				throw new GOLException("Dateigröße darf maximal 250kb betragen.");
			}
			if (fileIsEmpty(filePath)) {
				throw new GOLException("Datei darf nicht leer sein.");
			}
			if (boardDimensionsWrong(filePath)) {
				throw new GOLException("Das Spielbrett muss in jeder Zeile gleich viele Zellen haben.");
			}
			buildBoard(filePath);
		} catch (IOException ioe) {
			throw new GOLException("Die Datei konnte nicht geöffnet werden.");
		}	
	}
	
	private boolean fileTypeIsNotGol(Path filePath) throws IOException {
		String filePathString = filePath.toString();
		return (!(filePathString.substring(filePathString.length()-4).equals(".gol")));
	}
	
	private boolean fileSizeTooBig(Path filePath) throws IOException {
		double bytes = Files.size(filePath);
		return (bytes >= 250000);
	}
	
	private boolean fileIsEmpty(Path filePath) throws IOException {
		Scanner scanner = new Scanner(filePath);
		boolean hasNext = scanner.hasNext();
		scanner.close();
		return !hasNext;
	}
	
	private boolean boardDimensionsWrong(Path filePath) throws IOException {
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

	private void buildBoard(Path filePath) throws Exception {
		initializeBoard(filePath);
		int row = 0;
		Scanner scanner = new Scanner(filePath);
		while (scanner.hasNextLine()) {
			fillBoardWithLine(scanner.nextLine(), row);
			row++;
		}
		scanner.close();
	}
	
	private void initializeBoard(Path filePath) throws IOException {
		int i = numberOfColumns(filePath);
		int j = numberOfRows(filePath);
		board = new Cell[i][j];
	}
	
	private int numberOfColumns(Path filePath) throws IOException {
		Scanner scanner = new Scanner(filePath);
		String nextLine = scanner.nextLine();
		scanner.close();
		return nextLine.length();
	}
	
	private int numberOfRows(Path filePath) throws IOException{
		Scanner scanner = new Scanner(filePath);
		int i = 0;
		while (scanner.hasNextLine()) {
			i++;
			scanner.nextLine();
		}
		scanner.close();
		return i;
	}
	
	private void fillBoardWithLine(String line, int row) throws GOLException{
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
