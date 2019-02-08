package algorithm.doit.chap04;

public class IntQueue {
	
	private int max;
	private int front;
	private int rear;
	private int num;
	private int[] que;
	
	public class EmptyIntQueueException extends RuntimeException{};
	public class OverflowIntQueueException extends RuntimeException{};
	
	public IntQueue(int capacity) {
		num = front = rear = 0;
		max = capacity;
		try {
			que = new int[max];
		}
		catch(OutOfMemoryError e) {
			max = 0;
		}
	}
	
	public int enque(int x) throws OverflowIntQueueException{
		// num이 max를 체크함
		if(num >= max) throw new OverflowIntQueueException();
		// num이 아니라 rear를 포인터로 사용함. 
		// 왜냐하면 deque의 경우는 front를 늘리고 enque시에는 rear를 늘리기 때문
		que[rear++] = x;
		num++;
		if(rear == max) rear = 0;
		return x;
	}
	
	public int deque() throws EmptyIntQueueException{
		if(num <= 0) throw new EmptyIntQueueException();
		int x = que[front++];
		// IntStack에서는 stk[--ptr]로 처리한 이유는 ptr을 바로 줄이고 줄인 값을 바로 리턴해야 되기 때문에  그렇게 처리했다.
		// 하지만 이 경우에는 메소드가 호출하는 동안 num을 사용하지 않기 때문에 num--로 처리함.
		num--;
		if(front == max) front = 0;
		return x;
	}
	
	public int peek() throws EmptyIntQueueException{
		if(num <= 0) throw new EmptyIntQueueException();
		return que[front];
	}
	
	public int indexOf(int x) {
		for(int i=0; i < num; i++) {
			// 선입선출법인 que는 값이 front부터 시작한다. 즉 기준은 front 부터 num까지 조희를 한다.
			// max를 기준으로 0으로 변경하기 때문에 idx % max로 처리한다.
			int idx = (i + front) % max;
			// 해당 큐의 idx 값이 x와 일치하는지 확인한다.
			if(que[idx] == x) return idx;
		}
		return -1;
	}
	
	public void clear() {
		front = rear = num = 0;
	}
	
	public int capacity() {
		return max;
	}
	
	public int size() {
		return num;
	}
	
	public boolean isEmpty() {
		return num <= 0;
	}
	
	public boolean isFull() {
		return num >= max;
	}
	
	public void dump() {
		if(num <= 0) System.out.println("큐가 비어 있습니다.");
		else {
			for(int i=0; i < num; i++) {
				System.out.print(que[(i + front) % max] + " ");
			}
			System.out.println();
		}
	}
	
}
