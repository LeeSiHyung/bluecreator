package test.if_;

public class DigitSequence implements IntSequence{
	
	private int number;
	

	public DigitSequence(int number) {
		this.number = number;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return number != 0;
	}

	@Override
	public int next() {
		int result = number % 10;
		number /= 10;
		return result;
	}
	
	public int rest() {
		return number;
	}

}
