import java.util.*;

public class Graph {
    private final GraphNode[] vertices;  // Adjacency list for graph.
    private final String name;  //The file from which the graph was created.

    public Graph(String name, int vertexCount) {
        this.name = name;

        vertices = new GraphNode[vertexCount];
        for (int vertex = 0; vertex < vertexCount; vertex++) {
            vertices[vertex] = new GraphNode(vertex);
        }
    }

    public boolean addEdge(int source, int destination, int capacity) {
        // A little bit of validation
        if (source < 0 || source >= vertices.length) return false;
        if (destination < 0 || destination >= vertices.length) return false;

        // This adds the actual requested edge, along with its capacity
        vertices[source].addEdge(source, destination, capacity);

        return true;
    }

    /**
     * Algorithm to find max-flow in a network
     */
    public int findMaxFlow(int s, int t, boolean report) {
        // TODO:
        return 0;
    }

    /**
     * Algorithm to find an augmenting path in a network
     */
    private boolean hasAugmentingPath(int s, int t) {
//        Queue<Integer> q = new LinkedList<>();
//        for (int i = 0; i < vertices.length; i++) {        // reset parents of all nodes
//            vertices[i].parent = -1;
//        }
//        q.add(s);                                          // add s to the queue
//        while (!q.isEmpty() && vertices[t].parent == -1) { // while queue isn't empty and vertex t doesn't have a parent
//            int v = q.remove();                            // remove from queue as vertex v
//            for (var edge : vertices[v].successor) {       // for all successors from v
//                int w = edge.to;                           // for the edge, call the other vertex w
//                if (vertices[v].successor.get(w).capacity > 0 && !vertices[v].visited && vertices[v].successor.get(w).from != s) {
//                    vertices[v].visited = true;
//                    vertices[w].parent = v;
//                    q.add(w);
//                }
//            }
//        }
//        if (vertices[t].parent != -1) {
//            return true;
//        }
//
//
//        return false;
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
