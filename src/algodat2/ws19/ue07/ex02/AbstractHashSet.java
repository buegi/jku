package algodat2.ws19.ue07.ex02;

abstract class AbstractHashSet {
	public final int getHashCode(Integer key, Integer hashTableLength) {
		return key.hashCode() % hashTableLength;
	}
}