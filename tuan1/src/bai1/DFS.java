package bai1;

import java.util.Stack;

public class DFS {
	public void dfsUsingStack(Node start, int goal) {
		if (start.getState() == goal) {
			System.out.println(start.getState());
		}
		Stack<Node> stack = new Stack<>();
		start.setVisited(true);
		stack.push(start);
		while (!stack.isEmpty()) {
			Node node = stack.peek();
			if (node.getNeighbours() != null) {
				for (Node neighbour : node.getNeighbours()) {
					if (!neighbour.isVisited()) {
						neighbour.setVisited(true);

						if (neighbour.getState() == goal) {
							String s = "";
							while (neighbour != start && neighbour != null) {
								s = neighbour.getState() + " " + s;
								neighbour = neighbour.getParent();
							}
							System.out.println(start.getState() + " " + s);
							return;
						}

						stack.add(neighbour);

					} 
				}
			}
		}

	}
}
