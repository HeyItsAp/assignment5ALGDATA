package heyitsap.flowgraph;

import java.util.List;

public class node {
    private pointer[] pointers;
    private int value;
    private int id;

    public node (pointer[] pointers, int id){
        this.pointers = pointers;
        this.value = 0;
        this.id = id;
    }
}
