package algodat2.ue07.ex01;

abstract class AbstractChainingHashSet {
	public final int getHashCode(Integer key, Integer hashTableLength) {
		return key.hashCode() % hashTableLength;
	}
}