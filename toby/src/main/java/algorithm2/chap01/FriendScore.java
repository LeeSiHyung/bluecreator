package algorithm2.chap01;

public class FriendScore {
	
	public static void main(String[] args) {
		
		String[] friends = new String[] {
			"NYNNN",
			"YNYNN",
			"NYNYN",
			"NNYNY",
			"NNNYN"
		};
		
		System.out.println(highestScore(friends));
		
	}
	
	public static int highestScore(String[] friends) {
		int ans = 0;
		for(int i=0; i < friends.length; i++) {
			int score = 0;
			for(int j=0; j < friends.length; j++) {
				if(i == j) continue;
				// 둘 모두 친구라면
				if(friends[i].charAt(j) == 'Y') {
					score++;
					// 카운트를 세는 일이기 때문에 break를 할필요는 없음
					// break;
				}
				else {
					for(int k=0; k < friends.length; k++) {
						
						// 친구 j가 K와 친구 이거나 친구 k와 i가 친구인지를 체크
						if((friends[j].charAt(k) == 'Y')
								&& (friends[k].charAt(i) == 'Y')
								) {
							score++;
							// 카운트를 세는 일이기 때문에 break를 할필요는 없음
							break;
						}
					}
				}
			}
			ans = Math.max(ans, score);
			
		}
		
		return ans;
	}
	

}
