package HW7;

public interface Graph {

    void addVertex(String label);

    boolean addEdge(String startLabel, String... others);
    boolean addEdge(String startLabel, String secondLabel);
    boolean addEdgeAndSetWeight(String startLabel, String secondLabel, int weight);

    int getSize();

    void display();
    void printAdjMatrix();
    /**
     * англ. Depth-first search, DFS
     */
    void dfs(String startLabel);
    String shortestRoute(String startLabel, String endLabel);

    /**
     * англ. breadth-first search, BFS
     */
    void bfs(String startLabel);

}
