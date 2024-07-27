package graph;

import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * Provides common graph algorithms such as BFS, DFS, and Dijkstra's algorithm.
 *
 * @param <T> the type of the node data
 */
public class GraphAlgorithms<T> {

    /**
     * Performs breadth-first search (BFS) starting from the given node.
     *
     * @param startNode the starting node
     * @return a list of nodes visited in BFS order
     */
    public List<Node<T>> bfs(Node<T> startNode) {
        List<Node<T>> result = new ArrayList<>();

        Deque<Node<T>> queue = new ArrayDeque<>();
        Set<Node<T>> visited = new HashSet<>();

        queue.offerLast(startNode);
        visited.add(startNode);

        while(!queue.isEmpty()) {
            Node<T> currentNode = queue.pollFirst();
            result.add(currentNode);

            for(Edge<T> edge: currentNode.getEdges()) {
                Node<T> neighbour = edge.getToNode();

                if(!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.offerLast(neighbour);
                }
            }
        }

        return result;
    }

    /**
     * Performs depth-first search (DFS) starting from the given node.
     *
     * @param startNode the starting node
     * @return a list of nodes visited in DFS order
     */
    public List<Node<T>> dfs(Node<T> startNode) {
        List<Node<T>> result = new ArrayList<>();

        Deque<Node<T>> stack = new ArrayDeque<>();
        Set<Node<T>> visited = new HashSet<>();

        stack.push(startNode);
        visited.add(startNode);

        while(!stack.isEmpty()) {
            Node<T> curr = stack.pop();
            result.add(curr);

            for(Edge<T> edge: curr.getEdges()) {
                Node<T> neighbour = edge.getToNode();

                if(!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    stack.push(neighbour);
                }
            }
        }

        return result;
    }

    /**
     * Performs Dijkstra's algorithm to find the shortest paths from the source node.
     *
     * @param graph the graph
     * @param sourceNode the source node
     * @return a map of nodes to their shortest distance from the source node
     * @throws NegativeWeightException - if any of the weights are negative
     */
    public Map<Node<T>, Double> dijkstra(Graph<T> graph, Node<T> sourceNode) {
        if (!graph.getNodes().contains(sourceNode)) {
            throw new IllegalArgumentException("Source node must be part of the graph");
        }

        Map<Node<T>, Double> distances = new HashMap<>();
        for(Node<T> node: graph.getNodes()) {
            distances.put(node, Double.POSITIVE_INFINITY);
        }

        distances.put(sourceNode, 0.0);

        PriorityQueue<Node<T>> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        Set<Node<T>> visited = new HashSet<>();

        priorityQueue.add(sourceNode);

        while(!priorityQueue.isEmpty()) {
            Node<T> currentNode = priorityQueue.poll();

            if(visited.contains(currentNode)) {
                continue;
            }

            visited.add(currentNode);

            for(Edge<T> edge: currentNode.getEdges()) {
                double edgeWeight = edge.getWeight();
                if(edgeWeight < 0) {
                    throw new NegativeWeightException("Graph contains a negative weight edge: " + edgeWeight + "\nSee: https://stackoverflow.com/questions/13159337/why-doesnt-dijkstras-algorithm-work-for-negative-weight-edges");
                }

                Node<T> neighbour = edge.getToNode();
                double newDist = distances.get(currentNode) + edgeWeight;

                if(newDist < distances.get(neighbour)) {
                    distances.put(neighbour, newDist);
                    priorityQueue.add(neighbour);
                }
            }
        }

        return distances;
    }
}
