package graph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BFS {
    public List<Node> bfsUsingQueue(Node start) {
        List<Node> result = new ArrayList<>();
        Queue<Node> queue = new ConcurrentLinkedQueue<Node>();
        result.add(start);
        queue.add(start);

        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if(currentNode.getNeighbors() != null) {
                for (Node neighbor : currentNode.getNeighbors()) {
                    if(!neighbor.isVisited()) {
                        neighbor.setVisited(true);
                        queue.add(neighbor);
                        result.add(neighbor);
                    }
                }
            }
        }
        return result;
    }

    public void setVisitedFalseAll(Node start) {
        start.setVisited(false);
        for (Node node : start.getNeighbors()) {
            if(node.isVisited())
                setVisitedFalseAll(node);
        }
    }

    public List<Node> bfsFindPath(Node start, Node goal) {
        if(start.equals(goal))
            return new ArrayList<Node>(Arrays.asList(start));
        List<Node> result = new ArrayList<>();
        Queue<Node> queue = new ConcurrentLinkedQueue<Node>();
        result.add(start);
        queue.add(start);
        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if(currentNode.getNeighbors() != null) {
                for (Node neighbor : currentNode.getNeighbors()) {
                    if(!neighbor.isVisited()) {
                        neighbor.setVisited(true);
                        if(neighbor.equals(goal)) {
                            return neighbor.getPath(start);
                        }
                        queue.add(neighbor);
                        result.add(neighbor);
                    }
                }
            }
        }
        return new ArrayList<>();
    }
    public static void main(String[] args) {
    	Node startNode = new Node("1", null);
        Node node2 = new Node("2", startNode);
        Node node3 = new Node("3", startNode);
        Node node4 = new Node("4", node3);
        Node node5 = new Node("5", node2);
        Node node6 = new Node("6", node2);
        Node node7 = new Node("7", node3);
        Node node8 = new Node("8", node7);

        startNode.setNeighbors(new ArrayList<Node>(Arrays.asList(node2, node3)));
        node2.setNeighbors(new ArrayList<Node>(Arrays.asList(node5, node6)));
        node3.setNeighbors(new ArrayList<Node>(Arrays.asList(node4, node7)));
        node4.setNeighbors(new ArrayList<Node>(Arrays.asList(node3)));
        node5.setNeighbors(new ArrayList<Node>(Arrays.asList(node2)));
        node6.setNeighbors(new ArrayList<Node>(Arrays.asList(node2)));
        node7.setNeighbors(new ArrayList<Node>(Arrays.asList(node3, node8)));
        node8.setNeighbors(new ArrayList<Node>(Arrays.asList(node7)));

        BFS bfs = new BFS();
        NodeListPrinter.print(bfs.bfsFindPath(startNode, node8));
	}
}