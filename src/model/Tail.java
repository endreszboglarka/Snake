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
            lastSnakePart.position.x = head.position.x;
            lastSnakePart.position.y = head.position.y;
            body.add(0, new SnakePart(
                            new Point(
                                    lastSnakePart.position.x,
                                    lastSnakePart.position.y)
                    )
            );
            body.remove(lastSnakePart);
        }
    }

    public void grow(Head head, Apple apple) {
        body.add(new SnakePart(head.position));
        head.position = apple.position;
    }
}
