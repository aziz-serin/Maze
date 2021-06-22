package maze;

// Class generating maze from text files and provides its methods.
// @author Aziz Serin

import java.util.*;
import java.io.*;

public class Maze{

  // Directions that can be used to navigate in the maze.

  public enum Direction{
    NORTH,
    SOUTH,
    EAST,
    WEST
  } 

  private Tile entrance;
  private Tile exit;
  private List<List<Tile>> tiles;
  private static int lineNumber;


  // An empty constructor for the maze object. It's private to ensure that
  // maze object cannot be instantiated without a text file.


  private Maze(){}


//  Actual constructor method for the maze object.
//  @param input path to the .txt file in the working directory.
//  @return Maze from the given .txt file.
//  @throws InvalidMazeException if the maze is not proper.
//  @throws IOException if there is an error reading/accessing the .txt file.

  public static Maze fromTxt(String input) 
  throws InvalidMazeException{

    List<List<Tile>> tilesList;
    int numberOfLines = getLineNumber(input);
    tilesList = new ArrayList<>(numberOfLines);
    Maze maze = new Maze();
    maze.lineNumber = numberOfLines;
    int entranceError = 0; int exitError = 0;
    int exitX =  getExitLine(input)[0];
    int exitY = numberOfLines - getExitLine(input)[1];

    try(FileReader fReader = new FileReader(input)){

      BufferedReader bReader = new BufferedReader(fReader);

      String line = bReader.readLine();
      int rowTracker = 0;
      int ragged = line.length();

      for(int i = 0; i < numberOfLines; ++i) {
            tilesList.add(new ArrayList<>(line.length()));
      }

      while(line != null){
        int tileTracker = 0;
        for(int i = 0; i< line.length(); i++){
            if(line.charAt(i) == 'e' || line.charAt(i) =='x' || line.charAt(i) == '#' || line.charAt(i) == '.'){
              int h = Math.abs(i-exitX) + Math.abs(rowTracker - exitY); // Heuristic(h) is Manhattan Distance, since we can only move in four directions.
              Tile t = Tile.fromChar(line.charAt(i), h);
              if(line.charAt(i) == 'e'){
                maze.entrance = t;
                entranceError++;
              }
              else if(line.charAt(i) == 'x'){
                maze.exit = t;
                exitError++;
              }
              tilesList.get(rowTracker).add(tileTracker, t);
              tileTracker++;
            }
            else{
              throw new InvalidMazeException("Unsupported character for maze found in .txt file, Invalid Maze!");
            }
        }
        rowTracker++;
        if(line.length() != ragged){
          throw new RaggedMazeException("Maze is Ragged, Invalid Maze!");
        }
        line = bReader.readLine();
      }
      fReader.close();
      bReader.close();
      // Uses custom exceptions classes
      if(exitError > 1){
        throw new MultipleExitException("More than one entrance is detected, Invalid Maze!");
      }
      else if(exitError == 0){
        throw new NoExitException("No exit detected, Invalid Maze!");
      }
      else if(entranceError > 1){
        throw new MultipleEntranceException("More than one entrance detected, Invalid Maze!");
      }
      else if(entranceError == 0){
        throw new NoEntranceException("No entrance detected, Invalid Maze!");
      }

    }
    catch(IOException error){
      error.printStackTrace();
    }

    List<Direction> dir = new ArrayList<Direction>();
    dir.add(Direction.NORTH);
    dir.add(Direction.EAST);
    dir.add(Direction.WEST);
    dir.add(Direction.SOUTH);
    maze.tiles = tilesList;

    int currentLine = 0;
    Coordinate coordExit = maze.getTileLocation(maze.exit);
    for (List<Tile> tiles : maze.tiles){
      for(Tile t : tiles) {
        if (!t.toString().equals("#")) {
          for (Direction d : dir) {
            Tile check = maze.getAdjacentTile(t, d);
            if (!check.toString().equals("#")) {
              t.addBranch(1, check); //Adding branchest to every corridor, and g value is 1 since cost of a move is constant
            }
          }
        }
      }
      currentLine++;
    }
    return maze;

  }
  // Get the x and y location of exit tile to calculate the Heuristic function.
  public static int[] getExitLine(String input){
    int exitX = 0;
    int exitY = 0;
    int lineN = 0;
    try(BufferedReader reader = new BufferedReader(new FileReader(input))){
      String line = reader.readLine();
      while (reader.readLine() != null){
        for(int i = 0; i< line.length(); i++) {
          if (line.charAt(i) == 'x') {
            exitX = i;
            exitY = lineN;
          }
        }
        lineN += 1;
        line = reader.readLine();
      }
      exitY = lineN - exitY;
      reader.close();
    }
    catch(IOException e){e.printStackTrace();}
    int[] list = new int[2];
    list[0] = exitX;
    list[1] = exitY;
    return list;
  }


