import maze.*;
import maze.routing.*; 

public class MazeDriver {
  public static void main(String args[]) {

    Maze maze = null;  

  	try{
  		maze = Maze.fromTxt("../resources/mazes/maze2.txt");
  	}
  	catch(InvalidMazeException e){
  	  e.printStackTrace();
  	}

    System.out.println(maze.toString());

    try{

      RouteFinder route = new RouteFinder(maze);
      Tile t = route.aStar(maze.getEntrance(), maze.getExit());
      route.getPath(t);
			System.out.println(route.toString());

    }catch(NoRouteFoundException e){
      e.printStackTrace();
    }
  } 
}
