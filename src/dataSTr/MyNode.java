package dataSTr;

public class MyNode<T> {
    private MyNode<T> leftChild;
    private MyNode<T> rightChild;
    private T value;

    public MyNode(T myNode) {
        setValue(myNode);
        this.leftChild = null;
        this.rightChild = null;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        if (value != null) {
            this.value = value;
        } else {
            this.value = null;
        }
    }

    public MyNode<T> getLeftChild() {
        return this.leftChild;
    }

    public void setLeftChild(T value) {
        if (value != null) {
            this.leftChild = new MyNode<T>(value);
        } else {
            this.leftChild = null;
        }
    }

    public void setLeftChild(MyNode<T> node) {
        if (node != null) {
            this.leftChild = node;
        } else {
            this.leftChild = null;
        }
    }

    public MyNode<T> getRightChild() {
        return this.rightChild;
    }

    public void setRightChild(T value) {
        if (value != null) {
            this.rightChild = new MyNode<T>(value);
        } else {
            this.rightChild = null;
        }
    }

    public void setRightChild(MyNode<T> node) {
        if (node != null) {
            this.rightChild = node;
        } else {
            this.rightChild = null;
        }
    }

    public boolean hasChildren(MyNode<T> node) throws Exception {
        if (node != null) {
            return ((node.getLeftChild() != null) || (node.getRightChild() != null));
        }
        throw new Exception("Given node does not exist");
    }

    public boolean hasChildren() throws Exception {
        if (this.value != null) {
            return ((this.getLeftChild() != null) || (this.getRightChild() != null));
        }
        throw new Exception("Given node does not exist");
    }

    @Override
    public String toString() {
        return "" + this.getValue();
    }
}