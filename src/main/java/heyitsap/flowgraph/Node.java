package heyitsap.flowgraph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Edge> edges;
    private final int id;

    public Node(int id){
        this.edges = new ArrayList<>();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
