import java.util.*;
import java.math.*;
import java.io.*;

// author: Oscar O'Neill, Student no. 17330989

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *    - Each contestant walks at a given estimated speed.
 *    - The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */

    int numVertices;
    ArrayList<Edge> adjList[];
    int speeds[] = new int[3];

    PriorityQueue<Node> pq = new PriorityQueue<Node>();
    double nodeDistance[];

    void parseGraphFile(String fileName) throws Exception {
        Scanner scanner = new Scanner(new File(fileName));

        if (!scanner.hasNextInt()) {
            return;
        }

        numVertices = scanner.nextInt();
        adjList = new ArrayList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjList[i] = new ArrayList();
        }
        nodeDistance = new double[numVertices];

        int numEdges = scanner.nextInt();
        while (numEdges > 0) {
            numEdges--;
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            double length = scanner.nextDouble() * 1000;
            adjList[a].add(new Edge(a, b, length));
        }
    }

    CompetitionDijkstra (String fileName, int sA, int sB, int sC) {
        speeds[0] = sA;
        speeds[1] = sB;
        speeds[2] = sC;
        try {
            parseGraphFile(fileName); // Import and construct graph
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition() {
        if (numVertices == 0 || Math.min(speeds[0], Math.min(speeds[1], speeds[2])) < 50 || Math.max(speeds[0], Math.max(speeds[1], speeds[2])) > 100) {
            return -1;
        }

        double maxDistance = 0;
        for (int startingPosition = 0; startingPosition < numVertices; startingPosition++) {
            for (int i = 0; i < numVertices; i++) {
            	// Initialize all distances to INF
            	nodeDistance[i] = -1;
            }

            pq.add(new Node(startingPosition, 0));
            nodeDistance[startingPosition] = 0;

            while (!pq.isEmpty()) {
                Node front = pq.poll();
                int node = front.u;
                double distance = front.distance; // Distance is essentially just time

                if (nodeDistance[node] < distance) {
                    continue;
                }

                for (Edge edge : adjList[node]) {
                    double newDistance = distance + edge.length;
                    if (nodeDistance[edge.v] > newDistance || nodeDistance[edge.v] == -1) {
                        nodeDistance[edge.v] = newDistance;
                        pq.add(new Node(edge.v, newDistance));
                    }
                }
            }

            for (int i = 0; i < numVertices; i++) {
                maxDistance = Math.max(maxDistance, nodeDistance[i]);
                if (nodeDistance[i] < 0) {
                	// Graph not connected
                    return -1;
                }
            }
        }
        maxDistance /= Math.min(speeds[0], Math.min(speeds[1], speeds[2]));
        return (int)Math.ceil(maxDistance);
    }
}

class Edge {
    public int u, v;
    public double length;
    public Edge(int u, int v, double length) {
        this.u = u;
        this.v = v;
        this.length = length;
    }
}


class Node implements Comparable<Node> {
    public int u;
    public double distance;
    public Node(int u, double distance) {
        this.u = u;
        this.distance = distance;
    }
    public int compareTo(Node node) {
        if (distance < node.distance) {
            return -1; 
        }
        if (distance > node.distance) {
            return 1; 
        }
        return 0; 
    }
}