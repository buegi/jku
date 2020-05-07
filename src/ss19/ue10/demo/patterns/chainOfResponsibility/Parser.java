package ss19.ue10.demo.patterns.chainOfResponsibility;

public abstract class Parser {

	private Parser successor;

	protected Parser getSuccessor() {
		return successor;
	}

	protected void setSuccessor(Parser successor) {
		this.successor = successor;
	}

	protected boolean canHandleFile(String fileName, String format){
		return (fileName == null) || (fileName.endsWith(format));
	}

	protected void delegateCall(String fileName){
		if ( getSuccessor() != null ){
			getSuccessor().parse(fileName);
		}
		else{
			System.out.println("Unable to find parser for file: " + fileName);
		}
	}
	
	public abstract void parse(String fileName);

}
