package algorithm2.chap10;

import java.util.Comparator;

public class BinTree <K,V>{
	

	static class Node<K,V>{
		
		private K key;
		private V data;
		private Node<K,V> left;
		private Node<K,V> right;
		
		
		public Node(K key, V data, Node<K, V> left, Node<K, V> right) {
			this.key = key;
			this.data = data;
			this.left = left;
			this.right = right;
		}
		
		K getKey() {
			return key;
		}
		
		V getVaue() {
			return data;
		}
		
		void print(){
			System.out.println(data);
		}
		
	}
	
	
	private Node<K,V> root;
	private Comparator<? super K> comparator = null;
	
	
	public BinTree() {
		root = null;
	}

	public BinTree(Comparator<? super K> c) {
		this.comparator = c;
	}
	
	private int comp(K key1, K key2) {
		return (comparator == null) ? ((Comparable<K>) key1).compareTo(key2) : comparator.compare(key1, key2);
	}
	
	public V search(K key) {
		Node<K,V> p = root;
		
		while(true) {
			if(p == null)
				return null;
			int cond = comp(key, p.getKey());
			if(cond == 0)
				return p.getVaue();
			else if(cond < 0)
				p = p.left;
			else
				p = p.right;
		}
	}
	
	// node를 루트로 하는 서브 트리에 노드<K,V>를 삽입
	private void addNode(Node<K,V> node, K key, V data) {
		int cond = comp(key, node.getKey());
		if(cond == 0)
			return;
		else if(cond < 0 ) {
			if(node.left == null)
				node.left = new Node<K,V>(key, data, null, null);
			else
				addNode(node.left, key, data);
		}
		else {
			if(node.right == null)
				node.right = new Node<K,V>(key, data, null, null);
			else
				addNode(node.right, key, data);
		}
	}
	
	public void add(K key, V data) {
		if(root == null)
			root = new Node<K,V>(key, data, null, null);
		else
			addNode(root, key, data);
	}
	
	public boolean remove(K key) {
		Node<K,V> p = root;
		Node<K,V> parent = null;
		boolean isLeftChild = true;
		
		// 리무브할 데이터를 찾기
		while(true) {
			if(p == null)
				return false;
			int cond = comp(key, p.getKey());
			if(cond == 0)
				// 위치 검색 완료
				break;
			else {
				parent = p;
				if(cond < 0) {
					isLeftChild = true;
					p = p.left;
				}
				else {
					isLeftChild = false;
					p = p.right;
				}
			}
		}
		
		// 처리
		if(p.left == null) {
			if(p == root)
				// 이미 left 자식 노드가 없기 때문에 p.right 노드를  root에 넣는다 이 조차 업으면 당연히 null이 들어간다.
				root = p.right;
			else if (isLeftChild)
				parent.left = p.right;
			else
				parent.right = p.right;
		}
		else if(p.right == null) {
			if(p == root)
				root = p.left;
			else if(isLeftChild)
				parent.left = p.left;
			else
				parent.right = p.left;
		}
		else {
			// left, right 모두 자식 노드가 존재함.
			parent = p;
			// 자식 노드 중에 왼쪽 자식의 가장 큰 값을 p로 바꿔야 됨.
			Node<K,V> left = p.left;
			isLeftChild = true;
			// 일단 left 하위 노드에서 right 값이 크기 때문에 right 값이 null이 될 때 까지 찾는다.
			while(left.right == null) {
				parent = left;
				left = left.right;
				isLeftChild = false;
			}
			
			// 삭제할 값의 노드 중의 가장 큰 값이 left, 부모는 parent
			p.key = left.key;
			p.data = left.data;
			if(isLeftChild)
				parent.left = left.left;
			else
				parent.right = left.left;
			
		}
		
		return true;
		
	}
	
	private void printSubTree(Node node) {
		if(node != null) {
			printSubTree(node.left);
			System.out.println(node.key + " " + node.data);
			printSubTree(node.right);
		}
	}
	
	public void print() {
		printSubTree(root);
	}
}
