package heyitsap.flowgraph;

public class pointer {
    private int capacity;
    private int flow;
    private node nodeA;
    private node nodeB;

    public pointer(int capacity, node nodeA, node nodeB){
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

    public node getNodeA() {
        return nodeA;
    }

    public node getNodeB() {
        return nodeB;
    }

    public void removeFromCapacity(int flow){
        this.capacity = capacity - flow;
    }
}
