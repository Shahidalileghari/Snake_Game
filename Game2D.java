
package game;

import java.awt.Color;

import javax.swing.JFrame;

public class Game2D {

	public static void main(String[] args) {
      JFrame frame =new JFrame("Snake game");
      
      frame.setBounds(10,10,900,600);
      frame.setLocation(40,30);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Gamepanel panel=new Gamepanel();
       panel.setBackground(Color.darkGray);
      frame.add(panel);
      
      frame.setVisible(true);
      
      
	}

}
