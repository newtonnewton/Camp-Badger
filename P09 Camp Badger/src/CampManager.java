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
/**
 * class responsible for having an instance of a CamperBST and calling the
 * various operations on it along with tasks in regards to managing Camp Badger.
 * 
 * @author Niudun
 *
 */
public class CampManager {

	private CamperBST campers; // the tree containing all the campers enrolled
	private final static String[] CABIN_NAMES = new String[] { "Otter Overpass", "Wolverine Woodland",
			"Badger Bunkhouse" };

	/**
	 * initializes the campers field
	 */
	public CampManager() {
		this.campers = new CamperBST();
	}

	/**
	 * prints stats based on the current "state" of the camps. The stats to be
	 * printed is the total number of campers
	 */
	public void printStatistics() {
		System.out.println("--- Camp Statistics ---");
		if (campers == null)
			System.out.println("Number of Campers: " + 0);
		else {
			System.out.println("Number of Campers: " + campers.size());
		}
		System.out.println("-----------------------");
	}

	/**
	 * enrolls a camper by determining their cabin and adding them to the tree
	 * 
	 * @param newCamper, the camper to be enrolled into the camp
	 */
	public void enrollCamper(Camper newCamper) {
		// gets the age and assigns the cabin, newCamper wouldn't be null as stated a
		// priori
		if (newCamper.getAge() == 8 || newCamper.getAge() == 9) {
			newCamper.assignCabin(CABIN_NAMES[0]);
			System.out.println(
					"Enrollment of " + newCamper.getFirstName() + " " + newCamper.getLastName() + " Successful!");
			campers.insert(newCamper);
		}

		if (newCamper.getAge() == 10 || newCamper.getAge() == 11 || newCamper.getAge() == 12) {
			newCamper.assignCabin(CABIN_NAMES[1]);
			System.out.println(
					"Enrollment of " + newCamper.getFirstName() + " " + newCamper.getLastName() + " Successful!");
			campers.insert(newCamper);
		}

		if (newCamper.getAge() == 13 || newCamper.getAge() == 14) {
			newCamper.assignCabin(CABIN_NAMES[2]);
			System.out.println(
					"Enrollment of " + newCamper.getFirstName() + " " + newCamper.getLastName() + " Successful!");
			campers.insert(newCamper);
		}

		if (newCamper.getAge() < 8 || newCamper.getAge() > 14) {
			System.out.println("This person is either too old or too young to be in Camp Badger.");
		}

	}

	/**
	 * unenrolls a camper by removing them from the tree
	 * 
	 * @param delCamper, the camper to be unenrolled the camp
	 */
	public void unenrollCamper(Camper delCamper) throws java.util.NoSuchElementException {
		campers.delete(delCamper);
		System.out.println(
				"Unenrollment of " + delCamper.getFirstName() + " " + delCamper.getLastName() + " Successful!");

	}

	/**
	 * Traverses the tree in the designated order by calling it through the
	 * CamperBST class
	 * 
	 * @param order, the type of traversal for the tree to perferm
	 * @return the iterator of Campers from CampBST.traverse()
	 */
	public java.util.Iterator<Camper> traverse(String order) {

		return campers.traverse(order);
	}

	/**
	 * a helper method to print the CamperBST that this class stores in the order
	 * with respect to the specified iterator
	 * 
	 * @param iter, traverses the BST campers in such prescribed order
	 */
	public void printTree(java.util.Iterator<Camper> iter, String message) {
		System.out.println("--- " + message + " Traversal ---");
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		System.out.println("--------------------------");
	}

}
