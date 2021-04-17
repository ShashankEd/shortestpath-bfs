// Java program to find the shortest path between a source and destination in the network graph 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.*;  
 
public class ShortestDistanceInNetwork {

    public static void main(String args[])
    {
        Scanner sc= new Scanner(System.in); 
        // No of vertices(users in the network)
        int v = 14;
        
        // Adjacency list for storing which vertices are connected
        ArrayList<ArrayList<Integer>> adj =
                            new ArrayList<ArrayList<Integer>>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<Integer>());
        }
        
        ArrayList<String> users =
                            new ArrayList<String>(v);
        //indexes from 0-14 and their respective values/vertices(users) in the network
        users.add("Prateek");
        users.add("Rishika");
        users.add("Sneha");
        users.add("Sufi");
        users.add("Lucas");
        users.add("Pranav");
        users.add("Priyanka");
        users.add("Scott");
        users.add("Rahul");
        users.add("Devika");
        users.add("Aatika");
        users.add("Vanika");
        users.add("Vashinka");
        users.add("Nathan");
        // Creating graph for the users and their nneighbours in the network
        addEdge(adj, 0, 1);
        addEdge(adj, 0, 2);
        addEdge(adj, 0, 5);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 3);
        addEdge(adj, 2, 4);
        addEdge(adj, 5, 6);
        addEdge(adj, 5, 9);
        addEdge(adj, 5, 12);
        addEdge(adj, 6, 8);
        addEdge(adj, 8, 7);
        addEdge(adj, 8, 9);
        addEdge(adj, 9, 10);
        addEdge(adj, 10, 11);
        addEdge(adj, 10, 12);
        addEdge(adj, 12, 13);
        //if you are running in local machine, then uncomment the 
        //following and take input from scanner 
        // System.out.println("Enter source: ");  
        // String sourceName= sc.nextLine(); 
        // System.out.println("Enter destination: ");  
        // String destinationName= sc.nextLine(); 
        
        String sourceName= "Prateek";
        String destinationName= "Rahul";
        System.out.println("Input "+ sourceName +" "+ destinationName);
        int source = users.indexOf(sourceName), dest = users.indexOf(destinationName);
        printShortestDistance(adj, source, dest, v,users);
    }
 
    // function to form edge between two vertices
    // source and dest
    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j)
    {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }
    
    //Below BFS to explore the adjecent vertices and store the predecessor vertices
    private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src,
                                  int dest, int v, int pred[], int dist[])
    {
        // a queue to maintain queue of vertices whose
        // adjacency list is to be scanned as per normal
        // BFS algorithm using LinkedList of Integer type
        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        // boolean array visited[] which stores the
        // information whether ith vertex is reached
        // at least once in the Breadth first search
        boolean visited[] = new boolean[v];
 
        // initially all vertices are unvisited
        // so v[i] for all i is false
        // and as no path is yet constructed
        // dist[i] for all i set to infinity
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }
 
        // now source is first to be visited and
        // distance from source to itself should be 0
        visited[src] = true;
        dist[src] = 0;
        queue.add(src);
 
        // bfs Algorithm
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                if (visited[adj.get(u).get(i)] == false) {
                    visited[adj.get(u).get(i)] = true;
                    dist[adj.get(u).get(i)] = dist[u] + 1;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));
 
                    // break if destination found
                    if (adj.get(u).get(i) == dest)
                        return true;
                }
            }
        }
        return false;
    }
    
    // function to print the shortest distance and path
    // between source vertex and destination vertex
    private static void printShortestDistance(
                     ArrayList<ArrayList<Integer>> adj,
                             int s, int dest, int v,ArrayList<String> users)
    {
        // predecessor[i] array stores predecessor of
        // i and distance array stores distance of i
        // from s
        int pred[] = new int[v];
        int dist[] = new int[v];
 
        if (BFS(adj, s, dest, v, pred, dist) == false) {
            System.out.println("Not connected vertices");
            return;
        }
 
        // LinkedList to store path
        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }
        // Print path
        System.out.println("Path is ::");
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(users.get(path.get(i))+ " ");
        }
    }
}
