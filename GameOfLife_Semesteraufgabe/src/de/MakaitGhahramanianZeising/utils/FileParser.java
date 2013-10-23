package de.MakaitGhahramanianZeising.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import de.MakaitGhahramanianZeising.exceptions.FileException;
import de.MakaitGhahramanianZeising.model.CellModel;

public class FileParser {

	private CellModel[][] board;

	public FileParser(String filePathString) {
		Path filePath = Paths.get(filePathString);
		try {
			validate(filePath);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void validate(Path filePath) throws Exception {
		ValidBean validBean = isValidFile(filePath); 
		if (! validBean.isValid()) {
			throw validBean.getExceptionOnInvalid();
		}
	}
	
	private ValidBean isValidFile(Path filePath) {
		try {
			if (fileTypeIsNotGol(filePath)) {
				return new ValidBean(false, new FileException("Datei muss vom Typ .gol sein."));
			}
			if (fileSizeTooBig(filePath)) {
				return new ValidBean(false, new FileException("Dateigröße muss kleiner als 250kb sein."));
			}
			if (fileIsEmpty(filePath)) {
				return new ValidBean(false, new FileException("Datei darf nicht leer sein."));
			}
			if (boardDimensionsWrong(filePath)) {
				return new ValidBean(false, new FileException("Das Spielbrett muss in jeder Zeile gleich viele Zellen haben."));
			}
			return buildBoard(filePath);
		} catch (IOException ioe) {
			return new ValidBean(false, new FileException("Die Datei konnte nicht geöffnet werden."));
		}	
	}
	
	private boolean fileTypeIsNotGol(Path filePath) throws IOException {
		String filePathString = filePath.toString();
		return (!(filePathString.substring(filePathString.length()-4).equals(".gol")));
	}
	
	private boolean fileSizeTooBig(Path filePath) throws IOException {
		double bytes = Files.size(filePath);
		return (bytes > 250000);
	}
	
	private boolean fileIsEmpty(Path filePath) throws IOException {
		Scanner scanner = new Scanner(filePath);
		try {
			return !(scanner.hasNext());
		} finally {
			scanner.close();
		}
	}
	
	private boolean boardDimensionsWrong(Path filePath) throws IOException {
		Scanner scanner = new Scanner(filePath);
		try {
			int firstRowLength = scanner.nextLine().length();
			{
				while (scanner.hasNextLine()) {
					if (!(firstRowLength == scanner.nextLine().length())) {
						return true;
					}
				}
			}
			return false;
		} finally {
			scanner.close();
		}
	}

	private ValidBean buildBoard(Path filePath) throws IOException {
		initializeBoard(filePath);
		int row = 0;
		Scanner scanner = new Scanner(filePath);
		try {
			while (scanner.hasNextLine()) {
				fillBoardWithLine(scanner.nextLine(), row);
				row++;
			}
			return new ValidBean(true, null);
		} catch (FileException e) {
			return new ValidBean(false, e);
		} finally {
			scanner.close();
		}
	}
	
	private void initializeBoard(Path filePath) throws IOException {
		int i = numberOfColumns(filePath);
		int j = numberOfRows(filePath);
		board = new CellModel[i][j];
	}
	
	private int numberOfColumns(Path filePath) throws IOException {
		Scanner scanner = new Scanner(filePath);
		try {
			return scanner.nextLine().length();
		} finally {
			scanner.close();
		}
	}
	
	private int numberOfRows(Path filePath) throws IOException{
		Scanner scanner = new Scanner(filePath);
		try {
			int i = 0;
			while (scanner.hasNextLine()) {
				i++;
				scanner.nextLine();
			}
			return i;
		} finally {
			scanner.close();
		}
	}
	
	private void fillBoardWithLine(String line, int row) throws FileException{
		for (int column = 0; column < line.length(); column++) {
			if (line.charAt(column) == '0') {
				board[column][row] = new CellModel(false);
			} else if (line.charAt(column) == '1') {
				board[column][row] = new CellModel(true);
			} else {
				throw new FileException("Die Datei darf nur aus 0'en und 1'sen bestehen und muss als UTF-16 encodiert sein.");
			}
		}
	}
	
}
