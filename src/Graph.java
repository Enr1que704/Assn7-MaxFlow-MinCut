import java.util.*;

public class Graph {
    private final GraphNode[] vertices;  // Adjacency list for graph.
    private final String name;  //The file from which the graph was created.
    private GraphNode[] residual;

    public Graph(String name, int vertexCount) {
        this.name = name;
        vertices = new GraphNode[vertexCount];
        residual = new GraphNode[vertexCount]; // declares 2d residual
        for (int vertex = 0; vertex < vertexCount; vertex++) {
            vertices[vertex] = new GraphNode(vertex);
            residual[vertex] = new GraphNode(vertex); // fill with vertexes

        }

    }

    public boolean addEdge(int source, int destination, int capacity) {
        // A little bit of validation
        if (source < 0 || source >= vertices.length) return false;
        if (destination < 0 || destination >= vertices.length) return false;

        // This adds the actual requested edge, along with its capacity
        vertices[source].addEdge(source, destination, capacity);
        // This adds the backward flow
        vertices[destination].addEdge(destination, source, 0);

        residual[source].addEdge(source, destination, capacity);
        residual[destination].addEdge(destination, source, 0);
        return true;
    }

    /**
     * Algorithm to find max-flow in a network
     */
    public int findMaxFlow(int s, int t, boolean report) {
//        if (hasAugmentingPath(s ,t)) {
//            GraphNode current = vertices[t];
//            System.out.println("flow number");
//            while (current != vertices[s]) {
//                System.out.print(current.id + " ");
//                current = vertices[current.parent];
//            }
//            System.out.println(current.id);
//        }
        int totalFlow = 0;

        while (hasAugmentingPath(s, t)) {
            int availableFlow = 30;
            GraphNode v = residual[t];
            while (v.parent != -1) {
                GraphNode prevNode = v;
                v = residual[v.parent];
                for (int i = 0; i < v.successor.size(); i++) { // for each of the edges
                    if (v.successor.get(i).to == prevNode.id) { // if the successor of the current edge is equal to where we just were
                        if (v.successor.get(i).capacity < availableFlow) { // if the edge capacity is smaller than current available flow
                            availableFlow = v.successor.get(i).capacity;
                        }
                    }
                } /* END OF FOR LOOP */
            }
//            System.out.println(availableFlow);
//            break;

            v = residual[t];
            while (v.parent != -1) {
                GraphNode prevNode = v;
                v = residual[v.parent];
                for (int i = 0; i < v.successor.size(); i++) { // for each of the edges
                    if (v.successor.get(i).to == prevNode.id) {
                        v.successor.get(i).capacity -= availableFlow;
                    }
                }
                for (int i = 0; i < prevNode.successor.size(); i++) {
                    if (prevNode.successor.get(i).to == v.id) {
                        prevNode.successor.get(i).capacity += availableFlow;
                    }
                }
            }
            totalFlow += availableFlow;
//            var r = residual;
        }
        return totalFlow;
    }

    /**
     * Algorithm to find an augmenting path in a network
     */
    private boolean hasAugmentingPath(int s, int t) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < residual.length; i++) {
            residual[i].parent = -1;
        }
        q.add(s);
        while (!q.isEmpty() && residual[t].parent < 0) {
            int v = q.poll();
            for (int edge = 0; edge < residual[v].successor.size(); edge++) {
                var w = residual[v].successor.get(edge).to;
                int capacity = residual[v].successor.get(edge).capacity;
                if (capacity > 0 && residual[v] != residual[w] && residual[w].parent == -1) {
                    residual[w].parent = v;
                    q.add(w);
                }
            }
            if (residual[t].parent != -1) {
                return true;
            }
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
