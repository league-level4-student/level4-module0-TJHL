package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		int randomX= randGen.nextInt(width);
		int randomY= randGen.nextInt(height);
		
		Cell currentCell=maze.getCell(randomX,randomY);
		//name[randomX][randomY];
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(currentCell);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. check for unvisited neighbors using the cell
		ArrayList<Cell> unvisitedNeighbors =getUnvisitedNeighbors(currentCell);
		
		//C. if has unvisited neighbors,
		
			//C1. select one at random.
			
			//C2. push it to the stack
		
			//C3. remove the wall between the two cells

			//C4. make the new cell the current cell and mark it as visited
		
			//C5. call the selectNextPath method with the current cell
		if(!unvisitedNeighbors.isEmpty()) {
			int random= new Random().nextInt(unvisitedNeighbors.size());
			uncheckedCells.push(unvisitedNeighbors.get(random));
			removeWalls(currentCell, unvisitedNeighbors.get(random));
			unvisitedNeighbors.get(random).setBeenVisited(true);
			currentCell=unvisitedNeighbors.get(random);
		}	
			
		//D. if all neighbors are visited
		if(unvisitedNeighbors.isEmpty()) {
			if(!uncheckedCells.isEmpty()) {
				currentCell=uncheckedCells.pop();
				selectNextPath(currentCell);
			}
		}
			//D1. if the stack is not empty
			
				// D1a. pop a cell from the stack
		
				// D1b. make that the current cell
		
				// D1c. call the selectNextPath method with the current cell
				
			
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		int differenceX=c2.getX()-c1.getX();
		int differenceY=c2.getY()-c1.getY();
		//east
		if(differenceX==1) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		}
		//west
		if(differenceX==-1) {
			c1.setWestWall(false);
			c2.setEastWall(false);
		}
		//north
		if(differenceY==1) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		}
		//south
		if(differenceY==-1) {
			c1.setSouthWall(false);
			c2.setNorthWall(false);
		}
		
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell current) {
		ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();
		//west
		if(!maze.getCell(current.getX()-1, current.getY()).hasBeenVisited()){
			Cell west = new Cell(current.getX()-1,current.getY());
			unvisitedNeighbors.add(west);
		}
		//east
		if(!maze.getCell(current.getX()+1, current.getY()).hasBeenVisited()){
			Cell east = new Cell(current.getX()+1,current.getY());
			unvisitedNeighbors.add(east);
		}
		//south
		if(!maze.getCell(current.getX(), current.getY()-1).hasBeenVisited()){
			Cell south = new Cell(current.getX(),current.getY()-1);
			unvisitedNeighbors.add(south);
		}
		//north
		if(!maze.getCell(current.getX(), current.getY()+1).hasBeenVisited()){
			Cell north = new Cell(current.getX(),current.getY()+1);
			unvisitedNeighbors.add(north);
		}
		return unvisitedNeighbors;
	}
}
