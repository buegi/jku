package swe2.ss20.ue11.demo.patterns.chainofresponsibility;

public class TextParser extends Parser {

    public TextParser() {

    }

    public TextParser(Parser successor) {
        this.setSuccessor(successor);
    }

    @Override
    public void parse(String fileName) {
        if (canHandleFile(fileName, ".txt")) {
            System.out.println("The text parser is handling the file: " + fileName);
        } else {
            delegateCall(fileName);
        }
    }
}