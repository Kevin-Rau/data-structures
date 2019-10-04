  
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Deque;
import java.util.ArrayDeque;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

class FestivalOfGraphs
{
	private Graph g;
	
	public void init(String graphFile) 
	{
		System.out.println(graphFile);
		// open the file
		BufferedReader br = null;
		
		try
		{
			// open the file called "graph.txt"
			br = new BufferedReader(new FileReader(graphFile));
		
			// read the first line
			String line = br.readLine();	
			
			// convert the first line (a string) to an integer (numberOfVertices)
			Integer n = Integer.parseInt(line);
			
			g = new Graph(n);
		
			for (int fromVertex = 0; fromVertex < n; ++fromVertex)
			{
				line = br.readLine();
				if (line.length() > 0)
				{
					int pos = 0;
					while (line.indexOf("{", pos) > -1)
					{
						String sub = line.substring(line.indexOf("{", pos) + 1, line.indexOf("}", pos + 1));
						Integer toVertex = Integer.parseInt(sub.substring(0, sub.indexOf(",")));
						Integer weightOfEdge = Integer.parseInt(sub.substring(sub.indexOf(",") + 1, sub.length()));
						pos = line.indexOf("}", pos + 1);
						if (!g.hasEdge(fromVertex, toVertex))
							g.addEdge(fromVertex, toVertex, weightOfEdge);
					}	
				}	
			}
		}
		catch (FileNotFoundException ex) {
			// handle FileNotFoundException
			System.out.println("No such file as " + graphFile + " in your directory");
		}
		catch (IOException ex) {
			// handle IOException
			System.out.println("Ran into problems processing " + graphFile);
		}
		finally {
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException ex) {
					// handle IOException
					System.out.println("Ran into unrecoverable problems processing " + graphFile);
				}
			}
		}
		
	}
	
	public List<Integer> DepthFirstList(Integer v)
	{
		List<Integer> vertices = new ArrayList<Integer>();
		Deque<Integer> toExplore = new ArrayDeque<Integer>();
		List<Integer> visited = new ArrayList<Integer>();
		//using Carranos guide in every function
		toExplore.push(v);
		visited.add(v);
		//starting at every v and marking 
		while(toExplore.isEmpty() != true)
		{
			int u = toExplore.removeFirst();
			vertices.add(u);
		//setting a u variable and creating a vertices arrayList
			for (Integer neighbor: g.getAdjList(u).keySet())
			{
				if (visited.contains(neighbor) == false)
				{
					//checking if there is already a visited node if not set toExplore and mark
					toExplore.push(neighbor); 
					visited.add(neighbor);
				}
			}	
		}
		return vertices;
	}
	
	public List<Integer> BreadthFirstList(Integer v) 
	{
		List<Integer> vertices = new ArrayList<Integer>();
		Deque<Integer> toExplore = new ArrayDeque<Integer>();
		List<Integer> visited = new ArrayList<Integer>();
		//same starting logic as the top of the code
		toExplore.push(v);
		visited.add(v);

		while(toExplore.isEmpty() != true)
		{ 
			//same logic as the top but creating a queue rather than a stack, hence removeLast();
			int u = toExplore.removeLast();
			vertices.add(u);

			for (Integer neighbor: g.getAdjList(u).keySet())
			{
				if (visited.contains(neighbor) == false)
				{
					//checking if there is a node we already have visited 
					toExplore.push(neighbor); 
					visited.add(neighbor);
				}
			}	
		}
		return vertices;
	}

	
	public Graph DepthFirstSpanningTree(Integer v) 
	{
		Graph t = new Graph(g.getNumVertices()); 
		Deque<Integer> toExplore = new ArrayDeque<Integer>();
		List<Integer> visited = new ArrayList<Integer>();

		
		toExplore.push(v);
		visited.add(v);
		
		int track = 0;
		int w = 0;
		//same logic as the top but with the exception of keeping more track of our builing 
		while(toExplore.isEmpty() != true)
		{
			Integer u = toExplore.removeFirst();
			//this conditional builds our span, by checking out counting variable that increments 
			if (track != 0)
			{
				int back = 1;

				while (!g.hasEdge(w,u))
				{
					//keeps track of how many more elements are left and when we need to stop
					w = visited.get(visited.size() - back);
					back++;
				}

				t.addEdge(w, u, g.getEdgeWeight(w, u));
			}
			w = u;
			//words just like traversal to make sure we arent repeating any node.
			for (Integer neighbor: g.getAdjList(u).keySet())
			{
				if (visited.contains(neighbor) == false)
				{
					toExplore.push(neighbor); 
					visited.add(neighbor);
				}
			}	
			track++;
		}	
		return t;
	}

	public Graph BreadthFirstSpanningTree(Integer v) 
	{
		Graph t = new Graph(g.getNumVertices()); 
		Deque<Integer> toExplore = new ArrayDeque<Integer>();
		List<Integer> visited = new ArrayList<Integer>();

		//since this is a lot easier to use as a function just use same logic as the search traversal
		toExplore.push(v);
		visited.add(v);

		while(toExplore.isEmpty() != true)
		{
			int u = toExplore.removeFirst();

			for (Integer neighbor: g.getAdjList(u).keySet())
			{
				
				if (visited.contains(neighbor) == false)
				{
					//while we check our visited, build our tree
					toExplore.push(neighbor); 
					visited.add(neighbor);
					t.addEdge(u, neighbor, g.getEdgeWeight(u,neighbor));
				}
			}	
		}
		return t;
	}

	List<String> DijkstrasShortestPath(Integer t)
	{
		List<Integer> initialized = new ArrayList<Integer>();
		List<Integer> pathWeights = new ArrayList<Integer>();
		List<String> prettyWeights = new ArrayList<String>();
		
		Set<Integer> vertexSet = new HashSet<Integer>();
		
		return prettyWeights;
	}
	
	public Graph PrimsAlgorithm(Integer v) 
	{
		Graph t = new Graph(g.getNumVertices()); 
		return t;
	}
	
	
}

