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
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {
    int numVertices;
    int speeds[] = new int[3];

    double adjMatrix[][];

    void parseGraphFile(String fileName) throws Exception {
        Scanner scanner = new Scanner(new File(fileName));

        if (!scanner.hasNextInt()) {
            return;
        }

        numVertices = scanner.nextInt();
        adjMatrix = new double[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                adjMatrix[i][j] = 1000000000;
            }
        }
        
        for(int i = 0; i < numVertices; i++) {
            adjMatrix[i][i] = 0;
        }

        int numEdges = scanner.nextInt();
        while (numEdges > 0) {
            numEdges--;
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            double length = scanner.nextDouble() * 1000;
            adjMatrix[a][b] = length;
        }
    }

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
    CompetitionFloydWarshall (String fileName, int sA, int sB, int sC) {
        try {
            speeds[0] = sA;
            speeds[1] = sB;
            speeds[2] = sC;
            parseGraphFile(fileName);
        } catch(Exception e) {
            e.printStackTrace();
        };
    }

    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition() {
        if (numVertices == 0 || Math.min(speeds[0], Math.min(speeds[1], speeds[2])) < 50 || Math.max(speeds[0], Math.max(speeds[1], speeds[2])) > 100) {
            return -1;
        }

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                for (int k = 0; k < numVertices; k++) {
                    if (adjMatrix[j][k] > adjMatrix[j][i] + adjMatrix[i][k]) {
                        adjMatrix[j][k] = adjMatrix[j][i] + adjMatrix[i][k];
                    }
                }
            }
        }
        double answer = -1;
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                answer = Math.max(answer, adjMatrix[i][j]);
                if (adjMatrix[i][j] >= 1000000000) {
                    return -1;
                }
            }
        }
        answer /= (double)Math.min(speeds[0], Math.min(speeds[1], speeds[2]));
        return (int)Math.ceil(answer);
    }
}