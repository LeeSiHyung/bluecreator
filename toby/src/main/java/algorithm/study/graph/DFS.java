package algorithm.study.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class DFS {
	
	public static void main(String[] args) {
		Graph g = new Graph(9);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 4);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(3, 5);
		g.addEdge(5, 6);
		g.addEdge(5, 7);
		g.addEdge(6, 8);
		g.dfs();
	}
	
	static class Graph{
		
		class Node{
			int data;
			// 인접한 노드를 가리킴
			LinkedList<Node> adjacent;
			boolean marked;
			
			Node(int data){
				this.data = data;
				this.marked = false;
				adjacent = new LinkedList<Node>();
			}
		}
		
		Node[] nodes;
		Graph(int size){
			nodes = new Node[size];
			for(int i=0; i < size; i++) {
				// 인덱스와 값을 일치
				nodes[i] = new Node(i);
			}
		}
		
		// 두 노드의 관계를 저장
		public void addEdge(int i1, int i2) {
			Node n1 = nodes[i1];
			Node n2 = nodes[i2];
			// 한번 노드가 갔던 곳으로 다시 가지 않기 때문에  체크함
			if(!n1.adjacent.contains(n2)){
				n1.adjacent.add(n2);
			}
			if(!n2.adjacent.contains(n1)){
				n2.adjacent.add(n1);
			}
		}
		
		public void dfs() {
			dfs(0);
		}
		
		// 스택이기 때문에 호출한 곳으로 다시 돌아가서 자식 노드들을 찾음
		public void dfs(int idx) {
			Node root = nodes[idx];
			Deque<Node> stack = new ArrayDeque<Node>();
			stack.push(root);
			root.marked = true;
			while(!stack.isEmpty()) {
				// 스택에서 노드를 가져옴
				Node r = stack.pop();
				// 자식 노드를 가져옴
				for(Node n : r.adjacent) {
					if(n.marked == false) {
						n.marked = true;
						stack.push(n);
					}
				}
				// 각각 스택의 값을 찍음
				System.out.print(r.data + " ");
			}
		}
		
	}

}