  // Returns the number of lines in the file.

  public static int getLineNumber(String input){

    int lines = 0;

    try(BufferedReader reader = new BufferedReader(new FileReader(input))){
      while (reader.readLine() != null) lines++;
      reader.close();
    }
    catch(IOException e){e.printStackTrace();}

    return lines;
  }

  // Sets the entrance tile in the maze.

  private void setEntrance(Tile tile) throws InvalidMazeException{
    boolean tileNotInMaze = true;

    for(List<Tile> list : tiles){
      if(list.contains(tile)){
        tileNotInMaze = false;
        break;
      }
    }

    if(entrance != null){
      throw new MultipleEntranceException("One entrance already exists!");
    }
    else if (!tileNotInMaze){
      this.entrance = tile;
    }
  }

  // Sets the exit tile in the maze.

  private void setExit(Tile tile) throws InvalidMazeException{
    boolean tileNotInMaze = true;

    for(List<Tile> list : tiles){
      if(list.contains(tile)){
        tileNotInMaze = false;
        break;
      }
    }

    if(exit != null){
      throw new MultipleExitException("One exit already exists!");
    }
    else if(!tileNotInMaze){
      this.exit = tile;
    }
  }


  public Tile getEntrance(){
    return this.entrance;
  }

  public Tile getExit(){
    return this.exit;
  }

  public List<List<Tile>> getTiles(){
    return this.tiles;
  }

  // Returns the string representation of the maze.


  public String toString(){
    int a, b;
    int index = 1;
    a = this.tiles.size();
    b = this.tiles.get(0).size();

    String[] stringList = new String[(a*b)+a+1];
    stringList[0] = "\n";
    String string = "";

    for(List<Tile> list : this.tiles){
      for(Tile t : list){
          stringList[index] = t.toString();
          index++;
      }
      stringList[index] = "\n";
      index++;
    }

    for(String s : stringList){
      string = string + s;
    }
    return string;
  }

  // Gets the adjacent tile with given tile and direction.

  public Tile getAdjacentTile(Tile tile, Direction direction){

    Coordinate coordinate = getTileLocation(tile);
    int x = coordinate.getX();
    int y = tiles.size() - 1 - coordinate.getY();

    Tile t;

    switch(direction){
      case NORTH: t = tiles.get(y - 1).get(x);
      break;
      case SOUTH: t = tiles.get(y + 1).get(x);
      break;
      case EAST:  t = tiles.get(y).get(x + 1);
      break;
      case WEST:  t = tiles.get(y).get(x - 1);
      break;
      default: t = null; 
    }
    return t;
  }

  // Gets the tile at a specific coordinate.

  public Tile getTileAtLocation(Coordinate coordinate){
    int x, y;
    x = coordinate.x;
    y = coordinate.y;
    return tiles.get(tiles.size() - 1 - y).get(x);
  }

  // Gets the coordinate for a specific tile.

  public Coordinate getTileLocation(Tile tile){

    int tileX = 0; int tileY = 0;

    for (List list: tiles){
      tileY = tiles.indexOf(list);
      if(list.contains(tile)){
        tileX = list.indexOf(tile); 
        break;
      }
    } 
    Coordinate coordinate = new Coordinate(tileX, tiles.size() - 1 - tileY);
    return coordinate;
  }


  // Helper class in the maze to represent the
  // coordinates of the tiles in the maze.

  public class Coordinate{

    private int x;
    private int y;

    public Coordinate(int x, int y){
      this.x = x;
      this.y = y;
    }

    public int getX(){
      return this.x;
    }

    public int getY(){
      return this.y;
    }

    // Generates a string representation of the coordinate object.

    public String toString(){
      return "(" + String.valueOf(getX()) + ", " + String.valueOf(getY()) + ")";
    }
  }

}
