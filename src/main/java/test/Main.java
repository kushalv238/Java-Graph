package test;

import graph.Graph;
import graph.Edge;
import graph.Node;
import graph.DirectedGraph;
import graph.GraphAlgorithms;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
    * Example use case for the Graph project
*/
public class Main {
    public static void main(String[] args) {
        Graph<Integer> graph = new DirectedGraph<>();
        GraphAlgorithms<Integer> graphAlgorithms = new GraphAlgorithms<>();

        Node<Integer> n1 = graph.addNode(10, "bob");
        Node<Integer> n2 = graph.addNode(20, "marley");
        Node<Integer> n3 = graph.addNode(30, "tom");
        Node<Integer> n4 = graph.addNode(40, "cruise");
        Node<Integer> n5 = graph.addNode(50, "hiddleston");
        Node<Integer> n6 = graph.addNode(60);
        Node<Integer> n7 = graph.addNode(70, "steve");

        graph.addNode(80); // an independent unconnected node, to demonstrate disconnected graphs

        graph.connect(n1, n3, 3.0);
        graph.connect(n2, new ArrayList<>(Arrays.asList(n1, n3, n5, n6)), new ArrayList<>(Arrays.asList(0.2, 6.2, 0.9, 3.5)));
        graph.connect(n3, n6, 2.4);
        graph.connect(n4, new ArrayList<>(Arrays.asList(n1, n2)), new ArrayList<>(Arrays.asList(3.7, 2.0)));
        graph.connect(n5, n7, 0.3);
        graph.connect(n6, n7, 2.1);

        System.out.println("Nodes and their connections:");
        for (Node<Integer> node : graph.getNodes()) {
            System.out.println("Node " + node.getName() + " is connected to:");
            for (Edge<Integer> edge : node.getEdges()) {
                System.out.println(" -> Node " + edge.getToNode().getName() + " with weight " + edge.getWeight());
            }
        }

        System.out.println();

        List<Node<Integer>> bfs = graphAlgorithms.bfs(n4);
        System.out.print("BFS starting from node " + n4.getName() + ": ");
        for (Node<Integer> node : bfs) {
            System.out.print(node.getName() + ", ");
        }

        System.out.println();

        List<Node<Integer>> dfs = graphAlgorithms.dfs(n4);
        System.out.print("DFS starting from node " + n4.getName() + ": ");
        for (Node<Integer> node : dfs) {
            System.out.print(node.getName() + ", ");
        }

        System.out.println("\n");

        Map<Node<Integer>, Double> dijkstraMinDistances = graphAlgorithms.dijkstra(graph, n4);
        System.out.println("Min distances from node " + n4.getName() + " using Dijkstra's algorithm:-");
        for(Map.Entry<Node<Integer>, Double> entry: dijkstraMinDistances.entrySet()) {
            System.out.println("Node " + entry.getKey().getName() + ": " + entry.getValue());
        }
    }
}