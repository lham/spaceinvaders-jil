
package gamecore;

import java.awt.*;
import javax.swing.*;

public class Window extends JFrame{
    public JFrame frame;
    
    public static void main(String[] arg) {
        Window coolboy = new Window(800, 600);
    }
    
    public Window(int xSize, int ySize) {
        Game game = new Game();
        frame = new JFrame();
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.black);
        
        JLabel space = new JLabel("SCORE " + game.getScore(), JLabel.LEFT);
        
        space.setOpaque(true);
        add(space);
        setSize(xSize, ySize);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
