import java.util.List;

/**
 * A class to generate a binary search tree node.
 * @author Maike Anthony dos Santos Silva
 */
public class MyBinarySearchTreeNode<T extends Comparable> {
    /**The item stored in the node.*/
    private T item;
    /**The node's parent, which is null for the root node. */
    private MyBinarySearchTreeNode<T> parent;
    /**This node's left child. */
    private MyBinarySearchTreeNode<T> leftChild;
    /**This node's right child. */
    private MyBinarySearchTreeNode<T> rightChild;

    /**
     * Private constructor for non-root nodes
     * @param item node's item.
     * @param parent node's parent.
     */
    private MyBinarySearchTreeNode(T item, MyBinarySearchTreeNode<T> parent) {
        this.item = item;
        this.parent = parent;
        this.leftChild = null;
        this.rightChild = null;
    }

    /**
     * Public constructor for the root node.
     * @param item root node's item.
     */
    public MyBinarySearchTreeNode(T item) {
        this(item, null);
    }

    /**
     * Getter method for the node's item.
     * @return the node's item.
     */
    public T getItem() {
        return this.item;
    }

    /**
     * Getter method for the node's parent.
     * @return the node's parent.
     */
    public MyBinarySearchTreeNode<T> getParent() {
        return this.parent;
    }

    /**
     * Method to insert new nodes.
     * @param item contained by the new node.
     * @return the new node.
     */
    public MyBinarySearchTreeNode<T> insert(T item) {
        MyBinarySearchTreeNode<T> returnee = null;
        if (this.item.equals(item)) {
            return null;
        } else if (this.item.compareTo(item) > 0) {
            if (this.leftChild == null) {
                MyBinarySearchTreeNode<T> newNode = new MyBinarySearchTreeNode<T>(item, this);
                this.leftChild = newNode;
                returnee = newNode;
            } else {
                returnee = this.leftChild.insert(item);
            }
        } else if (this.item.compareTo(item) < 0) {
            if (this.rightChild == null) {
                MyBinarySearchTreeNode<T> newNode = new MyBinarySearchTreeNode<T>(item, this);
                this.rightChild = newNode;
                returnee = newNode;
            } else {
                returnee = this.rightChild.insert(item);
            }
        }
        return returnee;
    }

    /**
     * Method that does pre-order traversal on the tree.
     * @param list traversed tree.
     */
    public void preOrder(List<T> list) {
        list.add(this.item);
        if (this.leftChild != null) {
            this.leftChild.preOrder(list);
        }
        if (this.rightChild != null) {
            this.rightChild.preOrder(list);
        }
    }

    /**
     * Method that does in-order traversal on the tree.
     * @param list traversed tree.
     */
    public void inOrder(List<T> list) {
        if (this.leftChild != null) {
            this.leftChild.inOrder(list);
        }
        list.add(this.item);
        if (this.rightChild != null) {
            this.rightChild.inOrder(list);
        }        
    }

    /**
     * Method that does post-order traversal on the tree.
     * @param list traversed tree.
     */
    public void postOrder(List<T> list) {
        if (this.leftChild != null) {
            this.leftChild.postOrder(list);
        }
        if (this.rightChild != null) {
            this.rightChild.postOrder(list);
        }
        list.add(this.item);
    }

    /**
     * Method to find nodes in the tree.
     * @param obj item to be found.
     * @return the node that contains that item or null if not found.
     */
    public MyBinarySearchTreeNode<T> find(Object obj) {
        if (this.item.compareTo(obj) == 0) {
            return this;
        } else if (this.item.compareTo(obj) > 0) {
            if (this.leftChild != null) {
                return this.leftChild.find(obj);
            }
        } else if (this.item.compareTo(obj) < 0) {
            if (this.rightChild != null) {
                return this.rightChild.find(obj);
            }
        }
        return null;
    }

    /**
     * Helper method to find the successor node.
     * @return the node containing the successor item of the current one.
     */
    private MyBinarySearchTreeNode<T> findSuccessor() {
        MyBinarySearchTreeNode<T> returnee = null;
        if (this.rightChild == null) {
            if (this.parent != null) {
                return this.parent;
            } else {
                return null;
            }
        } else if (this.rightChild.leftChild == null) {
            return this.rightChild;
        }
        returnee = this.rightChild;
        while (returnee.leftChild != null) {
            returnee = returnee.leftChild;
        }
        return returnee;
    }

    /**
     * Method to remove nodes from the tree.
     * @param item the item of the node to be removed.
     */
    public void remove(T item) {
        if (this.item.equals(item)) {
            this.removeCurNode();
        } else if (this.item.compareTo(item) > 0) {
            if (this.leftChild == null) {
                return;
            } else {
                this.leftChild.remove(item);
            }
        } else if (this.item.compareTo(item) < 0) {
            if (this.rightChild == null) {
                return;
            } else {
                this.rightChild.remove(item);
            }
        }
    }

    /**
     * Helper method to remove the current node.
     */
    private void removeCurNode() {
        if (this.leftChild == null && this.rightChild == null) {
            this.removeLeaf();
        } else if ((this.leftChild == null || this.rightChild == null) && (this.leftChild != null || this.rightChild != null)) {
            this.removeOneChild();
        } else {
            this.removeTwoChildren();
        }
    }

    /**
     * Helper method to remove the current node if it is a leaf.
     */
    private void removeLeaf() {
        if (this.parent.leftChild == this) {
            this.parent.leftChild = null;
        } else {
            this.parent.rightChild = null;
        }
    }

    /**
     * Helper method to remove the current node if it has only one child.
     */
    private void removeOneChild() {
        if (this.parent.leftChild == this) {
            if (this.rightChild == null) {
                this.parent.leftChild = this.leftChild;
                this.leftChild.parent = this.parent;
            } else {
                this.parent.leftChild = this.rightChild;
                this.rightChild.parent = this.parent;
            }
        } else if (this.parent.rightChild == this) {
            if (this.rightChild == null) {
                this.parent.rightChild = this.leftChild;
                this.leftChild.parent = this.parent;
            } else {
                this.parent.rightChild = this.rightChild;
                this.rightChild.parent = this.parent;
            }
        }
    }

    /**
     * Helper method to remove the current node if it has two children.
     */
    private void removeTwoChildren() {
        MyBinarySearchTreeNode<T> sucNode = this.findSuccessor();
        T sucValue = sucNode.item;
        this.remove(sucValue);
        this.item = sucValue;
    }
}
