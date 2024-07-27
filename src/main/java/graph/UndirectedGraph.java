package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an undirected graph.
 *
 * @param <T> the type of the node data
 */
public class UndirectedGraph<T> implements Graph<T> {
    private final List<Node<T>> nodes;

    public UndirectedGraph() {
        this.nodes = new ArrayList<>();
    }

    @Override
    public List<Node<T>> getNodes() {
        return new ArrayList<>(nodes); // Return a copy to prevent external modification
    }

    @Override
    public Node<T> addNode(T data) {
        Node<T> node = new Node<>(data);
        nodes.add(node);
        return node;
    }

    @Override
    public Node<T> addNode(T data, String name) {
        if (data == null) {
            throw new IllegalArgumentException("Node data cannot be null");
        }

        Node<T> node = new Node<>(data, name);
        nodes.add(node);
        return node;
    }

    @Override
    public void connect(Node<T> node1, Node<T> node2, double weight) {
        validateNodesInGraph(node1, node2);
        node1.connect(node2, weight);
        node2.connect(node1, weight);
    }

    @Override
    public void disconnect(Node<T> node1, Node<T> node2) {
        validateNodesInGraph(node1, node2);
        node1.disconnect(node2);
        node2.disconnect(node1);
    }

    @Override
    public void updateNodeData(Node<T> node, T newData) {
        if (node == null || newData == null) {
            throw new IllegalArgumentException("Node and new data cannot be null");
        }

        if (!nodes.contains(node)) {
            throw new IllegalArgumentException("Node must be part of the graph");
        }

        node.setData(newData);
    }

    @Override
    public void updateEdgeWeight(Node<T> node1, Node<T> node2, double newWeight) {
        validateNodesInGraph(node1, node2);

        boolean edgeFound = false;
        for (Edge<T> edge : node1.getEdges()) {
            if (edge.getToNode().equals(node2)) {
                edge.setWeight(newWeight);
                edgeFound = true;
                break;
            }
        }

        if (!edgeFound) {
            throw new IllegalArgumentException("Edge does not exist");
        }

        for (Edge<T> edge : node2.getEdges()) {
            if (edge.getToNode().equals(node1)) {
                edge.setWeight(newWeight);
                break;
            }
        }
    }

    /**
     * Validates that both nodes are part of the graph.
     *
     * @param node1 the first node
     * @param node2 the second node
     * @throws IllegalArgumentException if either node is not part of the graph
     */
    private void validateNodesInGraph(Node<T> node1, Node<T> node2) {
        if (node1 == null || node2 == null) {
            throw new IllegalArgumentException("Nodes cannot be null");
        }

        if (!nodes.contains(node1) || !nodes.contains(node2)) {
            throw new IllegalArgumentException("Both nodes must be part of the graph");
        }
    }
}
