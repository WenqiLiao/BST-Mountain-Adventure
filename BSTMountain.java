package project5;

import java.util.Comparator;

/**
 * This should be the class that reuses as much as you can of the BST<E> class
 * that we have been using during lectures and recitations. Your class should
 * represent the mountain itself (therefore, it should not generic and its nodes
 * should store data items of type RestStop). It is up to you to decide how to
 * implement this class, which methods to provide etc. The only restriction is
 * that it must implement an actual binary search tree. You cannot just use one
 * of the BST implementations that is in Java libraries.
 * 
 * @author Wenqi Liao
 * @version 05/02/2021
 *
 */
public class BSTMountain {

	private RestStop root; // root node of the mountain
	private Comparator comparator;
	private boolean added;

	/**
	 * Constructs a new BSTMountain object with a restStop node.
	 * 
	 * @param the root of the BSTMountain
	 */
	public BSTMountain(RestStop node) {

		root = node;
		comparator = null;

	}

	/**
	 * 
	 * check whether a certain node can be added into a Mountain if can, insert it
	 * 
	 * @param a restStop node wanted to be inserted.
	 * @return true if the restStop can be inserted.
	 * 
	 */
	public boolean add(RestStop node) {

		added = false;
		if (node == null) {
			return added;
		}

		// replace root with the added node
		root = add(node, root);
		return added;
	}

	/**
	 * 
	 * recursive implementation of add.
	 * 
	 * @param a   restStop node wanted to be inserted.
	 * @param the current restStop
	 * @return the rest stop which is added.
	 * 
	 */
	public RestStop add(RestStop node, RestStop current) {

		if (current == null) {
			added = true;
			return node;
		}

		// decide using which comparison
		int comp = 0;
		// use natural ordering
		if (comparator == null) {
			comp = current.getLabel().compareTo(node.getLabel());
		} else {
			// use comparator
			comp = comparator.compare(current.getLabel(), node.getLabel());
		}

		// find the location for the added node
		if (comp > 0) {
			current.left = add(node, current.left);
		} else if (comp < 0) {
			current.right = add(node, current.right);
		} else {
			added = false;
			return current;
		}
		return current;

	}

	/**
	 * 
	 * return the root of the mountain
	 * 
	 * @return the root of the mountain
	 * 
	 */
	public RestStop getRoot() {

		return root;
	}

	/**
	 * 
	 * get the depth of the tree
	 * 
	 * @return the number of the depth of the tree
	 * 
	 */
	public int getDepth() {

		return maxDepth(root);
	}

	/**
	 * 
	 * find maximum depth of the tree
	 * 
	 * @param the node of current restStop.
	 * @return return the max depth.
	 * 
	 */
	private int maxDepth(RestStop node) {

		if (node == null) {
			return 0;
		} else {
			// find the depth of left and right subtree
			int leftDepth = maxDepth(node.left);
			int rightDepth = maxDepth(node.right);

			// find the subtree with the largest depth
			if (leftDepth > rightDepth) {
				return leftDepth + 1;
			} else {
				return rightDepth + 1;
			}
		}
	}

}
