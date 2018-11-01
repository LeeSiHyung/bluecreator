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
		
		// 현재 포인터 값을 return 하고 포이터를 ++ 해줌
		return stk[ptr++] = x;
	}
	
	public int pop() throws EmptyIntStackException{
		if(ptr <= 0 )
			throw new EmptyIntStackException();
		
		// 포인터 ptr을 감소시키고 감소시킨 값을 return 함
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
}
