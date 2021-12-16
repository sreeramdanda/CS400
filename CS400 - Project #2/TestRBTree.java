// --== CS400 File Header Information ==--
// Name: <your full name>
// Email: <your @wisc.edu email address>
// Team: <your team name: two letters>
// TA: <name of your team's ta>
// Lecturer: <name of your lecturer>
// Notes to Grader: <optional extra notes>
import org.junit.Test;
import static org.junit.Assert.*;
public class TestRBTree extends RedBlackTree {
	//Extended RedBlackTree to access Node.toString()

	@Test
	/**
	 * Inserts a root node and its children, checking that adding to a black parent (root) works properly.
	 */
	public void insertBlackParent() {
		// root node
		RedBlackTree tree = new RedBlackTree();
		Integer root = 5;
		tree.insert(root);
		Integer leftChild = 3;
		Integer rightChild = 6;
		tree.insert(leftChild);
		tree.insert(rightChild);
		assertEquals("[ 3, 5, 6 ]", tree.toString());
		assertEquals("[5, 3, 6]", tree.root.toString());
	}

	@Test
	/** 
	 * Inserts a child at a red parent with a red sibling, checking for proper insertion.
 	*/
	public void insertRedParentRedSibling() {
		RedBlackTree tree = new RedBlackTree();
		Integer root = 5;
		tree.insert(root);
		Integer leftChild = 3;
		Integer rightChild = 6;
		tree.insert(leftChild);
		tree.insert(rightChild);
		Integer leftBaby = 2;
		tree.insert(leftBaby);
		assertEquals("[ 2, 3, 5, 6 ]", tree.toString());
		assertEquals("[5, 3, 6, 2]", tree.root.toString());
	}

	@Test
	/**
 	* Inserts a left child at a red parent with a black sibling, checking for proper addition
 	*/
	public void insertRedParentBlackSiblingLeft() {
		RedBlackTree tree = new RedBlackTree();
		Integer root = 5;
		tree.insert(root);
		Integer leftChild = 3;
		tree.insert(leftChild);
		Integer leftBaby = 2;
		tree.insert(leftBaby);
		assertEquals("[ 2, 3, 5 ]", tree.toString());
		assertEquals("[3, 2, 5]", tree.root.toString());

		// Check subrotation
		RedBlackTree tree2 = new RedBlackTree();
		tree2.insert(root);
		tree2.insert(leftBaby);
		tree2.insert(leftChild);
		assertEquals("[ 2, 3, 5 ]", tree2.toString());
		assertEquals("[3, 2, 5]", tree2.root.toString());
	}

	@Test
	/**
	 * Inserts a right child at a red parent with a black sibling, checking for proper insertion.
 	*/
	public void insertRedParentBlackSiblingRight() {
		RedBlackTree tree = new RedBlackTree();
		Integer root = 5;
		tree.insert(root);
		Integer rightChild = 7;
		tree.insert(rightChild);
		Integer rightBaby = 8;
		tree.insert(rightBaby);
		assertEquals("[ 5, 7, 8 ]", tree.toString());
		assertEquals("[7, 5, 8]", tree.root.toString());

		// Check subrotation
		RedBlackTree tree2 = new RedBlackTree();
		tree2.insert(root);
		tree2.insert(rightBaby);
		tree2.insert(rightChild);
		assertEquals("[ 5, 7, 8 ]", tree2.toString());
		assertEquals("[7, 5, 8]", tree2.root.toString());
	}

	@Test
	/**
	 * Tests a specific case that fails on Gradescope
	 */
	public void testUncleTest() {
		RedBlackTree tree = new RedBlackTree();
		Node root = new Node(45);
		root.isBlack = true;
		Node leftChild = new Node(26);
		leftChild.parent = root;
		root.leftChild = leftChild;
		Node rightChild = new Node(72);
		rightChild.isBlack = true;
		rightChild.parent = root;
		root.rightChild = rightChild;
		tree.root = root;
		tree.insert(18);
		assertEquals(true, tree.root.isBlack);
		// Bellow is the prob
		assertEquals(false, tree.root.leftChild.isBlack);

		assertEquals(false, tree.root.rightChild.isBlack);
		assertEquals(true, tree.root.rightChild.rightChild.isBlack);
	}
}
