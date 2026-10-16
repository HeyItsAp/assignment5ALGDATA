package heyitsap.flowgraph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List pointers;
    private int id;

    public Node(int id){
        this.pointers = new ArrayList();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List getPointers() {
        return pointers;
    }
}
