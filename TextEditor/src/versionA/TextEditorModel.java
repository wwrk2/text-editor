package versionA;

import java.io.*;

/**
 * Simple model for text editor with write-to-file
 * and get-text-from-file functions.
 * 
 * @author Won Rhim
 *
 */
public class TextEditorModel {
	
	/* Write text to a given file using FileWriter. */
	public static void writeToFile(File file, String text) throws IOException {
		if (file != null) {
			try {
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(text);
				fileWriter.close();
				
			} catch (IOException exc) {
				// Let the controller component handle the exception.
				throw new IOException("Error: could not write to file.");
			}
		}
	}
	
	/* Retrieve text from file with FileReader, then return it
	 * with StringBuffer.
	 */
	public static String getTextFromFile(File file) throws IOException {
		// Use StringBuffer to save the text from the file.
		StringBuffer stringBuffer = new StringBuffer();
		
		try {
			FileReader fileReader = new FileReader(file);
			
			/* Read characters from file, and append to string buffer. */
			int nextChar = fileReader.read();
			
			// While file reader hasn't reached end of file.
			while (nextChar != -1) {
				// Make sure to change int to char, before appending.
				stringBuffer.append((char) nextChar);
				nextChar = fileReader.read();
			}
			
			// Remember to always close if not using try-with-resources.
			fileReader.close();
		} catch (IOException exc) {
			// Let the controller component handle the exception.
			throw new IOException("Error: could not retrieve text from file.");
		}
		
		return stringBuffer.toString();
	}
}
