package ss19.ue10.demo.patterns.chainOfResponsibility;

public class TextParser extends Parser{

	public TextParser(){

	}

	public TextParser(Parser successor){
		this.setSuccessor(successor);
	}

	@Override
	public void parse(String fileName) {
		if ( canHandleFile(fileName, ".txt")){
			System.out.println("The text parser is handling the file: "+fileName);
		}
		else{
			delegateCall(fileName);
		}
	}
}
