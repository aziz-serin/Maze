package maze;

/**
* Subclass of InvalidMazeException, used to throw exception if the source of maze is ragged. 
* @author Aziz Serin
* @version 1.0, 29th April 2021
* @see java.nio.file.Path 
*/

import java.util.*;
import java.lang.*;

public class RaggedMazeException extends InvalidMazeException{

	public RaggedMazeException(String message){
		super(message);
	}
}