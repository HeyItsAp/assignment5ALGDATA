package heyitsap.flowgraph;

public class Edge {
    private final int capacity;
    private int flow;
    private Node nodeA;
    private Node nodeB;

    public Edge(int capacity, Node nodeA, Node nodeB){
        this.capacity = capacity;
        this.flow = 0;
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlow() {
        return flow;
    }

    public Node getNodeA() {
        return nodeA;
    }

    public Node getNodeB() {
        return nodeB;
    }

    public void removeFromCapacity(int flow){
        this.flow = capacity - flow;
    }
}
