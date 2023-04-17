package dataSTr;

public class MyLinkedTree<T> {
	private MyNode<T> root;
	private int length = 0;
	private final int MAX_LENGTH = 50;

	public MyLinkedTree() {
		this.root = null;
	}

	public void clear() {
		this.root = null;
		this.length = 0;
	}

	public boolean isEmpty() {
		return this.length == 0;
	}

	public boolean isFull() {
		return this.length == MAX_LENGTH;
	}

	public int elementCount() {
		return this.length;
	}

	public MyNode<T> getRoot() {
		return this.root;
	}

	public void setRoot(MyNode<T> root) {
		this.root = root;
	}

	public MyNode<T> retrieve(T data) {
		if (data != null) {
			return this.retrieve(this.root, data);
		}
		return null;
	}

	private MyNode<T> retrieve(MyNode<T> thisNode, T data) {
		if (data != null) {
			if (!isEmpty()) {
				if (thisNode.getValue().equals(data)) {
					return thisNode;
				} else if (((Comparable<T>) thisNode.getValue()).compareTo(data) > 0) { // rootValue > data
					if (thisNode.getLeftChild() != null) {
						return retrieve(thisNode.getLeftChild(), data);
					} else { // no left child
						return null;
					}
				} else if (((Comparable<T>) thisNode.getValue()).compareTo(data) < 0) { // rootValue < data
					if (thisNode.getRightChild() != null) {
						return retrieve(thisNode.getRightChild(), data);
					} else { // no right child
						return null;
					}
				}
			}
		}
		return null;
	}

	public void insertItem(T data) throws Exception {
		if (data != null) {
			if (!this.isFull()) {
				if (retrieve(data) == null) {
					this.insertItem(this.root, data);
				} else {
					throw new Exception("This value is already in tree");
				}
			} else {
				throw new Exception("Tree is full");
			}
		} else {
			throw new Exception("Incorrect value");
		}
	}

	private void insertItem(MyNode<T> thisNode, T data) throws Exception {
		if (data != null) {
			if (thisNode == null) {
				thisNode = new MyNode<T>(data);
				if (isEmpty()) {
					this.root = thisNode;
				}
				++this.length;
			} else {
				if (((Comparable<T>) thisNode.getValue()).compareTo(data) > 0) { // rootValue > data
					if (thisNode.getLeftChild() != null) {
						insertItem(thisNode.getLeftChild(), data);
					} else {
						thisNode.setLeftChild(data);
						++this.length;
					}
				} else if (((Comparable<T>) thisNode.getValue()).compareTo(data) < 0) { // rootValue < data
					if (thisNode.getRightChild() != null) {
						insertItem(thisNode.getRightChild(), data);
					} else {
						thisNode.setRightChild(data);
						++this.length;
					}
				}
			}
		}
	}

	public void deleteItem(T data) throws Exception {
		if (data != null) {
			if (!isEmpty()) {
				if (retrieve(data) != null) {
					MyNode<T> parent = this.getPredecessor(data);
					this.deleteNode(parent, data);
				} else {
					throw new Exception("This value is not in tree");
				}
			} else {
				throw new Exception("Tree is empty");
			}
		} else {
			throw new Exception("Incorrect value");
		}
	}

	private boolean deleteNode(MyNode<T> theParent, T data) throws Exception {
		if (data != null && theParent != null) {
			if (theParent.getLeftChild() != null && theParent.getLeftChild().getValue().equals(data)) {
				if (!this.getRoot().hasChildren(theParent.getLeftChild())) {
					theParent.setLeftChild((MyNode<T>) null);
					--this.length;
					return true;
				} else if (theParent.getLeftChild().getLeftChild() != null
						&& theParent.getLeftChild().getRightChild() == null) {// Node to be removed has only left
					// child
					theParent.setLeftChild(theParent.getRightChild().getLeftChild());
					--this.length;
					return true;
				} else if (theParent.getLeftChild().getRightChild() != null
						&& theParent.getLeftChild().getLeftChild() == null) { // Node to be removed has only right
					// child
					theParent.setLeftChild(theParent.getLeftChild().getRightChild());
					--this.length;
					return true;
				} else { // Node to be removed has both children
					T tempValue = this.getMinValueFromTree(theParent.getLeftChild().getRightChild());
					theParent.getLeftChild().setValue(tempValue);
					this.deleteNode(theParent.getLeftChild(), tempValue);
				}
			} else if (theParent.getRightChild() != null && theParent.getRightChild().getValue().equals(data)) {
				if (!this.getRoot().hasChildren(theParent.getRightChild())) { // Node to be removed doesn't have
					// children
					theParent.setRightChild((MyNode<T>) null);
					--this.length;
					return true;
				} else if (theParent.getRightChild().getLeftChild() != null
						&& theParent.getRightChild().getRightChild() == null) {// Node to be removed has only left
					// child
					theParent.setRightChild(theParent.getRightChild().getLeftChild());
					--this.length;
					return true;
				} else if (theParent.getRightChild().getRightChild() != null
						&& theParent.getRightChild().getLeftChild() == null) { // Node to be removed has only right
					// child
					theParent.setRightChild(theParent.getRightChild().getRightChild());
					--this.length;
					return true;
				} else { // Node to be removed has both children
					T tempValue = this.getMinValueFromTree(theParent.getRightChild().getRightChild());
					theParent.getRightChild().setValue(tempValue);
					this.deleteNode(theParent.getRightChild(), tempValue);
				}
			}
		}
		return false;
	}

