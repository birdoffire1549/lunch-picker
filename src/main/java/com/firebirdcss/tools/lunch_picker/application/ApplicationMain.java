package com.firebirdcss.tools.lunch_picker.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This application is a random lunch destination picker.
 * When running this application one supplies as an argument, the path
 * of a file that contains lines of destination names. This application
 * then reads in that list of names and picks one at random.
 * 
 * @author Scott Griffis
 *
 */
public class ApplicationMain {
	/**
	 * Body of the application.<br> 
	 * This is the primary method of this application.
	 * 
	 * @param args - Program arguments as {@link String}[]
	 */
	public static void main(String[] args) {
		List<String> choiceList = new ArrayList<>();
		
		if (args.length > 0) {
			File file = new File(args[0]);
			
			if (file.exists()) {
				try (BufferedReader buffRead = new BufferedReader(new FileReader(file));) {
					String line = buffRead.readLine();
					while (line != null) {
						choiceList.add(line);
						line = buffRead.readLine();
					}
					
					Random rnd = new Random(System.currentTimeMillis());
					System.out.print("Today's food choice is");
					try {
						for (int i = 1; i <= 3; i++) {
							Thread.sleep(1000L);
							System.out.print(".");
						}
						Thread.sleep(1000L);
					} catch (InterruptedException ignored) {}
					System.out.println("\t" + choiceList.get((rnd.nextInt(10) + 1)));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			} else {
				System.out.println("\n\nERROR: File specified doesn't exist; File specified: '" + args[0] + "'");
				displayUsage();
			}
		} else {
			displayUsage();
		}
	}
	
	/**
	 * Displays the usage for this command.
	 */
	private static void displayUsage() {
		System.out.println("Usage: LunchPicker <choice file path>\n");
	}
}
