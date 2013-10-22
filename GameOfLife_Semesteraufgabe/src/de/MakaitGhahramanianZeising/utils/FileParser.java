package de.MakaitGhahramanianZeising.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileParser {

	static int lengthFirstString;
	static int x=0;
	private final Charset ENCODING = StandardCharsets.UTF_16;
	private final Path fFilePath;

	/** Reads the text file */
	public static void main(String[] aArgs) throws IOException {
		FileParser parser = new FileParser(
				"/Users/patrickghahramanian/Desktop/Java/Asci/MusterText.txt");
		parser.parseLineByLine();
	}

	public FileParser(String aFileName) {
		fFilePath = Paths.get(aFileName);
	}

	/** Method that reads Line by Line from the text file */
	public void parseLineByLine() throws IOException {
		Scanner scanner = new Scanner(fFilePath, ENCODING.name());
		 lengthFirstString= scanner.next().length();
		{
			while (scanner.hasNext()) {
				parserLengthLinebyLine(scanner.next());
			}
		}
	}

	/** Method that sets the length of each Stringtoken from each line */
	private void parserLengthLinebyLine(String aString) {
		Scanner scanner = new Scanner(aString);
		int lengthLine;
		while (scanner.hasNext()) {
			lengthLine = scanner.next().length();
			parserLengthValidation(lengthLine);
		}
	}
	
	/**Method that checks the file against the length */
	private boolean parserLengthValidation(int lengthLine) {
		if (!(lengthLine == lengthFirstString)) {
			return false;
		} else {
			return true;
		}
	}

	/** Method for the console output */
	private void output(Object object) {
		System.out.println(String.valueOf(object));
	}

}
