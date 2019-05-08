package model;

import java.awt.*;

public class Head extends Field {
    private Direction direction;

    public Head(Point position, Direction direction) {
        super(position);
        this.direction = direction;
    }

    public void move() {
        position.translate(direction.value().x, direction.value().y);
    }

    public void changeDirection(Direction direction) {
        this.direction = direction;
    }

    public Point prevPosition() {
        return new Point(direction.value().x - position.x, direction.value().y - position.y);
    }
}
