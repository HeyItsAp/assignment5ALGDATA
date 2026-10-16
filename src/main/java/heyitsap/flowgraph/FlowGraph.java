package heyitsap.flowgraph;

import java.util.ArrayList;
import java.util.List;

public class FlowGraph {
    private List<Node> nodes;
    private Node source;
    private Node sink;

    public FlowGraph(List<Node> nodes){
        this.nodes = nodes;
        this.source = nodes.get(0);
        this.sink = nodes.get(nodes.size()-1);
    }

    public void addEdge(Node from, Node to, int capaity){

    }
    public void edmondkarp(){

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node n : nodes){
            for (Edge e : n.getEdges()){
                if (e.getCapacity() > 0) {   // skip the reverse edges
                    sb.append(n.getId()).append(" -> ").append(e.getTo().getId())
                            .append(" (").append(e.getFlow()).append("/").append(e.getCapacity()).append(")\n");
                }
            }
        }
        return sb.toString();
    }
}
