// Class starting the application.
// @author Aziz Serin


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
import javafx.stage.FileChooser;
import javafx.scene.text.*;
import java.io.File;
import javafx.scene.shape.*;
import java.util.*;


public class MazeApplication extends Application {

	private Maze maze;
	private RouteFinder route;
	private VBox mazeVisual;
	private Part part1;
	private File selectedFile = null;
	private long startTime = 0; private long endTime = 0;



	// Start method to initialise the application window and run it.


	@Override
  public void start (Stage stage){

  	maze = null; route = null; part1 = null;
		FileChooser fileChooser = new FileChooser();

		fileChooser.getExtensionFilters().addAll(
						new FileChooser.ExtensionFilter("Text Files", "*.txt")
		);

		Label time = new Label("Time elapsed in ns: " + String.valueOf(endTime-startTime));

  	/////////////////////////////////////Load maze/////////////////////////////////////////

    Button button1 = new Button("Load Maze");
		part1 = new Part();
    button1.setOnAction(e -> {
  		selectedFile = fileChooser.showOpenDialog(stage);
			if(selectedFile != null){
				try{maze = Maze.fromTxt(selectedFile.getPath());}
				catch(InvalidMazeException a){a.printStackTrace();}
				route = new RouteFinder(maze);
				part1.mazeVisual(route);
				selectedFile = null;
				startTime = 0;
				endTime = 0;
				time.setText("Time elapsed in ns: " + String.valueOf(endTime-startTime));
			}
    });
  	VBox page1 = new VBox(50);

  	Button solve = new Button("Solve!");

		solve.setOnAction(b -> {
			startTime = System.nanoTime();
			try{route.getPath(route.aStar(maze.getEntrance(), maze.getExit()));}
			catch(NoRouteFoundException err){err.printStackTrace();}
			endTime = System.nanoTime();
			part1.mazeVisual(route);
			time.setText("Time elapsed in ns: " + String.valueOf(endTime-startTime).substring(0,3));
	 });

    page1.setAlignment(Pos.CENTER);

    page1.getChildren().addAll(part1.getShapes(), solve);

  	///////////Start Scene/////////////////////////////////////////////////////////////////////


    Label label = new Label("Maze Solver");
  	label.setFont(Font.font(30));


  	Button quit = new Button("QUIT");
  	quit.setStyle("-fx-background-color: #ff0000; ");

  	quit.setOnAction(e ->{
  		stage.close();
  	});


	  HBox hBox = new HBox();
	  hBox.setAlignment(Pos.TOP_CENTER);
	  hBox.getChildren().addAll(label);

  	VBox vBox = new VBox(10);
  	vBox.setAlignment(Pos.TOP_CENTER);
  	vBox.getChildren().addAll(button1);

  	VBox group = new VBox(50);
  	group.setAlignment(Pos.CENTER);
  	group.getChildren().addAll(hBox, vBox,page1, time, quit);

  	Scene sceneStart = new Scene(group, 1000, 1000);

  	////////////////////////////////////////////////////////////////////////////////////////////

    stage.setScene(sceneStart);
    stage.setTitle("Maze Solver");
    stage.setResizable(false);
    stage.show();

  }

  public static void main(String args[]){
  	launch(args);
  }
}
 