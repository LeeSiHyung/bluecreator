package algorithm.chap09;

import java.util.Comparator;

public class DblLinkedList<E> {
	
	class Node<E>{
		
		private E data;
		private Node<E> prev;
		private Node<E> next;
		
		Node(){
			data = null;
			prev = next = this;
		}
		
		Node(E obj, Node<E> prev, Node<E> next){
			data = obj;
			this.prev = prev;
			this.next = next;
		}
	}
	
	private Node<E> head;
	private Node<E> crnt;
	
	public DblLinkedList() {
		head = crnt = new Node<E>(); // 더미 노드를 생성
	}
	
	public boolean isEmpty() {
		// 더미노드의 next 포인터가 더미노드 head를 가리킨다면 비어있다는 것을 의미
		return head.next == head; 
	}
	
	
	public E search(E obj, Comparator<? super E> c) {
		// head는 더미노드이기 때문에 head.next 노드부터 검색한다.
		Node<E> ptr = head.next;
		
		while(ptr != head) {
			if(c.compare(obj, ptr.data) == 0) {
				crnt = ptr;
				return ptr.data;
			}
			ptr = ptr.next;
		}
		return null;
	}
	
	public void printCurrentNode() {
		if(isEmpty()) 
			System.out.println("선택 노드가 없습니다.");
		else
			System.out.println(crnt.data);
	}
	
	public void dump() {
		Node<E> ptr = head.next;
		
		while(ptr != head) {
			System.out.println(ptr.data);
			ptr = ptr.next;
		}
	}
	
	public void dumpReverse() {
		Node<E> ptr = head.prev;
		
		while(ptr != head) {
			System.out.println(ptr.data);
			ptr = ptr.prev;
		}
	}
	
	public boolean next() {
		if(isEmpty() || crnt.next == head)
			return false;
		crnt = crnt.next;
		return true;
	}
	
	public boolean prev() {
		if(isEmpty() || crnt.prev == head)
			return false;
		crnt = crnt.prev;
		return true;
	}
	
	public void add(E obj) {
		Node<E> node = new Node<E>(obj, crnt, crnt.next);
		crnt.next = crnt.next.prev = node;
		crnt = node;
	}
	
	public void addFirst(E obj) {
		crnt = head;
		add(obj);
	}
	
	public void addLast(E obj) {
		crnt = head.prev;
		add(obj);
	}
	
	public void removeCurrentNode() {
		
	}
	

}
