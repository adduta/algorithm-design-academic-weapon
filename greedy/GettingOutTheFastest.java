package greedy;
import greedy.graph.Edge;
import greedy.graph.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class GettingOutTheFastest {
    /**
     * A maze is represented by a weighted directed graph 𝐺 = (𝑉,𝐸),
     * where 𝑉 denotes the set containing 𝑛 vertices
     * and 𝐸 the set containing 𝑚 directed edges.
     * Each vertex represents an intersection or end point in the maze and the edges represent paths between them.
     * A directed edge is used for (downhill) tunnels and holes that you can jump into,
     * but where it is impossible to get back.
     * Because of this, it may become impossible to reach the exit.
     * Some edges take longer than others, which is expressed in their weight.
     * Additionally, the sheer number of options you can choose from in every vertex overwhelms you quite a bit.
     * As such every vertex takes 1 additional time step per outgoing edge
     * (because you have to find out what is the correct one).
     * Design and implement an algorithm that determines the path from 𝑠 to 𝑡
     *  that takes the least amount of time (which is the sum of lengths of all edges plus for all vertices
     *  (except 𝑡 the number of outgoing edges). Let the algorithm just print the total time of this path.
     * Aim for the most efficient algorithm you can think of.
     * Extremely slow implementations will likely trigger timeouts in the spec tests.
     */

    /**
     * @param n the number of nodes
     * @param m the number of edges
     * @param s the starting node (1 <= s <= n)
     * @param t the ending node (1 <= t <= n)
     * @param nodes the set of n nodes in the graph.
     * @param edges the set of edges of the graph, with endpoints labelled between 1 and n
     *     inclusive.
     * @return the time required to get out, or -1 if it is not possible to get out.
     */
    public static int getMeOuttaHerePolynomial(int n, int m, int s, int t, Set<Node> nodes, Set<Edge> edges) {
        // As we are looking for a solution that finds the least required time to get out of the maze,
        // we will implement Dijkstra's algorithm.
        if (n == 0 || m == 0) return 0;
        boolean[] visitedVertex = new boolean[n + 1];
        int[] distance = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            visitedVertex[i] = false;
            distance[i] = Integer.MAX_VALUE;
        }

        Node currNode = null;
        for (Node node : nodes) {
            if (node.id == s) {
                currNode = node;
                visitedVertex[s] = true;
                distance[s] = 0;
                break;
            }
        }

        if (currNode == null) return 0;

        while (currNode.id != t) {
            int totalOutgoingEdges = currNode.outgoingEdges.size();
            int minIndex = currNode.id;
            int minDist = Integer.MAX_VALUE;
            for (Edge edge : currNode.outgoingEdges) {
                int edgeStart = edge.from;
                int edgeEnd = edge.to;
                int distanceEdge = distance[edgeStart] + edge.weight + totalOutgoingEdges;

                if (!visitedVertex[edgeEnd] && distanceEdge < distance[edgeEnd]) {
                    distance[edgeEnd] = distanceEdge;
                }

                if (distance[edgeEnd] < minDist) {
                    minDist = distance[edgeEnd];
                    minIndex = edgeEnd;
                }

            }

            visitedVertex[minIndex] = true;
            for (Node node : nodes) {
                if (node.id == minIndex) {
                    currNode = node;
                    break;
                }
            }
        }

        return distance[t];
    }

    public static int getMeOuttaHere(int n, int m, int s, int t, Set<Node> nodes, Set<Edge> edges) {
        // As we are looking for a solution that finds the least required time to get out of the maze,
        // we will implement Dijkstra's algorithm.
        if (n == 0 || m == 0) return 0;

        Map<Integer, Node> nodesWithId = new HashMap<>();
        for (Node node : nodes) nodesWithId.put(node.id, node);

        boolean[] visitedVertex = new boolean[n + 1];
        int[] distance = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            visitedVertex[i] = false;
            distance[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Tuple> pq = new PriorityQueue<>();

        pq.add(new Tuple(s, 0));
        distance[s] = 0;
        while (!pq.isEmpty()) {
            Tuple currTuple = pq.poll();
            if (visitedVertex[currTuple.id]) continue;

            visitedVertex[currTuple.id] = true;
            if (currTuple.id == t) return distance[t];


            for (Edge edge : nodesWithId.get(currTuple.id).outgoingEdges) {
                int edgeStart = edge.from;
                int edgeEnd = edge.to;
                int totalEdges = nodesWithId.get(currTuple.id).outgoingEdges.size();
                int distanceEdge = distance[edgeStart] + edge.weight + totalEdges;

                if (!visitedVertex[edgeEnd] && distanceEdge < distance[edgeEnd]) {
                    Tuple newTuple = new Tuple(edgeEnd, distanceEdge);
                    distance[edgeEnd] = newTuple.cost;
                    pq.add(newTuple);
                }
            }
        }

        return visitedVertex[t] ? distance[t] : -1;
    }
}

class TestSuiteGettingOutTheFastest {

    @Test
    public void example() {
        int n = 7;
        int m = 7;
        int s = 1;
        int t = 5;
        Set<Node> nodes = new HashSet<>();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);
        nodes.add(n5);
        nodes.add(n6);
        nodes.add(n7);
        Set<Edge> edges = new HashSet<>();
        Edge e = new Edge(1, 2, 2);
        n1.outgoingEdges.add(e);
        edges.add(e);
        e = new Edge(2, 3, 100);
        n2.outgoingEdges.add(e);
        edges.add(e);
        e = new Edge(3, 4, 10);
        n3.outgoingEdges.add(e);
        edges.add(e);
        e = new Edge(4, 5, 10);
        n4.outgoingEdges.add(e);
        edges.add(e);
        e = new Edge(2, 6, 10);
        n2.outgoingEdges.add(e);
        edges.add(e);
        e = new Edge(6, 7, 10);
        n6.outgoingEdges.add(e);
        edges.add(e);
        e = new Edge(7, 4, 80);
        n7.outgoingEdges.add(e);
        edges.add(e);
        Assertions.assertEquals(118, GettingOutTheFastest.getMeOuttaHere(n, m, s, t, nodes, edges));
    }
}

class Tuple implements Comparable<Tuple> {
    int id;
    int cost;

    public Tuple(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple tuple = (Tuple) o;
        return id == tuple.id && cost == tuple.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost);
    }

    @Override
    public int compareTo(Tuple tuple) {
        int res = Integer.signum(this.cost - tuple.cost);
        if (res == 0) {
            return Integer.signum(this.id - tuple.id);
        }
        return res;
    }
}
