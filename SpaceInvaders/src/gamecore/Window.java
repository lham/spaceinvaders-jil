/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gamecore;

import java.awt.*;
import javax.swing.*;

public class Window extends JFrame{
    public Window() {
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.black);

        JLabel space = new JLabel("Space Invaders", JLabel.CENTER);
        space.setOpaque(true);
        add(space);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
