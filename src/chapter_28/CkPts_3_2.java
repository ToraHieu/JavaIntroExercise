package chapter_28;

import java.util.ArrayList;

/**
 * Represent the following graph using
 * an edge array,
 * a list of edge objects,
 * an adjacency matrix,
 * an adjacency vertex list,
 * and an adjacency edge list,
 * respectively.
 */
public class CkPts_3_2 {
    public static void main(String[] args) {

    }

    private int[][] edgeArray() {
        return new int[][]{
                {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5},
                {1, 0}, {1, 2}, {1, 3}, {1, 4},
                {2, 0}, {2, 1}, {2, 3}, {2, 4},
                {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
                {4, 0}, {4, 1}, {4, 2}, {4, 3},
                {5, 0}, {5, 2}
        };
    }

    private ArrayList<Edge> edgeObject() {
        ArrayList<Edge> list = new ArrayList<>();
        list.add(new Edge(0, 1));
        list.add(new Edge(0, 2));
        list.add(new Edge(0, 3));
        list.add(new Edge(0, 4));
        list.add(new Edge(0, 5));

        list.add(new Edge(1, 0));
        list.add(new Edge(1, 2));
        list.add(new Edge(1, 3));
        list.add(new Edge(1, 4));

        list.add(new Edge(2, 0));
        list.add(new Edge(2, 1));
        list.add(new Edge(2, 3));
        list.add(new Edge(2, 4));

        list.add(new Edge(3, 0));
        list.add(new Edge(3, 1));
        list.add(new Edge(3, 2));
        list.add(new Edge(3, 4));
        list.add(new Edge(3, 5));

        list.add(new Edge(4, 0));
        list.add(new Edge(4, 1));
        list.add(new Edge(4, 2));
        list.add(new Edge(4, 3));

        list.add(new Edge(5, 0));
        list.add(new Edge(5, 2));

        return list;
    }

    private int[][] adjacencyMatrix() {
        return new int[][]{
                {0, 1, 1, 1, 1, 1},
                {1, 0, 1, 1, 1, 0},
                {1, 1, 0, 1, 1, 0},
                {1, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 0, 0},
                {1, 0, 0, 1, 0, 0},
        };
    }

    private ArrayList<Integer>[] adjacencyVertexList() {
        ArrayList<Integer>[] lists = new ArrayList[6];
        lists[0] = new ArrayList<>();
        lists[0].add(1);
        lists[0].add(2);
        lists[0].add(3);
        lists[0].add(4);
        lists[0].add(5);

        lists[1] = new ArrayList<>();
        lists[1].add(0);
        lists[1].add(2);
        lists[1].add(3);
        lists[1].add(4);

        lists[2] = new ArrayList<>();
        lists[2].add(0);
        lists[2].add(1);
        lists[2].add(3);
        lists[2].add(4);

        lists[3] = new ArrayList<>();
        lists[3].add(0);
        lists[3].add(1);
        lists[3].add(2);
        lists[3].add(4);
        lists[3].add(5);

        lists[4] = new ArrayList<>();
        lists[4].add(0);
        lists[4].add(1);
        lists[4].add(2);
        lists[4].add(3);

        lists[5] = new ArrayList<>();
        lists[5].add(0);
        lists[5].add(3);

        return lists;
    }

    private ArrayList<ArrayList<Edge>> adjacencyEdgeList() {
        ArrayList<ArrayList<Edge>> list = new ArrayList<>();
    
        list.add(new ArrayList<>());
        list.get(0).add(new Edge(0, 1));
        list.get(0).add(new Edge(0, 2));
        list.get(0).add(new Edge(0, 3));
        list.get(0).add(new Edge(0, 4));
        list.get(0).add(new Edge(0, 5));

        list.add(new ArrayList<>());
        list.get(1).add(new Edge(1, 0));
        list.get(1).add(new Edge(1, 2));
        list.get(1).add(new Edge(1, 3));
        list.get(1).add(new Edge(1, 4));

        list.add(new ArrayList<>());
        list.get(2).add(new Edge(2, 0));
        list.get(2).add(new Edge(2, 1));
        list.get(2).add(new Edge(2, 3));
        list.get(2).add(new Edge(2, 4));

        list.add(new ArrayList<>());
        list.get(3).add(new Edge(3, 0));
        list.get(3).add(new Edge(3, 1));
        list.get(3).add(new Edge(3, 2));
        list.get(3).add(new Edge(3, 4));
        list.get(3).add(new Edge(3, 5));

        list.add(new ArrayList<>());
        list.get(4).add(new Edge(4, 0));
        list.get(4).add(new Edge(4, 1));
        list.get(4).add(new Edge(4, 2));
        list.get(4).add(new Edge(4, 3));

        list.add(new ArrayList<>());
        list.get(5).add(new Edge(5, 0));
        list.get(5).add(new Edge(5, 2));

        return list;
    }


    private static class Edge {
        int u, v;

        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Edge)) return false;
            Edge edge = (Edge) o;
            return u == edge.u &&
                    v == edge.v;
        }
    }
}
