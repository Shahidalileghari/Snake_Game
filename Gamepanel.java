package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Gamepanel extends JPanel implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	int moves=0;
	boolean gameover=false;
	int score=0;
	private boolean left=false;
	private boolean right=true;
	private boolean up=false;
	private boolean down=false;
	
	private int [] snakexlength=new int[750];
	private int [] snakeylength=new int[750];

    int enemyX,enemyY;
    
    private Random random=new Random();
	
	int []x= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825};
	int []y={100,125,150,175,200,225,250,275,300,325,350};
	
	private int   lengthofsnake=3;
	
	private ImageIcon snaketitle=new ImageIcon(getClass().getResource("snaketitle.jpg"));
	
	private ImageIcon leftmouth=new ImageIcon(getClass().getResource("leftmouth.png"));
	private ImageIcon rightmouth=new ImageIcon(getClass().getResource("rightmouth.png"));
	private ImageIcon upmouth=new ImageIcon(getClass().getResource(  "upmouth.png"));
	private ImageIcon downmouth=new ImageIcon(getClass().getResource("downmouth.png"));
	private ImageIcon snakeimage=new ImageIcon(getClass().getResource("snakeimage.png"));
	private ImageIcon enemy=new ImageIcon(getClass().getResource("enemy.png"));
	
    private javax.swing.Timer timer;
	private int delay=100;
	
	
    public Gamepanel() {
    	addKeyListener(this);
    	setFocusable(true);
    	setFocusTraversalKeysEnabled(true);
    	timer=new javax.swing.Timer(delay, this);
    	timer.start();
    	newenemy();
    	
	}
    
    
    //@Override;
    public void paint(Graphics g)
    {
    	super.paint(g);
    	g.setColor(Color.white);
    	g.drawRect(17, 10, 851,57);
    	g.drawRect(17, 90, 851,370);
    	
    	snaketitle.paintIcon(this, g,18 ,13);
    	g.setColor(Color.black);
    	g.fillRect(25, 100, 833, 350);
    	
    	if(moves==0)
    	{
    		snakexlength[0]=100;
    		snakexlength[1]=75;
    		snakexlength[2]=50;
    		
    		snakeylength[0]=100;
    		snakeylength[1]=100;
    		snakeylength[2]=100;
    		
    	}
    	
    	if(left)
    	{
    		leftmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
    	}
    	if(right)
    	{
    		rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
    	}
    	
    	if(up)
    	{
    		upmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
    	}
    	
    	if(down)
    	{
    		downmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
    	}
    	
    	for(int i=1;i<lengthofsnake;i++)
    	{
    		snakeimage.paintIcon(this, g, snakexlength[i], snakeylength[i]);
    	}
    	
    	enemy.paintIcon(this, g, enemyX, enemyY);
    	
    	if(gameover)
    	{
    		g.setColor(Color.white);
    		g.setFont(new Font("Tohomo",Font.BOLD,30));
    		g.drawString("Game over ", 300, 200);
    		
    		g.setFont(new Font("Tohomo",Font.BOLD,30));
    		g.drawString("press SPACE to restart", 340, 240);
    		
    	}
    	
    	g.setColor(Color.white);
    	g.setFont(new Font("Tohomo",Font.PLAIN,14));
    	g.drawString("Score  :"+ score,800, 30);
    	g.drawString("Length :"+ lengthofsnake,800, 50);
    	g.dispose();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i=lengthofsnake;i>0;i--)
		{
			snakexlength[i]=snakexlength[i-1];
			snakeylength[i]=snakeylength[i-1];
		}
		if(left)
		{
			snakexlength[0]=snakexlength[0]-25;
		}
		if(right)
		{
			snakexlength[0]=snakexlength[0]+25;
		}
		
		if(up)
		{
			snakeylength[0]=snakeylength[0]-25;
		}
		if(down)
		{
			snakeylength[0]=snakeylength[0]+25;
		}
		if(snakexlength[0]>833)snakexlength[0]=25;
		if(snakexlength[0]<25)snakexlength[0]=833;
		
		if(snakeylength[0]>350)snakeylength[0]=100;
		if(snakeylength[0]<100)snakeylength[0]=350;
		
		collidesWithEnemy();
		collideWithBody();
		repaint();
	}

	
	

	@Override
	public void keyTyped(KeyEvent e) {
      
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			restart();
		}
     if(e.getKeyCode()==KeyEvent.VK_LEFT && (!right))
     {
    	 left=true;
    	 right=false;
    	 up=false;
    	 down=false;
    	 moves++;
     }
     
     if(e.getKeyCode()==KeyEvent.VK_RIGHT && (!left))
     {
    	 left=false;
    	 right=true;
    	 up=false;
    	 down=false;
    	 moves++;
     }
     
     if(e.getKeyCode()==KeyEvent.VK_UP && (!down))
     {
    	 left=false;
    	 right=false;
    	 up=true;
    	 down=false;
    	 moves++;
     }
     
     if(e.getKeyCode()==KeyEvent.VK_DOWN && (!up))
     {
    	 left=false;
    	 right=false;
    	 up=false;
    	 down=true;
    	 moves++;
     }
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void newenemy()
	{
		enemyX=x[random.nextInt(33)];
		enemyY=y[random.nextInt(11)];
		
		for(int i=lengthofsnake-1;i>0;i--)
		{
			if(snakexlength[0]==enemyX && snakeylength[0]==enemyY)
			{
				newenemy();
			}
		}
	}
	
   public void	  collidesWithEnemy()
   {
	   if(snakexlength[0]==enemyX && snakeylength[0]==enemyY)
	   {
		   newenemy();
		   lengthofsnake++;
		   
		   score++;
	   }
   }
   
   public void  collideWithBody()
   {
	   for(int i=lengthofsnake-1;i>0;i--)
	   {
		   if(snakexlength[i]==snakexlength[0] && snakeylength[i]==snakeylength[0]) 
		   {
			   timer.start();
			   
			   gameover=true;
		   }
	   }
   }
   
   public void restart()
   {
	   gameover=false;
	   moves=0;
	   score=0;
	   left=false;
	   right=true;
	   up=false;
	   down=false;
	   lengthofsnake=3;
	   timer.start();
	   
	   repaint();
	   
   }
}
