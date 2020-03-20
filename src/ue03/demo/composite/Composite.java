package ue03.demo.composite;

public class Composite extends Element {
	
	private Element[] subElements; 

	public Composite(int i, Element... subElements) {
		super(i);
		this.subElements = subElements;
	}

	@Override
	public int operation() {
		int depth = 0; 
		for (Element element: subElements) {
			int elemDepth = element.operation(); 
			if (elemDepth > depth) {
				depth = elemDepth; 
			}
		}
		return depth + 1;
	}

}
