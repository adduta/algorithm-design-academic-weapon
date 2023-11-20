package greedy.graph;

import greedy.graph.Edge;

import java.util.ArrayList;
import java.util.List;

public class Node {

    public List<Edge> outgoingEdges;

    public int id;

    public boolean marked;

    public Node(int id) {
        this.outgoingEdges = new ArrayList<>();
        this.marked = false;
        this.id = id;
    }

    public String toString() {
        return Integer.toString(id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}