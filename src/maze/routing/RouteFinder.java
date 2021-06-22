package maze.routing;

 //Class providing an algorithm to solve the given mazes (A*).
// @author Aziz Serin

import java.util.*;
import java.io.*;
import maze.*;

public class RouteFinder{

	private Maze maze;
	private List<Integer> route = new ArrayList<Integer>();
	private PriorityQueue<Tile> closedList = new PriorityQueue<>();
	private PriorityQueue<Tile> openList = new PriorityQueue<>();

	// Constructor of the RouteFinder object.


	public RouteFinder(Maze maze){
		this.maze = maze;
	}

	// A method to get the maze for the RouteFinder.

	public Maze getMaze(){  
		return maze;
	}

	// A* algorithm which solves the given mazes.

	public Tile aStar(Tile start, Tile exit) throws NoRouteFoundException{

		start.f = start.g + start.calculateHeuristic(exit);
		openList.add(start);

		while(!openList.isEmpty()){
			Tile t = openList.peek();

			if(t == exit){
				return t;
			}

			for (Tile.Edge edge : t.neighbors){
				Tile x = edge.tile;
				double totalWeight = t.g + edge.weight;
				if(!openList.contains(x) && !closedList.contains(x)){
					x.parent = t;
					x.g = totalWeight;
					x.f = x.g + x.calculateHeuristic(exit);
					openList.add(x);
				}
				else{
					if(totalWeight < x.g){
						x.parent = t;
						x.g = totalWeight;
						x.f = x.g + x.calculateHeuristic(exit);
						if(closedList.contains(x)){
							closedList.remove(x);
							openList.add(x);
						}
					}
				}
			}
			openList.remove(t);
			closedList.add(t);
		}
		return null;
	}

	// Gets the path which A* algorithm finds

	public void getPath(Tile t){
		Tile tile = t;

		if(tile==null)
			return;

		List<Integer> ids = new ArrayList<>();

		while(tile.parent != null){
			ids.add(tile.id);
			tile = tile.parent;
		}
		ids.add(tile.id);
		Collections.reverse(ids);

		this.route = ids;
	}


	// A method to get the string representation of the solved maze.

	public String toString(){
		List<List<Tile>> allTiles = maze.getTiles();

		int a, b;
    int index = 1;
    a = allTiles.size();
    b = allTiles.get(0).size();


    String[] stringList = new String[(a*b)+a+1];
    stringList[0] = "\n";
    String string = "";

    for(List<Tile> list : allTiles){
      for(Tile t : list){
      	if(route.contains(t.id)){
      		stringList[index] = "+";
      		index++;
      	}
      	else{
      	stringList[index] = t.toString();
        index++;
      	}
      }
      stringList[index] = "\n";
      index++;
    }

    for(String s : stringList){
      string = string + s;
    }
    return string;
	}
}































