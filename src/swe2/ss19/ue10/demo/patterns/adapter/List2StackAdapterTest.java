package swe2.ss19.ue10.demo.patterns.adapter;

import java.util.LinkedList;

/**
 * Show the adapter adapting lists to stacks.
 */
public class List2StackAdapterTest {
	public static void main(String[] args) {
		Stack<String> stack = new List2StackAdapter<String>(new LinkedList<String>());

		stack.push("A");
		stack.push("B");
		stack.push("C");

		System.out.println(stack.top());
		stack.pop();

		System.out.println(stack.top());
		stack.pop();

		System.out.println(stack.top());
		stack.pop();
	}
}
