package maze;

/**
* An abstract class for custom exception classes. 
* @author Aziz Serin
* @version 1.0, 29th April 2021
* @see java.nio.file.Path 
*/

import java.util.*;
import java.lang.*;

public class InvalidMazeException extends Exception{

	public InvalidMazeException(String message){
		super(message);
	}
}