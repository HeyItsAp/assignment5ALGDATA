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

    public FlowGraph(){
        this.nodes = new ArrayList<>();
        this.source = null;
        this.sink = null;
    }

    public void addEdge(Node from, Node to, int capaity){
        Edge forward = new Edge(capaity, from, to);
        Edge back = new Edge(0, to, from);
        forward.setReverse(back);
        back.setReverse(forward);
        from.getEdges().add(forward);
        to.getEdges().add(back);
    }
    public void edmondkarp(){

    }

    public Node getOrCreateNode(int id) {
        while (nodes.size() <= id) {
            addNode();
        }
        return nodes.get(id);
    }

    public Node addNode() {
        Node n = new Node(nodes.size());
        nodes.add(n);
        return n;
    }

    public List<Node> getNodes() { return nodes; }

    public void setSink(Node sink) {
        this.sink = sink;
    }

    public void setSource(Node source) {
        this.source = source;
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
