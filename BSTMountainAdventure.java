package project5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the class that is the program. This means it has the main method.
 * This class is responsible for parsing and validating the command line
 * arguments, reading and parsing the input file, producing any error messages,
 * handling any exceptions thrown by other classes, and producing output.
 * 
 * @author Wenqi Liao
 * @version 05/02/2021
 *
 */
public class BSTMountainAdventure {

	public static ArrayList<String[]> eachStop; // arrayList of eachStop's content
	public static ArrayList<String> output; // the paths would be printed
	public static ArrayList<Integer> outputCount;
	public static BSTMountain mount;
	public static Hiker mainHiker;
	public static ArrayList<String> allLabels; // all labels of a mountain

	private static String CONTAIN_LETTER_REGEX = ".*[a-zA-z].*";
	private static String CONTAIN_DIGIT_REGEX = ".*[0-9].*";

	public static void main(String[] args) {

		// verify that the command line argument exists
		if (args.length == 0) {
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}

		// verify that command line argument contains a name of an existing file
		File dataFile = new File(args[0]);
		if (!dataFile.exists()) {
			System.err.println("Error: the file " + dataFile.getAbsolutePath() + " does not exist.\n");
			System.exit(1);
		}

		if (!dataFile.canRead()) {
			System.err.println("Error: the file " + dataFile.getAbsolutePath() + " cannot be opened for reading.\n");
			System.exit(1);
		}

		// open the file for reading
		Scanner inDatas = null;
		Scanner parseLine = null;

		// check error
		try {
			inDatas = new Scanner(dataFile);
		} catch (FileNotFoundException e) {
			System.err.println("Error: the file " + dataFile.getAbsolutePath() + " cannot be opened for reading.\n");
			System.exit(1);
		}

		// read the content of the file and save it
		String line = null;
		eachStop = new ArrayList<String[]>();
		allLabels = new ArrayList<String>();

		while (inDatas.hasNextLine()) {

			line = inDatas.nextLine();
			parseLine = new Scanner(line);
			parseLine.useDelimiter(", ");
			String[] result = line.split(" ");

			if (isValid(result)) {
				eachStop.add(result);
				allLabels.add(result[0]);
			}

		}

		// create the mountain
		setMount();

		RestStop root = mount.getRoot();
		output = new ArrayList<String>();
		mainHiker = new Hiker(0, 0, 0);
		// find all possible paths
		findPath(root);
		// print all possible paths
		printOutput();
		System.exit(0);
	}

	/**
	 * 
	 * create a mountain with the rest stops
	 * 
	 */
	public static void setMount() {

		// no available string to be restStop
		if (eachStop.size() == 0) {
			System.err.println("The file is empty");
			System.exit(1);
		}
		RestStop root = new RestStop(eachStop.get(0));
		root.setAttributes();
		// create the mount with the root node
		mount = new BSTMountain(root);
		for (int i = 1; i < eachStop.size(); i++) {
			RestStop node = new RestStop(eachStop.get(i));
			node.setAttributes();
			mount.add(node);
		}
	}

	/**
	 * 
	 * check whether there is any digit in a string
	 * 
	 * @param the string needed to be checked.
	 * @return true if the line contains digit.
	 * 
	 */
	public static boolean hasDigit(String ret) {

		return ret.matches(CONTAIN_DIGIT_REGEX);
	}

	/**
	 * 
	 * check whether there is any letter in a string
	 * 
	 * @param the string needed to be checked.
	 * @return true if the line contains letter.
	 * 
	 */
	public static boolean hasLetter(String ret) {

		return ret.matches(CONTAIN_LETTER_REGEX);
	}

	/**
	 * 
	 * check whether the line can be a rest stop
	 * 
	 * @param the String array of the input line.
	 * @return true if the line can be a rest stop.
	 * 
	 */
	public static boolean isValid(String[] result) {

		// no element in the line
		if (result.length == 0) {
			return false;
		}

		// no element in the right format in the line
		if (!hasDigit(result[0]) && !hasLetter(result[0])) {
			return false;
		}

		// this line contains a label which is a duplicate
		if (isDuplicate(result[0])) {
			return false;
		}

		return true;
	}

	/**
	 * 
	 * check whether a certain node is a duplicate
	 * 
	 * @param a label need to be check.
	 * @return true if the restStop is a duplicate.
	 * 
	 */
	public static boolean isDuplicate(String label) {

		if (allLabels.contains(label)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * find all paths of a mountain
	 * 
	 * @param the root of the mountain.
	 * @return the arraylist of all possible way.
	 * 
	 */
	public static ArrayList<String> findPath(RestStop root) {

		// only the root restStop with food can begin
		if (root != null && root.getFood() > 0) {
			// to record a possible path
			String temp = "";
			findPathHelper(root, temp, mainHiker);
		}
		return output;
	}

	/**
	 * 
	 * the helper method of findPath
	 * 
	 * @param the current location of the mountain.
	 * @param a   string represents all restStops in a possible path.
	 * 
	 */
	private static void findPathHelper(RestStop root, String ans, Hiker hiker) {

		// the node does not exist
		if (root == null) {
			return;
		}

		// add the supplies in this stop to the bag
		hiker.setFood(root.getFood());
		hiker.setAxe(root.getAxe());
		hiker.setRaft(root.getRaft());

		// create a copy of supplies
		int tempFood = hiker.getFood();
		int tempAxe = hiker.getAxe();
		int tempRaft = hiker.getRaft();

		// no food to eat so that cannot move
		if (tempFood == 0) {
			// if food equals zero and the hiker reaches the bottom. It works.
			if (!(root.left == null && root.right == null)) {
				return;
			}
		}
		// check whether there is a river and the hiker can go through it
		if (root.getRiver() > 0) {
			if (hiker.getRaft() < root.getRiver()) {
				return;
			} else {
				tempRaft = tempRaft - root.getRiver();
				hiker.setRaft(-root.getRiver());
			}
		}

		// check whether there is a fallen tree and the hiker can go through it
		if (root.getTree() > 0) {
			if (hiker.getAxe() < root.getTree()) {
				return;
			} else {
				tempAxe = tempAxe - root.getTree();
				hiker.setAxe(-root.getTree());
			}
		}

		String value = root.getLabel();
		ans += value;
		// reach the bottom
		if (root.left == null && root.right == null) {
			output.add(ans);
		} else {
			ans += " ";
			// the hiker needs to consume a food to go on
			hiker.setFood(-1);
			tempFood--;
		}

		// set two hikers to go down to left and right subtree
		Hiker hiker1 = new Hiker(tempFood, tempAxe, tempRaft);
		Hiker hiker2 = new Hiker(tempFood, tempAxe, tempRaft);

		findPathHelper(root.left, ans, hiker1);
		findPathHelper(root.right, ans, hiker2);
	}

	/**
	 * 
	 * print all the possible paths
	 * 
	 */
	public static void printOutput() {

		for (int i = 0; i < output.size(); i++) {
			String ans = output.get(i);
			// check how many node in a path
			String[] counter = ans.split(" ");
			// check whether the hiker reach the bottom
			if (counter.length == mount.getDepth()) {
				System.out.println(ans);
			}
		}

	}

}
