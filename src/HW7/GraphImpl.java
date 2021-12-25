package HW7;

import java.util.*;

public class GraphImpl implements Graph {

    private final List<Vertex> vertexList;
    private final int[][] adjMatrix;

    public GraphImpl(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.adjMatrix = new int[maxVertexCount][maxVertexCount];
    }

    @Override
    public void printAdjMatrix() {
        for (int i = 0; i < getSize(); i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < getSize(); j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
    }

    @Override
    public boolean addEdgeAndSetWeight(String startLabel, String secondLabel, int weight) {

        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(secondLabel);

        if (startIndex == -1 || endIndex == -1) {
            return false;
        }
        adjMatrix[startIndex][endIndex] = weight;
        return true;
    }

    private int getEdgeWeight(String startLabel, String secondLabel) {
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(secondLabel);

        if (startIndex == -1 || endIndex == -1) {
            return 0;
        }
        return adjMatrix[startIndex][endIndex];
    }

    @Override
    public boolean addEdge(String startLabel, String secondLabel) {
        return addEdgeAndSetWeight(startLabel, secondLabel, 1);
    }

    private int indexOf(String label) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i).getLabel().equals(label)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean addEdge(String startLabel, String... others) {
        boolean result = true;

        for (String other : others) {
            result &= addEdge(startLabel, other);
        }

        return result;
    }

    @Override
    public int getSize() {
        return vertexList.size();
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getSize(); i++) {
            sb.append(vertexList.get(i));
            for (int j = 0; j < getSize(); j++) {
                if (adjMatrix[i][j] > 0) {
                    sb.append(" -> ").append(vertexList.get(j));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String shortestRoute(String startLabel, String endLabel) {
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(endLabel);
        List<List<Vertex>> result = new ArrayList<>();

        if (startIndex == -1) {
            throw new IllegalArgumentException("Неверная вершина: " + startLabel);
        }
        if (endIndex == -1) {
            throw new IllegalArgumentException("Неверная вершина: " + endLabel);
        }

        Stack<Vertex> stack = new Stack<>();
        Vertex vertex = vertexList.get(startIndex);
        resetVertexVisited();

        visitVertex(stack, vertex);
        int routeNumber = 0;
        while (!stack.isEmpty()) {
            if (stack.size() == 1) {
                routeNumber++;
            }
            vertex = getNearUnvisitedVertex(stack.peek());

            if (vertex != null && !vertex.getLabel().equals(endLabel)) {
                visitVertex(stack, vertex, routeNumber);
            } else if (vertex != null && vertex.getLabel().equals(endLabel)) {
                stack.push(vertex);
                result.add(new ArrayList<>(stack));
                stack.pop();

                stack.pop();
            } else {
                stack.pop();
            }
        }

        return getShortestRoute(result);
    }

    private String getShortestRoute(List<List<Vertex>> routes) {
        int shortestRoute = Integer.MAX_VALUE;
        int shortestRouteNumber = 0;
        for (int routeNo = 0; routeNo < routes.size(); routeNo++) {
            int routeLength = 0;
            for (int i = 1; i < routes.get(routeNo).size(); i++) {
                routeLength += getEdgeWeight(routes.get(routeNo).get(i - 1).getLabel(), routes.get(routeNo).get(i).getLabel());
            }
            if (routeLength < shortestRoute) {
                shortestRoute = routeLength;
                shortestRouteNumber = routeNo;
            }
        }

        return routes.get(shortestRouteNumber).toString();
    }

    @Override
    public void dfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Неверная вершина: " + startLabel);
        }

        Stack<Vertex> stack = new Stack<>();
        Vertex vertex = vertexList.get(startIndex);
        resetVertexVisited();

        visitVertex(stack, vertex);
        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex != null) {
                visitVertex(stack, vertex);
            } else {
                stack.pop();
            }
        }
    }

    private Vertex getNearUnvisitedVertex(Vertex vertex) {
        int currentIndex = vertexList.indexOf(vertex);
        for (int i = 0; i < getSize(); i++) {
            if (adjMatrix[currentIndex][i] > 0 && (!vertexList.get(i).isVisited() || vertex.getVisitedOnRoute() != 0)) {
                if (vertexList.get(i).isVisited()) {
                    if (vertexList.get(i).getVisitedOnRoute() != vertex.getVisitedOnRoute()) {
                        return vertexList.get(i);
                    }
                } else {
                    return vertexList.get(i);
                }
            }
        }
        return null;
    }

    private void resetVertexVisited() {
        for (Vertex vertex : vertexList) {
            vertex.setVisited(false);
            vertex.setVisitedOnRoute(0);
        }
    }

    private void visitVertex(Stack<Vertex> stack, Vertex vertex, int routeNumber) {
        //System.out.println(vertex.getLabel() + " ");
        stack.add(vertex);
        vertex.setVisited(true);
        vertex.setVisitedOnRoute(routeNumber);
    }

    private void visitVertex(Stack<Vertex> stack, Vertex vertex) {
        visitVertex(stack, vertex, 0);
    }

    private void visitVertex(Queue<Vertex> queue, Vertex vertex) {
        visitVertex(queue, vertex, 0);
    }

    private void visitVertex(Queue<Vertex> queue, Vertex vertex, int routeNumber) {
        //System.out.println(vertex.getLabel() + " ");
        queue.add(vertex);
        vertex.setVisited(true);
        vertex.setVisitedOnRoute(routeNumber);
    }

    @Override
    public void bfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Неверная вершина: " + startLabel);
        }

        Queue<Vertex> queue = new LinkedList<>();
        Vertex vertex = vertexList.get(startIndex);
        resetVertexVisited();

        visitVertex(queue, vertex);
        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                visitVertex(queue, vertex);
            } else {
                queue.remove();
            }
        }

    }
}
