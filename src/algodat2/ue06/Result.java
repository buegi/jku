package algodat2.ue06;

public class Result {
	Integer resultIndices[];
	Integer count;

	public Result(Integer resultIndices[], int count) {
		this.resultIndices = resultIndices.clone();
		this.count = new Integer(count);
	}
}