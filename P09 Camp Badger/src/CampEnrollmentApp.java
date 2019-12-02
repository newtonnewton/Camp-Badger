//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Binary Search Tree Stroll
// Files:           Camper.java, CampeTreeNode.java, CamperBST.java, CampManager.java, CampEnrollmentApp.java
// Course:          CS-300, Fall 2019
//
// Author:          (Niudun Wang)
// Email:           (nwang66@wisc.edu)
// Lecturer's Name: (Mouna Kacem)
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         NONE  
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * this class uses an instance of the CampManager to execute certain commands as
 * read from a text file
 * 
 * @author Niudun
 *
 */
public class CampEnrollmentApp {

	public static void main(String[] args) {
		CampManager manager = new CampManager();
		try {
			List<String> fileLines = Files.readAllLines(Paths.get("sim.txt"));

			// retrieves the first element in the string in each line
			for (int i = 0; i < fileLines.size(); i++) {
				String[] parse = fileLines.get(i).split(" "); // stores all the words in the i-th line

				if (parse[0].equals("S")) {
					manager.printStatistics();
				}

				// enroll the camper given the info in that line of the file
				try {
					if (parse[0].equals("E")) {
						int age = Integer.parseInt(parse[3]);
						Camper newCamper = new Camper(parse[2], parse[1], age);
						manager.enrollCamper(newCamper);
					}
				}

				catch (java.lang.IllegalArgumentException e) {
					System.out.println("This person is either too old or too young to be in Camp Badger.");
				}

				// attempts to remove a camper from the tree
				try {
					if (parse[0].equals("R")) {
						Camper delCamper = new Camper(parse[2], parse[1], 8);
						manager.unenrollCamper(delCamper);
					}
				}

				catch (java.util.NoSuchElementException f) {
					System.out.println("That camper is not enrolled.");
				}

				// traverses the campers tree and prints all elements
				if (parse[0].equals("T")) {

					java.util.Iterator<Camper> iter = manager.traverse(parse[1]);
					manager.printTree(iter, parse[1]);
				}

			}

			// makes sure the file is readable
		} catch (java.io.IOException e) {
			System.out.println("something wrong with the file");
		}

	}
}
