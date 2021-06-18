package maze;

/**
* Subclass of InvalidMazeException, used to throw exception if there is no exit in the source of maze. 
* @author Aziz Serin
* @version 1.0, 29th April 2021
* @see java.nio.file.Path 
*/

import java.util.*;
import java.lang.*;


public class NoExitException extends InvalidMazeException{

	public NoExitException(String message){
		super(message);
	}
}