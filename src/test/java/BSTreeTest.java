import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class BSTreeTest {

    @Test
    void emptyTree() {
        BSTree tree = new BSTree();

        assertTrue(tree.isEmpty());
        assertEquals(0, tree.getSize());
        assertNull(tree.retrieve(5));
        assertNull(tree.largest());
        assertTrue(tree.toList().isEmpty());
        assertEquals(0, tree.sum());
        assertEquals(0, tree.retrieveDepth(5));
        assertTrue(tree.myEquals(new BSTree()));
        BSTree nonEmpty = new BSTree();
        nonEmpty.insert(10);
        assertFalse(tree.myEquals(nonEmpty));
        assertFalse(nonEmpty.myEquals(tree));
    }

    @Test
    void basicOperations() {
        BSTree tree = new BSTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);

        assertFalse(tree.isEmpty());
        assertEquals(5, tree.getSize());
        assertEquals(Integer.valueOf(10), tree.retrieve(10));
        assertEquals(Integer.valueOf(3), tree.retrieve(3));
        assertEquals(Integer.valueOf(15), tree.retrieve(15));
        assertNull(tree.retrieve(6));
        assertEquals(Integer.valueOf(15), tree.largest());
        assertEquals(Arrays.asList(3, 5, 7, 10, 15), tree.toList());
        assertEquals(40, tree.sum());
    }

    @Test
    void depth() {
        BSTree tree = new BSTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);

        assertEquals(0, tree.retrieveDepth(10));
        assertEquals(1, tree.retrieveDepth(5));
        assertEquals(1, tree.retrieveDepth(15));
        assertEquals(2, tree.retrieveDepth(3));
        // Missing numbers return the depth where they would be inserted.
        assertEquals(3, tree.retrieveDepth(6));
        assertEquals(2, tree.retrieveDepth(20));
    }

    @Test
    void equality() {
        BSTree tree = new BSTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        BSTree same = new BSTree();
        same.insert(10);
        same.insert(15);
        same.insert(5);
        assertTrue(tree.myEquals(same)); // Different insertion order, but same tree.

        BSTree differentShape = new BSTree();
        differentShape.insert(5);
        differentShape.insert(10);
        differentShape.insert(15);
        assertFalse(tree.myEquals(differentShape));

        BSTree differentValue = new BSTree();
        differentValue.insert(10);
        differentValue.insert(5);
        differentValue.insert(16);
        assertFalse(tree.myEquals(differentValue));

        BSTree missingValue = new BSTree();
        missingValue.insert(10);
        missingValue.insert(5);
        assertFalse(tree.myEquals(missingValue));

        same.insert(12);
        assertFalse(tree.myEquals(same)); // Now it has an extra value.

        BSTree duplicate = new BSTree();
        duplicate.insert(10);
        duplicate.insert(5);
        duplicate.insert(15);
        duplicate.insert(5);
        assertFalse(tree.myEquals(duplicate)); // Extra duplicate.
    }

    @Test
    void largestOnDifferentTrees() {
        BSTree left = new BSTree();
        left.insert(40);
        assertEquals(Integer.valueOf(40), left.largest()); // One node.
        left.insert(30);
        left.insert(20);
        left.insert(10);
        assertEquals(Integer.valueOf(40), left.largest()); // Largest is the root.

        BSTree right = new BSTree();
        right.insert(10);
        right.insert(20);
        right.insert(30);
        right.insert(40);
        right.insert(50);
        assertEquals(Integer.valueOf(50), right.largest()); // Long right branch.

        BSTree mixed = new BSTree();
        mixed.insert(50);
        mixed.insert(30);
        mixed.insert(80);
        mixed.insert(20);
        mixed.insert(40);
        mixed.insert(70);
        mixed.insert(90);
        mixed.insert(85);
        assertEquals(Integer.valueOf(90), mixed.largest()); // Largest has a left child.
        mixed.insert(100);
        assertEquals(Integer.valueOf(100), mixed.largest()); // New largest value.
        mixed.insert(100);
        assertEquals(Integer.valueOf(100), mixed.largest()); // Duplicate largest.

        BSTree negative = new BSTree();
        negative.insert(-10);
        negative.insert(-20);
        negative.insert(-5);
        negative.insert(-2);
        assertEquals(Integer.valueOf(-2), negative.largest()); // All negative.

        BSTree zero = new BSTree();
        zero.insert(0);
        zero.insert(-5);
        zero.insert(-10);
        assertEquals(Integer.valueOf(0), zero.largest());
    }

    @Test
    void sumOfBiggerTree() {
        BSTree tree = new BSTree();
        tree.insert(8);
        tree.insert(4);
        tree.insert(12);
        tree.insert(2);
        tree.insert(6);
        tree.insert(10);
        tree.insert(14);
        tree.insert(1);
        tree.insert(3);
        tree.insert(5);
        tree.insert(7);
        tree.insert(9);
        tree.insert(11);
        tree.insert(13);
        tree.insert(15);
        assertEquals(120, tree.sum()); // Numbers 1 through 15.
        assertEquals(120, tree.sum()); // Calling it again gives the same total.

        tree.insert(-20);
        assertEquals(100, tree.sum());
        tree.insert(0);
        assertEquals(100, tree.sum());
        tree.insert(15);
        assertEquals(115, tree.sum()); // Count the duplicate too.
    }
}
