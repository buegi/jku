package prswe2.ss21.ue02.demo.box;

import java.io.Serializable;

public class Box<T extends Comparable<T> & Serializable>{
	private T val;
	
	public Box(T val) {
		this.val = val;
	}

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}
	
	public String toString() {
		return "Box[" + val + "]";
	}
}
