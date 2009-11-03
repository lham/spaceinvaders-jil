package gamecore;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean firePressed = false;
    private boolean pausePressed = false;

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.leftPressed = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.rightPressed = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.firePressed = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_PAUSE){
            this.pausePressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                this.leftPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                this.rightPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                this.firePressed = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_PAUSE){
                this.pausePressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 27) {
            System.exit(0);
        }
    }

    public boolean firePressed() {
        return firePressed;
    }

    public boolean leftPressed() {
        return leftPressed;
    }

    public boolean rightPressed() {
        return rightPressed;
    }

    public boolean pausePressed(){
        return this.pausePressed;
    }

    public void clearPresses(){
        this.firePressed = false;
        this.rightPressed = false;
        this.leftPressed = false;
        this.pausePressed = false;
    }
}
