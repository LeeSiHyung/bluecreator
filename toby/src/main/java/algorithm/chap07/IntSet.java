package algorithm.chap07;

public class IntSet {

	private int max;
	private int num;
	private int[] set;
	
	
	public IntSet(int capacity) {
		num = 0;
		max = capacity;
		try {
			set = new int[max];
		}
		catch(OutOfMemoryError e) {
			max = 0;
		}
	}
	
	public int capacity() {
		return max;
	}
	
	public int size() {
		return num;
	}
	
	public int indexOf(int n) {
		for(int i=0; i < num; i++)
			if(set[i] == n)
				return i;
		return -1;
	}
	
	public boolean contains(int n) {
		return (indexOf(n) != -1) ? true : false;
	}
	
	public boolean add(int n) {
		if(num >= max || contains(n) == true)
			return false;
		else {
			set[num++] = n;
			return true;
		}
	}
	
	public boolean remove(int n) {
		int idx;
		if(num <= 0 || (idx = indexOf(n)) == -1)
			return false;
		else {
			// -- num을 해주고 마지막 num 데이터를 idx 자리고 옮긴다.
			set[idx] = set[--num];
			return true;
		}
	}
	
	public void copyTo(IntSet s) {
		int n = (s.max < num) ? s.max : num;
		for(int i=0; i < n; i++)
			s.set[i] = set[i];
		s.num = num;
	}
	
	public void copyFrom(IntSet s) {
		int n = (max < s.num) ? max : s.num;
		for(int i=0; i < n; i++)
			set[i] = s.set[i];
		num = s.num;
	}
	
	public boolean equalTo(IntSet s) {
		if(num != s.num)
			return false;
		
		for(int i=0; i < num; i++) {
			int j=0;
			for(;j < s.num; j++)
				if(set[i] == s.set[j])
					break;
			
			// i와 j를 계속 대조해서 j가 끝까지 돌 때 까지 같은 값이 존재하지 않으면 false
			// 위에서 같은 값이 나오면 break를 하기 때문에 j == s.num이 되지 않고, 다음 i로 넘어간다.
			if(j == s.num)
				return false;
		}
		return true;
	}
	
	public void unionOf(IntSet s1, IntSet s2) {
		copyFrom(s1);
		for(int i=0; i < s2.num; i++)
			add(s2.set[i]);
	}
	
	public String toString() {
		StringBuffer temp = new StringBuffer("{");
		for(int i=0; i < num; i++)
			temp.append(set[i] + " ");
		temp.append("}");
		return temp.toString();
	}
	
}
