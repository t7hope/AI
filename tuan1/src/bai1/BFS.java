package bai1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BFS {
	public List<Node> bfs(Node start) {
		List<Node> result = new ArrayList<>();
		Queue<Node> queue = new ConcurrentLinkedQueue<Node>();
		result.add(start);
		queue.add(start);

		while (!queue.isEmpty()) {
			Node currentNode = queue.poll();
			if (currentNode.getNeighbours() != null) {
				for (Node neighbor : currentNode.getNeighbours()) {
					if (!neighbor.isVisited()) {
						neighbor.setVisited(true);
						queue.add(neighbor);
						result.add(neighbor);
					}
				}
			}
		}
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i).getState() + " ");
		}
		return result;
	}

	

	public void bfsUsingQueue(Node initial, int goal) {
		if (initial.getState() == goal) {
			System.out.println(initial.getState());
		}
		Queue<Node> queue = new ConcurrentLinkedDeque<Node>();
		queue.add(initial);
		initial.setVisited(true);

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node.getNeighbours() != null) {
				for (Node neighbour : node.getNeighbours()) {
					if (!neighbour.isVisited()) {
						neighbour.setVisited(true);

						if (neighbour.getState() == goal) {
							String s = "";
							while (neighbour != initial && neighbour != null) {
								s = neighbour.getState() + " " + s;
								neighbour = neighbour.getParent();
							}
							System.out.println(initial.getState() + " " + s);
							return;
						}

						queue.add(neighbour);

					} else {
						System.out.println("No find goal");
					}
				}
			}
		}
	}

	public static void main(String[] args) {

		Node node40 = new Node(40);
		Node node10 = new Node(10);
		Node node20 = new Node(20);
		Node node30 = new Node(30);
		Node node60 = new Node(60);
		Node node50 = new Node(50);
		Node node70 = new Node(70);

		node40.addNeighbours(node10);
		node40.addNeighbours(node20);
		node10.addNeighbours(node30);
		node20.addNeighbours(node10);
		node20.addNeighbours(node30);
		node20.addNeighbours(node60);
		node20.addNeighbours(node50);
		node30.addNeighbours(node60);
		node60.addNeighbours(node70);
		node50.addNeighbours(node70);

		BFS bfsExample = new BFS();
		System.out.println("The BFS traversal of the graph using queue ");
		// bfsUsingQueue(node40, 90);
		;

	}

}
