package algorithm.chap09;

import java.util.Comparator;

public class AryLinkedList<E> {
	
	class Node<E>{
		private E data;
		private int next;					// 다음포인터
		private int dnext;					// free 리스트의 다음 포인터
		
		void set(E data, int next) {
			this.data = data;
			this.next = next;
		}
	}
	
	private Node<E>[] n;
	private int size;						// 리스트 본체
	private int max;						// 사용중인 꼬리노드
	private int head;						// 머리노드
	private int crnt;						// 선택노드
	private int deleted;					// free 리스트의 머리노드
	private static final int NULL = -1;		// 다음노드가 없음 | 리스트가 가득참
	
	
	public AryLinkedList(int capacity) {
		
		head = crnt = max = deleted = NULL;
		
		try {
			n = new Node[capacity];
			for(int i=0; i < capacity; i++)
				n[i] = new Node<E>();
			size = capacity;
		}
		catch(OutOfMemoryError e) {
			size = 0;
		}
		
	}
	
	private int getInsertIndex() {
		if(deleted == NULL) {
			// max index를 추가함
			if(max < size)
				return ++ max;
			else
				return NULL;
		}
		else { 
			// deleted는 free노드[삭제된]의 머리노드로 추가할 때 이미 삭제된 deleted를 사용한다.
			int rec = deleted;				
			deleted = n[rec].dnext;	// free노드의 다음 머리노드는 삭제된 노드의 다음 노드이다.
			return rec;
		}
	}
	
	
	private void deleteIndex(int idx) {
		if(deleted == NULL) {
			// deleted는 free노드[삭제된]의 머리노드가 NULL은 다음 노드가 없으므르 다음 노드에 NULL을 넣어준다. 
			deleted = idx;
			n[idx].dnext = NULL;
		}
		else {
			// 삭제할 deleted의 다음 노드를 임시로 저장하고, idx를 deleted로 변경한후 이전 deleted를 다음 free노드에 추가한다.
			int rec = deleted;
			deleted = idx;
			n[rec].dnext = rec;
		}
	}
	
	public E search(E obj, Comparator<? super E> c) {
		int ptr = head;						// 현재 스캔 중인 노드
		
		while(ptr != NULL) {
			if(c.compare(obj, n[ptr].data) == 0) {
				crnt = ptr;
				return n[ptr].data;			// 검색성공
			}
			ptr = n[ptr].next;
		}
		
		return null;
	}
	
	
	public void addFirst(E obj) {
		int ptr = head;
		int rec = getInsertIndex();
		if(rec != NULL) {
			head = crnt = rec;
			n[head].set(obj, ptr);
		}
	}
	
	public void addLast(E obj) {
		if(head == NULL)
			addFirst(obj);
		else {
			int ptr = head;
			while(n[ptr].next != NULL)
				ptr = n[ptr].next;
			int rec = getInsertIndex();
			if(rec != NULL) {
				n[ptr].next = crnt = rec;
				n[rec].set(obj, NULL);
			}
		}
	}
	
	public void removeFirst() {
		if(head != NULL) {
			int ptr = n[head].next;
			deleteIndex(head);
			// head inx를 삭제하고 다음 index를 head로 변경한다.
			head = crnt = ptr;
		}
	}
	
	
	public void removeLast() {
		if(head != NULL) {
			if(n[head].next == NULL)
				removeFirst();
			else {
				int ptr = head;
				int pre = head;
				while(n[ptr].next != NULL) {
					pre = ptr;
					ptr = n[ptr].next;
				}
				n[pre].next = NULL;
				deleteIndex(ptr);
				crnt = pre;
			}
		}
	}
	
	public void remove(int p) {
		if(head != NULL) {
			if(p == head)
				removeFirst();
			else {
				int ptr = head;
				while(n[ptr].next != p) {
					ptr = n[ptr].next;
					if(ptr == NULL) return;
				}
				// ptr은 삭제할 포인터
				n[ptr].next = NULL;
				deleteIndex(ptr);
				n[ptr].next = n[p].next;
				crnt = ptr;
			}
		}
	}
	
	public void removeCurrentNode() {
		remove(crnt);
	}
	
	public void clear() {
		while(head != NULL)
			removeFirst();
		crnt = NULL;
	}
	
	public boolean next() {
		if(crnt == NULL || n[crnt].next == NULL)
			return false;
		crnt = n[crnt].next;
		return true;
	}
	
	public void printCurrentNode() {
		if(crnt == NULL)
			System.out.println("선택 노드가 없습니다.");
		else
			System.out.println(n[crnt].data);
	}
	
	public void dump() {
		int ptr = head;
		while(ptr != NULL) {
			System.out.println(n[ptr].data);
			ptr = n[ptr].next;
		}
	}
}
