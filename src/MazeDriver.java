import maze.*;
import maze.routing.*;
import java.util.concurrent.TimeUnit;

public class MazeDriver {
  public static void main(String args[]) {

    Maze maze = null;  

  	try{
  		maze = Maze.fromTxt("../resources/mazes/maze3.txt");
  	}
  	catch(InvalidMazeException e){
  	  e.printStackTrace();
  	}

    System.out.println(maze.toString());

    try{

      RouteFinder route = new RouteFinder(maze);
      long startTime = System.nanoTime();
      Tile t = route.aStar(maze.getEntrance(), maze.getExit());
      long endTime = System.nanoTime();
      route.getPath(t);
			System.out.println(route.toString());
      System.out.println(endTime - startTime);

    }catch(NoRouteFoundException e){
      e.printStackTrace();
    }
  } 
}
