package algodat2.ue07.ex02;

public class OpenHashNode {
	Integer key;
	String data;
	boolean removed = false;

	OpenHashNode(Integer key, String data) {
		this.key = key;
		this.data = data;
		removed = false;
	}
}