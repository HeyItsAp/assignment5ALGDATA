# Short Answer
|       | Kilde | Sluk | Max flyt |
|-------|-------| ----- |----------|
| graf1 | $0$   | 7 | 10       |
| graf2 | $0$   | 1 | 27       |
| graf3 | $0$   | 1 | **$42$** | 
| graf4 | $0$   | 7 | 11       |
| graf5 | $0$   | 7 | **$90$** |

Maks flyt i *flytgraf3* er **42**, og i *flytgraf5* er det **90**.

FlytGraf5, Kilde 0 og Sluk 1:
```bash
Path 1: 0 -> 8 -> 9 -> 1  | added flow: 10  | total: 10
Path 2: 0 -> 2 -> 3 -> 9 -> 8 -> 14 -> 15 -> 1  | added flow: 10  | total: 20
Path 3: 0 -> 2 -> 3 -> 9 -> 8 -> 14 -> 15 -> 1  | added flow: 5  | total: 25
Path 4: 0 -> 2 -> 3 -> 4 -> 5 -> 8 -> 9 -> 12 -> 13 -> 14 -> 15 -> 1  | added flow: 10  | total: 35
Path 5: 0 -> 2 -> 3 -> 4 -> 5 -> 8 -> 9 -> 12 -> 13 -> 14 -> 15 -> 1  | added flow: 1  | total: 36
Path 6: 0 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 9 -> 8 -> 10 -> 11 -> 12 -> 13 -> 14 -> 15 -> 1  | added flow: 6  | total: 42
42
```

FlytGraf5, Kilde 0 og Sluk 7:
```bash
Path 1: 0 -> 2 -> 3 -> 7  | added flow: 10  | total: 10
Path 2: 0 -> 2 -> 4 -> 7  | added flow: 25  | total: 35
Path 3: 0 -> 5 -> 6 -> 7  | added flow: 10  | total: 45
Path 4: 0 -> 5 -> 6 -> 1 -> 7  | added flow: 40  | total: 85
Path 5: 0 -> 2 -> 4 -> 6 -> 1 -> 7  | added flow: 5  | total: 90
90
```

# Longer Answer (Details and explantion of the code)
> Implementation of the code uses classes which contains references to other nodes and edges. It does not use a neighbor table and other similar data structures. It uses getter-methods and use of different lists to achieve certain principles (FIFO) and for traversing (BFS). It also uses certain API for file reading.

Implementation is similar to previous assignment, if you're the same person evaluating this.

The "objects" necessary are nodes, vertices (will be referred to as edges) and the actual Flowgraph containing nodes and edges. I will be going through each class individual to explain their function and purpose;

## Node.java
The simplest of the objects are nodes, as they only contain an id for reference and any amount of edges. An class with id and a mutable list, with getter method represent this fine: 
```java
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
```

## Edge.java
Edges in a flowgraph are one-way pointer the have a an allowed flow and a capacity. Due to the nature of Edmond Karps algorithm, a reverse of that pointer might be necessary to use. Also, due to the nature of node contruction, edges must be mutable for make the flowgraph generation smoother. For example, when making an edge, we need to make the reverse-edge afterwards and store eachothers referances respectivly. The edge class should have a method to calculate new flow value for Edmond Karps algorithm. 

The contructor for Edge.java will not set a referance for the reverse edge upon creation; And should be set after creation during runtime:
