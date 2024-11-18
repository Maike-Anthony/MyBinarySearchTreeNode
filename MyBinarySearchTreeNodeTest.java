import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import javax.naming.LimitExceededException;

import org.junit.jupiter.api.Test;

public class MyBinarySearchTreeNodeTest {
    @Test
    void testGetItem() {
        MyBinarySearchTreeNode<Integer> node = new MyBinarySearchTreeNode<Integer>(1);
        int value = node.getItem();
        assertEquals(1, value);
    }

    @Test
    void testGetParent() {
        MyBinarySearchTreeNode<Integer> node = new MyBinarySearchTreeNode<Integer>(2);
        assertNull(node.getParent());
    }

    @Test
    void testInsert() {
        MyBinarySearchTreeNode<Integer> root = new MyBinarySearchTreeNode<Integer>(2);
        MyBinarySearchTreeNode<Integer> second = root.insert(1);
        MyBinarySearchTreeNode<Integer> third = root.insert(3);
        assertEquals(root, second.getParent());
        assertEquals(root, third.getParent());
        MyBinarySearchTreeNode<Integer> fourth = root.insert(2);
        assertNull(fourth);
    }

    @Test
    void testPreOrder() {
        MyBinarySearchTreeNode<Integer> root = new MyBinarySearchTreeNode<Integer>(151);
        root.insert(150);
        root.insert(241);
        root.insert(210);
        root.insert(275);
        root.insert(280);
        List<Integer> received = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        expected.add(151);
        expected.add(150);
        expected.add(241);
        expected.add(210);
        expected.add(275);
        expected.add(280);
        root.preOrder(received);
        assertEquals(expected, received);
    }

    @Test
    void testInOrder() {
        MyBinarySearchTreeNode<Integer> root = new MyBinarySearchTreeNode<Integer>(151);
        root.insert(150);
        root.insert(241);
        root.insert(210);
        root.insert(275);
        root.insert(280);
        List<Integer> received = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        expected.add(150);
        expected.add(151);
        expected.add(210);
        expected.add(241);
        expected.add(275);
        expected.add(280);
        root.inOrder(received);
        assertEquals(expected, received);
    }

    @Test
    void testPostOrder() {
        MyBinarySearchTreeNode<Integer> root = new MyBinarySearchTreeNode<Integer>(151);
        root.insert(150);
        root.insert(241);
        root.insert(210);
        root.insert(275);
        root.insert(280);
        List<Integer> received = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        expected.add(150);
        expected.add(210);
        expected.add(280);
        expected.add(275);
        expected.add(241);
        expected.add(151);
        root.postOrder(received);
        assertEquals(expected, received);
    }

    @Test
    void testFind() {
        MyBinarySearchTreeNode<Integer> root = new MyBinarySearchTreeNode<Integer>(151);
        MyBinarySearchTreeNode<Integer> second = root.insert(150);
        MyBinarySearchTreeNode<Integer> third = root.insert(241);
        MyBinarySearchTreeNode<Integer> fourth = root.insert(210);
        MyBinarySearchTreeNode<Integer> fifth = root.insert(275);
        MyBinarySearchTreeNode<Integer> sixth = root.insert(280);
        assertEquals(root, root.find(151));
        assertEquals(second, root.find(150));
        assertEquals(third, root.find(241));
        assertEquals(fourth, root.find(210));
        assertEquals(fifth, root.find(275));
        assertEquals(sixth, root.find(280));
        assertNull(root.find(4));
    }

    @Test
    void testRemove() {
        MyBinarySearchTreeNode<Integer> root = new MyBinarySearchTreeNode<Integer>(151);
        root.insert(150);
        root.insert(241);
        root.insert(210);
        root.insert(275);
        root.insert(280);
        /** Removing leaves: */
        root.remove(280);
        assertNull(root.find(280));
        root.remove(210);
        assertNull(root.find(210));
        root.insert(280);
        root.insert(210);
        /** Removing node with one child: */
        /** right right: */
        root.remove(275);
        assertNull(root.find(275));
        /** right left: */
        root.insert(275);
        root.remove(280);
        assertNull(root.find(280));
        root.insert(280);
        /** left left: */
        root.insert(200);
        root.remove(210);
        assertNull(root.find(210));
        /** left right: */
        root.insert(210);
        root.remove(200);
        assertNull(root.find(200));
        /** Removing node with two children and successor is a leaf node: */
        root.remove(280);
        root.remove(241);
        assertNull(root.find(241));
        /** Removing node with two children and successor has one child: */
        root.remove(210);
        root.remove(275);
        root.insert(241);
        root.insert(210);
        root.insert(275);
        root.insert(280);
        root.remove(241);
        assertNull(root.find(241));
    }
}
