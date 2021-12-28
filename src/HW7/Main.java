package HW7;

public class Main {
    public static void main(String[] args) {
        testDfs();
    }

    private static void testDfs() {
        Graph graph = new GraphImpl(10);
        graph.addVertex("Москва");
        graph.addVertex("Тула");
        graph.addVertex("Рязань");
        graph.addVertex("Калуга");
        graph.addVertex("Липецк");
        graph.addVertex("Тамбов");
        graph.addVertex("Орел");
        graph.addVertex("Саратов");
        graph.addVertex("Курск");
        graph.addVertex("Воронеж");

        graph.addEdge("Москва", "Тула", "Рязань", "Калуга");

        graph.addEdgeAndSetWeight("Москва", "Тула", 2);
        graph.addEdgeAndSetWeight("Москва", "Рязань", 3);
        graph.addEdgeAndSetWeight("Москва", "Калуга", 1);
        graph.addEdgeAndSetWeight("Тула", "Липецк", 2);
        graph.addEdgeAndSetWeight("Рязань", "Тамбов", 3);
        graph.addEdgeAndSetWeight("Калуга", "Орел", 3);
        graph.addEdgeAndSetWeight("Липецк", "Воронеж", 5);
        graph.addEdgeAndSetWeight("Тамбов", "Саратов", 2);
        graph.addEdgeAndSetWeight("Орел", "Курск", 4);
        graph.addEdgeAndSetWeight("Саратов", "Воронеж", 3);
        graph.addEdgeAndSetWeight("Курск", "Воронеж", 1);
        graph.addEdgeAndSetWeight("Тамбов", "Орел", 1);

        //graph.printAdjMatrix();
        //graph.dfs("Москва");
        System.out.println(graph.shortestRoute("Москва","Воронеж"));
    }
}
