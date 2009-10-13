
package gamecore;

import java.awt.*;
import javax.swing.*;

public class Window extends JFrame{
    public static void main(String[] arg) {
        Window coolboy = new Window();
    }
    public Window() {
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.black);

        JLabel space = new JLabel("Space Invaders", JLabel.CENTER);
        
        space.setOpaque(true);
        add(space);
        setSize(800,600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
