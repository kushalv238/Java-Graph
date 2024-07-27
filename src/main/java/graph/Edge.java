package graph;

/**
 * Represents an edge in a graph connecting two nodes with a weight.
 *
 * @param <T> the type of the node data
 */
public class Edge<T> {
    private final Node<T> fromNode;
    private final Node<T> toNode;
    private double weight;

    /**
     * Constructs an edge with a specified weight.
     *
     * @param fromNode the starting node
     * @param toNode the ending node
     * @param weight the weight of the edge
     * @throws IllegalArgumentException if fromNode or toNode is null
     */
    Edge(Node<T> fromNode, Node<T> toNode, double weight) {
        if (fromNode == null || toNode == null) {
            throw new IllegalArgumentException("fromNode and toNode cannot be null");
        }

        this.fromNode = fromNode;
        this.toNode = toNode;
        this.weight = weight;
    }

    /**
     * Constructs an edge with a default weight of 1.0.
     *
     * @param fromNode the starting node
     * @param toNode the ending node
     */
    Edge(Node<T> fromNode, Node<T> toNode) {
        this(fromNode, toNode, 1.0);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Node<T> getFromNode() {
        return fromNode;
    }

    public Node<T> getToNode() {
        return toNode;
    }
}
