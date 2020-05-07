package algodat2.ws19.ue07.ex01;

public class ChainingHashNode {
	Integer key;
	String data;
	ChainingHashNode next;

	ChainingHashNode(Integer key, String data) {
		this.key = key;
		this.data = data;
		next = null;
	}
}