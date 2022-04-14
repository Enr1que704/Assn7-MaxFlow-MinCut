import java.util.*;

public class Graph {
    private final GraphNode[] vertices;  // Adjacency list for graph.
    private final String name;  //The file from which the graph was created.
    private GraphNode[][] residual;

    public Graph(String name, int vertexCount) {
        this.name = name;
        vertices = new GraphNode[vertexCount];
        residual = new GraphNode[vertexCount][2]; // declares 2d residual
        for (int vertex = 0; vertex < vertexCount; vertex++) {
            vertices[vertex] = new GraphNode(vertex);
            residual[vertex][0] = new GraphNode(vertex); // fill with vertexes
            residual[vertex][1] = new GraphNode(vertex);
        }
    }

    public boolean addEdge(int source, int destination, int capacity) {
        // A little bit of validation
        if (source < 0 || source >= vertices.length) return false;
        if (destination < 0 || destination >= vertices.length) return false;

        // This adds the actual requested edge, along with its capacity
        vertices[source].addEdge(source, destination, capacity);
        // This adds the backward flow
        vertices[destination].addEdge(destination, source, 0); // how do we know how much is actually being sent? Is this just for initialization, or for any reference

        residual[source][0].addEdge(source, destination, capacity); // how much space is left forward?
        residual[source][1].addEdge(destination, source, capacity); // how much was put in going forward? should it be at index source, or destination?
        return true;
    }

    /**
     * Algorithm to find max-flow in a network
     */
    public int findMaxFlow(int s, int t, boolean report) {
        hasAugmentingPath(s, t);
        return 0;
    }

    /**
     * Algorithm to find an augmenting path in a network
     */
    private boolean hasAugmentingPath(int s, int t) { // should we use vertices or residual?
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < vertices.length; i++) {        // reset parents of all nodes
            vertices[i].parent = -1;
        }
        q.add(s);                                          // add s to the queue
        while (!q.isEmpty() && vertices[t].parent == -1) { // while queue isn't empty and vertex t doesn't have a parent
            int v = q.remove();                            // remove from queue as vertex v
//            for (var edge : vertices[v].successor) {       // for all successors from v
            for (int edge = 0; edge < vertices[v].successor.size(); edge++) {
                int w = vertices[v].successor.get(edge).to;                           // for the edge, call the other vertex w
                if (vertices[v].successor.get(edge).capacity > 0 && !vertices[v].visited) {
                    vertices[v].visited = true;
                    vertices[w].parent = v;
                    q.add(w);
                    System.out.println(v);
                }
            }

        }
        System.out.println(t);
        if (vertices[t].parent != -1) {
            return true;
        }


        return false;
    }

    /**
     * Algorithm to find the min-cut edges in a network
     */
    public void findMinCut(int s) {
        // TODO:
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("The Graph " + name + " \n");
        for (var vertex : vertices) {
            sb.append((vertex.toString()));
        }
        return sb.toString();
    }
}
