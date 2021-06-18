package maze.visualisation;

/**
* Class providing necessary shapes and their maze representation for the application. 
* @author Aziz Serin
* @version 1.0, 29th April 2021
* @see java.nio.file.Path 
*/


import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import java.util.*;
import maze.*;
import maze.routing.*;

public class Part{

	private Rectangle rect;
	private Polygon poly;
	private Circle circ;
	private List<List<Shape>> tilesList;
	private VBox mazeShapes;

	/**
  	* Constructor of the Part object.
  	*/

	public Part(){
		this.tilesList = new ArrayList<>();
		this.mazeShapes = new VBox();
	}

	/**
  	* Method to create a rectangle for wall object.
  	* @return a rectangle representing wall.
  	*/

	private Rectangle wall(){
		rect = new Rectangle(); 
    rect.setWidth(40);
    rect.setHeight(40);
    rect.setFill(Color.BLACK);
    return rect;
	}

	/**
  	* Method to create a rectangle for corridor object.
  	* @return a rectangle representing corridor.
  	*/

	private Rectangle corridor(){
		rect = new Rectangle(); 
    rect.setWidth(40);
    rect.setHeight(40);
    rect.setFill(Color.WHITE);
    return rect;
	}

	/**
  	* Method to create a polygon for entrance object.
  	* @return a polygon representing entrance.
  	*/

	private Polygon entrance(){
		poly = new Polygon();
	  poly.getPoints().addAll(new Double[]{
		20.0, 0.0,
		0.0, 40.0,
		40.0, 40.0});
	  poly.setFill(Color.GREEN);
	  return poly;
	}

	/**
  	* Method to create a polygon for exit object.
  	* @return a polygon representing exit.
  	*/

	private Polygon exit(){
		poly = new Polygon();
	  poly.getPoints().addAll(new Double[]{
		20.0, 0.0,
		0.0, 40.0,
		40.0, 40.0});
	  poly.setFill(Color.RED);
	  return poly;
	}

	/**
  	* Method to create a circle to see the route.
  	* @return a circle representing route.
  	*/

	private Circle process(){
		circ = new Circle(20, 20, 20);
	  circ.setFill(Color.BLUE);
	  return circ;
	}

	/**
  	* Method to create the visual representation of route finder object.
  	*	@param routeMaze to visualise.
  	*/

	public void mazeVisual(RouteFinder routeMaze){
		this.tilesList.clear();
		this.mazeShapes.getChildren().clear();

		String string = routeMaze.toString();
	  char[] charTiles = new char[string.length()];
	  int numberOfLines = 0;

    for (int i = 0; i < string.length(); i++) {
    	charTiles[i] = string.charAt(i);
    	if(string.charAt(i) == '\n'){
    		numberOfLines++;
    	}
    }

    int a = 0;

    for(int i = 0; i < numberOfLines; ++i) {
      tilesList.add(new ArrayList<>());
    }

    for(char c : charTiles){
    	switch(c){
    		case 'e':  tilesList.get(a).add(entrance());
    		break;		
    		case '#':	 tilesList.get(a).add(wall());
    		break;
    		case '.':	 tilesList.get(a).add(corridor());
    		break;
    		case 'x':	 tilesList.get(a).add(exit());
    		break;
    		case '+':	 tilesList.get(a).add(process());
    		break;
    		case '\n': a++;
    		break; 	
    		default: break;
    	}
    }
    for(List<Shape> shapeList : tilesList){
    	HBox hBoxx = new HBox();
    	for(Shape shape: shapeList){
    		hBoxx.getChildren().add(shape);
    	}
    	hBoxx.setAlignment(Pos.CENTER);
    	mazeShapes.getChildren().add(hBoxx);
    }
  }

  /**
  	* Method to return the visual representation of route finder object.
  	*	@return visual representation of the routefinder object
  	*/

	public VBox getShapes(){
		return mazeShapes;
	}

}









