package swe2.ss19.ue10.demo.patterns.chainOfResponsibility;

public class JSONParser extends Parser {

	public JSONParser(){

	}

	public JSONParser(Parser successor){
		this.setSuccessor(successor);
	}

	@Override
	public void parse(String fileName) {
		if ( canHandleFile(fileName, ".json")){
			System.out.println("A JSON parser is handling the file: "+fileName);
		}
		else{
			delegateCall(fileName);
		}

	}

}
