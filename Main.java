package code;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import code.Contents.spBool;

public class Main	{
	
	class spBool	{
		
		boolean bool;
		public int tileNum;
		
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
	
	public Main()	{	//figure out if needed after putting map as background

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

	public static void main(String[] args) {
		Main a = new Main();
		
		for(spBool[] b : a.grid) {
			for(spBool c : b)	{
				System.out.print(c.tileNum + " ");
			}
			System.out.println();
		}
	
	}
	
}
