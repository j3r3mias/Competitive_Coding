
//Top view of a binary tree is the set of nodes visible when the tree is viewed 
//from the top. Given a binary tree, print the top view of it.

//		Author - Aastha Aneja (Github handle - Aashu24)
//		Email - anejaaastha@gmail.com

import java.util.*;

public class topView {

	// scanner
	static Scanner scn = new Scanner(System.in);

	public static void main(String[] args) {

		// object of topView class
		topView m = new topView();

		// Binary Tree
		BinaryTree bt = m.new BinaryTree();

		// This is the function call for the required question
		bt.printTopView();

	}

	// Implementation of Binary Tree
	private class BinaryTree {

		// Node class of the Binary tree
		private class Node {

			// data of node
			int data;

			// pointer to left child
			Node left;

			// pointer to right child
			Node right;
		}

		// root of the tree
		private Node root;

		// size=no. of nodes in the tree
		private int size;

		// constructor
		public BinaryTree() {

			// takeInput will take input for the binary tree and return its root
			this.root = this.takeInput(null, false);
		}

		// display function displays the binary tree
		public void display() {
			this.display(this.root);
		}

		// private display that accepts root initially
		private void display(Node node) {
			if (node.left != null) {
				System.out.print(node.left.data + "=>");
			} else {
				System.out.print("END=>");
			}

			System.out.print(node.data);
			if (node.right != null) {
				System.out.print("<=" + node.right.data);
			} else {
				System.out.print("<=END");
			}

			System.out.println();

			if (node.left != null) {
				this.display(node.left);
			}
			if (node.right != null) {
				this.display(node.right);
			}

		}

		// This takes input for the Binary tree (ilc=is Left child)
		public Node takeInput(Node parent, boolean ilc) {

			System.out.println("Enter data:");

			int cdata = scn.nextInt();
			Node child = new Node();
			child.data = cdata;
			this.size++;

			System.out.println("Does " + child.data + " have a left child (true/false):");
			// left
			boolean hlc = scn.nextBoolean();

			if (hlc) {
				child.left = this.takeInput(child, true);

			}

			System.out.println("Does " + child.data + " have a right child (true/false):");
			// right
			boolean hrc = scn.nextBoolean();

			if (hrc) {
				child.right = this.takeInput(child, false);

			}

			// return
			return child;
		}

		// this is a helper class for the printTopView function
		public class Pair {
			// it will contain a node
			Node node;
			// and the node's distance
			int dist;

			// constructor
			public Pair(Node n, int d) {
				node = n;
				dist = d;
			}
		}

		// This function prints the top view of a binary tree
		public void printTopView() {

			// if the root is null, it means the tree is empty
			if (this.root == null) {
				System.out.println("Tree is empty");
				return;
			}

			// we create a hashmap of distances that will store a distance and the
			// corresponding node on that distance. If we assume that we are putting our
			// tree on the x axis with the root situated at 0 and a distance of 1 unit
			// between every two consecutive nodes as seen from the top, we can say that
			// every node's left child will be at a distance of -1 from the parent's
			// distance and every node's right child will be at a distance of +1 from the
			// parent's distance. This Hash Map will in the end store all the nodes that be
			// seen from the top view
			HashMap<Integer, Node> distances = new HashMap<>();

			// we create a queue to traverse the binary tree in level order
			LinkedList<Pair> queue = new LinkedList<>();

			// We add the root to the queue with a distance of 0
			queue.addLast(new Pair(this.root, 0));

			// While the queue is not empty (for level order traversal)
			while (!queue.isEmpty()) {

				// we remove one element from the queue
				Pair p = queue.removeFirst();

				// If the distance that this element is at is not present in the hash map, this
				// means that we haven't seen any node before situated at that distance. If we
				// have seen a node at that distance, we will not add it to the hash map becuase
				// the node seen before will cover the new node and hence the new node will not
				// be visible from the top view
				if (!distances.containsKey(p.dist)) {
					distances.put(p.dist, p.node);
				}

				// if node's left child is not null, we add it to the queue for level order
				// traversal
				if (p.node.left != null) {
					queue.addLast(new Pair(p.node.left, p.dist - 1));
				}

				// if node's right child is not null, we add it to the queue for level order
				// traversal
				if (p.node.right != null) {
					queue.addLast(new Pair(p.node.right, p.dist + 1));
				}
			}

			System.out.println("Nodes in top view will be:");

			// in the end, we print all the nodes present in the hash map
			for (Node n : distances.values()) {
				System.out.println(n.data);
			}
		}

	}

}

// Time complexity analysis -

// The function printTopView contains a while loop that will run once for every
// node. It is simple the level order traversal with one added comparison
// statement. So, its time complexity will be O(n) where n is the number of
// nodes in the binary tree.

// Space complexity analysis -

// The function printTopView takes O(n) extra space for the queue and the Hash
// map where n is the number of nodes in the binary tree.