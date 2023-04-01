package main;

import dataSTr.MyLinkedTree;

public class Main {

	public static void main(String[] args) {
		try {
			MyLinkedTree<Integer> testTree = new MyLinkedTree<Integer>();
			testTree.insertItem(5);
			testTree.insertItem(2);
			testTree.insertItem(3);
			testTree.insertItem(12);
			testTree.insertItem(9);
			testTree.insertItem(21);
			testTree.insertItem(19);
			testTree.insertItem(25);
			testTree.print();
			testTree.deleteItem(21);
			testTree.print();
			System.out.println("\nRoot: " + testTree.getRoot());
			System.out.println("\nLeft: " + testTree.getRoot().getLeftChild());
			System.out.println("\nRight: " + testTree.getRoot().getRightChild());

			System.out.println();
			testTree.printTreeWithText();

			testTree.deleteItem(12);
			System.out.println();
			testTree.printTreeWithText();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
