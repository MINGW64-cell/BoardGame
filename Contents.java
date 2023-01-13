package code;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Contents extends JPanel implements ActionListener	{
 
	private Image thing;
	
	class spBool	{
		
		boolean bool;
		private int tileNum;
		
		spBool(boolean bool, int tileNum)	{
			this.bool = bool;
			
			if(bool)	{
				this.tileNum = tileNum;
			}	else	{
				this.tileNum = 0;
			}
		}
		
	}	//end of 'spBool'
	
	int row = 25, col = 18, diceSum = 1;
	public spBool[][] grid = new spBool[row][col];	//60x60 px squares needed
	
	
	public Contents(/* JFrame base */)	{	//figure out if needed after putting map as background

		int fillerNum = 1;
		boolean cat = false;
		
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

			
		}
		
		flipBoard();
		super.setDoubleBuffered(true);
		t = new Timer(7, this); //	int (frame/millisec)
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
	
	private int x = 956, y = 660;
	
    public void paintComponent(Graphics g) {
    	
    	super.paintComponent(g);	//refreshes the screen
    	
        Graphics2D g2d = (Graphics2D) g;
        ArrayList<Graphics2D> polygons = new ArrayList<Graphics2D>();
    	ImageIcon ii = new ImageIcon(this.getClass().getResource("My project.jpg"));
        thing = ii.getImage();
        g2d.drawImage(thing, x, y, this);
        
        for (int i = 0; i < row; i++) {
        	for(int j = 0; j < col; j++)	{
        		if(grid[i][j].bool)	{            	
                g2d.fillOval(i * squareSize, j * squareSize, squareSize-10, squareSize-10);	//change x and y location values to fix        		
        		g2d.setColor(path);  
        		polygons.add(g2d);
//        		g2d.setText(grid[i][j].tileNum);
        		}
        	        		
        		if(grid[i][j].tileNum % 10 != 0)	{
        			g2d.setColor(new Color(0,0,0));
        		}
        	}
        }
        
		g2d.fillRect(160, 100, 40, 215);
		g2d.setColor(new Color(200, 200, 200));
		
//		for(int i = 0; i < 4; i++) {	//Consider adding JTextField to ask for number of players
//		}
		
		if(!created) {
			createSprite(160, 260, g2d, Color.CYAN);	//cyan (956, 660)
			createSprite(160, 110, g2d, new Color(0, 255, 0));	//green (y + 50)
			createSprite(160, 160, g2d, new Color(0, 0, 255));	//red
			createSprite(160, 210, g2d, new Color(255, 0, 0));	//blue
			created = !created;
		}
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
         
    private Timer t;
    int x2;
    int y2;
    
//    boolean open = false;    
//	public void move(/* p1.getColor() */)	{	//override of methods (apparently)
//    	//use diceSum
//    	if(test.dieRolled)	{
//	    	for(int i = 0 ; i < row; i++)	{
//	    		for(int j = 0; j < col; j++)	{
//	    			if(diceSum == grid[i][j].tileNum)	{
//	    				test.dieRolled = false;
//	    				open = false;
////	    				createSprite(i * 40 - 64, j * 40 - 100, p1, Color.CYAN);	//replace 'Color c' with 'this'
//	    				
//	    			}
//	    		}
//	    	}
//    	}    	
//   }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(test.dieRolled) {
			x = x2;
			y = y2;
			repaint();	//refreshes a frame
			test.dieRolled = !test.dieRolled;
		}
	}
    
    
}
