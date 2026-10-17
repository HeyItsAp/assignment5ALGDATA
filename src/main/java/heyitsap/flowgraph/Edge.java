package heyitsap.flowgraph;

public class Edge {
    private final int capacity;
    private int flow;
    private final Node from;
    private final Node to;
    private Edge reverse;

    public Edge(int capacity, Node from, Node to){
        this.capacity = capacity;
        this.flow = 0;
        this.from = from;
        this.to = to;
    }

    public int rest(){return capacity - flow;}

    public void setNewFlowStats(int ammount){
        flow += ammount;
        reverse.flow -= ammount;
    }

    public void setReverse(Edge reverse) {
        this.reverse = reverse;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlow() {
        return flow;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }
}
