package maze;

/**
* Class generating tiles from different characters and provides 
* useful methods for their management, also implements serialization.
* @author Aziz Serin
* @version 1.0, 29th April 2021
* @serial object.
* @see java.nio.file.Path 
*/

import java.util.*;
import java.io.*;

public class Tile implements Serializable, Comparable<Tile>{

  public enum Type{
    CORRIDOR,
    ENTRANCE,
    EXIT,
    WALL
  }

  private Type type;
  private static int idCounter = 0;
  public int id;
  public Tile parent = null;
  public List <Edge> neighbors;
  public double f = 0;
  public double g = 0;
  public double h;

  /**
  * Constructor for tile object with specified type.
  * @param type the type to generate the tile according to its type. 
  */
  private Tile(Type type, double h){
    this.type = type;
    this.h = h;
    this.id = idCounter++;
    this.neighbors = new ArrayList<>();
  }

  /**
  * Gets the char as a parameter when called and generates the tile from it. 
  * @param c is the character representing the tile.
  * @return Returns the appropriate tile which is generated from the charc c. 
  */

  protected static Tile fromChar(char c, double h){

    switch(c){
      case 'e': Tile tile1 = new Tile(Type.ENTRANCE, h);
      return tile1;
      case 'x': Tile tile2 = new Tile(Type.EXIT, h);
      return tile2;
      case '#': Tile tile3 = new Tile(Type.WALL, h);
      return tile3;
      case '.': Tile tile4 = new Tile(Type.CORRIDOR, h);
      return tile4;
      default: return null;  
    }
  }

  @Override
  public int compareTo(Tile t){
    return Double.compare(this.f, t.f);
  }

  public static class Edge{

    public int weight;
    public Tile tile;

    Edge(int weight, Tile t){
      this.weight = weight;
      this.tile = t;
    }
  }

  public void addBranch(int weight, Tile t){
    Edge newEdge = new Edge(weight, t);
    neighbors.add(newEdge);
  }

  public double calculateHeuristic(Tile t){
    return this.h;
  }

  /**
  * Returns the type of the tile which exists.
  * @return Returns the type of the tile. 
  */

  public Type getType(){
    return this.type;
  }

  /**
  * Returns a boolean value indicating whether a tile is navigable or not.
  * @return Returns a boolean value about navigability of the tile. 
  */

  public boolean isNavigable(){
    if(type.equals(Type.CORRIDOR) || type.equals(Type.ENTRANCE) || type.equals(Type.EXIT)){
      return true;
    }
    else{
      return false;
    }
  }

  /**
  * Gets the type of the tile and returns the appropriate string..
  * @return Returns the string representation of the tile. 
  */

  public String toString(){
    switch(getType()){
      case CORRIDOR: return ".";
      case ENTRANCE: return "e";
      case EXIT: return "x";
      case WALL: return "#";
      default: return null;
    }
  }
}





















