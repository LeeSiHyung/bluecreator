package algorithm.study.review;

import java.util.LinkedList;

public class DFSRecursion {
	
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
		g.dfsR();
	}
	
	static class Graph{
		class Node{
			int data;
			LinkedList<Node> adjacent;
			boolean marked;
			
			Node(int data){
				this.data = data;
				this.marked = false;
				this.adjacent = new LinkedList<Node>();
			}
		}
		
		Node[] nodes;
		Graph(int size){
			nodes = new Node[size];
			for(int i=0; i < size; i++) {
				nodes[i] = new Node(i);
			}
		}
		
		public void addEdge(int i1, int i2) {
			Node n1 = nodes[i1];
			Node n2 = nodes[i2];
			
			if(!n1.adjacent.contains(n2)) {
				n1.adjacent.add(n2);
			}
			if(!n2.adjacent.contains(n1)) {
				n2.adjacent.add(n1);
			}
		}
		
		public void dfsR(Node r) {
			if(r == null) return;
			r.marked = true;
			System.out.print(r.data + " ");
			for(Node n : r.adjacent) {
				if(n.marked == false) {
					dfsR(n);
				}
			}
		}
		
		public void dfsR(int idx) {
			Node root = nodes[idx];
			dfsR(root);
		}
		
		public void dfsR() {
			dfsR(0);
		}
		
	}
	
}
