package com.framework.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestPartOne {

	public static void main(String[] args) throws FileNotFoundException {
		// calling the method to get the values
		displayFileValues();

	}

	public static void displayFileValues() throws FileNotFoundException {
		// Getting the file path into a string
		String desktopPath = System.getProperty("user.home") + "/Desktop";
		//
		File file = new File(desktopPath + "/examplefile.txt");
		String longestWord = "";
		String current;
		try (Scanner scan = new Scanner(file)) {
			while (scan.hasNext()) {
				current = scan.next();
				if (current.length() > longestWord.length()) {
					longestWord = current;
				}
			}
		}

		// getBytes() method to convert string
		// into bytes[]
		byte[] strAsByteArray = longestWord.getBytes();
		byte[] result = new byte[strAsByteArray.length];
		// Store result in reverse order into the
		// result byte[]
		for (int i = 0; i < strAsByteArray.length; i++)
			result[i] = strAsByteArray[strAsByteArray.length - i - 1];
		// Printing the results
		System.out.println("The longest word for the document is: " + longestWord);
		System.out.println("The longest word reversed is: " + new String(result));
	}
}
