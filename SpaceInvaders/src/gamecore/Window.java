
package gamecore;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class Window extends Canvas{
    public JFrame frame;
    
    public static void main(String[] arg) {
        Window coolboy = new Window(800, 600);
    }
    
    public Window(int xSize, int ySize) {
        //Game game = new Game();
        frame = new JFrame("Space Invaders");
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(800, 600));
        panel.setLayout(null);
        
        panel.setBackground(Color.BLACK);
        setBounds(0, 0, 800, 600);
        panel.add(this);

        //setIgnoreRepaint(true);
       

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
        });
        /*
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.black);
        
        JLabel space = new JLabel("SCORE " + game.getScore(), JLabel.LEFT);
        
        space.setOpaque(true);
        add(space);
        setSize(xSize, ySize);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);*/
    }
}
