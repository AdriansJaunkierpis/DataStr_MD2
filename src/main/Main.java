package main;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Stack;

import dataSTr.MyLinkedTree;
import dataSTr.MyNode;

public class Main {

	public static void main(String[] args) {
		
		try {
			File myFile = new File("resources/expressions.txt");
			FileInputStream myInputStream = new FileInputStream(myFile);
			Scanner myScanner = new Scanner(myInputStream);
			while (myScanner.hasNextLine()) {
				String expression = myScanner.nextLine();
				System.out.println(expression);
				MyLinkedTree tree = createTree(expression);
				tree.print();
				System.out.println(expression + " = " + calculateTree(tree));
				System.out.println("------------------------");
			}
			
			MyLinkedTree<String> testTree = new MyLinkedTree<String>();
			MyLinkedTree<String> theTree = new MyLinkedTree<String>();
			// (2*5)+(6^3)
			theTree.insertItem("+");
			MyNode<String> valTemp = theTree.getRoot();
			valTemp.setLeftChild(new MyNode<String>("*"));
			valTemp.getLeftChild().setLeftChild("2");
			valTemp.getLeftChild().setRightChild("5");
			valTemp.setRightChild("^");
			valTemp.getRightChild().setLeftChild("6");
			valTemp.getRightChild().setRightChild("3");
			//theTree.print();

			//System.out.println();
			//theTree.printTreeWithText();
			System.out.println("\nExample of tree calculation working: ");
			System.out.println("(2*5)+(6^3) = " + calculateTree(theTree));

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			
		} catch (Exception e) { //
			e.printStackTrace();
		}

	}

	public static int calculateTree(MyLinkedTree tree) {
		return calculateTree(tree.getRoot());
	}

	private static int calculateTree(MyNode<String> temp) {
		if (temp == null)
			return 0;
	
		int leftVal = calculateTree(temp.getLeftChild());
		int rightVal = calculateTree(temp.getRightChild());

		if (temp.getValue() == "+") {
			return leftVal + rightVal;
		} else if (temp.getValue() == "-") {
			return leftVal - rightVal;
		} else if (temp.getValue() == "*") {
			return leftVal * rightVal;
		} else if (temp.getValue() == "/") {
			return leftVal / rightVal;
		} else if (temp.getValue() == "^") {
			return (int) Math.pow(leftVal, rightVal);
		}

		return Integer.parseInt(temp.getValue());
	}

	public static boolean isOperator(char input) {
		return (input == '+' || input == '-' || input == '/' || input == '*' || input == '^');
	}

	private static boolean hasPrecedence(MyNode operator1, MyNode operator2) {
		int precedence1 = getPrecedence((String) operator1.getValue());
		int precedence2 = getPrecedence((String) operator2.getValue());
		return precedence1 >= precedence2;
	}

	private static int getPrecedence(String operator) {
		switch (operator) {
		case "+":
		case "-":
			return 1;
		case "*":
		case "/":
			return 2;
		case "^":
			return 3;
		default:
			throw new IllegalArgumentException("Invalid operator: " + operator);
		}
	}

	public static MyLinkedTree createTree(String input) {
		Stack<MyNode> operatorStack = new Stack<MyNode>();
		Stack<MyNode> operandStack = new Stack<MyNode>();

		for (int i = 0; i < input.length(); i++) {
			char val = input.charAt(i);

			if (Character.isDigit(val)) {
				operandStack.push(new MyNode(String.valueOf(val)));
			} else if (isOperator(val)) {
				MyNode node = new MyNode(String.valueOf(val));
				while (!operatorStack.isEmpty() && hasPrecedence(operatorStack.peek(), node)) {
					MyNode operator = operatorStack.pop();
					operator.setRightChild(operandStack.pop());
					operator.setLeftChild(operandStack.pop());
					operandStack.push(operator);
				}
				operatorStack.push(node);
			}
		}
		while (!operatorStack.isEmpty()) {
			MyNode operator = operatorStack.pop();
			operator.setRightChild(operandStack.pop());
			operator.setLeftChild(operandStack.pop());
			operandStack.push(operator);
		}

		MyLinkedTree<String> tempTree = new MyLinkedTree<String>();
		tempTree.setRoot(operandStack.pop());
		return tempTree;
	}
}
