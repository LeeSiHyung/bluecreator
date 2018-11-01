package algorithm.chap04;

public class IntStack {
	private int max;
	private int ptr;
	private int[] stk;
	
	public class EmptyIntStackException extends RuntimeException{
		public EmptyIntStackException() {};
	}
	
	public class OverflowIntStackException extends RuntimeException{
		public OverflowIntStackException() {};
	}
	
	public IntStack(int capacity) {
		ptr = 0;
		max = capacity;
		try {
			stk = new int[max];
		}
		catch (OutOfMemoryError e) {
			max = 0;
		}
	}
	
	public int push(int x) throws OverflowIntStackException{
		if(ptr >= max)
			throw new OverflowIntStackException();
		
		// stk에 ptr 저장 후 ptr ++ 즉 0을 먼저 저장 한 후 1로 변환
		return stk[ptr++] = x;
	}
	
	public int pop() throws EmptyIntStackException{
		if(ptr <= 0 )
			throw new EmptyIntStackException();
		
		// push에서 했던 ptr++를 취소한 후에 stk[ptr]값을 리턴함 만약 ptr--로 한다면 ++ 됬던 값이 리턴되서 빈 값이 리턴됨
		return stk[--ptr]; 
	}
	
	public int peek() throws EmptyIntStackException{
		if(ptr <= 0 )
			throw new EmptyIntStackException();
		
		// 포인터를 감소시키지 않고, 해당 -1 값을 return 함
		return stk[ptr -1 ];
	}
	
	public int indexOf(int x) {
		// 정상 부터 선형 검색
		for(int i= (ptr-1); i >= 0; i--) {
			if(stk[i] == x)
				return i;
		}
		return -1;
	}
	
	public void clear() {
		ptr = 0;
	}
	
	public int capacity() {
		return max;
	}
	
	public int size() {
		return ptr;
	}
	
	public boolean isEmpty() {
		return ptr <= 0;
	}
	
	public boolean isFull() {
		return ptr >= max;
	}
	
	public void dump() {
		if(ptr <= 0)
			System.out.println();
		else {
			for(int i=0; i < ptr; i++) {
				System.out.print(stk[i] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int a = 0;
		System.out.println(a++);
		System.out.println(a--);
	}
}
