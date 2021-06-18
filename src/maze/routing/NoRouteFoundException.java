package maze.routing;

/**
* A custom exception class to throw if route cannot be found with RouteFinder object. 
* @author Aziz Serin
* @version 1.0, 29th April 2021
* @see java.nio.file.Path 
*/

import java.util.*;
import java.lang.*;

public class NoRouteFoundException extends Exception{

	public NoRouteFoundException(String message){
		super(message);
	}
}