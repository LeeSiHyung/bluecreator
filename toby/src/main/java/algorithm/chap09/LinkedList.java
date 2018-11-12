package algorithm.chap09;

import java.util.Comparator;

public class LinkedList<E> {
	
	class Node<E>{
		
		private E data;
		private Node<E> next;
		
		public Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
	}
	
	private Node<E> head;
	private Node<E> crnt;
	
	
	public LinkedList() {
		head = crnt = null;
	}
	
	public E search(E obj, Comparator<? super E> c) {
		Node<E> ptr = head; // 현재 스캔중인 노드
		while(ptr != null) {
			if(c.compare(obj, ptr.data) == 0) { // 검색 성공
				crnt = ptr;
				return ptr.data;
			}
			ptr = ptr.next; // 다음 노드를 계속 선택
		}
		return null;
	}
	
	public void addFirst(E obj) {
		Node<E> ptr = head;
		// 머리노드를 새로 만들고 기존 머리 노드를 next 노드로 변경한다.
		head = crnt = new Node<E>(obj, ptr);
	}
	
	public void addLast(E obj) {
		if(head == null)
			addFirst(obj);
		else {
			Node<E> ptr = head;
			while(ptr.next != null)
				ptr = ptr.next;
			ptr.next = new Node<E>(obj, null);
		}
	}
	
	public void removeFirst() {
		// 만약 head가 비어있지 않으면 head.next 즉 다음 노드를 head로 변경
		if(head != null)
			head = crnt = head.next;
	}
	
	public void removeLast() {
		if(head != null) {
			if(head.next == null)
				removeFirst();
			else {
				Node<E> ptr = head;
				Node<E> pre = head;
				
				while(ptr.next != null) {
					pre = ptr;		// 현재 노드를 저장함. 즉 꼬리노드의 이전 노드임
					ptr = ptr.next; // 계속 다음 노드를 검색
				}
				
				// 위의 while문이 끝나면 ptr은 꼬리노드 pre는 꼬리노드 이전 노드가 선택된다.
				// pre.next 즉 꼬리 전 노드에서 다음 노드를 null로 만든다.
				pre.next = null;
				crnt = pre; // pre가 꼬리노드가 되었기 때문에 crnt를 pre로 변경한다.
			}
		}
	}
	
	public void remove(Node p) {
		if(head != null) {
			if(p == head) 
				removeFirst();
			else {
				Node<E> ptr = head;
				// while을 거치면 p 노드 앞쪽 노드 ptr을 얻을 수 있다.
				while(ptr.next != p) {
					ptr = ptr.next;
					if(ptr == null) return;
				}
				ptr.next = p.next;
				crnt = ptr;
			}
		}
	}
	
	public void removeCurrentNode() {
		remove(crnt);
	}
	
	public void clear() {
		while(head != null)
			removeFirst(); // 마지막 head.next가 null이되면 삭제된다.
		crnt = null;
	}
	
	public boolean next() {
		if(crnt == null || crnt.next == null)
			return false; // 꼬리노드이거나 그 다음이 꼬리노드인 경우 다음 노드로 이동할 수 없음.
		
		crnt = crnt.next; // 선택 다음 노드를 선택 노드로 변경
		return true;
	}
	
	public void printCurrentNode() {
		if(crnt == null) 
			System.out.println("선택한 노드가 없습니다.");
		else
			System.err.println(crnt.data);
	}
	
	public void dump() {
		Node<E> ptr = head;
		
		while(ptr != null) {
			System.out.println(ptr.data);
			ptr = ptr.next;
		}
	} 
}
