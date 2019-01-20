package algorithm.test;

public class Test2 {

	public static void main(String[] args) {
		System.out.println(solution(3,	20,	4));
	}
	
	public static long solution(int price, int money, int count) {
        
		long result = 0;
		long tmp = 0;
		
        for(int i=0; i < count; i++){
        	tmp += price; result += tmp;
        }
        
        result = result - money;
        
        return result;
    }
	
}
