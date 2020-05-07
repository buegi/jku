package swe1.ws17.ue00;

import swe1.input.Input;

public class MyFirstProgram {
    public static void main(String[] arg) {
        System.out.print("Please enter your name: ");
        String name = Input.readString();
        System.out.print("Hello ");
        System.out.print(name);
        System.out.println("!");
    }
}