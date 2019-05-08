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
        return new Point(position.x - direction.value().x, position.y - direction.value().y);
    }
}
