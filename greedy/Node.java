package greedy;

import greedy.GettingOutTheFastest;

import java.util.ArrayList;
import java.util.List;

class Node {

    List<Edge> outgoingEdges;

    int id;

    boolean marked;

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