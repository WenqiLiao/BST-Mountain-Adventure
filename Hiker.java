package project5;

import java.util.ArrayList;

/**
 * This class should represent a hiker traveling down the mountain. An object of
 * this class should be capable of keeping track of all the supplies that the
 * hiker has in their possession. This information should be updated as the
 * hiker travels along a trail and consumes supplies (by either eating along the
 * way, or using the tools to clear the path, or cross the river). You may, but
 * you are not required to, implement other classes.
 * 
 * @author Wenqi Liao
 * @version 05/02/2021
 *
 */
public class Hiker {

	private int food;
	private int axe;
	private int raft;

	/**
	 * Constructs a new Hiker object with the number of food, axe, and raft.
	 * 
	 * @param the number of food in the bag
	 * @param the number of axe in the bag
	 * @param the number of raft in the bag
	 */
	public Hiker(int food, int axe, int raft) {

		this.food = food;
		this.axe = axe;
		this.raft = raft;
	}

	/**
	 * 
	 * find the number of food in the bag
	 * 
	 * @return the number of food in the bag.
	 * 
	 */
	public int getFood() {
		return food;
	}

	/**
	 * 
	 * find the number of axe in the bag
	 * 
	 * @return the number of axe in the bag.
	 * 
	 */
	public int getAxe() {
		return axe;
	}

	/**
	 * 
	 * find the number of raft in the bag
	 * 
	 * @return the number of raft in the bag.
	 * 
	 */
	public int getRaft() {
		return raft;
	}

	/**
	 * 
	 * change the number of food in the bag
	 * 
	 * @return the number of food in the bag.
	 * 
	 */
	public int setFood(int number) {

		return food += number;
	}

	/**
	 * 
	 * change the number of axe in the bag
	 * 
	 * @return the number of axe in the bag.
	 * 
	 */
	public int setAxe(int number) {

		return axe += number;
	}

	/**
	 * 
	 * change the number of raft in the bag
	 * 
	 * @return the number of raft in the bag.
	 * 
	 */
	public int setRaft(int number) {

		return raft += number;
	}
}
