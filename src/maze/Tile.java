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

public class Tile implements Serializable{

  public enum Type{
    CORRIDOR,
    ENTRANCE,
    EXIT,
    WALL
  }

  private Type type;


  /**
  * Constructor for tile object with specified type.
  * @param type the type to generate the tile according to its type. 
  */
  private Tile(Type type){
    this.type = type; 
  }

  /**
  * Gets the char as a parameter when called and generates the tile from it. 
  * @param c is the character representing the tile.
  * @return Returns the appropriate tile which is generated from the charc c. 
  */

  protected static Tile fromChar(char c){  

    switch(c){
      case 'e': Tile tile1 = new Tile(Type.ENTRANCE);
      return tile1;
      case 'x': Tile tile2 = new Tile(Type.EXIT);
      return tile2;
      case '#': Tile tile3 = new Tile(Type.WALL);
      return tile3;
      case '.': Tile tile4 = new Tile(Type.CORRIDOR);
      return tile4;
      default: return null;  
    }
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





















