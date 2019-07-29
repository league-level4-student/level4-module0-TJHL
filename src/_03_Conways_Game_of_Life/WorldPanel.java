package _03_Conways_Game_of_Life;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import _02_Pixel_Art.Pixel;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;
	
	private Timer timer;
	
	//1. Create a 2D array of Cells. Do not initialize it.
	Cell[][] grid;
	
	
	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;
	
		//2. Calculate the cell size.
		cellSize =w/cpr;
		//3. Initialize the cell array to the appropriate size.
		 grid= new Cell[w][h];
		//3. Iterate through the array and initialize each cell.
		//   Don't forget to consider the cell's dimensions when 
		//   passing in the location.
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Cell(i*cellSize,j*cellSize,cellSize);
			}	
		}	
	}
	
	public void randomizeCells() {
		//4. Iterate through each cell and randomly set each
		//   cell's isAlive memeber to true of false
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j].isAlive=true;
			}
		}
		repaint();
	}
	
	public void clearCells() {
		//5. Iterate through the cells and set them all to dead.
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j].isAlive=false;
			}
		}
		repaint();
	}
	
	public void startAnimation() {
		timer.start();
	}
	
	public void stopAnimation() {
		timer.stop();
	}
	
	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//6. Iterate through the cells and draw them all
		for(int i = 0; i< grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j].draw(g);
				
				//grid "screen door"
				g.setColor(Color.BLACK);
				g.drawRect(i*cellSize, j*cellSize, cellSize, cellSize);
			}
		}
	}
	
	//advances world one step
	public void step() {
		//7. iterate through cells and fill in the livingNeighbors array
		// . using the getLivingNeighbors method.
		int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];
		
		
	
		/*
		 * 1. Any live cell with fewer than two live nieghbours dies, as if caused by underpopulation.
		 * 2. Any live cell with two or three live neighbours lives on to the next generation.
		 * 3. Any live cell with more than three live neighbours dies, as if by overpopulation.
		 * 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
		 * (source: Wikipedia)
		 * */
	
		
		//8. check if each cell should live or die
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				
				if(grid[i][j].isAlive==true) {
					
					if(getLivingNeighbors(i,j)<2) {
						grid[i][j].isAlive=false;
					}
					
					if(getLivingNeighbors(i,j)>2&&getLivingNeighbors(i,j)<3) {
						grid[i][j].isAlive =true;
					}
					
					if(getLivingNeighbors(i,j)>3) {
						grid[i][j].isAlive=false;
					}
				}
				
				if(grid[i][j].isAlive=false) {
					
					if(getLivingNeighbors(i,j)==3) {
						grid[i][j].isAlive =true;
					}
				}
				
			}
		}
		
		repaint();
	}
	
	//9. Complete the method.
	//   It returns an int of 8 or less based on how many
	//   living neighbors there are of the 
	//   cell identified by x and y
	public int getLivingNeighbors(int x, int y){
		int cellNeighbors=0;
		for (int i = x-1; i <= x+1; i++) {
			for (int j = y-1; j <= y+1; j++) {
				if(grid[i][j].isAlive==true) {
					cellNeighbors=cellNeighbors+1;
				}
			}
		}
		if(grid[x][y].isAlive==true) {
			cellNeighbors=cellNeighbors-1;
		}
		System.out.println(cellNeighbors);
		return cellNeighbors;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		//10. Use e.getX() and e.getY() to determine
		//    which cell is clicked. Then toggle
		//    the isAlive variable for that cell.
		
		if(grid[e.getX()/cellSize][e.getY()/cellSize].isAlive==true) {
			grid[e.getX()/cellSize][e.getY()/cellSize].isAlive=false;
		}
		
		else if(grid[e.getX()/cellSize][e.getY()/cellSize].isAlive==false) {
			grid[e.getX()/cellSize][e.getY()/cellSize].isAlive=true;
		}
		
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();		
	}
}
