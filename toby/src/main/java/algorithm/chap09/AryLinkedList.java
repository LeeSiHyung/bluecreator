package algorithm.chap09;

public class AryLinkedList<E> {
	
	class Node<E>{
		
		private E data;
		private int next;
		private int dnext;
		
		void set(E data, int next) {
			this.data = data;
			this.next = next;
		}
		
	}

	private Node<E>[] n;		            // 리스트 본체
	private int size;			            // 리스트의 용량
	private int max;			            // 사용 중인 꼬리 record
	private int head;			            // 머리노드
	private int crnt;			            // 선택노드
	private int deleted;		
	private static final int NULL = -1;		// 
	
	

}
