package code;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class test extends JPanel {

	static boolean dieRolled = false;
	JButton die = new JButton();
	
	public static void init(JFrame base, int width, int height){	//starts up JFrame
		base.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		base.setSize(width, height);	//was 1920x1080 and 32x18
		base.setVisible(true);
	}
	
	public static void initJPanel(JPanel op, boolean baseLayout)	{
		
		op.setVisible(true);
		op.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
	}
	
	public void initBGImg(JLabel a, String fileName) {
		
		a = new JLabel(new ImageIcon(fileName));
		a.setVisible(true);
		
	}
	
	public void initButton(JButton a, String iconName, boolean mnemonic, int x, int y) {
		
		if(mnemonic)	{
			a.setMnemonic(KeyEvent.VK_P);
		}
		
		a.setText(iconName);
		a.setVisible(true);
		a.setLocation(x, y);
		
	}

	public void initJLabel(JLabel a, String hoverText, int xCoord, int yCoord) {
		a.setLocation(xCoord * 39, yCoord * 39);
		a.setToolTipText(hoverText);
		a.setSize(80, 80);
	}
	
	public static void main(String[] args) {

		int row = 25, col = 18;
		
		test a = new test();
		JFrame base = new JFrame("Board Game");
		base.setResizable(false);
		
		
		Contents content = new Contents();
		
		init(base, row * 40 + 20, col * 40 + 40);
		base.add(a);
		base.add(content);		
//Die
		
		JFrame diceHolder = new JFrame("Die");
		init(diceHolder, 170, 170);		
		diceHolder.setLocation(1000, 0);
		
		JPanel diePanel = new JPanel();
		diePanel.setLayout(new FlowLayout(3, 50, 50));
		diePanel.setVisible(true);
		
		JLabel yourRollIs = new JLabel();
		yourRollIs.setVisible(true);
		yourRollIs.setSize(15, 100);
		yourRollIs.setLocation(25, 45 + 50);
		diceHolder.add(yourRollIs);
		
		JButton die = new JButton();
		a.initButton(die, "Die", true, 25, 25);   
		die.setSize(15, 40);
		die.setVisible(true);
		die.setToolTipText("Roll a virtual 6-sided die");

//ITW References
		
		JFrame miniBoard = new JFrame();
		init(miniBoard, 200, 200);
		miniBoard.setLocationRelativeTo(base);
		miniBoard.setResizable(false);
		
		JPanel bg = new JPanel();
		miniBoard.add(bg);
		
		JTextField thing = new JTextField();
		
		bg.add(thing);
		
//Win Condition and Game Runner
		
		die.addActionListener(new ActionListener() 	{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int rollResult = (int) (Math.random()*6 + 1);
				yourRollIs.setText("Your Roll Is: " + rollResult);
				content.diceSum += rollResult;
				System.out.println(rollResult + " " + content.diceSum);
				content.move();
				
				
				int bool = content.diceSum / 10;
			
				if(bool == 1)	{	
					thing.setText("McCandless was hungry and was foraging berries off of bushes. "
							+ "Jan and Bob Burres found him, with the former being someone Chris "
							+ "sent many post cards to.");
				}	else if(bool == 2)	{
					thing.setText("Welcome to Alaska, your excusion will start here");
				}	else if(bool == 3)	{
					thing.setText("Welcome to Alaska, your excusion will start here");
				}	else if(bool == 4)	{
					thing.setText("Welcome to Alaska, your excusion will start here");
				}	else if(bool == 5)	{
					thing.setText("Welcome to Alaska, your excusion will start here");
				}	else if(bool == 6)	{
					thing.setText("Welcome to Alaska, your excusion will start here");
				}	else if(bool == 7)	{
					thing.setText("Welcome to Alaska, your excusion will start here");
				}	else if(bool == 8)	{
					thing.setText("Welcome to Alaska, your excusion will start here");
				}	else if(bool == 9)	{
					thing.setText("Welcome to Alaska, your excusion will start here");
				}	else if(bool == 10)	{
					thing.setText("Welcome to Alaska, your excusion will start here");						
				}	else if(bool == 11)	{
					thing.setText("Welcome to Alaska, your excusion will start here");
				}
				
				if(content.diceSum >= 110)	{
					JFrame victory = new JFrame("You Won");
					init(victory, 170, 170);
					
					JPanel vic = new JPanel();
					initJPanel(vic, false);
					
					JLabel message = new JLabel();
					message.setVisible(true);
					message.setText("We have a winner!");
					vic.add(message);
					diceHolder.setVisible(false);
					victory.setLocationRelativeTo(diceHolder);
					victory.add(vic);
				}
				
				dieRolled = true;
			}
			
		});
		
		diePanel.add(die);
		diceHolder.add(diePanel);
	}
}
