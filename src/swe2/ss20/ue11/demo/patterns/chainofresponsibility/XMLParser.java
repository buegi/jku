package swe2.ss20.ue11.demo.patterns.chainofresponsibility;

public class XMLParser extends Parser {

    public XMLParser() {

    }

    public XMLParser(Parser successor) {
        this.setSuccessor(successor);
    }

    @Override
    public void parse(String fileName) {
        if (canHandleFile(fileName, ".xml")) {
            System.out.println("A XML parser is handling the file: " + fileName);
        } else {
            delegateCall(fileName);
        }
    }
}