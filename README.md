
# What is A*?

A* is a path finding algorithm, maybe one of the best ones out there. For people who worked with Dijkstra's algorithm,
A* is an improved version of it, or we can say Dijkstra is a special case of A* algorithm where f(n) = g(n) instead of f(n) = g(n) + h(n).
Heuristic function (**h(n)**) is the heart of the A* algorithm, since the algorithm relies on that functions value to make decisions in order to
find the shortest route. Manhattan Distance is used as a heuristic function in this project since we can only move in 4 directions within the maze.

# How do I compile/run the code?

Since the code is written in java11 and java11 does not include javaFx as a default library, I've supplied some execution scripts that will compile/run code with the `--add-modules` flag set (for Java FX).
Use `javac.sh` to compile on Linux and macOS, or `javac.bat` to compile on Windows. Likewise, use `java.sh` to run on Linux and Mac OS, or `java.bat` to run on Windows. E.g.

```
$ cd Maze
$ ./javac.sh src/MazeApplication.java  
$ ./java.sh MazeApplication
```

Note that in the above example, on line 3, the name of the class file to run (`MazeApplication`) is **NOT** prefixed with the source path.
<br><br>

**Addition:** If you would like to configure the files and play around with them, modifying javaFx file (MazeApplication.java)
may be problematic during development. Instead, you can use the MazeDriver.java file which is also provided within this repo.
To run the code in this manner, comment out the whole MazeApplication.java file, then while you are in the Maze directory run the commands:

```
$ cd src
$ javac ./*.java 
$ java MazeDriver
```


