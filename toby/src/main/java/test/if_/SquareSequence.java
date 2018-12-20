package test.if_;

public class SquareSequence implements IntSequence{

	private int i;
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int next() {
		return i * i;
	}

}
