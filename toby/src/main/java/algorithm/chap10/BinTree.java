package algorithm.chap10;

import java.util.Comparator;

public class BinTree<K, V> {
	
	static class Node<K, V>{
		
		private K key;
		private V data;
		private Node<K,V> left;
		private Node<K,V> right;
		
		Node(K key, V data, Node<K,V> left, Node<K,V> right){
			this.key = key;
			this.data = data;
			this.left = left;
			this.right = right;
		}

		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return data;
		}
		
		void print() {
			System.out.println(data);
		}
	}
	
	private Node<K,V> root;
	private Comparator<? super K> comparator = null;
	
	
	public BinTree() {
		root = null;
	}
	
	public BinTree(Comparator<? super K> c) {
		this();
		comparator = c;
	}
	
	private int comp(K key1, K key2) {
		// comparator가 null이면 Comparable 인터페이스 형으로 형변환을 해준다.
		return (comparator == null) ? ((Comparable<K>) key1).compareTo(key2) : comparator.compare(key1, key2);
	}
	
	public V search(K key) {
		Node<K,V> p = root;
		while(true) {
			if(p == null)
				return null;
			int cond = comp(key, p.getKey());
			if(cond == 0)
				return p.getValue();
			else if(cond < 0)
				p = p.left;
			else
				p = p.right;
		}
	}
	
	private void addNode(Node<K,V> node, K key, V data) {
		int cond = comp(key, node.getKey());
		if(cond == 0)
			return;
		else if(cond < 0) {
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
		
		
		// p 검색
		while(true) {
			if(p == null)
				return false;
			int cond = comp(key, p.getKey());
			if(cond == 0)
				// 살제할 key를 가진 Node p를 찾음
				break;
			else {
				// 키가 루트가 아니면 자식 노드로 내려가야된다.
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
		
		
		// 만약 p의 왼쪽 자식 노드가 null 일경우
		if(p.left == null) {
			if(p == root)
				// 만약 p가 루트면 오른쪽 자식을 root에 넣는다. 만약 p.right 가 null이면  null이 들어간다.
				root = p.right;
			else if(isLeftChild)
				// 부모 왼쪽노드에 자식에 현재 p노드의  오른쪽 자식 노드를 올린다. 만약 p.right 가 null이면  null이 들어간다.
				parent.left = p.right;
			else
				// 부모 오른쪽 노드에 자식에 현재 p노드의  오른쪽 자식 노드를 올린다. 만약 p.right 가 null이면  null이 들어간다.
				parent.right = p.right;
		}
		// 만약 p의 오른쪽 자식 노드가 null 일경우
		else if(p.right == null){
			if(p == root)
				// 만약 p가 루트면 왼쪽 자식을 root에 넣는다. 만약 p.left 가 null이면  null이 들어간다.
				root = p.left;
			else if(isLeftChild)
				// 부모 왼쪽 노드에 자식에 현재 p노드의  왼쪽  자식 노드를 올린다. 만약 p.left 가 null이면  null이 들어간다.
				parent.left = p.left;
			else
				// 부모 오른쪽 노드에 자식에 현재 p노드의  왼쪽  자식 노드를 올린다. 만약 p.left 가 null이면  null이 들어간다.
				parent.right = p.left;
		}
		// 위의 경우는 자식 노드가 없거나 자식 노드가 하나 일 경우에만 수행한다. 아래 로직은 자식 노드가 두개 있을 경우를 의미한다.
		else {
			// 현재 p를  부모노드에 저장한다.
			parent = p;
			
			// 자식 노드가 2개 일경우 왼쪽자식 노드부터 검색하여서 가장 큰  수를 찾아와야 한다. 즉 left는 p의 왼쪽 자식들 중 가장 큰 수 이다.
			Node<K,V> left = p.left;
			isLeftChild = true;
			
			// p의 왼쪽 자식에서 부터 오른쪽 자식 노드를 검색하면 오른쪽 노드의 null 값이 오기 전이 가장 큰 수이다.
			while(left.right != null) {
				parent  = left.right;
				left = left.right;
				isLeftChild = false;
			}
			
			// 위의 로직이 끝나면 left는 왼쪽에서 가장 큰 수이다. 위의 수는 오른쪽 자손이 null이 올때까지 조회했기 때문에 왼쪽 자식 노드 밖에 없게 된다.
			p.key = left.key;
			p.data = left.data;
			
			if(isLeftChild)
				// isLeftChild의 경우는 parent가 p일 경우 밖에 없다. 즉 자식 왼쪽 노드에서 큰 수는 왼쪽 자식 밖에 없다는 뜻.
				parent.left = left.left;
			else
				// p는 left로 값으로 변경되었고, 남은 parent.right는 left읜 왼쪽 자식 노드로 업데이트 한다.
				parent.right = left.left;
		}
		
		return true;
	}
	
	private void printSubTree(Node node) {
		if(node != null) {
			printSubTree(node.left);
			System.out.println(node.key + " " + node.data );
			printSubTree(node.right);
		}
	}
	
	public void print() {
		printSubTree(root);
	}
	
}
