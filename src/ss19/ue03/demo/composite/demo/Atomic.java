package ss19.ue03.demo.composite.demo;

public class Atomic extends Element {
	
	public Atomic(int i) {
		super(i);
	}

	@Override
	public int operation() {
		return 1; 
	}

}
