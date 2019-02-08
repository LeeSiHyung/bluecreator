package algorithm.doit.chap04;

public class IntStack {
	
	private int max;
	private int ptr;
	private int[] stk;
	
	public class EmptyIntStackException extends RuntimeException{}
	public class OverflowIntStackException extends RuntimeException{}
	
	public IntStack(int capacity) {
		ptr = 0;
		max = capacity;
		try {
			stk = new int[max];
		}
		catch(OutOfMemoryError e) {
			max = 0;
		}
	}
	
	public int push(int x) throws OverflowIntStackException{
		if(ptr >= max) throw new OverflowIntStackException();
		// ptr++를 하면 기본적으로 처음 ptr=0에 값을 저장하고 ptr을 ++ 하게 된다.ㄴ
		return stk[ptr++] = x;
	}
	
	public int pop() throws EmptyIntStackException{
		if(ptr <= 0) throw new EmptyIntStackException();
		// 왜 (ptr--)가 아닌 (--ptr)인지 깨달아야 한다 
		// 위의 push에서는 기본적으로 값을 추가한다음 ptr을 ++를 한다.
		// 그렇기 때문에 ptr은 기본적으로 빈 최상위를 가리키고 있고 (--ptr)를 해줘야 빈 최상위가 아닌 마지막 스텍 값을 가리키게 된다.
		return stk[--ptr]; 
	}
	
	public int peek() throws EmptyIntStackException{
		if(ptr <= 0) throw new EmptyIntStackException();
		return stk[ptr - 1];
	}
	
	public int indexOf(int x) {
		for(int i=ptr-1; i >= 0; i--) {
			if(stk[i] == x) return i;
		}
		return -1;
	}
	
	
	public void clear(){
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
		if(ptr <= 0) System.out.println("스택이 비어 있습니다.");
		else {
			for(int i=0; i < ptr; i++) {
				System.out.print(stk[i] + "  ");
			}
			System.out.println();
		}
	}
}
