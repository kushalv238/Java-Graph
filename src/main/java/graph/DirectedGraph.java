package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a directed graph.
 *
 * @param <T> the type of the node data
 */
public class DirectedGraph<T> implements Graph<T> {
    private final List<Node<T>> nodes;

    public DirectedGraph() {
        this.nodes = new ArrayList<>();
    }

    @Override
    public List<Node<T>> getNodes() {
        return new ArrayList<>(nodes); // Return a copy to prevent external modification
    }

    @Override
    public Node<T> addNode(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Node data cannot be null");
        }

        Node<T> node = new Node<>(data);
        nodes.add(node);
        return node;
    }

    @Override
    public Node<T> addNode(T data, String name) {
        Node<T> node = new Node<>(data, name);
        nodes.add(node);
        return node;
    }

    @Override
    public void connect(Node<T> fromNode, Node<T> toNode, double weight) {
        validateNodesInGraph(fromNode, toNode);
        fromNode.connect(toNode, weight);
    }

    @Override
    public void disconnect(Node<T> fromNode, Node<T> toNode) {
        validateNodesInGraph(fromNode, toNode);
        fromNode.disconnect(toNode);
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
    public void updateEdgeWeight(Node<T> fromNode, Node<T> toNode, double newWeight) {
        validateNodesInGraph(fromNode, toNode);

        for (Edge<T> edge : fromNode.getEdges()) {
            if (edge.getToNode().equals(toNode)) {
                edge.setWeight(newWeight);
                return;
            }
        }

        throw new IllegalArgumentException("Edge does not exist");
    }

    /**
     * Validates that both nodes are part of the graph.
     *
     * @param fromNode the starting node
     * @param toNode the ending node
     * @throws IllegalArgumentException if either node is not part of the graph
     */
    private void validateNodesInGraph(Node<T> fromNode, Node<T> toNode) {
        if (fromNode == null || toNode == null) {
            throw new IllegalArgumentException("Nodes cannot be null");
        }

        if (!nodes.contains(fromNode) || !nodes.contains(toNode)) {
            throw new IllegalArgumentException("Both nodes must be part of the graph");
        }
    }
}
