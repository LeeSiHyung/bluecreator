package algorithm.chap11;

public class ChainHash <K,V>{
	
	class Node<K,V>{
		
		private K key;
		private V data;
		private Node<K,V> next;
		
		Node(K key, V data, Node<K,V> next){
			this.key = key;
			this.data = data;
			this.next = next;
		}
		
		K getKey() {
			return key;
		}
		
		V getValue() {
			return data;
		}
		
		public int hashCode() {
			return key.hashCode();
		}
		
	}
	
	private int size;
	private Node<K,V>[] table;
	
	public ChainHash(int capacity) {
		try {
			table = new Node[capacity];
			this.size = capacity;
		}
		catch(OutOfMemoryError e) {
			this.size = 0;
		}
	}
	
	public int hashValue(Object key) {
		return key.hashCode() % size;
	}
	
	
	public V search(K key) {
		int hash = hashValue(key);
		Node<K,V> p = table[hash];
		
		while(p != null) {
			if(p.getKey().equals(key))
				return p.getValue();
			p = p.next;
		}
		return null;
	}
	
	public int add(K key, V data) {
		int hash = hashValue(key);
		Node<K,V> p = table[hash];
		
		while(p != null) {
			if(p.getKey().equals(key))
				return 1;
			p = p.next;
		}
		
		// 여기서 참조되는 table[hash]는 아래 temp로 변경하기 이전의 값
		Node<K,V> temp = new Node<K,V>(key, data, table[hash]);
		table[hash] = temp;
		return 0;
	}
	
	public int remove(K key) {
		int hash = hashValue(key);
		Node<K,V> p = table[hash];
		Node<K,V> pp = null;
		
		while(p != null) {
			if(p.getKey().equals(key)) {
				if(pp == null)
					// 삭제하는 값이 가장 최근에 넣었던 값이기 때문에 다음값을 현재 값으로 변경한다.
					table[hash] = p.next;
				else	
					// 삭제하는 값 앞에 이전의 삽입한 값이 있으므로 이전 다음 값을 현재 다음 값으로 변경한다.
					pp.next = p.next;
				
				return 0;
			}
			
			// P.next를 p로 업데이트 하기 전의 값을 pp에 담는다.
			pp = p;
			p = p.next;
		}
		return 1;
	}
	
	public void dump() {
		for(int i=0; i < size; i++) {
			Node<K,V> p = table[i];
			System.out.printf("%02d ", i);
			while(p != null) {
				System.out.printf("-> %s (%s) ", p.getKey(), p.getValue());
				p = p.next;
			}
			System.out.println();
		}
	}
	
}
