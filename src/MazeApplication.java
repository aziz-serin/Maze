 /**
* Class starting the application. 
* @author Aziz Serin
* @version 1.0, 29th April 2021
* @see java.nio.file.Path 
*/

// maze1.obj and maze2.obj should exist within the same directory with maze1.txt and maze2.txt for this app to run.

import maze.*;
import maze.routing.*;
import maze.visualisation.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.paint.*;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.text.*;
import javafx.scene.shape.*;
import java.util.*; 


public class MazeApplication extends Application {

	private Maze maze1, maze2;
	private RouteFinder route1, route2;
	private RouteFinder routeMaze1, routeMaze2;
	private Scene sceneStart, sceneSolve1, sceneSolve2, sceneSolve3, sceneSolve4;
	private VBox mazeVisual;

	/**
  	* Start method to initialise the application window and run it.
  	* @param stage window where application runs;
  	*/
	
	@Override
  public void start (Stage stage){

  	maze1 = null;  maze2 = null;
  	route1 = null; route2 = null;
  	routeMaze1 = null; routeMaze2 = null;

  	

  	/////////////////////////////////////Load maze1/////////////////////////////////////////

    Button button1 = new Button("Load Maze 1");
    button1.setOnAction(e -> {
  		stage.setScene(sceneSolve1);
    });
    Part part1 = new Part();
  	VBox page1 = new VBox(50);

  	if(maze1 == null){
  		try{maze1 = Maze.fromTxt("resources/mazes/maze1.txt");}
			catch(InvalidMazeException a){a.printStackTrace();}
  		routeMaze1 = new RouteFinder(maze1);
  		part1.mazeVisual(routeMaze1);
  	}
  	
  	Button stepMaze1 = new Button("Step!");
  	Button save1 = new Button("Save Maze");
  	Button returnButton1 = new Button("Go to Start Page");

  	returnButton1.setOnAction(e ->{
  		stage.setScene(sceneStart);
  	});

  	save1.setOnAction(e -> {
  		routeMaze1.save("resources/mazes/maze1.obj");
  	});
    
    stepMaze1.setOnAction(b -> {
	   	try{routeMaze1.step();}
	   	catch(NoRouteFoundException err){err.printStackTrace();}
	   	part1.mazeVisual(routeMaze1);
  	 });

    page1.setAlignment(Pos.CENTER);   

    page1.getChildren().addAll(returnButton1, part1.getShapes(), stepMaze1, save1);

  	sceneSolve1 = new Scene(page1, 350, 600);

    /////////////////////////////////////Load maze2/////////////////////////////////////////

    Button button2 = new Button("Load Maze 2");
    button2.setOnAction(e -> {
  		stage.setScene(sceneSolve2);
    });

    Part part2 = new Part();
  	VBox page2 = new VBox(50);

  	if(maze2 == null){
  		try{maze2 = Maze.fromTxt("resources/mazes/maze2.txt");}
			catch(InvalidMazeException a){a.printStackTrace();}
  		routeMaze2 = new RouteFinder(maze2);
  		part2.mazeVisual(routeMaze2);
  	}
  	
  	Button stepMaze2 = new Button("Step!");
  	Button save2 = new Button("Save Maze");
  	Button returnButton2 = new Button("Go to Start Page");

  	returnButton2.setOnAction(e ->{
  		stage.setScene(sceneStart);
  	});

  	save2.setOnAction(e -> {
  		routeMaze2.save("resources/mazes/maze2.obj");
  	});
    
    stepMaze2.setOnAction(b -> {
	   	try{routeMaze2.step();}
	   	catch(NoRouteFoundException err){err.printStackTrace();}
	   	part2.mazeVisual(routeMaze2);
  	 });

    page2.setAlignment(Pos.CENTER);   

    page2.getChildren().addAll(returnButton2, part2.getShapes(), stepMaze2, save2);

  	sceneSolve2 = new Scene(page2, 600, 600);

    /////////////////////////////////////Continue maze1/////////////////////////////////////////

    Button button3 = new Button("Continue Maze 1");
    button3.setOnAction(e -> {
    	stage.setScene(sceneSolve3);
    });

    Part part3 = new Part();
  	VBox page3 = new VBox(50);

  	if(route1 == null){
  		route1 = RouteFinder.load("resources/mazes/maze1.obj");
  		part3.mazeVisual(route1);
  	}
  	
  	Button stepMaze3 = new Button("Step!");
  	Button save3 = new Button("Save Maze");
  	Button returnButton3 = new Button("Go to Start Page");

  	returnButton3.setOnAction(e ->{
  		stage.setScene(sceneStart);
  	});

  	save3.setOnAction(e -> {
  		route1.save("resources/mazes/maze1.obj");
  	});
    
    stepMaze3.setOnAction(b -> {
	   	try{route1.step();}
	   	catch(NoRouteFoundException err){err.printStackTrace();}
	   	part3.mazeVisual(route1);
  	 });

    page3.setAlignment(Pos.CENTER);   

    page3.getChildren().addAll(returnButton3, part3.getShapes(), stepMaze3, save3);

  	sceneSolve3 = new Scene(page3, 350, 600);

    /////////////////////////////////////Continue maze2/////////////////////////////////////////

    Button button4 = new Button("Continue Maze 2");
    button4.setOnAction(e -> {
    	stage.setScene(sceneSolve4);
    });
    
    Part part4 = new Part();
  	VBox page4 = new VBox(50);

  	if(route2 == null){
  		route2 = RouteFinder.load("resources/mazes/maze2.obj");
  		part4.mazeVisual(route2);
  	}
  	
  	Button stepMaze4 = new Button("Step!");
  	Button save4 = new Button("Save Maze");
  	Button returnButton4 = new Button("Go to Start Page");

  	returnButton4.setOnAction(e ->{
  		stage.setScene(sceneStart);
  	});

  	save4.setOnAction(e -> {
  		route2.save("resources/mazes/maze2.obj");
  	});
    
    stepMaze4.setOnAction(b -> {
	   	try{route2.step();}
	   	catch(NoRouteFoundException err){err.printStackTrace();}
	   	part4.mazeVisual(route2);
  	 });

    page4.setAlignment(Pos.CENTER);   

    page4.getChildren().addAll(returnButton4, part4.getShapes(), stepMaze4, save4);

  	sceneSolve4 = new Scene(page4, 600, 600);
    
  	
  	


  	///////////Start Scene/////////////////////////////////////////////////////////////////////


    Label label = new Label("Maze Solver");
  	label.setFont(Font.font(30));

  	Label warning = new Label("After you save the maze status, \n you need to quit  to see the effects. ");
  	warning.setFont(Font.font(10));

  	Button quit = new Button("QUIT");
  	quit.setStyle("-fx-background-color: #ff0000; ");

  	quit.setOnAction(e ->{
  		stage.close();
  	});
  	
  	VBox warningBox = new VBox(10);
  	warningBox.setAlignment(Pos.TOP_CENTER);
  	warningBox.getChildren().addAll(warning, quit);

	  HBox hBox = new HBox();
	  hBox.setAlignment(Pos.TOP_CENTER);
	  hBox.getChildren().addAll(label);

  	VBox vBox = new VBox(10);
  	vBox.setAlignment(Pos.TOP_CENTER);
  	vBox.getChildren().addAll(button1, button2, button3, button4);

  	VBox group = new VBox(50);
  	group.setAlignment(Pos.CENTER);
  	group.getChildren().addAll(hBox, vBox, warningBox);

  	sceneStart = new Scene(group, 400, 400);

  	////////////////////////////////////////////////////////////////////////////////////////////

    stage.setScene(sceneStart);
    stage.setTitle("Maze Solver");
    stage.setResizable(false);
    stage.show();

  }

  /**
  	* Main method to launch the application.
  	*/

  public static void main(String args[]){
  	launch(args);
  }
}
 