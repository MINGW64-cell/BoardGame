package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Contents extends JPanel	{
 
	private Image imgBG;
	
	class spBool	{
		
		boolean bool;
		int tileNum;
		
		spBool(boolean bool, int tileNum)	{
			this.bool = bool;
			this.tileNum = tileNum;
		}
		
	}	//end of 'spBool'
	
	int row = 25, col = 18, diceSum = 1;
	ArrayList<Integer> pieces = new ArrayList<Integer>();
	public spBool[][] grid = new spBool[row][col];	//60x60 px squares needed
	
	
	public Contents(/* JFrame base */)	{	//figure out if needed after putting map as background

		int fillerNum = 1;
		boolean cat = false;
		pieces.add(1000);
		
		for(int i = 0; i < grid.length; i++)	{
			
			double wthIsARiceTable = Math.sqrt(i+1);
				
			if(isInteger(wthIsARiceTable))	{
				cat = !cat;
			}
				
			if(isInteger(wthIsARiceTable))	{
				
				if(cat) {
					for(int f = grid[i].length - 1; f >= 0; f--)
						grid[i][f] = new spBool(true, fillerNum++);					
				}	else	{
					for(int f = 0; f < grid[i].length; f++)
						grid[i][f] = new spBool(true, fillerNum++);
				}
				
			}	else	{
				for(int f = 0; f < grid[i].length; f++)
					grid[i][f] = new spBool(false, 0);					
			}
		
			if(!isInteger(wthIsARiceTable))	{
				if(!cat)	{	//somehow always false
					grid[i][grid[i].length - 1].bool = true;
					grid[i][grid[i].length - 1].tileNum = fillerNum++;
				}	else	{
					grid[i][0].bool = true;
					grid[i][0].tileNum = fillerNum++;
				}
			}
			
//			if(grid[row - 1][col - 1].tileNum == 1);	{
//				grid[row - 1][col - 1].tileNum = pieces.get(0);
//			}
			
		}
		
		flipBoard();
//		super.setDoubleBuffered(true);
//		t = new Timer(7, this); //	int (frame/millisec)
		
	}
	
	void flipBoard()	{
		spBool[][] temp = new spBool[row][col];
		
		for(int i = 0; i < row; i++) {
			temp[i] = grid[row - i - 1];
		}
		grid = temp;
	}
	
	public boolean isInteger(double a) {
		
		double b = a - (int) a;
		
		if(b != 0)
			return false;
		return true;
	}

	
//Graphics
	
	public final Color path = new Color(115, 115, 115);
	Color piece = new Color(255, 0, 0);
	final int squareSize = 40;
	boolean created = true;	//was false

//	ImageIcon ii = new ImageIcon(this.getClass().getResource("USMap.jpg"));
	
	private int x = row, y = col;
	
    public void paintComponent(Graphics g) {
    	
    	super.paintComponent(g);	//refreshes the screen
    	
        Graphics2D g2d = (Graphics2D) g;
//        ArrayList<Graphics2D> polygons = new ArrayList<Graphics2D>();
//        imgBG = ii.getImage();
//        g2d.drawImage(imgBG, row/2*40, col/2*40, this);
        
        int k = 0;
        
        for (int i = 0; i < row; i++) {
        	for(int j = 0; j < col; j++)	{
        		if(grid[i][j].bool)	{            	
                g2d.fillOval(i * squareSize, j * squareSize, squareSize-10, squareSize-10);	//change x and y location values to fix        		
        		g2d.setColor(path);  
//        		g2d.setText(grid[i][j].tileNum);
        		}
        	        		
        		if(grid[i][j].tileNum % 10 != 0)	{
        			g2d.setColor(new Color(0,0,0));
        		}	
        		
        	}
        }

        //The Bus
		g2d.setColor(new Color (215, 215, 215));
		g2d.fillRect(160, 100, 60, 215);
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(170, 150, 5, 150);
		g2d.fillRect(188, 150, 5, 150);
		g2d.fillRect(205, 150, 5, 150);
		g2d.setColor(Color.cyan);
		int[] xpoints = {160, 220, 210, 170};
		int[] ypoints = {130, 130, 100, 100};
		g2d.fillPolygon(xpoints, ypoints, 4);
		g2d.setColor(Color.black);
		g2d.fillRect(150, 140, 10, 33);	//x and y based on top-left corner
		g2d.fillRect(220, 140, 10, 33);
		g2d.fillRect(150, 270, 10, 33);
		g2d.fillRect(220, 270, 10, 33);
		int[] xpointsWS = {160, 220, 210, 170};	//windshield
		int[] ypointsWS = {130, 130, 140, 140};
		g2d.fillPolygon(xpointsWS, ypointsWS, 4);

		createSprite(x * 39, y * 39, g2d, Color.CYAN);
		
//		for(int i = 0; i < 4; i++) {	//Consider adding JTextField to ask for number of players
//		}
		
//		createSprite(160, 110, g2d, new Color(0, 255, 0));	//green (y + 50)
//		createSprite(160, 160, g2d, new Color(0, 0, 255));	//red
//		createSprite(160, 210, g2d, new Color(255, 0, 0));	//blue
			//init x and y: (row*40, col*40)
		
		
    }
    
    Graphics2D p1;
    
    void createSprite(int x, int y, Graphics2D g, Color c)	{	//50 px tall
    	g.setColor(c);		//works by making all new polygons produced that color until overridden
    	g.fillOval(x + 6, y , 25, 25);
    	
    	int triangleXShift = 15;
    	int triangleYShift = 10;
    	int xpoints[] = {x+4+triangleXShift, x-11+triangleXShift, x+19+triangleXShift};
        int ypoints[] = {y+4+triangleYShift, y+34+triangleYShift, y+34+triangleYShift};
        int npoints = 3;
        
        g.fillPolygon(xpoints, ypoints, npoints);
        
        p1 = g;
    }

//Mudada 

    
	public void move(/* p1.getColor() */)	{	//override of methods (apparently)
    	//use diceSum
    	for(int i = 0 ; i < row; i++)	{
    		for(int j = 0; j < col; j++)	{
    			if(diceSum == grid[i][j].tileNum)	{
    				x = i;
    				y = j;
    				repaint();
    			}
       		}
    	}    	
   }

//ITW Features
    
}
