package project5;

/**
 * This class should represent a single rest stop. It should be capable of
 * storing the label of the rest stop along with a list of the supplies that a
 * hiker can collect at this rest-stop and a list of obstacles that a hiker may
 * encounter at this rest-stop. It may be useful to implement the Comparable
 * interface.
 * 
 * @author Wenqi Liao
 * @version 05/02/2021
 *
 */
public class RestStop {

	private String[] data; // the data needed to be converted into a restStop type
	RestStop left;
	RestStop right;
	private String label;
	private int food;
	private int axe;
	private int raft;
	private int river;
	private int tree;

	/**
	 * Constructs a new RestStop object with an array of data from the input file.
	 * 
	 * @param an array of data from the input file
	 */
	public RestStop(String[] data) {

		this.data = data;
		label = data[0];
		food = 0;
		axe = 0;
		raft = 0;
		river = 0;
		tree = 0;

	}

	/**
	 * 
	 * set the obstacles and supplies in one restStop
	 * 
	 */
	public void setAttributes() {

		// check legitimate supplies and obstacles
		for (int i = 1; i < data.length; i++) {
			if (data[i].equals("food")) {
				food++;
			} else if (data[i].equals("axe")) {
				axe++;
			} else if (data[i].equals("raft")) {
				raft++;
			} else if (data[i].equals("river")) {
				river++;
			} else if (data[i].equals("fallen") && (i + 1) < data.length && data[i + 1].equals("tree")) {
				tree++;
			}
		}
	}

	/**
	 * 
	 * return the label of this restStop
	 * 
	 */
	public String getLabel() {

		return label;
	}

	/**
	 * 
	 * return the numberof food of this restStop
	 * 
	 */
	public int getFood() {

		return food;
	}

	/**
	 * 
	 * return the number of axe of this restStop
	 * 
	 */
	public int getAxe() {

		return axe;
	}

	/**
	 * 
	 * return the number of raft of this restStop
	 * 
	 */
	public int getRaft() {

		return raft;
	}

	/**
	 * 
	 * return the number of river of this restStop
	 * 
	 */
	public int getRiver() {

		return river;
	}

	/**
	 * 
	 * return the number of tree of this restStop
	 * 
	 */
	public int getTree() {

		return tree;
	}

}
