package gamecore;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public Direction invert(){
        if(this == LEFT)
            return RIGHT;
        else if (this == RIGHT)
            return LEFT;
        else if (this == UP)
            return DOWN;
        else
            return UP;
    }
}
