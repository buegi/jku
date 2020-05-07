package swe2.ss19.ue10.demo.patterns.chainOfResponsibility;

public class CSVParser extends Parser {

	public CSVParser(){

	}

	public CSVParser(Parser successor){
		this.setSuccessor(successor);
	}

	@Override
	public void parse(String fileName) {
		if ( canHandleFile(fileName, ".csv")){
			System.out.println("A CSV parser is handling the file: "+fileName);
		}
		else{
			delegateCall(fileName);
		}
	}

}
