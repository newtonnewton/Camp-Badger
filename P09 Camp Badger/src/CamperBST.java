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
import java.util.LinkedList;
import java.util.Iterator;

public class CamperBST {

	public CampTreeNode root; // root node
	private int size; // the size of Binary Search Tree

	private LinkedList<Camper> traversedLList; // linkedlist to maintain current traversal

	// creates an empty binary search tree
	public CamperBST() {
		this.root = null;
		this.size = 0;
	}

	/**
	 * gets size
	 * 
	 * @return the current size of the camerBST
	 */
	public int size() {
		return size;
	}

	/**
	 * determines if the tree is empty
	 * 
	 * @return true if the tree is empty, false otherwise
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * starts tree insertion by calling insertHelp() on the root and assigning root
	 * to be the subtree returned by that method
	 * 
	 * @param newCamper the camper to be inserted into the tree
	 */
	public void insert(Camper newCamper) {
		root = insertHelp(root, newCamper);
		size++;
	}

	/**
	 * Recursive helper method to insert
	 * 
	 * @param current, the "root" of the subtree we are inserting into, i.e. the
	 *        node we are currently at
	 * @param newCamper, the camper to be inserted into the tree
	 * @return the root of the modified subtree we inserted into
	 */
	private CampTreeNode insertHelp(CampTreeNode current, Camper newCamper) {
		if (current == null) {
			current = new CampTreeNode();
			current.setData(newCamper);
		} // initializes the tree with the given Camper if the tree was empty

		// case 1: new item is less than the current node so it should be assigned to
		// the left subtree
		else if (newCamper.compareTo(current.getData()) < 0)
			current.setLeftNode(insertHelp(current.getLeftNode(), newCamper));
		// case 2: new item is greater than the current node so it should be assigned to
		// the right subtree
		else if (newCamper.compareTo(current.getData()) > 0) {
			current.setRightNode(insertHelp(current.getRightNode(), newCamper));
		}
		// case 3:
		else
			throw new java.lang.IllegalArgumentException("The camper has been added.");

		return current;
	}

	/**
	 * prints the contents of this tree in alphabetical order based on the string
	 * "lastName, firstName"
	 */
	public void print() {
		printHelp(root);
	}

	private void printHelp(CampTreeNode current) {
		if (current == null)
			return;
		// recursively print the content of the node
		printHelp(current.getLeftNode());
		System.out.println(current.getData());
		printHelp(current.getRightNode());
	}

	/**
	 * deletes a Camper into the binary search tree if it exists
	 * 
	 * @param key, the camper to be deleted from the tree
	 * @throws java.util.NoSuchElementException if there is no such camper found in
	 *         the tree
	 */
	public void delete(Camper key) throws java.util.NoSuchElementException {
		root = deleteHelp(root, key);
		size--;
	}

	/**
	 * a helper method to find the descending node containing the min value within
	 * the subtree that roots at the node current. suppose the input current is not
	 * null
	 * 
	 * @param current
	 * @return
	 */
	public CampTreeNode minCampTreeNode(CampTreeNode current) {
		CampTreeNode min = new CampTreeNode();
		if (current.getLeftNode() == null)
			min = current;
		else
			min = minCampTreeNode(current.getLeftNode());

		return min;
	}

	/**
	 * Recursive helper method to delete
	 * 
	 * @param current, the root of the subtree we are deleting from, i.e the node we
	 *        are currently at
	 * @param key, the camper to be deleted from the tree
	 * @return the root of the modified subtree we deleted from
	 * @throws NoSuchElementException if the camper is not in the tree
	 */
	private CampTreeNode deleteHelp(CampTreeNode current, Camper key) throws java.util.NoSuchElementException {
		// case 1: key not found
		if (current == null) {
			throw new java.util.NoSuchElementException("No such element in the tree.");
		}

		// case 1: we found the node that equals to key
		if (current.getData().compareTo(key) == 0) {
			// subcase 1: the node has 2 children
			if (current.getLeftNode() != null && current.getRightNode() != null) {

				CampTreeNode min = minCampTreeNode(current.getRightNode()); // finds the min node
				// updates the current node to be the min value of its right branch
				current.setData(min.getData());
				// deletes the original min node recursively
				current.setRightNode(deleteHelp(current.getRightNode(), min.getData()));
			}
			// subcase 2: the left or right child is null, skip this one
			else if (current.getLeftNode() == null) {
				current = current.getRightNode();
			} else if (current.getRightNode() == null) {
				current = current.getLeftNode();
			}
		}

		// case 2: the node is greater than the key to be deleted
		else if (current.getData().compareTo(key) > 0) {
			// trace down along the left branch
			current.setLeftNode(deleteHelp(current.getLeftNode(), key));
		}
		// case 3: the node is less than the key to be deleted
		else if (current.getData().compareTo(key) < 0) {
			// trace down along the right branch
			current.setRightNode(deleteHelp(current.getRightNode(), key));
		}

		return current;
	}

	/**
	 * returns an iterator of camper in the correct order as designated
	 * 
	 * @param order, the way that the tree is being traversed
	 * @return an iterator that would traverse the tree with respect to the
	 *         specified order
	 */
	public Iterator<Camper> traverse(String order) {

		if (traversedLList == null) {
			traversedLList = new LinkedList<Camper>();
		}

		else { // clears the list to start over for a new traversal
			traversedLList.clear();
		}

		traverseHelp(root, order);
		return traversedLList.listIterator();
	}

	/**
	 * Recursive helper method to traverse. Will take the current CampTreeNode's
	 * data and add it to traversedLList based on the given order. Then continue to
	 * recurse on the correct subtree
	 * 
	 * @param current, the root of the current subtree we are traversing
	 * @param order, the type of traversal to perform
	 */
	private void traverseHelp(CampTreeNode current, String order) {
		// case 1: postorder traversal
		if (order.equals("POSTORDER")) {
			if (current == null)
				return;
			// first recur on left subtree
			traverseHelp(current.getLeftNode(), order);
			// then recur on right subtree
			traverseHelp(current.getRightNode(), order);
			// add the current node to the traversedLList
			traversedLList.add(current.getData());
		}

		// case 2: inorder traversal
		else if (order.equals("INORDER")) {
			if (current == null)
				return;
			// first recur on left subtree
			traverseHelp(current.getLeftNode(), order);
			// add the current node to the traversedLList
			traversedLList.add(current.getData());
			// then recur on right subtree
			traverseHelp(current.getRightNode(), order);
		}

		// case 3: preorder traversal
		else if (order.equals("PREORDER")) {
			if (current == null)
				return;
			// first add the current node to the traversedLList
			traversedLList.add(current.getData());
			// then recur on left subtree
			traverseHelp(current.getLeftNode(), order);
			// finally recur on right subtree
			traverseHelp(current.getRightNode(), order);
		}
	}

}
