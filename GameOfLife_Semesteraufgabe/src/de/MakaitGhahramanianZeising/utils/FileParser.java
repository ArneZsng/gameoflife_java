package de.MakaitGhahramanianZeising.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileParser {
	
	/** Reads the text file
	public static void main(String[] aArgs) throws IOException {
		AsciParser parser = new AsciParser(
				"/Users/patrickghahramanian/Desktop/Java/Asci/MusterText.txt");
		parser.parseLineByLine();
	}
	*/
	
	private final Path fFilePath;
	
	public FileParser(String aFileName) {
		fFilePath = Paths.get(aFileName);
	}

	/** Method that reads Line by Line from the text file */
	private final Charset ENCODING = StandardCharsets.UTF_16;

	public void parseLineByLine() throws IOException {
		Scanner scanner = new Scanner(fFilePath, ENCODING.name());
		{
			while (scanner.hasNext()) {
				parserStringByString(scanner.nextLine());
			}
		}
	}

	/** Method that verifies the lines againts default */
	private void parserStringByString(String aString) {
		StringTokenizer tokenizer=new StringTokenizer(aString);
		while (tokenizer.hasMoreElements()){
			int lengthToken=tokenizer.nextToken().length();
			String newString=aString;
			StringTokenizer newTokenizer= new StringTokenizer(newString);
			while (newTokenizer.hasMoreElements()){
				int newLengthToken=newTokenizer.nextToken().length();
			if(lengthToken!=newLengthToken){
				output("not valid");
			}
			else {
				output("valid");
			}
			}
		}
	}

	/** Method for the console output */
	private void output(Object object) {
		System.out.println(String.valueOf(object));
	}

}
