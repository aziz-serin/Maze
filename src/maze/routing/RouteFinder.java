package maze.routing;

/**
* Class providing an algorithm to solve the given mazes. 
* @author Aziz Serin
* @version 1.0, 29th April 2021
* @serial object.
* @see java.nio.file.Path 
*/


import java.util.*;
import java.io.*;
import maze.*;

public class RouteFinder implements Serializable {

	private Maze maze;
	private List<Integer> route = new ArrayList<Integer>();
	private PriorityQueue<Tile> closedList = new PriorityQueue<>();
	private PriorityQueue<Tile> openList = new PriorityQueue<>();

	/**
  	* Constructor of the RouteFinder object.
  	* @param maze is the maze to generate the RouteFinder for.
  	*/

	public RouteFinder(Maze maze){
		this.maze = maze;
	}
	/**
  	* A method to get the maze for the RouteFinder.
  	* @return returns the maze.
  	*/
	public Maze getMaze(){  
		return maze;
	}

//	public Stack<Tile> getStack(){
//		return route;
//	}
//	/**
//  	* A method to get the route for the RouteFinder.
//  	* @return returns the route as a List of tiles.
//  	*/
//	public List<Tile> getRoute(){
//		return route;
//	}
//	/**
//  	* A method to step through the maze.
//  	* @return true if the maze is solved, false otherwise.
//  	* @throws NoRouteFoundException if the exit cannot be accessed.
//  	*/

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

//		return ids;
//		for(int id : ids){
//			System.out.print(id + " ");
//		}
//		System.out.println("");
	}

	/**
	* A method to get the string representation of the solved/partially solved maze.
	* @return a String which represents the maze status..
	*/
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
	/**
	* A method serialize out the RouteFinder object into a file.
	* @param fileName is the path to save the file at the working directory.
	*/
//	public void save(String fileName){
//
//      try(FileOutputStream fos = new FileOutputStream(fileName)){
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        RouteFinder objectToWrite = new RouteFinder(getMaze());
//        objectToWrite.route = this.route;
//        objectToWrite.maze = this.maze;
//        objectToWrite.finished = this.finished;
//        oos.writeObject(objectToWrite);
//
//        oos.flush();
//        oos.close();
//        fos.close();
//      }
//      catch(IOException e){
//        e.printStackTrace();
//      }
//	}
//	/**
//	* A method serialize in the RouteFinder object into a file.
//	* @param fileName is the path to load the file from the working directory.
//	* @exception IOException if the file is not found/cannot be accessed.
//	* @exception ClassNotFoundException if the classes are not found.
//	*/
//	public static RouteFinder load(String fileName){
//
//		try(FileInputStream fis = new FileInputStream(fileName)){
//        ObjectInputStream ois = new ObjectInputStream(fis);
//
//        RouteFinder routeToReturn = (RouteFinder) ois.readObject();
//
//        ois.close();
//        fis.close();
//        return routeToReturn;
//
//  	}catch(IOException e){
//      e.printStackTrace();
//      return null;
//    }catch(ClassNotFoundException e){
//      e.printStackTrace();
//      return null;
//    }
// }
	
}































