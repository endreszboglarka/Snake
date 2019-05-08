package model;

import java.awt.*;
import java.util.List;

public class Tail {
    public List<SnakePart> body;

    public Tail(List<SnakePart> body) {
        this.body = body;
    }

    public void move(Head head) {
        if (body.size() > 0) {
            SnakePart lastSnakePart = body.get(body.size() - 1);
            Point lastSnakePartPosition = lastSnakePart.position;
            lastSnakePartPosition.x = head.position.x;
            lastSnakePartPosition.y = head.position.y;
            body.add(0, new SnakePart(
                            new Point(
                                    lastSnakePartPosition.x,
                                    lastSnakePartPosition.y)
                    )
            );
            body.remove(lastSnakePart);
        }
    }

    public void grow(Head head, Apple apple) {
        body.add(new SnakePart(head.prevPosition()));
    }
}
