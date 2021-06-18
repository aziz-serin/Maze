import maze.*;
import maze.routing.*; 

public class MazeDriver {
  public static void main(String args[]) {

    Maze maze = null;  

  	try{
  		maze = Maze.fromTxt("../resources/mazes/invalid/invalidChar.txt");
  	}
  	catch(InvalidMazeException e){
  	  e.printStackTrace();
  	}

    System.out.println(maze.toString());

    try{
      RouteFinder route = new RouteFinder(maze);

    for(int i = 0; i <12; ++i){
      route.step();
      System.out.print(route.getRoute());
      System.out.println(maze.getTileLocation(route.getStack().peek()));
    }
    System.out.println(route.toString());

    // route.save("resources/mazes/maze1.obj");
    // RouteFinder routeTest = RouteFinder.load("resources/mazes/maze1.obj");

    // for(int i = 0; i <12; ++i){
    //   routeTest.step();
    //   System.out.print(routeTest.getRoute());
    //   System.out.println(maze.getTileLocation(routeTest.getStack().peek()));
    // }
    // System.out.println(routeTest.toString());

    }catch(NoRouteFoundException e){
      e.printStackTrace();
    }
  } 
}
