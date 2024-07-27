package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a node in a graph.
 *
 * @param <T> the type of the node data
 */
public class Node<T> {
    private T data;
    private String name;
    private final List<Node<T>> neighbors;
    private final List<Edge<T>> edges;

    /**
     * Constructs a node with specified data and specified node name.
     *
     * @param data the data of the node
     * @param name the name of the node
     * @throws IllegalArgumentException if data or name is null
     */
    Node(T data, String name) {
        if (data == null || name == null) {
            throw new IllegalArgumentException("Data and name cannot be null");
        }

        this.data = data;
        this.name = name;
        this.neighbors = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    /**
     * Constructs a node with specified data.
     *
     * @param data the data of the node
     */
    Node(T data) {
        this(data, "n" + data);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        this.name = name;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        this.data = data;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public List<Node<T>> getNeighbors() {
        return neighbors;
    }

    /**
     * Connects this node to another node with a specified weight.
     *
     * @param toNode the node to connect to
     * @param weight the weight of the connection
     * @throws IllegalArgumentException if toNode is null or if nodes are already connected
     */
    void connect(Node<T> toNode, double weight) {
        if (toNode == null) {
            throw new IllegalArgumentException("Target node cannot be null");
        }

        if (neighbors.contains(toNode)) {
            throw new IllegalArgumentException("Nodes are already connected");
        }

        edges.add(new Edge<>(this, toNode, weight));
        neighbors.add(toNode);
    }

    /**
     * Disconnects this node from another node.
     *
     * @param toNode the node to disconnect from
     * @throws IllegalArgumentException if toNode is null ot if nodes are not already connected
     */
    void disconnect(Node<T> toNode) {
        if (toNode == null) {
            throw new IllegalArgumentException("Target node cannot be null");
        }

        if (!neighbors.contains(toNode)) {
            throw new IllegalArgumentException("Nodes are not connected");
        }

        edges.removeIf(edge -> edge.getToNode().equals(toNode));
        neighbors.remove(toNode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(data, node.data) && Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, name);
    }
}
