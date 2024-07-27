package graph;

import java.util.List;

/**
 * Represents a generic graph.
 *
 * @param <T> the type of the node data
 */
public interface Graph<T> {

    List<Node<T>> getNodes();

    /**
     * Adds a new node with the specified data to the graph.
     *
     * @param data the data of the new node
     * @return the newly added node
     */
    Node<T> addNode(T data);

    /**
     * Adds a new node with the specified data and name to the graph.
     *
     * @param data the data of the new node
     * @param name the name of the new node
     * @return the newly added node
     */
    Node<T> addNode(T data, String name);

    /**
     * Connects two nodes in the graph with a specified weight.
     *
     * @param fromNode the starting node
     * @param toNode the ending node
     * @param weight the weight of the connection
     * @throws IllegalArgumentException if either node is not part of the graph
     */
    void connect(Node<T> fromNode, Node<T> toNode, double weight);

    /**
     * Connects two nodes in the graph with a default weight of 1.0.
     *
     * @param fromNode the starting node
     * @param toNode the ending node
     */
    default void connect(Node<T> fromNode, Node<T> toNode) {
        this.connect(fromNode, toNode, 1.0);
    }

    /**
     * Connects a node to multiple nodes with specified weights.
     *
     * @param fromNode the starting node
     * @param toNodes the list of ending nodes
     * @param weights the list of weights
     * @throws IllegalArgumentException if fromNode is null, or if toNodes or weights are null or different sizes
     */
    default void connect(Node<T> fromNode, List<Node<T>> toNodes, List<Double> weights) {
        if (fromNode == null || toNodes == null || weights == null || toNodes.size() != weights.size()) {
            throw new IllegalArgumentException("Invalid nodes or weights");
        }
        for (int i = 0; i < toNodes.size(); i++) {
            this.connect(fromNode, toNodes.get(i), weights.get(i));
        }
    }

    /**
     * Connects a node to multiple nodes with a default weight of 1.0.
     *
     * @param fromNode the starting node
     * @param toNodes the list of ending nodes
     * @throws IllegalArgumentException if fromNode or toNodes is null
     */
    default void connect(Node<T> fromNode, List<Node<T>> toNodes) {
        if (fromNode == null || toNodes == null) {
            throw new IllegalArgumentException("Invalid nodes");
        }
        for (Node<T> toNode : toNodes) {
            this.connect(fromNode, toNode, 1.0);
        }
    }

    /**
     * Disconnects two nodes in the graph.
     *
     * @param fromNode the starting node
     * @param toNode the ending node
     * @throws IllegalArgumentException if fromNode or toNode is null
     */
    void disconnect(Node<T> fromNode, Node<T> toNode);

    /**
     * Disconnects a node from multiple nodes.
     *
     * @param fromNode the starting node
     * @param toNodes the list of ending nodes
     * @throws IllegalArgumentException if fromNode or toNodes is null
     */
    default void disconnect(Node<T> fromNode, List<Node<T>> toNodes) {
        if (fromNode == null || toNodes == null) {
            throw new IllegalArgumentException("Invalid nodes");
        }
        for (Node<T> toNode : toNodes) {
            this.disconnect(fromNode, toNode);
        }
    }

    /**
     * Updates data of a node
     *
     * @param node the node whose data needs to be updated
     * @param newData the updated new data
     * @throws IllegalArgumentException if node or newData is null or node is not part of this graph
     */
    void updateNodeData(Node<T> node, T newData);

    /**
     * Updates weight of an edge
     *
     * @param fromNode the starting node of the edge
     * @param toNode the ending node of the edge
     * @param newWeight the updated new weight
     * @throws IllegalArgumentException if either start and end nodes are not part of the graph or the edge doesn't exist between the two nodes
     */
    void updateEdgeWeight(Node<T> fromNode, Node<T> toNode, double newWeight);
}
