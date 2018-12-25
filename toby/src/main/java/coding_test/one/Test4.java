package coding_test.one;

import java.util.LinkedList;
import java.util.Queue;

public class Test4 {
	public static void main(String[] args) {
		Test4 t = new Test4();
		int[] A = { 60, 80, 40 };
		int[] B = { 2, 3, 5};
		int M = 5;
		int X = 2;
		int Y = 200;
		System.out.println(t.solution(A, B, M, X, Y));
	}

    public int solution(int[] A, int[] B, int M, int X, int Y) {
    	
    	// write your code in Java SE 8
        Queue<People> queue = new LinkedList<People>();
    	for(int i=0; i < A.length; i++) {
    		People p = new People(A[i], B[i]);
    		queue.offer(p);
    	}
    	
    	int counter = 0;
    	int nowPeople = X;
    	int nowWeight = Y;
    	int nowFloor = 1;
    	
    	while(!queue.isEmpty()) {
    		People p = queue.poll();
    		
    		if(nowFloor != p.floor) {
    		    // 같은 층이 아닐 경우 엘레베이터가 움직여야 하기 대문에 counter++
    			nowFloor = p.floor;
    			++counter;
			}
    		
    		nowPeople -= 1;
    		nowWeight -= p.weight;
    		
    		// 인원이나 무게가 오버 되었을 경우
    		if(nowPeople < 0 || nowWeight < 0) {
    		    // 1층으로 돌아간다.
    			++counter;
    			nowPeople = X;
    			nowWeight = Y;
    			
    			// 그리고 다시  해당 층으로 간다.
    			nowFloor = p.floor;
    			nowPeople -= 1;
        		nowWeight -= p.weight;
    			//++counter;
    		}
    	
    			
    	}
    	
    	// 마지막 1층
    	if(nowFloor != 1) ++counter;
    	return counter;
	}
    
    class People{
    	
    	int weight;
    	int floor;
    	
    	public People(int weight, int floor) {
			this.weight = weight;
			this.floor = floor;
		}
    	
    	public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public int getFloor() {
			return floor;
		}

		public void setFloor(int floor) {
			this.floor = floor;
		}

	
    	
    }
}
