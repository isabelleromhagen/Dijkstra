import java.util.InputMismatchException;
import java.util.Scanner;


class Hub {
    private Utility utility = new Utility();
    private Graph graph;
    private int start, destination;
    private Dijkstra dijkstra = new Dijkstra();
    private RandomGraphMaker randomGraphMaker = new RandomGraphMaker();
    private Scanner sc;

    Hub() {
        System.out.println("System online");
    }

    void menu() {

        while (true) {

            System.out.println("\nWelcome to RouteCity! \n Enter '1' to create a new network\n Enter '2' to exit.");
            sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.contains(" ")) {
                System.out.println("Invalid input, please try again.");
                continue;
            }
            switch (input) {
                case "1":
                    System.out.println("\n Enter '1' to plan a new trip\n Enter '2' to return.\n");
                    String choice = sc.nextLine();
                    if (choice.contains(" ")) {
                        System.out.println("Invalid input, please try again.");
                        continue;
                    }
                    switch (choice) {
                        case "1":
                            graph = randomGraphMaker.createNewGraph();
                            planTrip(graph);
                            break;
                        case "2":
                            System.out.println("Returning");
                            break;
                        default:
                            System.out.println("Invalid input, please try again.");
                    }
                    break;
                case "2":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Not a valid option, please try again.");
            }

        }
    }

    private void planTrip(Graph graph) {
        if (graph == null) {
            System.out.println("Please create a graph first. ");
            return;
        }
        System.out.println("\nWhere do you want to start? \nPress 0 to return\n");
        while (true) {
            for (int i = 1; i <= Constants.NAMES.length; i++) {
                System.out.println(i + " - " + Constants.NAMES[i - 1]);
            }
            try {
                start = sc.nextInt();
                if (start <= 0 || start > Constants.NUMBER_OF_VERTICES) {
                    System.out.println(start + " is not a valid stop");

                } else break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again.");
                sc.next();
            }
        }
        while (true) {
            System.out.println("\nWhere do you want to go? (Destination) \nPress 0 to return\n");

            for (int i = 1; i <= Constants.NAMES.length; i++) {
                System.out.println(i + " - " + Constants.NAMES[i - 1]);
            }
            try {
                destination = sc.nextInt();
                if (destination <= 0 || destination > Constants.NUMBER_OF_VERTICES) {
                    System.out.println(destination + " is not a valid stop, try again");

                } else showDistance(graph, start, destination);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again.");
                sc.next();
            }
           /* if (start > 0 && start < Constants.NUMBER_OF_VERTICES + 1 && destination > 0 && destination < Constants.NUMBER_OF_VERTICES) {
                showDistance(graph, start, destination);
            }*/

        }
    }

    //call Dijkstra to obtain the result set and print the result.
    HeapNode[] showDistance(Graph graph, int start, int destination) {
        this.graph = graph;
        HeapNode[] resultSet = dijkstra.dijkstraGetMinDistance(graph, start - 1);
        utility.printDijkstra(resultSet, start - 1, destination - 1);

        return resultSet;
    }
}
