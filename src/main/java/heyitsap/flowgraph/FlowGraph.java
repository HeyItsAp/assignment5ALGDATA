package heyitsap.flowgraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
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
    public int edmondkarp(){
        int maxFlow = 0;
        int iteration = 0;
        while (true) {
            // BFS: find shortest augmenting path, remembering the edge used to reach each node
            Edge[] parentEdge = new Edge[nodes.size()];
            Deque<Node> queue = new ArrayDeque<>();
            queue.add(source);

            while (!queue.isEmpty() && parentEdge[sink.getId()] == null) {
                Node cur = queue.poll();
                for (Edge e : cur.getEdges()) {
                    Node next = e.getTo();
                    if (e.rest() > 0 && next != source && parentEdge[next.getId()] == null) {
                        parentEdge[next.getId()] = e;
                        queue.add(next);
                    }
                }
            }

            if (parentEdge[sink.getId()] == null) break;   // no augmenting path left

            // Find bottleneck by walking back from sink
            int bottleneck = Integer.MAX_VALUE;
            for (Node n = sink; n != source; n = parentEdge[n.getId()].getFrom()) {
                bottleneck = Math.min(bottleneck, parentEdge[n.getId()].rest());
            }

            // Augment
            for (Node n = sink; n != source; n = parentEdge[n.getId()].getFrom()) {
                parentEdge[n.getId()].setNewFlowStats(bottleneck);
            }
            maxFlow += bottleneck;
        }
        return maxFlow;

    }

    public void addEdge(Node from, Node to, int capaity){
        Edge forward = new Edge(capaity, from, to);
        Edge back = new Edge(0, to, from);
        forward.setReverse(back);
        back.setReverse(forward);
        from.getEdges().add(forward);
        to.getEdges().add(back);
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