	private T getMinValueFromTree(MyNode<T> node) throws Exception {
		if (node != null) {
			if (!isEmpty()) {
				if (node.getLeftChild() == null) {
					return node.getValue();
				} else {
					return getMinValueFromTree(node.getLeftChild());
				}
			} else {
				throw new Exception("Tree is empty");
			}
		} else {
			throw new Exception("Given node does not exist");
		}
	}

	public MyNode<T> getPredecessor(T temp) throws Exception {
		if (temp != null) {
			if (!isEmpty()) {
				if (retrieve(temp) != null) {
					return this.getPredecessor(temp, root);
				} else {
					throw new Exception("This node is not in the Tree");
				}
			} else {
				throw new Exception("Tree is empty");
			}
		} else {
			throw new Exception("Given node does not exist");
		}
	}

	public MyNode<T> getPredecessor(T temp, MyNode<T> root) throws Exception {
		if (temp != null) {
			if (!isEmpty()) {
				if (retrieve(temp) != null) {
					MyNode<T> thisNode = root;
					if (thisNode.getValue().equals(temp)) {
						return thisNode;
					} else if (((Comparable<T>) thisNode.getValue()).compareTo(temp) > 0) {// rootValue >
																							// data
						if (thisNode.getLeftChild() != null) {
							if (thisNode.getLeftChild().getValue().equals(temp)) {
								return thisNode;
							} else {
								return getPredecessor(temp, thisNode.getLeftChild());
							}
						}
						return null;
					} else {// rootValue < data
						if (thisNode.getRightChild() != null) {
							if (thisNode.getRightChild().getValue().equals(temp)) {
								return thisNode;
							} else {
								return getPredecessor(temp, thisNode.getRightChild());
							}
						}
						return null;
					}
				} else {
					throw new Exception("This value is not in the Tree");
				}
			} else {
				throw new Exception("Tree is empty");
			}
		} else {
			throw new Exception("Incorrect value");
		}
	}

	public void print() {
		System.out.println("Printing tree preOrder: ");
		this.printTreePreOrder(this.root);

		System.out.println("\nPrinting tree postOrder: ");
		this.printTreePostOrder(this.root);

		System.out.println("\nPrinting tree inOrder: ");
		this.printTreeInOrder(this.root);
	}

	public void printTreePreOrder() {
		this.printTreePreOrder(this.root);
	}

	public void printTreePostOrder() {
		this.printTreePostOrder(this.root);
	}

	public void printTreeInOrder() {
		this.printTreeInOrder(this.root);
	}

	private void printTreePreOrder(MyNode<T> temp) {
		if (temp != null) {
			System.out.println(temp.getValue());
			if (temp.getLeftChild() != null) {
				printTreePreOrder(temp.getLeftChild());
			}
			if (temp.getRightChild() != null) {
				printTreePreOrder(temp.getRightChild());
			}
		}
	}

	private void printTreePostOrder(MyNode<T> temp) {
		if (temp != null) {
			if (temp.getLeftChild() != null) {
				printTreePostOrder(temp.getLeftChild());
			}
			if (temp.getRightChild() != null) {
				printTreePostOrder(temp.getRightChild());
			}
			System.out.println(temp.getValue());
		}
	}

	private void printTreeInOrder(MyNode<T> temp) {
			if (temp != null) {
				if (temp.getLeftChild() != null) {
					printTreeInOrder(temp.getLeftChild());
				}
				System.out.println(temp.getValue());
				if (temp.getRightChild() != null) {
					printTreeInOrder(temp.getRightChild());
				}
			}
		}

	public void printTreeWithText() throws Exception {
		this.printTreeWithText(this.root);
	}

	private void printTreeWithText(MyNode<T> temp) throws Exception {
		if (!isEmpty()) {
			if (temp != null) {
				if (temp.getValue() != null) {
					System.out.print("Parent: " + temp.getValue());
				}
				if (temp.getLeftChild() != null) {
					System.out.print(" Left: " + temp.getLeftChild().getValue());
				}
				if (temp.getRightChild() != null) {
					System.out.print(" Right: " + temp.getRightChild().getValue() + "\n");
				}
				System.out.print("\n");
				if (temp.getLeftChild() != null) {
					printTreeWithText(temp.getLeftChild());
				}
				if (temp.getRightChild() != null) {
					printTreeWithText(temp.getRightChild());
				}
			}
		}
	}
	
}
