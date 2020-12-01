// author: Oscar O'Neill, Student no. 17330989

/*
Question 4

1.
Dijkstra:
I used an adjacency list. It uses less memory than an adjacency matrix, and provides instant access to
edges coming from any given node. The array (ArrayList) of edges coming from node u can be found at
adjList[u]. Being able to iterate over neighbors of any given node is important for Dijkstra's algorithm.

Floyd-Warshall:
I used an adjacency matrix. This costs more memory than an adjacency list, however it offers fast (O(1))
access to edge weights. In order to get the edge weight of an edge going from u to v, one must simply
index the matrix at adjMatrix[u][v]. Fast access to edge weights is important for the algorithm, and
so I felt an adjacency matrix was appropriate.

2.
a)
Dijkstra's algorithm is O(N*log(N)) where N is the number of vertices in the graph. In this problem I
ran the algorithm from every node in the graph so as to find the longest of the shortest paths
between any 2 nodes. This increases the complexity of my algorithm to O(N^2*log(N)). For 1000 vertices
this is acceptable.

Floyd-Warshall's algorithm is O(N^3) where N is the number of vertices in the graph. Being that
Floyd-Warshall's is an all pairs shortest path algorithm, I needed to only run the algorithm once
and had access to all shortest paths (from which I found the longest). I then had to check if the graph
was connected by using a O(N^2) nested for-loop. This makes my algorithm O(N^3 + N^2) = O(N^3). This
would be acceptable for smaller values of N (say around 200 vertices), as is always the case with
Floyd-Warshall's algorithm.

b)
For graphs with small number of vertices, Floyd-Warshall's algorithm is okay to use. However beyond that
one should use Dijskra's algorithm, as it's complexity O(N^2 * log(N)) is better than O(N^3). For denser
graphs (that being graphs which contain a large number of edges) Floyd-Warshall's algorithm's runtime is
not affected, as its runtime is solely dependent on the number of vertices. However with Dijkstra's
algorithm, the more edges in the graph, the longer the runtime. This is because when a node "u" is
being processed in Dijkstra's, we iterate over each neighbor of u (the adjList of u) in order the push
more nodes into the priority queue. This would make Floyd-Warshall's algorithm more attractive for
denser graphs provided the number of nodes is still quite small.
*/

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.*;

public class CompetitionTests {
    @Test
    public void testDijkstraConstructor() {
    	CompetitionDijkstra dijkstra = new CompetitionDijkstra("tinyEWD.txt", 50, 50, 50);
    }

    @Test
    public void testFWConstructor() {
    	CompetitionFloydWarshall floydwarshall = new CompetitionFloydWarshall("tinyEWD.txt", 50, 50, 50);
    }    
    
    @Test
    public void testDijkstra() {
    	CompetitionDijkstra dijkstra = new CompetitionDijkstra(null, 63, 77, 95);
    	int time = dijkstra.timeRequiredforCompetition();
    	assertEquals("Dijsktra with null input", -1, time);
    	
    	dijkstra = new CompetitionDijkstra("input-J.txt", 60, 75, 61);
    	time = dijkstra.timeRequiredforCompetition();
    	assertEquals("Dijsktra with input-J", -1, time);
    	
    	dijkstra = new CompetitionDijkstra("input-K.txt", 51, 70, 88);
    	time = dijkstra.timeRequiredforCompetition();
    	assertEquals("Dijsktra with input-K", 314, time);
    	
    	dijkstra = new CompetitionDijkstra("input-L.txt", 63, 77, 95);
    	time = dijkstra.timeRequiredforCompetition();
    	assertEquals("Dijsktra with input-L", 127, time);

    	dijkstra = new CompetitionDijkstra("input-D.txt", 50, 80, 60);
    	time = dijkstra.timeRequiredforCompetition();
    	assertEquals("Dijkstra with input-D", 38, time);
    }
    
    @Test
    public void testFW() {
    	CompetitionFloydWarshall floydwarshall = new CompetitionFloydWarshall(null, 63, 77, 95);
    	int time = floydwarshall.timeRequiredforCompetition();
    	assertEquals("FW with null input", -1, time);
    	
    	floydwarshall = new CompetitionFloydWarshall("input-J.txt", 60, 75, 61);
    	time = floydwarshall.timeRequiredforCompetition();
    	assertEquals("FW with input-J", -1, time);

    	floydwarshall = new CompetitionFloydWarshall("input-K.txt", 51, 70, 88);
    	time = floydwarshall.timeRequiredforCompetition();
    	assertEquals("FW with input-K", 314, time);

    	floydwarshall = new CompetitionFloydWarshall("input-L.txt", 63, 77, 95);
    	time = floydwarshall.timeRequiredforCompetition();
    	assertEquals("FW with input-L", 127, time);

    	floydwarshall = new CompetitionFloydWarshall("input-D.txt", 50, 80, 60);
    	time = floydwarshall.timeRequiredforCompetition();
    	assertEquals("FW with input-D", 38, time);
    }
}