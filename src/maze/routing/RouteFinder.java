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
	private Stack<Tile> route;
	private boolean finished;

	/**
  	* Constructor of the RouteFinder object.
  	* @param maze is the maze to generate the RouteFinder for.
  	*/

	public RouteFinder(Maze maze){
		this.maze = maze;
		this.route = new Stack<Tile>();
	}
	/**
  	* A method to get the maze for the RouteFinder.
  	* @return returns the maze.
  	*/
	public Maze getMaze(){  
		return maze;
	}

	public Stack<Tile> getStack(){
		return route; 
	}
	/**
  	* A method to get the route for the RouteFinder.
  	* @return returns the route as a List of tiles.
  	*/
	public List<Tile> getRoute(){
		return route; 
	}
	/**
  	* A method to step through the maze.
  	* @return true if the maze is solved, false otherwise.
  	* @throws NoRouteFoundException if the exit cannot be accessed.
  	*/
	public boolean step() throws NoRouteFoundException{

		int lengthX = maze.getTiles().get(0).size() - 1;
		int lengthY = maze.getTiles().size() - 1;

		if(route.empty()){
			route.push(maze.getEntrance());
		}

		else if(route.peek().equals(maze.getExit())){
			;
		}

		else{
			// left-up corner
			if(maze.getTileLocation(route.peek()).getX() == 0 && maze.getTileLocation(route.peek()).getY() == lengthY){
				if(maze.getAdjacentTile(route.peek(), Maze.Direction.SOUTH).isNavigable()&&
					(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.SOUTH)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.SOUTH));
				}
				else if(maze.getAdjacentTile(route.peek(), Maze.Direction.EAST).isNavigable()&&
					(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.EAST)) != 2)){
					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.EAST));
				}
			}
			// right-up corner
			else if(maze.getTileLocation(route.peek()).getX() == lengthX && maze.getTileLocation(route.peek()).getY() == lengthY){
				if(maze.getAdjacentTile(route.peek(), Maze.Direction.SOUTH).isNavigable()&&
					(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.SOUTH)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.SOUTH));
				}
			else if(maze.getAdjacentTile(route.peek(), Maze.Direction.WEST).isNavigable()&&
				(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.WEST)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.WEST));
				}	
			}
			// left-down corner
			else if(maze.getTileLocation(route.peek()).getX() == 0 && maze.getTileLocation(route.peek()).getY() == 0){
				if(maze.getAdjacentTile(route.peek(), Maze.Direction.EAST).isNavigable()&&
					(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.EAST)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.EAST));
				}
				else if(maze.getAdjacentTile(route.peek(), Maze.Direction.NORTH).isNavigable()&&
					(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.NORTH)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.NORTH));
				}
			}
			// right-down corner
			else if(maze.getTileLocation(route.peek()).getX() == lengthX && maze.getTileLocation(route.peek()).getY() == 0){
				if(maze.getAdjacentTile(route.peek(), Maze.Direction.WEST).isNavigable()&&
					(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.WEST)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.WEST));
				}
				else if(maze.getAdjacentTile(route.peek(), Maze.Direction.NORTH).isNavigable()&&
					(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.NORTH)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.NORTH));
				}
			}
			//at top
			else if(maze.getTileLocation(route.peek()).getY() == lengthY){
				if(maze.getAdjacentTile(route.peek(), Maze.Direction.SOUTH).isNavigable()&&
					(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.SOUTH)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.SOUTH));
				}
				else if(maze.getAdjacentTile(route.peek(), Maze.Direction.EAST).isNavigable()&&
					(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.EAST)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.EAST));
				}
				else if(maze.getAdjacentTile(route.peek(), Maze.Direction.WEST).isNavigable()&&
					(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.WEST)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.WEST));
			}
		}
			// at bottom
			else if(maze.getTileLocation(route.peek()).getY() == 0){
				if(maze.getAdjacentTile(route.peek(), Maze.Direction.EAST).isNavigable() && 
						(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.EAST)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.EAST));
				}
				else if(maze.getAdjacentTile(route.peek(), Maze.Direction.WEST).isNavigable() && 
						(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.WEST)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.WEST));
				}
				else if(maze.getAdjacentTile(route.peek(), Maze.Direction.NORTH).isNavigable() && 
						(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.NORTH)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.NORTH));
				}
			}
			// at left
			else if(maze.getTileLocation(route.peek()).getX() == 0){
				if(maze.getAdjacentTile(route.peek(), Maze.Direction.SOUTH).isNavigable()&&
					(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.SOUTH)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.SOUTH));
				}
				else if(maze.getAdjacentTile(route.peek(), Maze.Direction.EAST).isNavigable()&&
					(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.EAST)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.EAST));
				}
				else if(maze.getAdjacentTile(route.peek(), Maze.Direction.NORTH).isNavigable()&&
					(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.NORTH)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.NORTH));
				}
			}
			// at right
			else if(maze.getTileLocation(route.peek()).getX() == lengthX){
				if(maze.getAdjacentTile(route.peek(), Maze.Direction.SOUTH).isNavigable()&&
					(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.SOUTH)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.SOUTH));
				}
				else if(maze.getAdjacentTile(route.peek(), Maze.Direction.WEST).isNavigable()&&
					(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.WEST)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.WEST));
				}
				else if(maze.getAdjacentTile(route.peek(), Maze.Direction.NORTH).isNavigable()&&
					(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.NORTH)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.NORTH));
				}
			}
			// in the middle
			else{
				if(maze.getAdjacentTile(route.peek(), Maze.Direction.SOUTH).isNavigable() && 
					(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.SOUTH)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.SOUTH));
				}
				else if(maze.getAdjacentTile(route.peek(), Maze.Direction.NORTH).isNavigable()&& 
						(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.NORTH)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.NORTH));
				}
				else if(maze.getAdjacentTile(route.peek(), Maze.Direction.EAST).isNavigable()&& 
						(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.EAST)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.EAST));
				}
				else if(maze.getAdjacentTile(route.peek(), Maze.Direction.WEST).isNavigable()&& 
						(route.search(maze.getAdjacentTile(route.peek(), Maze.Direction.WEST)) != 2)){

					route.push(maze.getAdjacentTile(route.peek(), Maze.Direction.WEST));
				}
				else{

					route.pop();
				}
			}
		}

		return isFinished();
	}
	/**
	* A method to see if the maze is finished or not.
	* @return a boolean value true if the maze is solved, false otherwise.
	*/
	public boolean isFinished(){
		if(!route.empty()){
			if(route.peek().equals(maze.getExit())){
				return finished = true;
			}
			else{
				return false;
			}
		}
		else{
			return finished = false;
		}
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
    List<Tile> route = getRoute();

    for(List<Tile> list : allTiles){
      for(Tile t : list){
      	if(route.contains(t)){
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
	public void save(String fileName){

      try(FileOutputStream fos = new FileOutputStream(fileName)){
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        RouteFinder objectToWrite = new RouteFinder(getMaze());
        objectToWrite.route = this.route;
        objectToWrite.maze = this.maze;
        objectToWrite.finished = this.finished;
        oos.writeObject(objectToWrite);

        oos.flush();
        oos.close();
        fos.close();
      }
      catch(IOException e){
        e.printStackTrace();
      }
	}
	/**
	* A method serialize in the RouteFinder object into a file.
	* @param fileName is the path to load the file from the working directory.
	* @exception IOException if the file is not found/cannot be accessed.
	* @exception ClassNotFoundException if the classes are not found.
	*/
	public static RouteFinder load(String fileName){

		try(FileInputStream fis = new FileInputStream(fileName)){
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        RouteFinder routeToReturn = (RouteFinder) ois.readObject();

        ois.close();
        fis.close();
        return routeToReturn;

  	}catch(IOException e){
      e.printStackTrace();
      return null;
    }catch(ClassNotFoundException e){
      e.printStackTrace();
      return null;
    }
	}
	
}































