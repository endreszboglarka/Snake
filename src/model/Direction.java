package model;

import java.awt.*;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public Point value() {
        if(this.equals(UP)){
            return new Point(0,-1);
        }else if(this.equals(DOWN)){
            return new Point(0,1);
        }else if(this.equals((LEFT))){
            return new Point(-1,0);
        }else{
            return new Point(1,0);
        }
    }
}
