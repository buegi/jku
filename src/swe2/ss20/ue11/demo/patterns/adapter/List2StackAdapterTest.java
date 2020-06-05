package swe2.ss20.ue11.demo.patterns.adapter;

import java.util.LinkedList;

/**
 * Show the adapter adapting lists to stacks.
 */
public class List2StackAdapterTest {
    public static void main(String[] args) {
        Stack<String> stack = new List2StackAdapter<>(new LinkedList<>());

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