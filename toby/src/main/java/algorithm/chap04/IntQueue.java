package algorithm.chap04;

public class IntQueue {
	
	private int max;
	private int front;
	private int rear;
	private int num;
	
	private int[] que;
	
	public class EmptyIntQueueException extends RuntimeException{}
	public class OverflowIntQueueException extends RuntimeException{}
	
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
		if(num >= max)
			throw new OverflowIntQueueException();
		
		// enque를 하면 현재 포인터 rear 데이터를 추가하고 그 다음 포인터를 바라보도록 한다. rear++
		que[rear++] = x;
		num ++;
		
		if(rear == max)
			rear = 0;
		
		return x;
	}
	
	public int deque() throws EmptyIntQueueException{
		
		if(num == 0)
			throw new EmptyIntQueueException();
		
		// deque를 하면 현재 front 포인터 상의 데이터가 리턴되고 그 다음 포인터를 바라보도록 한다. front++
		int x = que[front++];
		num --;
		
		if(front == max) {
			front = 0;
		}
		
		return x;
	}
	
	public int peek() throws EmptyIntQueueException{
		
		if(num == 0)
			throw new EmptyIntQueueException();
		
		return que[front];
	}
	
	
	public int indexOf(int x) {
		for(int i=0; i < num; i++) {
			// 큐는 front가 기준임. 왜냐하면 선입선출 방법이기 때문이다.
			// 링버퍼이기 때문에 0부터 시작하지 않으니, i + front를 두고, 다시 처음 0부터 순차적으로 변해야 하기 때문에 (i + front) % max를 둔다.
			// num이 아닌 max인 이유는 max를 기준으로 링이 돌기 때문이다.
			int idx = (i + front) % num;
			if(que[idx] == x)
				return idx;
		}
		return -1;
	}
	
	public void clear() {
		num = front = rear = 0;
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
		if(num <= 0 ) {
			System.out.println("큐가 비어 있습니다.");
		}
		else {
			for(int i=0; i < num; i++) {
				System.out.print(que[(i + front) % max] + " ");
			}
			System.out.println();
		}
	}
}
